package org.theglicks.bukkit.governance.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.governance.Messages;
import org.theglicks.bukkit.governance.Vassal;

public class KingdomKick {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		if(!(args.length == 2)){
			sender.sendMessage(Messages.getMessage("incorrectCmdUsage", null));
			return;
		}
	
		Vassal v = new Vassal(args[1]);
		Vassal v1 = new Vassal(sender.getName());
		
		//Makes sure command sender is in the right kingdom
		if(!(v.getKingdom().getName().equals(v1.getKingdom().getName()))){
			sender.sendMessage(Messages.getMessage("notInSameKingdom", v.getName()));
			return;
		}
		
		if(!v1.isLeader()){
			sender.sendMessage(Messages.getMessage("mustBeLeader", null));
			return;
		}
		
		v.removeKingdom();
	}
}
