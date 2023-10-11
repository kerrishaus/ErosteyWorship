// TODO: good boy points for growing plants with bonemeal
// TODO: good boy points for growing animals with their item

package com.kerrishaus.ErosteyWorship.gods.traits;

import com.kerrishaus.ErosteyWorship.events.PlayerPraiseEvent;
import com.kerrishaus.ErosteyWorship.events.PlayerPunishEvent;
import com.kerrishaus.ErosteyWorship.gods.God;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EnvironmentalistTrait extends Trait
{
    public String name = "Environmentalist";

    public EnvironmentalistTrait(God god, PluginManager pluginManager, Plugin plugin)
    {
        super(god, pluginManager, plugin);
    }

    // TODO: track time since last kill, and reduce reputation by a multiplier
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event)
    {
        // this excludes players from the event
        // PlayerDeathEvent is handled separately
        if (!(event.getEntity() instanceof Mob deadEntity))
            return;

        // make sure the entity was killed by a player (null if not)
        if (deadEntity.getKiller() == null)
            return;

        LivingEntity deadEntityTarget = deadEntity.getTarget();

        if (deadEntityTarget != null)
        {
            // if the dead mob was targeting a mob that is not a monster,
            // or if the dead mob was targeting a player, then it's ok
            if (!(deadEntityTarget instanceof Monster) ||
                  deadEntityTarget instanceof Player)
            {
                System.out.println("We're okay with this mob dying. " + deadEntity.getType().toString());
                return;
            }
        }

        // if we got this far, then we are mad at the player for killing someone innocent.

        Player killer = event.getEntity().getKiller();

        // killer won't be null because it's checked higher above
        if (god.getPlayerReputation(killer) > -5)
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
        if (!(event.getBreeder() instanceof Player player))
            return;

        if (god.getPlayerReputation(player) > 5)
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
