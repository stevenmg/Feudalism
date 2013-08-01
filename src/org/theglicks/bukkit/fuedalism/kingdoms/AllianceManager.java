package org.theglicks.bukkit.fuedalism.kingdoms;

import java.sql.SQLException;

import org.theglicks.bukkit.fuedalism.DataStore;

public class AllianceManager {
	public static boolean hasAlliance(Kingdom k1, Kingdom k2){	
		try {
			DataStore ds = new DataStore();
			ds.rs = ds.st.executeQuery("SELECT * FROM `alliances` WHERE (`kingdom1` = " + k1.getId() + " AND `kingdom2` = " + k2.getId() 
					+ ") OR (`kingdom1` = " + k1.getId() + " AND `kingdom2` = " + k2.getId() + ")");
			if(ds.rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void addAlliance(Kingdom k1, Kingdom k2){
		
	}
}
