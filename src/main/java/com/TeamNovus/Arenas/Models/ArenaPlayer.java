package com.TeamNovus.Arenas.Models;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ArenaPlayer {
	private String name;
	private Inventory storedInventory;
	private Inventory selectedClass;
	private int deaths;
	
	public ArenaPlayer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Inventory getStoredInventory() {
		return storedInventory;
	}
	
	public void setStoredInventory(Inventory storedInventory) {
		this.storedInventory = storedInventory;
	}
	
	public Inventory getSelectedClass() {
		return selectedClass;
	}
	
	public void setSelectedClass(Inventory selectedClass) {
		this.selectedClass = selectedClass;
	}
	
	public int getDeaths() {
		return deaths;
	}
	
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayerExact(name);
	}
}
