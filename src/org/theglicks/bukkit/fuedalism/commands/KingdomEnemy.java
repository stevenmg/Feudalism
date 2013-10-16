package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Kingdom;
import org.theglicks.bukkit.fuedalism.Messages;
import org.theglicks.bukkit.fuedalism.Vassal;

public class KingdomEnemy {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		if(!(args.length == 2)){
			sender.sendMessage(Messages.getMessage("incorrectCmdUsage", null));
			return;
		}
	
		Vassal v = new Vassal(sender.getName());
		
		//Makes sure player is in kingdom
		if(!v.hasKingdom()){
			sender.sendMessage(Messages.getMessage("notInAnyKingdom", null));
			return;
		}
		
		//Makes sure player is kingdom leader
		if(!v.isLeader()){
			sender.sendMessage(Messages.getMessage("mustBeLeader", null));
			return;
		}
		
		Kingdom k1 = v.getKingdom();
		Kingdom k2 = new Kingdom(args[1]);
		k1.setRelation(k2, 2);
		
		sender.sendMessage(Messages.getMessage("nowEnemy", k2.getName()));
	}
}
