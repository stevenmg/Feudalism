package org.theglicks.bukkit.fuedalism;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataStore {
	static Connection conn;
	public static Statement st;
	
	public static void initialize(){
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
