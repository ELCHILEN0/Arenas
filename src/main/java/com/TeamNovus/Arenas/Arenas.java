package com.TeamNovus.Arenas;

import org.bukkit.plugin.java.JavaPlugin;

import com.TeamNovus.Arenas.Collections.ArenaCollection;

public class Arenas extends JavaPlugin {
	public static ArenaCollection i = new ArenaCollection();
	private static Arenas plugin;

	public void onEnable() {
		plugin = this;
	}
	
	public void onDisable() {
		plugin = null;
	}

	public static Arenas getPlugin() {
		return plugin;
	}
	
}
