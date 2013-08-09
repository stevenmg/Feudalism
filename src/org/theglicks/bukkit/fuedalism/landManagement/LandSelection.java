package org.theglicks.bukkit.fuedalism.landManagement;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.theglicks.bukkit.fuedalism.Vassal;

public class LandSelection {
	private Location point1;
	private Location point2;
	private String playerName;
	
	public LandSelection(String player){
		playerName = player;
	}
	
	public LandSelection(Location p1, Location p2, String player){
		point1 = p1;
		point2 = p2;
		playerName = player;
	}
	
	public boolean canCreateClaim(){
		if(point1.distance(point2) >= 15){
			return true;
		} return false;
	}
	
	public Fief getFief(){
		return Fief.create(point1, point2, Bukkit.getPlayer(playerName));
	}
	
	public KingdomClaim getKingdomClaim(){
		Vassal v = new Vassal(playerName);
		
		return KingdomClaim.create(point1, point2, v.getKingdom());
	}
	
	public String getOwner(){
		return playerName;
	}
	
	public void addPoint(Location loc){
		if(point2 == null) {
			point2 = loc;
		} else if(point1 == null) {
			point1 = loc;
		} else {
			point2 = point1;
			point1 = loc;
		}
	}
	
	public int getSize(){
		Location loc0 = point1;
		Location loc1 = point2;
		Location loc2 = new Location(loc0.getWorld(), loc0.getX(), 0, loc1.getZ());
		loc0.setY(0);
		loc1.setY(0);
		
		return (int) (loc0.distance(loc1) * loc1.distance(loc2));
	}
}
