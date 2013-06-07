package org.theglicks.bukkit.fuedalism;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataStore {
	Connection conn;
	ResultSet rs;
	Statement st;
	
	public DataStore(){
		initialize();
	}
	
	public void initialize(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" +
					Fuedalism.mainConfig.getConfig().getString("DataStore.databaseURL"),
					Fuedalism.mainConfig.getConfig().getString("DataStore.user"),
					Fuedalism.mainConfig.getConfig().getString("DataStore.password"));
			
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
