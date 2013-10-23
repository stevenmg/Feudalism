package org.theglicks.bukkit.governance.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.governance.Messages;
import org.theglicks.bukkit.governance.Vassal;
import org.theglicks.bukkit.governance.landManagement.KingdomLandClaim;

public class KingdomAbandonClaim {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		Player p = (Player) sender;
		
		if(v.isLeader()){
			KingdomLandClaim claim = new KingdomLandClaim(p.getLocation());
			claim.delete();
		} else {
			p.sendMessage(Messages.getMessage("mustBeLeader", null));
		}
	}
}
