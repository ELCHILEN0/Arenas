package com.TeamNovus.Arenas.Models;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.TeamNovus.Arenas.Events.ArenaEndEvent;
import com.TeamNovus.Arenas.Events.ArenaStartEvent;
import com.TeamNovus.Arenas.Events.PlayerJoinArenaEvent;
import com.TeamNovus.Arenas.Events.PlayerLeaveArenaEvent;
import com.TeamNovus.Arenas.Events.TeamLoseArenaEvent;
import com.TeamNovus.Arenas.Events.TeamWinArenaEvent;

public abstract class Rule {
	private String name;
	
	public Rule(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * These do not need location checking.
	 */
	protected void onArenaStart(ArenaStartEvent event) {  }
	protected void onArenaEnd(ArenaEndEvent event) {  }
	protected void onPlayerJoinArena(PlayerJoinArenaEvent event) {  }
	protected void onPlayerLeaveArena(PlayerLeaveArenaEvent event) {  }
	protected void onTeamWinArena(TeamWinArenaEvent event) {  }
	protected void onTeamLoseArena(TeamLoseArenaEvent event) {  }
	
	/**
	 * Common damage and movement hooks.
	 */
	protected void onPlayerMove(PlayerMoveEvent event) {  }
	protected void onEntityDamage(EntityDamageEvent event, Arena arena) {
		if(event.getEntity() instanceof Player) {
			if(((Player) event.getEntity()).getHealth() - event.getDamage() < 1) {
				onPlayerDeath((Player) event.getEntity(), arena);
			}
		}		
	}
	
	protected void onEntityDamageByEntity() {  }
	protected void onEntityDamageByBlock() {  }
	protected void onPlayerItemConsume() {  }
	protected void onPlayerItemBreak() {  }
	protected void onPlayerDropItem() {  }
	protected void onPlayerPickupItem() {  }
	
	//================================================================================
	// Default Arena Behaviors
	//================================================================================
	
	/**
	 * Called when a Player dies in an arena.
	 * 
	 * @param player - The player that just died.
	 * @param arena - The arena that the player is in.
	 */
	protected void onPlayerDeath(Player player, Arena arena) {
		// 1. Heal the player completely.
		player.setHealth(player.getMaxHealth());
		
		// 2. Teleport the player to their team's spawn.
		player.teleport(arena.getTeam(arena.getPlayer(player)).getSpawn(), TeleportCause.PLUGIN);
		
		// 3. Trigger the respawn event for the arena.
		onPlayerRespawn(player, arena);
	}
	
	protected void onPlayerRespawn(Player player, Arena arena) {
		// 1. Fetch the ArenaPlayer object.
		ArenaPlayer p = arena.getPlayer(player);
		
		// 2. Increment the number of deaths.
		p.setDeaths(p.getDeaths() + 1);
	}
	
	protected void onPlayerQuit(PlayerQuitEvent event, Arena arena) {
		// 1. Remove the player from the arena.  This will be passed to the PlayerLeaveArena event.
		arena.removePlayer(arena.getPlayer(event.getPlayer()));
	}
	
	protected void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event, Arena arena) {
		if(!(event.getMessage().startsWith("/arena leave")) || !(event.getMessage().startsWith("/arena chat"))) {
			event.setCancelled(true);
			
			event.getPlayer().sendMessage(ChatColor.RED + "You cannot use " + ChatColor.YELLOW + event.getMessage() + ChatColor.RED + "  while in this arena!");
			event.getPlayer().sendMessage(ChatColor.RED + "To leave type: " + ChatColor.YELLOW + " /arena leave");
		}
	}
	
	protected void onPlayerPortalEnter(PlayerPortalEvent event, Arena arena) {
		event.setCancelled(true);
		
		event.getPlayer().sendMessage(ChatColor.RED + "You cannot enter portals while in this arena!");
		event.getPlayer().sendMessage(ChatColor.RED + "To leave type: " + ChatColor.YELLOW + " /arena leave");
	}
	
	protected void onPlayerTeleport(PlayerTeleportEvent event, Arena arena) {
		if(!(event.getCause().equals(TeleportCause.PLUGIN))) {
			event.setCancelled(true);
			
			event.getPlayer().sendMessage(ChatColor.RED + "You cannot teleport while in this arena!");
			event.getPlayer().sendMessage(ChatColor.RED + "To leave type: " + ChatColor.YELLOW + " /arena leave");
		}
	}
	
}
