package com.TeamNovus.Arenas.Database;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import com.TeamNovus.Arenas.Arenas;
import com.TeamNovus.Persistence.Databases.Database;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLConfiguration;
import com.TeamNovus.Persistence.Databases.MySQL.MySQLDatabase;
import com.TeamNovus.Persistence.Databases.SQLite.SQLiteConfiguration;
import com.TeamNovus.Persistence.Databases.SQLite.SQLiteDatabase;

public class StorageManager {
	private static StorageManager instance = null;
	private Database database;
	
	public static StorageManager getInstance() {
		if(instance == null)
			instance = new StorageManager();

		return instance;
	}

	public StorageManager() {		
		FileConfiguration config = Arenas.getPlugin().getConfig();
		
		if(config.getString("database.type").equalsIgnoreCase("mysql")) {
			MySQLConfiguration configuration = new MySQLConfiguration();
			
			configuration.setHost(config.getString("database.mysql.host"));
			configuration.setPort(config.getString("database.mysql.port"));
			configuration.setUsername(config.getString("database.mysql.username"));
			configuration.setPassword(config.getString("database.mysql.password"));
			configuration.setDatabase(config.getString("database.mysql.database"));
			
			database = new MySQLDatabase(configuration);
		} else if(config.getString("database.type").equalsIgnoreCase("sqlite")) {
			SQLiteConfiguration configuration = new SQLiteConfiguration();
			
			configuration.setFilePath(Arenas.getPlugin().getDataFolder() + File.separator + config.getString("database.sqlite.file"));
			
			database = new SQLiteDatabase(configuration);
		}
		
		if(database == null) {
			// Fail
			Bukkit.getLogger().severe("-----------------------------------------------------");
			Bukkit.getLogger().severe("Unable to connect to the database!");
			Bukkit.getLogger().severe("Verify that your information is correct then try again!");
			Bukkit.getLogger().severe("-----------------------------------------------------");
			Bukkit.getServer().getPluginManager().disablePlugin(Arenas.getPlugin());
			return;
		}
		
		database.connect();
	}

//	public void loadPlayers() {
//		List<SNPlayer> players = database.findAll(SNPlayer.class);
//
//		for(SNPlayer p : players) {
//			SNPlayers.i.attach(p);
//		}
//	}
//
//	public void savePlayers() {	
//		database.saveAll(SNPlayers.i.getAllPlayers());
//	}
}
