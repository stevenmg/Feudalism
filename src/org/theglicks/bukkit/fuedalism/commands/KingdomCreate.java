package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Kingdom;
import org.theglicks.bukkit.fuedalism.Vassal;

public class KingdomCreate {
	public static void execute(CommandSender sender, String[] args){
		Bukkit.getLogger().info("1");
		//Make sure command is being executed by a player
		if (!(sender instanceof Player)) return;
		
		Bukkit.getLogger().info("2");
		Vassal v = new Vassal(sender.getName());
		Player p = (Player) sender;
		
		//Makes sure the player isn't already part of a kingdom
		if (v.hasKingdom()) return;
		
		Bukkit.getLogger().info("3");
		
		if(v.canCreateKingdom()){
			Bukkit.getLogger().info("4");
			if(args.length == 2){
				Bukkit.getLogger().info("5");
				Double cost = Fuedalism.mainConfig.getConfig().getDouble("Economy.costToCreateKingdom");
				if(Fuedalism.econ.has(p.getName(), p.getWorld().getName(), cost)){
					Bukkit.getLogger().info("6");
					Fuedalism.econ.withdrawPlayer(p.getName(), p.getWorld().getName(), cost);
					v.setKingdom(Kingdom.createKingdom(args[1], sender.getName()));
					v.setLeader(true);
				} else {
					p.sendMessage("Insufficient funds!");
				}
			} else {
				p.sendMessage("Check correct command usage!");
			}
		} else {
			p.sendMessage("You do not have permission to create a kingdom!");
		}
	}
}
