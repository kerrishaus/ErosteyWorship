package com.kerrishaus.ErosteyWorship;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ErosteyWorship extends JavaPlugin implements Listener
{
    @Override
    public void onEnable()
    {
        //getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("ErosteyWorship enabled.");
    }

    @Override
    public void onDisable()
    {
        getLogger().info("ErosteyWorship disabled.");
    }
}
