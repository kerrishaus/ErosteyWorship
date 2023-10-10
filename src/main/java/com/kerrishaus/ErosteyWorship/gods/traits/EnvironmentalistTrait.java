package com.kerrishaus.ErosteyWorship.gods.traits;

import com.kerrishaus.ErosteyWorship.events.PlayerPunishEvent;
import com.kerrishaus.ErosteyWorship.gods.God;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EnvironmentalistTrait extends Trait
{
    public EnvironmentalistTrait(God god, PluginManager pluginManager, Plugin plugin)
    {
        super(god, pluginManager, plugin);
    }

    // TODO: track time since last kill, and reduce reputation by a multiplier
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event)
    {
        // we don't care about hostile mobs
        // TODO: maybe change this to only attacking mobs
        if (event.getEntity() instanceof Monster)
            return;

        // if it's null then it wasn't killed by a player
        if (event.getEntity().getKiller() == null)
            return;

        Player killer = event.getEntity().getKiller();

        int rep = god.getPlayerReputation(killer);

        if (rep > -5)
            this.warnPlayer(killer);
        else
            this.punishPlayer(killer);
    }

    @Override
    public boolean punishPlayer(Player player)
    {
        if (super.punishPlayer(player))
        {
            player.getWorld().strikeLightning(player.getLocation());
            return true;
        }

        return false;
    }
}
