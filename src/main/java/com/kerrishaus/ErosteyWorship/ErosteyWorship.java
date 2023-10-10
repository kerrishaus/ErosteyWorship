package com.kerrishaus.ErosteyWorship;

import com.kerrishaus.ErosteyWorship.gods.God;
import com.kerrishaus.ErosteyWorship.gods.traits.EnvironmentalistTrait;
import com.kerrishaus.ErosteyWorship.gods.traits.Trait;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErosteyWorship extends JavaPlugin
{
    public List<God> gods = new ArrayList<>();

    @Override
    public void onEnable()
    {
        this.saveDefaultConfig();

        //getServer().getPluginManager().registerEvents(this, this);

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

                    List<String> traitNames = this.getConfig().getStringList("gods." + godName + ".traits");

                    List<Trait> traits = new ArrayList<Trait>();

                    for (String trait : traitNames)
                    {
                        switch (trait)
                        {
                            case "Environmentalist":
                            {
                                traits.add(new EnvironmentalistTrait(pluginManager, this));
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

                    getLogger().info("Loaded god " + godName + " with " + traits.size() + " traits.");

                    this.gods.add(new God(godName, traits));
                }
                else
                    getLogger().warning("gods." + godName + " does not exist in config, but it should have.");
            }
        }

        /*
        List<?> savedGods = this.getConfig().getList("gods");

        if (savedGods != null)
        {
            getLogger().info(savedGods.toString());

            PluginManager pm = getServer().getPluginManager();

            for (int i = 0; i < 4; i++)
            {
                Trait trait = new EnvironmentalistTrait(pm, this);

                List<Trait> traits = new ArrayList<>();
                traits.add(trait);

                God newGod = new God(traits);
                gods.add(newGod);
            }
        }
        else
            getLogger().info("No gods saved in config to load.");
         */

        getLogger().info("ErosteyWorship enabled.");
    }

    @Override
    public void onDisable()
    {
        this.saveConfig();

        getLogger().info("ErosteyWorship disabled.");
    }
}
