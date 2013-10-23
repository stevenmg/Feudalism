package org.theglicks.bukkit.governance.eventListeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.theglicks.bukkit.governance.Governance;
import org.theglicks.bukkit.governance.Messages;
import org.theglicks.bukkit.governance.Vassal;
import org.theglicks.bukkit.governance.landManagement.SelectionManager;

public class PlayerInteract implements Listener{
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			Player p = event.getPlayer();
			
			//Finds if player is making a land selection
			if(p.getItemInHand().getTypeId() == Material.GOLD_AXE.getId())
				SelectionManager.addClaimPoint(p, event.getClickedBlock().getLocation());
			
			//Finds if player is interacting with a blocked item
			if(Governance.mainConfig.getConfig().getList("LandClaims.blockInteractInClaims").contains(event.getClickedBlock().getTypeId())){
				Vassal v = new Vassal(p.getName());
				if(!v.canBuild(event.getClickedBlock().getLocation())){
					event.setCancelled(true);
					p.sendMessage(Messages.getMessage("cannotInteract", null));
				}
			}
		}
	}
}
