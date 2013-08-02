package org.theglicks.bukkit.fuedalism.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.theglicks.bukkit.fuedalism.landManagement.Fief;
import org.theglicks.bukkit.fuedalism.landManagement.KingdomClaim;

public class PlayerMove implements Listener{
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		//Make sure the player has moved to another block
		int fromX = (int) event.getFrom().getX();
		int fromZ = (int) event.getFrom().getZ();
		int toX = (int) event.getTo().getX();
		int toZ = (int) event.getTo().getZ();
		
		if(fromX!=toX || fromZ!=toZ){
			//Checks if player is entering a kingdom claim
			KingdomClaim claim = new KingdomClaim(event.getTo());
			if (claim.exists()){
				event.getPlayer().sendMessage("You are now in " + claim.getKingdom().getName() + "'s claim");
			}
		
			//Checks if player is entering a fief
			Fief f = new Fief(event.getTo());
			if(f.exists()){
				event.getPlayer().sendMessage("You are now in " + f.getOwner() + "'s fief");
			}
		}
	}
}
