package org.theglicks.bukkit.fuedalism;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

public class Messages {
	
	static HashMap<String, String> messages = new HashMap<String, String>();
	
	public static void loadMessages(JavaPlugin pl){
		File f = new File(pl.getDataFolder(), "messages.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f.getAbsolutePath()));
			
			String line;
			while((line = reader.readLine()) != null){
				messages.put(line.split("::")[0], line.split("::")[1]);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessage(String messageName, String args){
		return messages.get(messageName).replace("%$%", args);
	}
}
