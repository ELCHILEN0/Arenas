package com.TeamNovus.Arenas.Events;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import com.TeamNovus.Arenas.Models.ArenaPlayer;
import com.TeamNovus.Arenas.Models.Arena.Arena;

public class PlayerLeaveArenaEvent extends PlayerEvent {
	private static final HandlerList handlers = new HandlerList();
	private Arena arena;
	private ArenaPlayer player;
	
    public PlayerLeaveArenaEvent(ArenaPlayer player, Arena arena) {
    	super(player.getPlayer());
    	this.arena = arena;
    	this.player = player;
    }

    public ArenaPlayer getArenaPlayer() {
		return player;
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