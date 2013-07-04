package org.theglicks.bukkit.fuedalism.landManagement;

import org.bukkit.Bukkit;
import org.bukkit.Location;

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
		return new Fief(point1, point2, Bukkit.getPlayer(playerName));
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
}
