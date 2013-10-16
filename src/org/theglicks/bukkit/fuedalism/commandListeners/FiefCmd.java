package org.theglicks.bukkit.fuedalism.commandListeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Messages;
import org.theglicks.bukkit.fuedalism.commands.FiefAbandon;
import org.theglicks.bukkit.fuedalism.commands.FiefCreate;

public class FiefCmd implements CommandExecutor{
	public FiefCmd(Fuedalism fuedalism) {}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(args.length > 0){
			if(args[0].equalsIgnoreCase("create")){
				FiefCreate.execute(sender, args);
			} else if(args[0].equalsIgnoreCase("abandon")){
				FiefAbandon.execute(sender, args);
			} else {
				sender.sendMessage(Messages.getMessage("invalidCmd", args[0]));
			}
		}
		return true;
	}
}
