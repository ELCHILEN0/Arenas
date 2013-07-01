package com.TeamNovus.Arenas.Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.TeamNovus.Arenas.Events.PlayerJoinArenaEvent;
import com.TeamNovus.Arenas.Events.PlayerLeaveArenaEvent;

public class Arena {
	private String name;
	private transient LinkedList<Rule> rules = new LinkedList<Rule>();
	private transient LinkedList<Team> teams = new LinkedList<Team>();
	private boolean running;
	
	public Arena(String name) {		
		running = false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	/**
	 * The player that should be pass should be one returned from getPlayer().
	 */
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
	
	/**
	 * To be called by the Objective class.  This will then be read from the objective and logic will be further processed.
	 */
	public void start() {
		
		
		setRunning(true);
	}
	
	/**
	 * To be called by the Objective class.  Any rewards will be done in here.
	 */
	public void end() {
		setRunning(false);
		
		for(Team team : teams) {
			team.clearPlayers();
		}
	}
}
