package com.TeamNovus.Arenas.Models;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.TeamNovus.Arenas.Events.ArenaEndEvent;
import com.TeamNovus.Arenas.Events.ArenaStartEvent;
import com.TeamNovus.Arenas.Events.PlayerJoinArenaEvent;
import com.TeamNovus.Arenas.Events.PlayerLeaveArenaEvent;
import com.TeamNovus.Arenas.Events.TeamLoseArenaEvent;
import com.TeamNovus.Arenas.Events.TeamWinArenaEvent;

public class TestExtend extends Rule {
	private static 

	public TestExtend() {
		super("test");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onPlayerDropItem(PlayerDropItemEvent event, Arena arena) {
		super.onPlayerDropItem();
		
		event.setCancelled(true);
		event.getPlayer().sendMessage("You cannot drop items in this arena!");
	}

}
