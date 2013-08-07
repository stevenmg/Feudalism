package org.theglicks.bukkit.fuedalism;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.theglicks.bukkit.fuedalism.commands.FiefCmd;
import org.theglicks.bukkit.fuedalism.commands.KingdomCmd;
import org.theglicks.bukkit.fuedalism.events.BlockBreak;
import org.theglicks.bukkit.fuedalism.events.BlockPlace;
import org.theglicks.bukkit.fuedalism.events.CommandPreprocess;
import org.theglicks.bukkit.fuedalism.events.EntityDamageByEntity;
import org.theglicks.bukkit.fuedalism.events.InventoryOpen;
import org.theglicks.bukkit.fuedalism.events.PlayerInteract;
import org.theglicks.bukkit.fuedalism.events.PlayerJoin;
import org.theglicks.bukkit.fuedalism.events.PlayerMove;

public class Fuedalism extends JavaPlugin{
	
	public JavaPlugin fuedalismPlugin;
	public static ConfigAccessor mainConfig;
	
	@Override
	public void onEnable(){
		fuedalismPlugin = (JavaPlugin) Bukkit.getPluginManager().getPlugin("Fuedalism");
		mainConfig = new ConfigAccessor(fuedalismPlugin, "mainConfig.yml");
		
		mainConfig.saveDefaultConfig();
		mainConfig.getConfig().options().copyDefaults(true);
		
		getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new BlockPlace(), this);
		getServer().getPluginManager().registerEvents(new InventoryOpen(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new CommandPreprocess(), this);
		
		getCommand("kingdom").setExecutor(new KingdomCmd(this));
		getCommand("fief").setExecutor(new FiefCmd(this));
	}
}