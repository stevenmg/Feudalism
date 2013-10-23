package org.theglicks.bukkit.governance.eventListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.theglicks.bukkit.governance.Messages;
import org.theglicks.bukkit.governance.Vassal;
import org.theglicks.bukkit.governance.landManagement.Fief;
import org.theglicks.bukkit.governance.landManagement.KingdomLandClaim;

public class BlockBreak implements Listener {
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
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
			event.getPlayer().sendMessage(Messages.getMessage("buildDeny", null));
		}
	}
}
