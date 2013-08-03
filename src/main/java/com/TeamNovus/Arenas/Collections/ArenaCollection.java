package com.TeamNovus.Arenas.Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.TeamNovus.Arenas.Models.Arena.Arena;

public class ArenaCollection {
	private List<Arena> arenas = new ArrayList<Arena>();
	
	public Collection<Arena> getArenas() {
		return arenas;
	}
	
	public Arena getArena(String name) {
		for(Arena arena : getArenas()) {
			if(arena.getName().toLowerCase().startsWith(name.toLowerCase())) {
				return arena;
			}
		}
		
		return null;
	}
	
	public Arena getArenaExact(String name) {
		for(Arena arena : getArenas()) {
			if(arena.getName().equals(name)) {
				return arena;
			}
		}
		
		return null;
	}
	
	public void attach(Arena a) {		
		arenas.add(a);
	}
	
	public void detach(Arena a) {
		arenas.remove(a);
	}
	
	public boolean attached(Arena a) {
		if (a.getName() == null)
			return false;
		
		return getArenaExact(a.getName()) != null;
	}
	
	public boolean detached(Arena a) {
		return !(attached(a));
	}
	
}
