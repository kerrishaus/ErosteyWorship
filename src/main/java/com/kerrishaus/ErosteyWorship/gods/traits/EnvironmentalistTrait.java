package com.kerrishaus.ErosteyWorship.gods.traits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EnvironmentalistTrait extends Trait
{
    public EnvironmentalistTrait(PluginManager pm, Plugin plugin)
    {
        super(pm, plugin);
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event)
    {
        // if it's null then it wasn't killed by a player
        if (event.getEntity().getKiller() == null)
            return;

        Player killer = event.getEntity().getKiller();
        killer.getWorld().strikeLightning(killer.getLocation());
    }
}
