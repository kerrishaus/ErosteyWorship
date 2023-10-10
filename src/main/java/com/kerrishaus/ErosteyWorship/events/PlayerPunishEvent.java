package com.kerrishaus.ErosteyWorship.events;

import com.kerrishaus.ErosteyWorship.gods.God;
import org.bukkit.entity.Player;

public class PlayerPunishEvent extends ErosteyEvent
{
    public PlayerPunishEvent(God god, Player player)
    {
        super(god, player);
    }
}
