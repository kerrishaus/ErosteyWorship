package com.kerrishaus.ErosteyWorship.commands;

import com.kerrishaus.ErosteyWorship.ErosteyWorship;
import com.kerrishaus.ErosteyWorship.gods.God;
import com.kerrishaus.ErosteyWorship.gods.traits.Trait;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor
{
    ErosteyWorship plugin;

    public GodCommand(ErosteyWorship plugin)
    {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments)
    {
        // TODO: find out why sender should be an instance of player
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Sender must be an instance of player for some reason.");
            return false;
        }

        if (arguments.length > 1)
        {
            if (arguments.length > 2)
            {
                sender.sendMessage("3 arguments");
            }
            else // arguments.length == 2,
            {
                sender.sendMessage("2 arguments");
            }
        }
        else if (arguments.length == 1 && arguments[0].equals("list"))
        {
            sender.sendMessage("There are " + plugin.gods.size() + " gods.");

            for (God god : plugin.gods)
            {
                sender.sendMessage("- " + god.name);

                for (Trait trait : god.traits)
                {
                    sender.sendMessage("  - " + trait.name);
                }
            }
        }
        else
        {
            sender.sendMessage("not enough arguments");
            return false;
        }

        return true;
    }
}
