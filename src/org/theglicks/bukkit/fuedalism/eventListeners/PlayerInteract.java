package org.theglicks.bukkit.fuedalism.eventListeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.landManagement.SelectionManager;

public class PlayerInteract implements Listener{
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			Player p = event.getPlayer();
			
			//Finds if player is making a land selection
			if(p.getItemInHand().getTypeId() == Material.GOLD_AXE.getId())
				SelectionManager.addClaimPoint(p, event.getClickedBlock().getLocation());
			
			//Finds if player is interacting with a blocked item
			if(Fuedalism.mainConfig.getConfig().getList("LandClaims.blockInteractInClaims").contains(event.getClickedBlock().getTypeId())){
				Vassal v = new Vassal(p.getName());
				if(!v.canBuild(event.getClickedBlock().getLocation()))
					event.setCancelled(true);
			}
		}
	}
}
