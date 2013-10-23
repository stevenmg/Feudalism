package org.theglicks.bukkit.governance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataStore {
	public static Connection conn;
	public Statement st;
	public ResultSet rs;
	
	public DataStore(){
		initialize();
	}
	
	public void initialize(){
		try {
			if (conn == null){
				conn = DriverManager.getConnection(
						Governance.mainConfig.getConfig().getString("DataStore.databaseURL"),
						Governance.mainConfig.getConfig().getString("DataStore.user"),
						Governance.mainConfig.getConfig().getString("DataStore.password"));
			}
			
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void verifyDB(){
		try {
			//Checks if the database exists
			DataStore ds = new DataStore();
			ds.rs = ds.st.executeQuery("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'governance'");
			if(ds.rs.first()) return;
			
			//Creates it if not
			InputStream is = ds.getClass().getResourceAsStream("/script.sql");
			InputStreamReader sr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(sr);
			StringBuilder sql = new StringBuilder();
			String line;
			
			while((line = br.readLine()) != null){
				sql.append(line);
			}
			
			ds.st.execute("CREATE DATABASE `governance`;");
			ds.st.execute("USE governance;");
			
			for(String section: sql.toString().split("#")){
				ds.st.execute(section);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}