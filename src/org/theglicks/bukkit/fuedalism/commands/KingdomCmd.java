package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.kingdoms.Kingdom;
import org.theglicks.bukkit.fuedalism.landManagement.KingdomClaim;
import org.theglicks.bukkit.fuedalism.landManagement.SelectionManager;

public class KingdomCmd implements CommandExecutor{
	public KingdomCmd(Fuedalism fuedalism) {}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			Player player = (Player) sender;
			Vassal v = new Vassal(player.getName());
			if(args[0].equalsIgnoreCase("create")){
				if(v.canCreateKingdom()){
					if(args.length == 2){
						Kingdom.createKingdom(args[1], sender.getName());
					} else {
						player.sendMessage("Check correct command usage!");
					}
				} else {
					player.sendMessage("You do not have permission to create a kingdom!");
				}
			} else if(args[0].equalsIgnoreCase("vassals")){
				if(args.length == 2){
					Kingdom k = new Kingdom(args[1]);
					StringBuilder builder = new StringBuilder();
					for(String name: k.getMembers()){
						builder.append(name);
					}
					player.sendMessage(builder.toString());
				}
			} else if(args[0].equalsIgnoreCase("claim")){
				if (v.canClaimForKingdom()) {
					if (SelectionManager.canCreateClaim(player)) {
						SelectionManager.getKingdomClaim(player);
						player.sendMessage("Kingdom claim created!");
					} else {
						player.sendMessage("Select two corners first!");
					}
				} else {
					player.sendMessage("You cannot claim for your kingdom!");
				}
			} else if(args[0].equalsIgnoreCase("abandonClaim")){
				if(v.canClaimForKingdom()){
					KingdomClaim claim = new KingdomClaim(player.getLocation());
					claim.delete();
				} else {
					player.sendMessage("You cannot delete this claim!");
				}
			}
		}
		return true;
	}
}
