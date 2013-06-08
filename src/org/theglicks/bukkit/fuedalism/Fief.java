package org.theglicks.bukkit.fuedalism;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;

public class Fief {
	ResultSet rs;
	
	public Fief(Location loc){
		try {
			rs = DataStore.st.executeQuery("SELECT * FROM `fiefs` WHERE MBRCONTAINS(" +
					"`region`, POINT(" + loc.getX() + "," + loc.getZ() + "10))" +
							" AND `world` = '" + loc.getWorld() + "';");
			
			rs.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean exists(){
		try {
			if(rs.first()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} return false;
	}
}
