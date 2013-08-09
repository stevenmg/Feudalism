package org.theglicks.bukkit.fuedalism.eventListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.landManagement.Fief;
import org.theglicks.bukkit.fuedalism.landManagement.KingdomLandClaim;

public class BlockPlace implements Listener {
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		Vassal v = new Vassal(event.getPlayer().getName());
		Fief f = new Fief(event.getBlock().getLocation());
		KingdomLandClaim c = new KingdomLandClaim(event.getBlock().getLocation());
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
