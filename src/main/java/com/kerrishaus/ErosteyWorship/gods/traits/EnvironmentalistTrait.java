package com.kerrishaus.ErosteyWorship.gods.traits;

import com.kerrishaus.ErosteyWorship.events.PlayerPraiseEvent;
import com.kerrishaus.ErosteyWorship.events.PlayerPunishEvent;
import com.kerrishaus.ErosteyWorship.gods.God;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EnvironmentalistTrait extends Trait
{
    public EnvironmentalistTrait(God god, PluginManager pluginManager, Plugin plugin)
    {
        super(god, pluginManager, plugin);
    }

    // TODO: track time since last kill, and reduce reputation by a multiplier
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event)
    {
        if (!(event.getEntity() instanceof Mob))
            return;

        // if it's null then it wasn't killed by a player
        if (event.getEntity().getKiller() == null)
            return;

        // cast it to Mob so we can get its target
        Mob deadEntity = (Mob) event.getEntity();

        // if the dead entity's target was a peaceful entity or a player
        // then it's okay that we killed it
        if (!(deadEntity.getTarget() instanceof Monster) ||
            deadEntity.getTarget() instanceof Player)
            return;

        // if we got this far, then we are mad at the player for killing someone innocent.

        Player killer = event.getEntity().getKiller();

        // killer won't be null becasue it's checked higher above
        int rep = god.getPlayerReputation(killer);

        if (rep > -5)
            this.warnPlayer(killer);
        else
            this.punishPlayer(killer);
    }

    /*
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        // Only want ageable blocks.
        if (!(event.getClickedBlock() instanceof Ageable))
            return;

    }
    */

    @EventHandler
    public void onEntityBreedEvent(EntityBreedEvent event)
    {
        // only want when players breed animals
        if (!(event.getBreeder() instanceof Player))
            return;

        Player player = (Player) event.getBreeder();

        int rep = god.getPlayerReputation(player);

        if (rep > 5)
            rewardPlayer(player);
        else
            praisePlayer(player);
    }

    /*
    @EventHandler
    public void onBlockGrowEvent(BlockGrowEvent event)
    {

    }
     */

    @Override
    public boolean punishPlayer(Player player)
    {
        if (super.punishPlayer(player))
        {
            player.getWorld().strikeLightning(player.getLocation());
            return true;
        }

        return false;
    }
}
