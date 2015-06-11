package us.pawgames.radiocoms;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class radioComsGui {
	
	public boolean clearItemsFromInventory(Inventory inventory) {
		
		inventory.clear();
		
		return true;
	}
	
	public Inventory comsGui() {
		
		int totalSlots = 27;
		
		ItemStack channelOn = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemStack channelOff = new ItemStack(Material.REDSTONE_TORCH_OFF);
		
		
		//for now we'll set 5 channels
		Inventory guiMenu = Bukkit.createInventory(null, totalSlots, "Apoc Radio Channels");
		
		if(this.clearItemsFromInventory(guiMenu)){
			
			for ( int currentSlot = 1 ; currentSlot < totalSlots ; currentSlot++ ){
			
				guiMenu.setItem(currentSlot, channelOff);
		
			}
			
		}
		
		return guiMenu;
	}
	
}