package org.theglicks.bukkit.fuedalism.eventListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.theglicks.bukkit.fuedalism.Kingdom;
import org.theglicks.bukkit.fuedalism.Vassal;

public class EntityDamageByEntity implements Listener {
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player && event.getEntity() instanceof Player){
			Player damager = (Player) event.getDamager();
			Player damagee = (Player) event.getEntity();
			Vassal vDamager = new Vassal(damager.getName());
			Vassal vDamagee = new Vassal(damagee.getName());
			Kingdom damagerKingdom = vDamager.getKingdom();
			Kingdom damageeKingdom = vDamagee.getKingdom();
			
			if(damagerKingdom == damageeKingdom || vDamager.isAllied(vDamagee)){
				event.setCancelled(true);
				damager.sendMessage("You cannot hit that player!");
			}
		}
	}
}
