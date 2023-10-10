package com.kerrishaus.ErosteyWorship.events;

import com.kerrishaus.ErosteyWorship.gods.God;
import org.bukkit.entity.Player;

public class PlayerInteractsGodEvent extends ErosteyEvent
{
    public PlayerInteractsGodEvent(God god, Player player)
    {
        super(god, player);
    }
}
