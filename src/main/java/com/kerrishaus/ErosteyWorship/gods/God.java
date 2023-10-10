package com.kerrishaus.ErosteyWorship.gods;

import com.kerrishaus.ErosteyWorship.gods.traits.Trait;
import org.bukkit.entity.Player;

import javax.swing.*;
import java.util.*;

public class God
{
    public String name;
    public List<Trait> traits = new ArrayList<>();

    public Map<UUID, Integer> playerReputation = new HashMap<>();

    public God(String name)
    {
        this.name   = name;
    }

    public Integer getPlayerReputation(Player player)
    {
        return this.playerReputation.getOrDefault(player.getUniqueId(), 0);
    }

    // Returns the new player reputation in relation to this God.
    public Integer decreasePlayerReputation(Player player, int amount)
    {
        int newAmount = this.playerReputation.getOrDefault(player.getUniqueId(), 0) - amount;
        return this.setPlayerReputation(player, newAmount);
    }

    // Returns the new player reputation in relation to this God.
    public Integer increasePlayerReputation(Player player, int amount)
    {
        int newAmount = this.playerReputation.getOrDefault(player.getUniqueId(), 0) + amount;

        return this.setPlayerReputation(player, newAmount);
    }

    // Returns the new player reputation in relation to this God.
    public Integer setPlayerReputation(Player player, int amount)
    {
        System.out.println(player.getName() + "'s reputation is now " + amount + ".");

        this.playerReputation.put(player.getUniqueId(), amount);
        return amount;
    }
}
