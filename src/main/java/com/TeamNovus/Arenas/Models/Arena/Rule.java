package com.TeamNovus.Arenas.Models.Arena;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
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
import com.TeamNovus.Arenas.Models.ArenaPlayer;

public abstract class Rule {
	private String name;
	
	public Rule(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ConfigurationSection getConfigurationSection(Arena arena) {
		return arena.getConfig().getConfigurationSection("modules." + name);
	}
	
	//================================================================================
	// Arena Events
	//================================================================================
	
	public void onArenaStart(ArenaStartEvent event) {  }
	public void onArenaEnd(ArenaEndEvent event) {  }
	public void onPlayerJoinArena(PlayerJoinArenaEvent event) {  }
	public void onPlayerLeaveArena(PlayerLeaveArenaEvent event) {  }
	public void onTeamWinArena(TeamWinArenaEvent event) {  }
	public void onTeamLoseArena(TeamLoseArenaEvent event) {  }
	
	//================================================================================
	// Common Arena Events
	//================================================================================
	
	public void onPlayerMove(PlayerMoveEvent event, Arena arena) {  }
	
	public void onEntityDamage(EntityDamageEvent event, Arena arena) {
		if(event.getEntity() instanceof Player) {
			if(((Player) event.getEntity()).getHealth() - event.getDamage() < 1) {
				onPlayerDeath((Player) event.getEntity(), arena);
			}
		}		
	}
	
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event, Arena arena) {  }
	public void onEntityDamageByBlock(EntityDamageByBlockEvent event, Arena arena) {  }
	public void onPlayerItemConsume(PlayerItemConsumeEvent event, Arena arena) {  }
	public void onPlayerItemBreak(PlayerItemBreakEvent event, Arena arena) {  }
	public void onPlayerDropItem(PlayerDropItemEvent event, Arena arena) {  }
	public void onPlayerPickupItem(PlayerPickupItemEvent event, Arena arena) {  }
	
	public void onBlockBreak(BlockBreakEvent event, Arena arena) {  }
	public void onBlockDamage(BlockDamageEvent event, Arena arena) {  }
	public void onBlockBurnEvent(BlockBurnEvent event, Arena arena) {  }
	public void on(BlockDispenseEvent event, Arena arena) {  }
	
	//================================================================================
	// Additional Arena Events
	//================================================================================
	
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event, Arena arena) {  }
	public void onPlayerPortalEnter(PlayerPortalEvent event, Arena arena) {  }
	public void onPlayerTeleport(PlayerTeleportEvent event, Arena arena) {  }
	
	//================================================================================
	// Standard Arena Behaviors
	//================================================================================
	
	public void onPlayerDeath(Player player, Arena arena) {
		// 1. Heal the player completely.
		player.setHealth(player.getMaxHealth());
		
		// 2. Teleport the player to their team's spawn.
		player.teleport(arena.getTeam(arena.getPlayer(player)).getSpawn(), TeleportCause.PLUGIN);
		
		// 3. Trigger the respawn event for the arena.
		onPlayerRespawn(player, arena);
	}
	
	public void onPlayerRespawn(Player player, Arena arena) {
		// 1. Fetch the ArenaPlayer object.
		ArenaPlayer p = arena.getPlayer(player);
		
		// 2. Increment the number of deaths.
		p.setDeaths(p.getDeaths() + 1);
	}
	
	public void onPlayerQuit(PlayerQuitEvent event, Arena arena) {
		// 1. Remove the player from the arena.  This will be passed to the PlayerLeaveArena event.
		arena.removePlayer(arena.getPlayer(event.getPlayer()));
	}
	
}
