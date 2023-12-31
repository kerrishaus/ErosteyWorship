package com.kerrishaus.ErosteyWorship.gods.traits;

import com.kerrishaus.ErosteyWorship.events.PlayerPraiseEvent;
import com.kerrishaus.ErosteyWorship.events.PlayerPunishEvent;
import com.kerrishaus.ErosteyWorship.events.PlayerRewardEvent;
import com.kerrishaus.ErosteyWorship.events.PlayerWarnEvent;
import com.kerrishaus.ErosteyWorship.gods.God;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public abstract class Trait implements Listener
{
    public String name;

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

        String message = god.name + " is displeased with your actions.";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

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

        String message = god.name + " is very displeased with your actions.";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

        return true;
    }

    public boolean praisePlayer(Player player)
    {
        System.out.println("Praising player");

        PlayerPraiseEvent praiseEvent = new PlayerPraiseEvent(this.god, player);
        this.pluginManager.callEvent(praiseEvent);

        if (praiseEvent.isCancelled())
            return false;

        god.increasePlayerReputation(player, 1);

        String message = god.name + " is pleased with your actions.";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

        return true;
    }

    public boolean rewardPlayer(Player player)
    {
        System.out.println("Rewarding player");

        PlayerRewardEvent rewardEvent = new PlayerRewardEvent(this.god, player);
        this.pluginManager.callEvent(rewardEvent);

        if (rewardEvent.isCancelled())
            return false;

        god.increasePlayerReputation(player, 2);

        String message = god.name + " is very displeased with your actions.";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

        return true;
    }
}
