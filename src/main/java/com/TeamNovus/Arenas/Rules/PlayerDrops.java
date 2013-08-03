package com.TeamNovus.Arenas.Rules;

import org.bukkit.event.player.PlayerDropItemEvent;

import com.TeamNovus.Arenas.Models.Arena.Arena;
import com.TeamNovus.Arenas.Models.Arena.Rule;

public class PlayerDrops extends Rule {
	public PlayerDrops() {
		super("test");
	}

	@Override
	public void onPlayerDropItem(PlayerDropItemEvent event, Arena arena) {		
		event.setCancelled(true);
		event.getPlayer().sendMessage("You cannot drop items in this arena!");		
	}

}
