package com.untamedears.Prisonbeds;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PrisonbedsPlayerListener implements Listener
{
	public static Prisonbeds plugin;

	public PrisonbedsPlayerListener(Prisonbeds instance)
	{
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent pre)
	{
		Player p = pre.getPlayer();
		Location loc = (Location)plugin.getCorrectedSpawns().get(p.getDisplayName());
		if (loc != null)
		{
			pre.setRespawnLocation(loc);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerBedEnter(PlayerBedEnterEvent pbee)
	{
		HashMap correctedSpawns = plugin.getCorrectedSpawns();

		correctedSpawns.remove(pbee.getPlayer().getDisplayName());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent pqe)
	{
		if (plugin.getPrimedCorrections().containsKey(pqe.getPlayer().getDisplayName())) 
		{
			plugin.getPrimedCorrections().remove(pqe.getPlayer().getDisplayName());
			System.out.println("Prisonbeds - " + pqe.getPlayer().getDisplayName() + "logged out before killing anyone and is now unprimed.");
		}
	}
}