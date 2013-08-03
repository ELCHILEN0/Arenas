package com.TeamNovus.Arenas.Events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import com.TeamNovus.Arenas.Models.ArenaPlayer;
import com.TeamNovus.Arenas.Models.Arena.Arena;

public class PlayerJoinArenaEvent extends PlayerEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private Arena arena;
	private ArenaPlayer player;
	
    public PlayerJoinArenaEvent(ArenaPlayer player, Arena arena) {
    	super(player.getPlayer());
    	this.player = player;
    	this.arena = arena;
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

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}