package org.theglicks.bukkit.fuedalism.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.theglicks.bukkit.fuedalism.Fuedalism;
import org.theglicks.bukkit.fuedalism.Vassal;
import org.theglicks.bukkit.fuedalism.kingdoms.AllianceManager;
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
			} else if(args[0].equalsIgnoreCase("claim")){
				if (v.isLeader()) {
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
				if(v.isLeader()){
					KingdomClaim claim = new KingdomClaim(player.getLocation());
					claim.delete();
				} else {
					player.sendMessage("You cannot abandon this claim!");
				}
			} else if(args[0].equalsIgnoreCase("show")){
				Kingdom k;
				if(args.length == 2){
					k = new Kingdom(args[1]);	
				} else {
					k = v.getKingdom();
				}			
				StringBuilder b = new StringBuilder();
				for(String name: k.getMembers()){
					if(!name.equals(k.getOwner().getName())){
						b.append(name);
					}
				}
				player.sendMessage("Name: " + k.getName());
				player.sendMessage("Owner: " + k.getOwner().getName());
				player.sendMessage("Members: " + b.toString());
			} else if(args[0].equalsIgnoreCase("ally")){
				if(args.length == 2){
					Kingdom kSender = v.getKingdom();
					Kingdom kReceiver = new Kingdom(args[1]);
					if(AllianceManager.hasRequest(kReceiver, kSender) && !AllianceManager.hasAlliance(kSender, kReceiver)){
						AllianceManager.addAlliance(kSender, kReceiver);
					} else if(!AllianceManager.hasRequest(kSender, kReceiver)){
						AllianceManager.addRequest(kSender, kReceiver);
					}	
				} else {
					player.sendMessage("Look up the correct command usage!");
				}
			}
		}
		return true;
	}
}
