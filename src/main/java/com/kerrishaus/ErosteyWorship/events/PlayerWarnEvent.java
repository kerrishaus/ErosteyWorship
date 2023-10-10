package com.kerrishaus.ErosteyWorship.events;

import com.kerrishaus.ErosteyWorship.gods.God;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class PlayerWarnEvent extends ErosteyEvent
{
    public PlayerWarnEvent(God god, Player player)
    {
        super(god, player);
    }
}
