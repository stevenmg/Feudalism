package org.theglicks.bukkit.fuedalism;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Fuedalism extends JavaPlugin{
	
	public JavaPlugin fuedalismPlugin;
	public static ConfigAccessor mainConfig;
	
	@Override
	public void onEnable(){
		fuedalismPlugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("Fuedalism");
		mainConfig = new ConfigAccessor(fuedalismPlugin, "mainConfig.yml");
		
		mainConfig.saveDefaultConfig();
		mainConfig.getConfig().options().copyDefaults(true);
		
		DataStore ds = new DataStore();
	}
}
