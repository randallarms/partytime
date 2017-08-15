package com.kraken.partytime;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConsoleCommands {
	
	  Main plugin;
	  
	  String VERSION;
	  FileConfiguration options;
	  
	  boolean enabled;
	  boolean whitelist;
	  boolean opRequired;
	  
	  ArrayList<String> isAllowed;
	  
	  Processing process;

	
  //Constructor, bro!
	public ConsoleCommands(Main plugin) {
		
		this.plugin = plugin;
		
		this.VERSION = plugin.VERSION;
		this.options = plugin.options;
	  
	    this.enabled = plugin.enabled;
	    this.whitelist = plugin.whitelist;
	    this.opRequired = plugin.opRequired;
	  
	    this.isAllowed = plugin.isAllowed;
	    
	    this.process = plugin.process;
	
	}
	
  //Commands, dude!
	@SuppressWarnings("deprecation")
	public boolean execute(boolean isPlayer, Player player, String command, String[] args) {
		
		//Commands
    	switch ( command.toLowerCase() ) {
    	
          //Command: partytime < on / off / opRequired <on/off> >        
    		case "partytime":
  	        	  
	            switch ( args.length ) {
	            
		            case 0:
		            	if (isPlayer) {
		            		player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | CURRENT: PartyTime v" + VERSION + ", bro!");
            			} else {
            				System.out.println("[PT] CURRENT: PartyTime v" + VERSION + ", bro!");
            			}
		            	
		                return true;
		            	
		            default:
		            	switch ( args[0].toLowerCase() ) {
		            	
		            		case "on":
		            		case "enable":
		            		case "true":
		            			options.set("enabled", true);
		            			enabled = true;
		            			plugin.saveOptions();
		            			
		            			if (isPlayer) {
		            				player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | PartyTime is now enabled. Party on, bro!");
		            			} else {
		            				System.out.println("[PT] PartyTime is now enabled. Party on, bro!");
		            			}
		            			
		            			return true;
		            			
		            		case "off":
		            		case "disable":
		            		case "false":
		            			options.set("enabled", false);
		            			enabled = false;
		            			plugin.saveOptions();

		            			if (isPlayer) {
		            				player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | PartyTime is now disabled. Harsh, dude.");
		            			} else {
		            				System.out.println("[PT] PartyTime is now disabled. Harsh, dude.");
		            			}
		            			
		            			return true;
		            			
		            	  //Command: opRequired
		            	    case "oprequired":
		            	    case "opreq":
		            	    		
	            	    		switch ( args[1].toLowerCase() ) {
	            	    		
	            	    			case "on":
	            	    			case "enable":
	            	    			case "enabled":
	            	    			case "true":
	            	    				options.set("opRequired", true);
	            	    				opRequired = true;
	            	    				plugin.saveOptions();
	            	    				
	            	            		if (isPlayer) {
	            	            			player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "The party OP requirement is now enabled. Harsh.");
	        	            			} else {
	        	            				System.out.println("[PT] The party OP requirement is now enabled. Harsh.");
	        	            			}
	            	    				
	            	    				return true;
	            	    				
	            	    			case "off":
	            	    			case "disable":
	            	    			case "disabled":
	            	    			case "false":
	            	    				options.set("opRequired", false);
	            	    				opRequired = false;
	            	    				plugin.saveOptions();
	            	    				
	            	    				if (isPlayer) {
	            	            			player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "The party OP requirement is now disabled. Party on, dudes!");
	        	            			} else {
	        	            				System.out.println("[PT] The party OP requirement is now disabled. Party on, dudes!");
	        	            			}
	            	    				
	            	    				return true;
	            	    				
	            	    			default:
	            	    				if (isPlayer) {
	            	    					player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "Try entering \"/party opReq <on/off>\", my man.");
	        	            			} else {
	        	            				System.out.println("[PT] Try entering \"/party opReq <on/off>\", my man.");
	        	            			}
	            	    				
	            	        	    	return true;
	            	        	    	
	            	    		}
	    	            			
		            		default:

		            			if (isPlayer) {
		            				player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | Try \"/party <on/off>\", dude.");
		            			} else {
		            				System.out.println("[PT] Try \"/party <on/off>\", dude.");
		            			}
		            			
		            			return true;
		            	
		            	}
	            	
	            }
	            
	      //Command: party        
    		case "party":
  	        	  
        		if (isPlayer) {
        			return process.partyBy(player);
    			} else {
    				System.out.println("[PT] Parties can only be started by players in-game, bro.");
    				return true;
    			}
	            	
		  //Command: allowParty
    	    case "allowparty":
		        
    	          Player targetPlayer;
    	          String targetUUID;
    	          
    	          if (player.isOp()) {
    	        	  
    	            if (args.length == 1) {
    	            	
    	            	if ( args[0].equals("*") ) {
    	            		
    	            		if ( options.getBoolean("whitelist") ) {
    	            			
    	            			whitelist = false;
    	            			options.set("whitelist", false);
    	            			plugin.saveOptions();
    	            			
    	            			if (isPlayer) {
    	            				player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "Party whitelisting is now disabled. Party on!");
    	            			} else {
    	            				System.out.println("[PT] Party whitelisting is now disabled. Party on!");
    	            			}
        	            		
        	            		return true;
        	            		
    	            		} else {
    	            			
    	            			whitelist = true;
    	            			options.set("whitelist", true);
    	            			plugin.saveOptions();
    	            			
    	            			if (isPlayer) {
    	            				player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "Party whitelisting is now enabled. Buzzkill, man.");
    	            			} else {
    	            				System.out.println("[PT] Party whitelisting is now enabled. Buzzkill, man.");
    	            			}
        	            		
        	            		return true;
        	            		
    	            		}
    	            		
    	            		
    	            		
    	            	}
    	            	
    	            	try {
    	            		targetPlayer = plugin.getServer().getPlayerExact(args[0]);
    	            		targetUUID = targetPlayer.getUniqueId().toString();
    	            	} catch (NullPointerException npe1) {
    	            		
    	            		if (isPlayer) {
    	            			player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "Player not found online, dude!");
	            			} else {
	            				System.out.println("[PT] Player not found online, dude!");
	            			}
    	            		
    	            		return true;
    	            	}
    	              
    	            	if ( !isAllowed.contains(targetUUID) ) {
    	            	  
    	            		plugin.getConfig().set(targetUUID + ".allowed", true);
    	            		isAllowed.add(targetUUID);
    	            		
    	            		if (isPlayer) {
    	            			player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "Added " + args[0] + " to the party whitelist. Rock on, " + args[0] + "!");
	            			} else {
	            				System.out.println("[PT] Added " + args[0] + " to the party whitelist. Rock on, " + args[0] + "!");
	            			}
    	                
    	            	} else {
    	            	  
    	            		plugin.getConfig().set(targetUUID + ".allowed", false);
    	            		isAllowed.remove(targetUUID);
    	            		
    	            		if (isPlayer) {
    	            			player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "Removed " + args[0] + " from the party whitelist. R.I.P., dude.");
	            			} else {
	            				System.out.println("[PT] Removed " + args[0] + " from the party whitelist. R.I.P., dude.");
	            			}
    	                
    	            	}
    	              
    	            	plugin.saveConfig();
    	            	return true;
    	              
    	            }
    	            
    	            if (isPlayer) {
    	            	player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "Try entering \"/allowParty <player>\", bro.");
        			} else {
        				System.out.println("[PT] Try entering \"/allowParty <player>\", bro.");
        			}
    	            
    	            return true;
    	            
    	          }
    	          
    	          if (isPlayer) {
    	        	  player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "You totally don't got the permissions to use this command, bro!");
      			  } else {
      				  System.out.println("[PT] You totally don't got the permissions to use this command, bro!");
      			  }
    	          
    	          return true;
		        
		    default:
		    	  
		    	if (isPlayer) {
		    		player.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "Sorry dude, command not recognized.");
    			} else {
    				System.out.println("[PT] Sorry dude, command not recognized.");
    			}
		        
		        return true;
		    
        }
		
	}
	
}
