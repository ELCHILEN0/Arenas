package com.TeamNovus.Arenas.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.TeamNovus.Arenas.Models.Arena.Arena;
import com.TeamNovus.Arenas.Models.Arena.Team;

public class TeamWinArenaEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Arena arena;
	private Team team;
	
    public TeamWinArenaEvent(Arena arena, Team team) {
    	this.arena = arena;
    	this.team = team;
    }

    public Arena getArena() {
		return arena;
	}
    
    public Team getTeam() {
    	return team;
    }
    
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}