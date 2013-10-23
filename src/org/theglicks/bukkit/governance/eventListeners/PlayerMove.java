package org.theglicks.bukkit.governance.eventListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.theglicks.bukkit.governance.Messages;
import org.theglicks.bukkit.governance.landManagement.Fief;
import org.theglicks.bukkit.governance.landManagement.KingdomLandClaim;

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
			KingdomLandClaim claim = new KingdomLandClaim(event.getTo());
			if (claim.exists()){
				KingdomLandClaim claim1 = new KingdomLandClaim(event.getFrom());
				if(!claim1.exists()){
					event.getPlayer().sendMessage(Messages.getMessage("claimEnter", claim.getKingdom().getName()));
				}
			}
		
			//Checks if player is entering a fief
			Fief f = new Fief(event.getTo());
			if(f.exists()){
				Fief f1 = new Fief(event.getFrom());
				if(!f1.exists()){
					event.getPlayer().sendMessage(Messages.getMessage("fiefEnter", f.getOwnerName()));
				}
			}
		}
	}
}
