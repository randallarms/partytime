package com.kraken.partytime;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Processing {
	
	  Main plugin;
	  boolean partyOnCooldown = false;
	
  //Constructor
	public Processing(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
  //Party processing
	public boolean partyBy(Player starter) {
		
		if (partyOnCooldown) {
			starter.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "Party time is currently on cooldown, bro. Don't push it.");
			return true;
		}
		
		List<Player> players = starter.getWorld().getPlayers();

		ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
		
		effects.add(new PotionEffect(PotionEffectType.CONFUSION, 600, 10));
		effects.add(new PotionEffect(PotionEffectType.HUNGER, 600, 2));
		effects.add(new PotionEffect(PotionEffectType.SPEED, 600, 2));
		effects.add(new PotionEffect(PotionEffectType.FAST_DIGGING, 600, 2));
		effects.add(new PotionEffect(PotionEffectType.GLOWING, 600, 5));
		effects.add(new PotionEffect(PotionEffectType.NIGHT_VISION, 500, 8));
		effects.add(new PotionEffect(PotionEffectType.LUCK, 1200, 10));
		
		for (Player player : players) {
			
			for (PotionEffect effect : effects) {
				player.addPotionEffect(effect);
			}
			
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title " + player.getName().toString() + " times 40 400 40");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title " + player.getName().toString() 
					+ " title {\"text\":\"PARTY TIME!\",\"color\":\"green\",\"bold\":\"true\"}");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title " + player.getName().toString() 
					+ " subtitle {\"text\":\"partypartypartypartyparty\",\"color\":\"aqua\",\"obfuscated\":\"true\"}");
			
		}
		
		starter.sendMessage(ChatColor.GREEN + "[PT]" + ChatColor.GRAY + " | " + "PAAAAAAARTY TIIIIIIME!!!");
		
		partyOnCooldown = true;
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    		public void run() {
    			partyOnCooldown = false;
    		}
    	}, 2000);
		
		return true;
		
	}
	
}
