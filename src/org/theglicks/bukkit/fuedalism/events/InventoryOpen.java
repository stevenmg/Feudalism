package org.theglicks.bukkit.fuedalism.events;

import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.landManagement.Fief;
import org.theglicks.bukkit.fuedalism.landManagement.KingdomClaim;

public class InventoryOpen implements Listener {
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event){
		InventoryHolder holder = event.getInventory().getHolder();
		
		if(holder instanceof Chest){
			Chest chest = (Chest) holder;		
			Vassal v = new Vassal(event.getPlayer().getName());
			Fief f = new Fief(chest.getLocation());
			KingdomClaim c = new KingdomClaim(chest.getLocation());
			boolean canBuild = true;
			
			if(f.exists()){
				if(!v.canBuild(f)){
					canBuild = false;
				}
			}
			
			if(c.exists()){
				if(!v.canBuild(c)){
					canBuild = false;
				}
			}
			
			if(canBuild == false){
				event.setCancelled(true);
			}
		} else if(holder instanceof DoubleChest){
			DoubleChest dChest = (DoubleChest) holder;	
			Vassal v = new Vassal(event.getPlayer().getName());
			Fief f = new Fief(dChest.getLocation());
			KingdomClaim c = new KingdomClaim(dChest.getLocation());
			boolean canBuild = true;
			
			if(f.exists()){
				if(!v.canBuild(f)){
					canBuild = false;
				}
			}
			
			if(c.exists()){
				if(!v.canBuild(c)){
					canBuild = false;
				}
			}
			
			if(canBuild == false){
				event.setCancelled(true);
			}
		}
	}
}
