package com.kerrishaus.ErosteyWorship.events;

import com.kerrishaus.ErosteyWorship.gods.God;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ErosteyEvent extends Event implements Cancellable
{
    God god;
    Player player;

    public ErosteyEvent(God god, Player player)
    {
        this.god    = god;
        this.player = player;

        System.out.println("Created new ErosteyEvent");
    }

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean isCancelled = false;

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled()
    {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled)
    {
        this.isCancelled = isCancelled;
    }
}
