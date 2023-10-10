package com.kerrishaus.ErosteyWorship.events;

import com.kerrishaus.ErosteyWorship.gods.God;
import org.bukkit.entity.Player;

public class PlayerRewardEvent extends ErosteyEvent
{
    public PlayerRewardEvent(God god, Player player)
    {
        super(god, player);
    }
}
