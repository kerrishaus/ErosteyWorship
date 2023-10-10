package com.kerrishaus.ErosteyWorship.gods.traits;

import com.kerrishaus.ErosteyWorship.events.PlayerPunishEvent;
import com.kerrishaus.ErosteyWorship.events.PlayerWarnEvent;
import com.kerrishaus.ErosteyWorship.gods.God;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public abstract class Trait implements Listener
{
    God god;
    PluginManager pluginManager;
    Plugin        plugin;

    public Trait(God god, PluginManager pluginManager, Plugin plugin)
    {
        this.god           = god;
        this.pluginManager = pluginManager;
        this.plugin        = plugin;

        pluginManager.registerEvents(this, plugin);
    }

    public boolean warnPlayer(Player player)
    {
        System.out.println("Warning player");

        PlayerWarnEvent punishEvent = new PlayerWarnEvent(this.god, player);
        this.pluginManager.callEvent(punishEvent);

        if (punishEvent.isCancelled())
            return false;

        god.decreasePlayerReputation(player, 1);

        player.sendTitle(null,god.name + " is displeased with your actions.", 10, 70, 20);
        return true;
    }

    public boolean punishPlayer(Player player)
    {
        System.out.println("Punishing player");

        PlayerPunishEvent punishEvent = new PlayerPunishEvent(this.god, player);
        this.pluginManager.callEvent(punishEvent);

        if (punishEvent.isCancelled())
            return false;

        god.decreasePlayerReputation(player, 2);

        player.sendTitle(null, god.name + " is very displeased with your actions.", 10, 70, 20);
        return true;
    }

    public boolean praisePlayer(Player player)
    {
        System.out.println("Praising player");

        player.sendTitle(null, god.name + " is pleased with your actions.", 10, 70, 20);

        return true;
    }

    public boolean rewardPlayer(Player player)
    {
        System.out.println("Rewarding player");

        player.sendTitle(null, god.name + " is very pleased with your actions.", 10, 70, 20);

        return true;
    }
}
