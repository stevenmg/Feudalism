package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.kingdoms.Kingdom;

public class KingdomCreate {
	public static void execute(CommandSender sender, String[] args){
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Vassal v = new Vassal(sender.getName());
		Player p = (Player) sender;
		
		if(v.canCreateKingdom()){
			if(args.length == 2){
				if(Fuedalism.econ.getBalance(p.getName(), p.getWorld().getName()) >= Fuedalism.mainConfig.getConfig().getDouble("Economy.costToCreateKingdom")){
					Fuedalism.econ.withdrawPlayer(p.getName(), p.getWorld().getName(), Fuedalism.mainConfig.getConfig().getDouble("Economy.costToCreateKingdom"));
					Kingdom.createKingdom(args[1], sender.getName());
				}
			} else {
				p.sendMessage("Check correct command usage!");
			}
		} else {
			p.sendMessage("You do not have permission to create a kingdom!");
		}
	}
}
