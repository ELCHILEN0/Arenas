package com.TeamNovus.Arenas.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.TeamNovus.Arenas.Models.Arena.Arena;

public class ArenaStartEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Arena arena;
	
    public ArenaStartEvent(Arena arena) {
    	this.arena = arena;
    }

    public Arena getArena() {
		return arena;
	}
    
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}