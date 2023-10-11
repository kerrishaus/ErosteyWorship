package com.kerrishaus.ErosteyWorship;

import com.kerrishaus.ErosteyWorship.commands.GodCommand;
import com.kerrishaus.ErosteyWorship.commands.GodTabCompleter;
import com.kerrishaus.ErosteyWorship.gods.God;
import com.kerrishaus.ErosteyWorship.gods.traits.EnvironmentalistTrait;
import com.kerrishaus.ErosteyWorship.gods.traits.Trait;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ErosteyWorship extends JavaPlugin
{
    public List<God> gods = new ArrayList<>();

    @Override
    public void onEnable()
    {
        this.saveDefaultConfig();

        PluginManager pluginManager = this.getServer().getPluginManager();

        ConfigurationSection gods = this.getConfig().getConfigurationSection("gods");

        if (gods != null)
        {
            // there's probably a way to get the value and the key
            for (String godName : gods.getKeys(false))
            {
                ConfigurationSection god = this.getConfig().getConfigurationSection("gods." + godName);

                if (god != null)
                {
                    getLogger().info("Loading god " + godName + ".");

                    God newGod = new God(godName);

                    List<String> traitNames = this.getConfig().getStringList("gods." + godName + ".traits");

                    for (String trait : traitNames)
                    {
                        switch (trait)
                        {
                            case "Environmentalist":
                            {
                                newGod.traits.add(new EnvironmentalistTrait(newGod, pluginManager, this));
                                break;
                            }
                            default:
                            {
                                getLogger().warning("Trait " + trait + " was specified for " + godName + " but that trait does not exist.");

                                // I use continue instead of break here because
                                // I want to log the creation of a new trait
                                // in every case except the default case
                                // (because it couldn't find the trait)
                                continue;
                            }
                        }

                        getLogger().info("Trait" + trait + " assigned to god " + godName + ".");
                    }

                    getLogger().info("Loaded god " + godName + " with " + newGod.traits.size() + " traits.");

                    this.gods.add(newGod);
                }
                else
                    getLogger().warning("gods." + godName + " does not exist in config, but it should have.");
            }
        }

        this.getCommand("god").setExecutor(new GodCommand());
        this.getCommand("god").setTabCompleter(new GodTabCompleter());
        this.getCommand("god").setPermission("erostey.god");
        this.getCommand("god").setPermissionMessage("You do not have permission.");

        getLogger().info("ErosteyWorship enabled.");
    }

    @Override
    public void onDisable()
    {
        this.saveConfig();

        getLogger().info("ErosteyWorship disabled.");
    }
}
