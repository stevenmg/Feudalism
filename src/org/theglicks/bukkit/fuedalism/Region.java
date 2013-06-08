package org.theglicks.bukkit.fuedalism;

import org.bukkit.Location;

public class Region {
	Location corner1;
	Location corner2;
	
	public Region(Location corner, Location corner0){
		corner1 = corner;
		corner2 = corner0;
	}
	
	public boolean contains(Location loc){
		if(((corner1.getX() < loc.getX()) && (loc.getX() < corner2.getX()) ||
				(corner1.getX() > loc.getX()) && (loc.getX() > corner2.getX())) &&
				((corner1.getY() < loc.getY()) && (loc.getY() < corner2.getY()) ||
				(corner1.getY() > loc.getY()) && (loc.getY() > corner2.getY()))){
			return true;
		} return false;
	}
}
