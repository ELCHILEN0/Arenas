package com.TeamNovus.Arenas.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
import org.bukkit.event.player.PlayerTeleportEvent;

import com.TeamNovus.Arenas.Arenas;
import com.TeamNovus.Arenas.Events.ArenaEndEvent;
import com.TeamNovus.Arenas.Events.ArenaStartEvent;
import com.TeamNovus.Arenas.Events.PlayerJoinArenaEvent;
import com.TeamNovus.Arenas.Events.PlayerLeaveArenaEvent;
import com.TeamNovus.Arenas.Events.TeamLoseArenaEvent;
import com.TeamNovus.Arenas.Events.TeamWinArenaEvent;
import com.TeamNovus.Arenas.Models.Arena.Arena;
import com.TeamNovus.Arenas.Models.Arena.Rule;

public class RuleListener implements Listener {

	//================================================================================
	// Arena Events
	//================================================================================
	
	@EventHandler
	public void onArenaStart(ArenaStartEvent event) {
		for(Rule rule : event.getArena().getRules()) {
			rule.onArenaStart(event);
		}
	}
	
	@EventHandler
	public void onArenaEnd(ArenaEndEvent event) {
		for(Rule rule : event.getArena().getRules()) {
			rule.onArenaEnd(event);
		}
	}
	
	@EventHandler
	public void onPlayerJoinArena(PlayerJoinArenaEvent event) {
		for(Rule rule : event.getArena().getRules()) {
			rule.onPlayerJoinArena(event);
		}
	}

	@EventHandler
	public void onPlayerLeaveArena(PlayerLeaveArenaEvent event) {
		for(Rule rule : event.getArena().getRules()) {
			rule.onPlayerLeaveArena(event);
		}
	}
	
	@EventHandler
	public void onTeamWinArena(TeamWinArenaEvent event) {
		for(Rule rule : event.getArena().getRules()) {
			rule.onTeamWinArena(event);
		}
	}
	
	@EventHandler
	public void onTeamLoseArena(TeamLoseArenaEvent event) {
		for(Rule rule : event.getArena().getRules()) {
			rule.onTeamLoseArena(event);
		}
	}
	
	//================================================================================
	// Common Arena Events
	//================================================================================
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		for(Arena arena : Arenas.i.getArenas()) {
			if(arena.isPlaying(event.getPlayer())) {
				for(Rule rule : arena.getRules()) {
					rule.onPlayerMove(event, arena);
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		for(Arena arena : Arenas.i.getArenas()) {
			if(event.getEntity() instanceof Player) {
				if(arena.isPlaying((Player) event.getEntity())) {
					for(Rule rule : arena.getRules()) {
						rule.onEntityDamage(event, arena);
					}
				}
			} else {
				if(arena.inArena(event.getEntity().getLocation())) {
					for(Rule rule : arena.getRules()) {
						rule.onEntityDamage(event, arena);
					}
				}
			}
		}		
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		for(Arena arena : Arenas.i.getArenas()) {
			if(event.getEntity() instanceof Player) {
				if(arena.isPlaying((Player) event.getEntity())) {
					for(Rule rule : arena.getRules()) {
						rule.onEntityDamageByEntity(event, arena);
					}
				}
			} else {
				if(arena.inArena(event.getEntity().getLocation())) {
					for(Rule rule : arena.getRules()) {
						rule.onEntityDamageByEntity(event, arena);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
		for(Arena arena : Arenas.i.getArenas()) {
			if(event.getEntity() instanceof Player) {
				if(arena.isPlaying((Player) event.getEntity())) {
					for(Rule rule : arena.getRules()) {
						rule.onEntityDamageByBlock(event, arena);
					}
				}
			} else {
				if(arena.inArena(event.getEntity().getLocation())) {
					for(Rule rule : arena.getRules()) {
						rule.onEntityDamageByBlock(event, arena);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
		for(Arena arena : Arenas.i.getArenas()) {
			if(arena.isPlaying(event.getPlayer())) {
				for(Rule rule : arena.getRules()) {
					rule.onPlayerItemConsume(event, arena);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerItemBreak(PlayerItemBreakEvent event) {
		for(Arena arena : Arenas.i.getArenas()) {
			if(arena.isPlaying(event.getPlayer())) {
				for(Rule rule : arena.getRules()) {
					rule.onPlayerItemBreak(event, arena);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		System.out.println("drop");
		
		for(Arena arena : Arenas.i.getArenas()) {
			if(arena.isPlaying(event.getPlayer())) {
				for(Rule rule : arena.getRules()) {
					rule.onPlayerDropItem(event, arena);
					System.out.println("calling event");
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		for(Arena arena : Arenas.i.getArenas()) {
			if(arena.isPlaying(event.getPlayer())) {
				for(Rule rule : arena.getRules()) {
					rule.onPlayerPickupItem(event, arena);
				}
			}
		}
	}
	
	public void onBlockBreak(BlockBreakEvent event, Arena arena) {  }
	public void onBlockDamage(BlockDamageEvent event, Arena arena) {  }
	public void onBlockBurnEvent(BlockBurnEvent event, Arena arena) {  }
	public void on(BlockDispenseEvent event, Arena arena) {  }
	
	//================================================================================
	// Additional Arena Events
	//================================================================================
	
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		for(Arena arena : Arenas.i.getArenas()) {
			if(arena.isPlaying(event.getPlayer())) {
				for(Rule rule : arena.getRules()) {
					rule.onPlayerCommandPreprocess(event, arena);
				}
			}
		}
	}
	
	public void onPlayerPortalEnter(PlayerPortalEvent event) {
		for(Arena arena : Arenas.i.getArenas()) {
			if(arena.isPlaying(event.getPlayer())) {
				for(Rule rule : arena.getRules()) {
					rule.onPlayerPortalEnter(event, arena);
				}
			}
		}
	}
	
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		for(Arena arena : Arenas.i.getArenas()) {
			if(arena.isPlaying(event.getPlayer())) {
				for(Rule rule : arena.getRules()) {
					rule.onPlayerTeleport(event, arena);
				}
			}
		}
	}
}
