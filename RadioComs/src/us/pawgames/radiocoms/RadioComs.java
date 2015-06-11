package us.pawgames.radiocoms;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class RadioComs extends JavaPlugin {
	File file = new File(getDataFolder(), "config.yml") ;
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
	
	@Override
	public void onEnable() {
		yaml.addDefault("name", "RadioComs");
		yaml.addDefault("main", "us.pawgames.radiocoms.RadioComs");
		yaml.addDefault("version", "0.0.1");
		yaml.options().copyDefaults(true);
		try {
			yaml.save(this.file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDisable() {}

	HashMap <UUID, String> comHashMap = new HashMap<UUID, String>();
	ItemStack radioItem = new ItemStack(Material.BRICK, 1);
	
	
	public boolean playerHasRadio(Player player) {
		
		return true;
	}
	
	//command [/radiocom <channel_id>
	public void moveToRadioChannel(CommandSender sender, Command cmd, String label, String[] args) {
	
		if (sender instanceof Player) {
			
			UUID playerUuid = ((Player) sender).getUniqueId();
		
			if(comHashMap.containsKey(playerUuid)){
		
				comHashMap.replace(playerUuid, "passchannel");
			
			} else {
			
				comHashMap.put(playerUuid, "failChannel");
		
			}
				
		}
		
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {

		// Unique User ID of the player sending the chat message
		UUID playerUuid = event.getPlayer().getUniqueId();
		
		// Check if the player sending the message is in a comChannel and if that player has a radio
		if (comHashMap.containsKey(playerUuid) && this.playerHasRadio(event.getPlayer())) {
			
			// Check for online players...
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				
				// Get each player's UUID 
				UUID onlinePlayerUuid = onlinePlayer.getUniqueId();
				
				// If the online player has a radio item in his/her inventory and is in the same channel as the message sender...
				if (comHashMap.get(onlinePlayerUuid) == comHashMap.get(playerUuid) && this.playerHasRadio(onlinePlayer)){
					
					//console sends the message to each person in the channel
					onlinePlayer.sendMessage(ChatColor.GOLD + comHashMap.get(playerUuid) + ChatColor.GREEN + event.getPlayer().getCustomName() + ": " + ChatColor.WHITE + event.getMessage());
					
					event.setCancelled(true);
					
				}
					
			}
				
		}
		
	}
	
//	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//find out if the command is being sent by a player
//		if (sender instanceof Player) {
			
			//verify the player has the RadioItem in his/her inventory
			
//			if (label.equalsIgnoreCase("partychat")) {
//				Player player = (Player) sender;
//				String playerName = player.getName();
//				if (!PlayerIsInChannel.containsKey(playerName)) {
//					PlayerIsInChannel.put(playerName, true);
//					player.sendMessage(ChatColor.GREEN + "PartyChat activated!");
//					return true;
//					} else {
//						if (PlayerIsInChannel.get(playerName) == true) {
//							PlayerIsInChannel.put(playerName, false);
//							player.sendMessage(ChatColor.RED + "PartyChat deactivated!");
//							return true;
//						} else {
//							PlayerIsInChannel.put(playerName, true);
//							player.sendMessage(ChatColor.GREEN + "PartyChat activated!");
//							return true;
//						}
//					}
//			}
//	 
//		}
//		return false;
//	}
}

