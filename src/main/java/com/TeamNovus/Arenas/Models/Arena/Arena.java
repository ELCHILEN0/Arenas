package com.TeamNovus.Arenas.Models.Arena;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.TeamNovus.Arenas.Arenas;
import com.TeamNovus.Arenas.Events.ArenaStartEvent;
import com.TeamNovus.Arenas.Events.PlayerJoinArenaEvent;
import com.TeamNovus.Arenas.Events.PlayerLeaveArenaEvent;
import com.TeamNovus.Arenas.Models.ArenaPlayer;

public class Arena {
	private String name;
	private Location corner1, corner2;
	private transient LinkedList<Rule> rules = new LinkedList<Rule>();
	private transient LinkedList<Team> teams = new LinkedList<Team>();
	private boolean running;
	private File file;
	
	public Arena(String name) {		
		running = false;
		file = new File(Arenas.getPlugin().getDataFolder() + File.separator + "arenas" + File.separator, name + ".yml");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Location getCorner1() {
		return corner1;
	}
	
	public void setCorner1(Location corner1) {
		this.corner1 = corner1;
	}
	
	public Location getCorner2() {
		return corner2;
	}
	
	public void setCorner2(Location corner2) {
		this.corner2 = corner2;
	}
	
	public boolean inArena(Location loc) {
		return loc.toVector().isInAABB(corner1.toVector(), corner2.toVector());
	}

	public void addRule(Rule rule) {
		rules.add(rule);
	}
	
	public void removeRule(Rule rule) {
		rules.remove(rule);
	}
	
	public LinkedList<Rule> getRules() {
		return rules;
	}
	
	public void addTeam(Team team) {
		teams.add(team);
	}
	
	public void removeTeam(Team team) {
		teams.remove(team);
	}
	
	public LinkedList<Team> getTeams() {
		return teams;
	}
	
	public void addPlayer(ArenaPlayer player, Team team) {
		PlayerJoinArenaEvent event = new PlayerJoinArenaEvent(player, this);
		Bukkit.getPluginManager().callEvent(event);
		
		if(!(event.isCancelled()))
			team.addPlayer(player);
	}
	
	public boolean removePlayer(ArenaPlayer player) {
		for(Team team : teams) {
			boolean removed = team.removePlayer(player);
			
			if(removed) {
				PlayerLeaveArenaEvent event = new PlayerLeaveArenaEvent(player, this);
				Bukkit.getPluginManager().callEvent(event);
				
				return true;
			}
		}
		
		return false;
	}
	
	public List<ArenaPlayer> getPlayers() {
		List<ArenaPlayer> players = new ArrayList<ArenaPlayer>();
		
		for(Team team : teams) {
			players.addAll(team.getPlayers());
		}
		
		return players;
	}
	
	public boolean isPlaying(Player player) {
		for(ArenaPlayer p : getPlayers()) {
			if(p.getName().equals(player.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	public ArenaPlayer getPlayer(Player player) {
		for(ArenaPlayer p : getPlayers()) {
			if(p.getName().equals(player.getName())) {
				return p;
			}
		}
		
		return null;
	}
	
	public Team getTeam(ArenaPlayer player) {
		for(Team team : teams) {
			if(team.hasPlayer(player)) {
				return team;
			}
		}
		
		return null;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void start() {
		setRunning(true);
		
		ArenaStartEvent event = new ArenaStartEvent(this);		
		Bukkit.getPluginManager().callEvent(event);
	}
	
	public void end() {
		setRunning(false);
		
		for(Team team : teams) {
			team.clearPlayers();
		}
		
		ArenaStartEvent event = new ArenaStartEvent(this);		
		Bukkit.getPluginManager().callEvent(event);
	}
	
	public boolean loadFromConfig() {
		return false;
	}
	
	public boolean saveToConfig() {	
		return true;
	}
	
	public FileConfiguration getConfig() {
		if(!(file.exists())) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	    return YamlConfiguration.loadConfiguration(file);
	}
}
