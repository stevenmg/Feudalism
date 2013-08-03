package org.theglicks.bukkit.fuedalism.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.landManagement.Fief;
import org.theglicks.bukkit.fuedalism.landManagement.KingdomClaim;

public class BlockBreak implements Listener {
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		Vassal v = new Vassal(event.getPlayer().getName());
		Fief f = new Fief(event.getBlock().getLocation());
		KingdomClaim c = new KingdomClaim(event.getBlock().getLocation());
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
