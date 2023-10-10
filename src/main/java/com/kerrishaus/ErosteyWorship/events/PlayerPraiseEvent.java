package com.kerrishaus.ErosteyWorship.events;

import com.kerrishaus.ErosteyWorship.gods.God;
import org.bukkit.entity.Player;

public class PlayerPraiseEvent extends ErosteyEvent
{
    public PlayerPraiseEvent(God god, Player player)
    {
        super(god, player);
    }
}
