package com.kerrishaus.ErosteyWorship.gods.traits;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public abstract class Trait implements Listener
{
    public Trait(PluginManager pluginManager, Plugin plugin)
    {
        pluginManager.registerEvents(this, plugin);
    }

    //public abstract void tick();

    public void warnPlayer()
    {

    }

    public void punishPlayer()
    {

    }

    public void praisePlayer()
    {

    }

    public void rewardPlayer()
    {

    }
}
