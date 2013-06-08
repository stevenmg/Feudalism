package org.theglicks.bukkit.fuedalism;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
		
		DataStore.initialize();
		
		Location loc = new Location(Bukkit.getWorld("world"), 20, 20, 1);
		Fief f = new Fief(loc);
		
		if(f.exists()){
			Bukkit.getLogger().info("fief exists");
		} else {
			Bukkit.getLogger().warning("fief does not exist");
		}
	}
}