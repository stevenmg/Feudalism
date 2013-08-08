package org.theglicks.bukkit.fuedalism.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.kingdoms.RelationManager;
import org.theglicks.bukkit.fuedalism.landManagement.Fief;
import org.theglicks.bukkit.fuedalism.landManagement.KingdomClaim;

public class CommandPreprocess implements Listener{
	@EventHandler
	public void onCommandPreprocess(PlayerCommandPreprocessEvent event){
		String[] cmd = event.getMessage().split(" ");
		cmd[0] = cmd[0].replaceFirst("/", "");
		Vassal v = new Vassal(event.getPlayer().getName());
		
		if(Fuedalism.mainConfig.getConfig().getList("CommandBlocking.blockedInEnemyClaim").contains(cmd[0])){	
			KingdomClaim c = new KingdomClaim(v.getPlayer().getLocation());
			Fief f = new Fief(v.getPlayer().getLocation());
			
			if(c.exists()){
				if(RelationManager.hasEnemy(v.getKingdom(), c.getKingdom())){
					event.setCancelled(true);
					return;
				}
			}
			
			if(f.exists()){
				if(RelationManager.hasEnemy(v.getKingdom(), f.getOwner().getKingdom())){
					event.setCancelled(true);
					return;
				}
			}
		}
		
		if(Fuedalism.mainConfig.getConfig().getList("CommandBlocking.blockedNearEnemyPlayer").contains(cmd[0])){
			Double distance = Fuedalism.mainConfig.getConfig().getDouble("CommandBlocking.blockDistanceNearPlayer");
			for(Entity ent: event.getPlayer().getNearbyEntities(distance, distance, distance)){
				if(ent instanceof Player){
					Vassal vassal = new Vassal(((Player) ent).getName());
					if(RelationManager.hasEnemy(v.getKingdom(), vassal.getKingdom())){
						event.setCancelled(true);
						return;
					}
				}
			}
		}
	}
}
