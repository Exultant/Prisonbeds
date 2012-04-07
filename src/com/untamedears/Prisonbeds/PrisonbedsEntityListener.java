package com.untamedears.Prisonbeds;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
 
public class PrisonbedsEntityListener implements Listener
{
	public static Prisonbeds plugin;
	
	public PrisonbedsEntityListener(Prisonbeds instance)
	{
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDeath(EntityDeathEvent ede)
	{
		Entity tmp = ede.getEntity();
 
		if ((tmp instanceof Player))
		{
			Player victim = (Player)tmp;
			Player killer = victim.getKiller();
 
			HashMap primedCorrections = plugin.getPrimedCorrections();
			HashMap correctedSpawns = plugin.getCorrectedSpawns();
			if ((killer != null) && (killer.getDisplayName() != null) && (primedCorrections.get(killer.getDisplayName()) != null))
			{
				System.out.println("Prisonbeds - " + killer.getDisplayName() + " imprisoned " + victim.getDisplayName());
				victim.sendMessage("You were imprisoned by " + killer.getDisplayName());
				//plugin.getServer().broadcastMessage(victim.getDisplayName() + " was imprisoned by " + killer.getDisplayName());
				correctedSpawns.put(victim.getDisplayName(), (Location)primedCorrections.get(killer.getDisplayName()));
				primedCorrections.remove(killer.getDisplayName());
			}
		}
   }
 }