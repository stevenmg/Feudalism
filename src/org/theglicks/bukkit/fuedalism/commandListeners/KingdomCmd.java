package org.theglicks.bukkit.fuedalism.commandListeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Messages;
import org.theglicks.bukkit.fuedalism.commands.KingdomAbandonClaim;
import org.theglicks.bukkit.fuedalism.commands.KingdomAlly;
import org.theglicks.bukkit.fuedalism.commands.KingdomClaim;
import org.theglicks.bukkit.fuedalism.commands.KingdomCreate;
import org.theglicks.bukkit.fuedalism.commands.KingdomDisband;
import org.theglicks.bukkit.fuedalism.commands.KingdomEnemy;
import org.theglicks.bukkit.fuedalism.commands.KingdomInvite;
import org.theglicks.bukkit.fuedalism.commands.KingdomJoin;
import org.theglicks.bukkit.fuedalism.commands.KingdomKick;
import org.theglicks.bukkit.fuedalism.commands.KingdomShow;

public class KingdomCmd implements CommandExecutor{
	public KingdomCmd(Fuedalism fuedalism) {}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(args.length > 0){
			if(args[0].equalsIgnoreCase("create"))
				KingdomCreate.execute(sender, args);
			else if(args[0].equalsIgnoreCase("claim"))
				KingdomClaim.execute(sender, args);
			else if(args[0].equalsIgnoreCase("abandonClaim"))
				KingdomAbandonClaim.execute(sender, args);
			else if(args[0].equalsIgnoreCase("show"))
				KingdomShow.execute(sender, args);
			else if(args[0].equalsIgnoreCase("ally"))
				KingdomAlly.execute(sender, args);
			else if(args[0].equalsIgnoreCase("enemy"))
				KingdomEnemy.execute(sender, args);
			else if(args[0].equalsIgnoreCase("invite"))
				KingdomInvite.execute(sender, args);
			else if(args[0].equalsIgnoreCase("join"))
				KingdomJoin.execute(sender, args);
			else if(args[0].equalsIgnoreCase("disband"))
				KingdomDisband.execute(sender, args);
			else if(args[0].equalsIgnoreCase("kick"))
				KingdomKick.execute(sender, args);
			else
				sender.sendMessage(Messages.getMessage("invalidCmd", args[0]));
		}
		return true;
	}
}
