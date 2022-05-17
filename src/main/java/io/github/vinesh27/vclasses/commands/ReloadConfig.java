package io.github.vinesh27.vclasses.commands;

import io.github.vinesh27.vclasses.VClasses;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class ReloadConfig implements CommandExecutor {
    
    private final VClasses vClasses;
    
    public ReloadConfig(VClasses vClasses) {
        this.vClasses = vClasses;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()) {
            File yaml = vClasses.getPortalsFile();
            try {
                vClasses.getConfig().load(yaml);
            } catch (IOException | InvalidConfigurationException e) {
                sender.sendMessage("An error occurred");
                e.printStackTrace();
            }
            sender.sendMessage("§aConfig reloaded!");
        }
        return false;
    }
}
