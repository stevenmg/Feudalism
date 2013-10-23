package org.theglicks.bukkit.governance.commandListeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.theglicks.bukkit.governance.Governance;
import org.theglicks.bukkit.governance.Messages;
import org.theglicks.bukkit.governance.commands.KingdomAbandonClaim;
import org.theglicks.bukkit.governance.commands.KingdomAlly;
import org.theglicks.bukkit.governance.commands.KingdomClaim;
import org.theglicks.bukkit.governance.commands.KingdomCreate;
import org.theglicks.bukkit.governance.commands.KingdomDisband;
import org.theglicks.bukkit.governance.commands.KingdomEnemy;
import org.theglicks.bukkit.governance.commands.KingdomInvite;
import org.theglicks.bukkit.governance.commands.KingdomJoin;
import org.theglicks.bukkit.governance.commands.KingdomKick;
import org.theglicks.bukkit.governance.commands.KingdomShow;

public class KingdomCmd implements CommandExecutor{
	public KingdomCmd(Governance fuedalism) {}

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
