package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.kingdoms.Kingdom;

public class KingdomCmd implements CommandExecutor{
	public KingdomCmd(Fuedalism fuedalism) {}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(args[0].equalsIgnoreCase("create")){
				Kingdom.createKingdom(args[1], sender.getName());
			} else if(args[0].equalsIgnoreCase("vassals")){
				Kingdom k = new Kingdom(args[1]);
				StringBuilder builder = new StringBuilder();
				for(String name: k.getMembers()){
					builder.append(name);
				}
				player.sendMessage(builder.toString());
			}
		}
		return true;
	}
}
