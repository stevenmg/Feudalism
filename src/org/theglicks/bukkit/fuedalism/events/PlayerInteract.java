package org.theglicks.bukkit.fuedalism.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.theglicks.bukkit.fuedalism.landManagement.SelectionManager;

public class PlayerInteract implements Listener{
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player p = event.getPlayer();
		
		if(p.getItemInHand().getTypeId() == Material.GOLD_AXE.getId()){
			SelectionManager.addClaimPoint(p, event.getClickedBlock().getLocation());
		}
	}
}
