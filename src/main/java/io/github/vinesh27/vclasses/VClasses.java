package io.github.vinesh27.vclasses;

import io.github.vinesh27.vclasses.commands.ReloadConfig;
import io.github.vinesh27.vclasses.entities.Portal;
import io.github.vinesh27.vclasses.events.PlayerBlockEnterEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class VClasses extends JavaPlugin {
    private YamlConfiguration config;
    private final List<Portal> portals = new ArrayList<>();
    private File portalsFile;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        portalsFile = new File(getDataFolder(), "portals.yml");
        ConfigurationSerialization.registerClass(Portal.class, "Portal");
        if (!portalsFile.exists()) {
            config = new YamlConfiguration();
            List<Portal> portalList = List.of(createDefaultPortal(), createDefaultPortal());
            config.set("portals", portalList);
            try {
                config.save(portalsFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            config = YamlConfiguration.loadConfiguration(portalsFile);
        }
        this.getCommand("vreload").setExecutor(new ReloadConfig(this));
        this.getServer().getPluginManager().registerEvents(new PlayerBlockEnterEvent(this), this);
        loadPortals();
    }
    
    public YamlConfiguration getConfig() {
        return config;
    }
    public List<Portal> getPortals() { return portals; }
    public File getPortalsFile() { return portalsFile; }
    private Portal createDefaultPortal() {
        Location start = Bukkit.getWorlds().get(0).getSpawnLocation();
        Location end = Bukkit.getWorlds().get(0).getSpawnLocation();
        List<String> commands = List.of("/spawn %p%", "/help");
        List<ItemStack> items = List.of(new ItemStack(Material.STONE, 32), new ItemStack(Material.COBBLESTONE, 32));
        List<PotionEffect> effects = List.of(new PotionEffect(PotionEffectType.ABSORPTION, 500, 1));
        return new Portal(start, end, commands, items, effects);
    }
    
    private void loadPortals() {
        portals.addAll((List<Portal>) config.getList("portals"));
    }
}
