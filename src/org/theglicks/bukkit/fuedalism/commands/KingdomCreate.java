package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Kingdom;
import org.theglicks.bukkit.fuedalism.Messages;
import org.theglicks.bukkit.fuedalism.Vassal;

public class KingdomCreate {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		if(!(args.length == 2)){
			sender.sendMessage(Messages.getMessage("incorrectCmdUsage", null));
			return;
		}
		
		Vassal v = new Vassal(sender.getName());
		Player p = (Player) sender;
		
		//Makes sure the player isn't already part of a kingdom
		if (v.hasKingdom()) return;
		
		if(v.canCreateKingdom()){
			Double cost = Fuedalism.mainConfig.getConfig().getDouble("Economy.costToCreateKingdom");
			if(Fuedalism.econ.has(p.getName(), p.getWorld().getName(), cost)){
				Fuedalism.econ.withdrawPlayer(p.getName(), p.getWorld().getName(), cost);
				v.setKingdom(Kingdom.createKingdom(args[1], sender.getName()));
				v.setLeader(true);
			} else {
				p.sendMessage(Messages.getMessage("notEnoughMoney", cost.toString()));
			}
		} else {
			p.sendMessage(Messages.getMessage("noPermission", null));
		}
	}
}
