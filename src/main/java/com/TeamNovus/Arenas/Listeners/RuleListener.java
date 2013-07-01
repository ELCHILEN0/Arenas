package com.TeamNovus.Arenas.Listeners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;

import com.TeamNovus.Arenas.Arenas;
import com.TeamNovus.Arenas.Models.Arena;
import com.TeamNovus.Arenas.Models.Rule;

public class RuleListener {

//	public void callEvent(Event event, Class<?> eventClass) {
//		for(Arena arena : Arenas.i.getAllArenas()) {
//			Location eventLocation = null;
//
//			if(event instanceof EntityEvent) {
//				eventLocation = ((EntityEvent) event).getEntity().getLocation();
//			} else if(event instanceof PlayerEvent) {
//				eventLocation = ((PlayerEvent) event).getPlayer().getLocation();
//			} else if(event instanceof BlockEvent) {
//				eventLocation = ((BlockEvent) event).getBlock().getLocation();
//			}
//			
//			if(!(isValidEventLocation(eventLocation, arena))) {
//				continue;
//			}
//			
//			
//			for(Rule rule : arena.getRules()) {
//				for(Method method : rule.getClass().getMethods()) {
//					if(method.isAnnotationPresent(ArenaEventHandler.class)) {
//						Type[] types = method.getParameterTypes();
//
//						boolean set = false;
//						Object[] params = new Object[types.length];
//						for (int i = 0; i < types.length; i++) {
//							Type type = types[i];
//
//							if(type.equals(eventClass)) {
//								params[i] = event; set = true;
//							} else {
//								params[i] = null;
//							}
//						}
//
//						if(set) {
//							try {
//								method.invoke(rule, params);
//							} catch (IllegalArgumentException e) {
//								e.printStackTrace();
//							} catch (IllegalAccessException e) {
//								e.printStackTrace();
//							} catch (InvocationTargetException e) {
//								e.printStackTrace();
//							}
//						}
//
//					}
//				}
//			}
//		}
//
//		//if(eventLocation)
//	}


	public boolean isValidEventLocation(Location location, Arena arena) {
		return false;
	}
}
