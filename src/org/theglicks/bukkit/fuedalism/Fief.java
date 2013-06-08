package org.theglicks.bukkit.fuedalism;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;

public class Fief {
	
	public Fief(Location location){
		try {
			ResultSet rs = DataStore.st.executeQuery(""); //Add query to get fief at that location
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
