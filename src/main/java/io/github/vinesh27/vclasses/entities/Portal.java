package io.github.vinesh27.vclasses.entities;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Portal implements ConfigurationSerializable {
    private final Location startBlock;
    private final Location endBlock;
    private final List<String> commands;
    private final List<ItemStack> items;
    private final List<PotionEffect> effects;
    
    public Portal(
        Location startBlock,
        Location endBlock,
        List<String> commands,
        List<ItemStack> items,
        List<PotionEffect> effects
    ) {
        this.startBlock = startBlock;
        this.endBlock = endBlock;
        this.commands = commands;
        this.items = items;
        this.effects = effects;
    }
    
    public Portal(Map<String, Object> map) {
        this(
            (Location) map.get("startBlock"),
            (Location) map.get("endBlock"),
            (List<String>) map.get("commands"),
            (List<ItemStack>) map.get("items"),
            (List<PotionEffect>) map.get("effects")
        );
    }
    
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("startBlock", startBlock);
        map.put("endBlock", endBlock);
        map.put("commands", commands);
        map.put("items", items);
        map.put("effects", effects);
        return map;
    }
    
    public static Portal deserialize(Map<String, Object> map) {
        Location startBlock = (Location) map.get("startBlock");
        Location endBlock = (Location) map.get("endBlock");
        List<String> commands = (List<String>) map.get("commands");
        List<ItemStack> items = (List<ItemStack>) map.get("items");
        List<PotionEffect> effects = (List<PotionEffect>) map.get("effects");
        return new Portal(startBlock, endBlock, commands, items, effects);
    }

    public Location startBlock() {
        return startBlock;
    }
    
    public Location endBlock() {
        return endBlock;
    }
    
    public List<String> commands() {
        return commands;
    }
    
    public List<ItemStack> items() {
        return items;
    }
    
    public List<PotionEffect> effects() {
        return effects;
    }

    @Override
    public String toString() {
        return "Portal{" +
                "startBlock=" + startBlock +
                ", endBlock=" + endBlock +
                ", commands=" + commands +
                ", items=" + items +
                ", effects=" + effects +
                '}';
    }
}