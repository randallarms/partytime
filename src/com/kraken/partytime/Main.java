// ========================================================================
// |PARTYTIME v420 (Indica)
// | by Kraken | URL: ???
// | code inspired by various Bukkit & Spigot devs -- thank you. 
// |
// | Always free & open-source! If this plugin is being sold or re-branded,
// | please let me know on the SpigotMC site, or wherever you can. Thanks!
// | Source code: https://github.com/randallarms/partytime
// ========================================================================

package com.kraken.partytime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;

public class Main extends JavaPlugin {
  	
	public String VERSION = "420 (Indica)";
	
    File optionsFile = new File("plugins/PartyTime", "options.yml");
    FileConfiguration options = YamlConfiguration.loadConfiguration(optionsFile);
	
	Processing process = new Processing(this);
    
	boolean enabled = true;
	boolean opRequired = false;
	boolean whitelist = false;
	
	ArrayList<String> isAllowed = new ArrayList<String>();
	
	@Override
    public void onEnable() {
    	
    	getLogger().info("PartyTime enabling...");
		PluginManager pm = getServer().getPluginManager();
		PartyListener listener = new PartyListener( getInstance() );
		pm.registerEvents(listener, this);
		
		if ( !options.getBoolean("loaded") ) {
			
			options.set("loaded", true);
			options.set("enabled", true);
			options.set("opRequired", false);
			options.set("whitelist", false);
			
	        saveOptions();
	        
		}

        enabled = options.getBoolean("enabled");
        opRequired = options.getBoolean("opRequired");
        
        for (String id : getConfig().getKeys(false) ) {
        	
        	if ( getConfig().getBoolean(id + ".allowed") ) {
        		
        		isAllowed.add(id);
        		
        	}
        	
        }
		
    }
    
    @Override
    public void onDisable() {
    	
        getLogger().info("PartyTime disabling...");
        
    }
    
    public Main getInstance() {
    	return this;
    }
    
    public void playerAllowed(String UUIDString, boolean allowed) {
    	
      if (allowed) {
        isAllowed.add(UUIDString);
      } else {
        isAllowed.remove(UUIDString);
      }
      
    }
    
    public boolean saveOptions() {
    	
        try {
			options.save(optionsFile);
			return true;
		} catch (IOException e) {
			System.out.println("[PT] Failed to save options.yml file, expect errors.");
			return false;
		}
        
    }
    
  //PT commands
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
    	Player player = Bukkit.getServer().getPlayerExact("Octopus__");
    	String command = cmd.getName();
    	ConsoleCommands commands = new ConsoleCommands(this);
    	boolean isPlayer = sender instanceof Player;
    	
    	if (isPlayer) {
    		player = (Player) sender;
    	}
    	
    	return commands.execute(isPlayer, player, command, args);
        
    }
    
}
