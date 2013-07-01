package com.TeamNovus.Arenas.Models;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Team {
	private String name;
	private ChatColor color;
	private int maxPlayers;
	private Location spawn;
	private Location lobby;
	private transient Set<ArenaPlayer> players = new HashSet<ArenaPlayer>();
	
	public Team(String name, ChatColor color, int maxPlayers) {
		this.name = name;
		this.color = color;
		this.maxPlayers = maxPlayers;
	}
	
	public String getName() {
		return name;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}
	
	public Location getSpawn() {
		return spawn;
	}
	
	public void setLobby(Location lobby) {
		this.lobby = lobby;
	}
	
	public Location getLobby() {
		return lobby;
	}
	
	public Set<ArenaPlayer> getPlayers() {
		return players;
	}
	
	public void addPlayer(ArenaPlayer player) {
		players.add(player);
	}
	
	public boolean removePlayer(ArenaPlayer player) {
		return players.remove(player);
	}
	
	public boolean hasPlayer(ArenaPlayer player) {
		for(ArenaPlayer p : players) {
			if(p.getName().equals(player.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	public void clearPlayers() {
		players.clear();
	}
}
