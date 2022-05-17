package io.github.vinesh27.vclasses.util;

import io.github.vinesh27.vclasses.entities.Portal;
import io.github.vinesh27.vclasses.VClasses;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.BoundingBox;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public record Util(VClasses vClasses) {
    //Giving rewards to the player
    public void giveRewards(Portal portal, Player player) {
        for(String commands : portal.commands()) {
            String cmd = commands.replace("%p%", player.getName());
            System.out.println(cmd);
            vClasses.getServer()
                .dispatchCommand(
                    vClasses.getServer().getConsoleSender(), cmd);
        }
        for(ItemStack itemStack : portal.items()) {
            player.getInventory().addItem(itemStack);
        }
        for(PotionEffect potionEffect : portal.effects()) {
            player.addPotionEffect(potionEffect);
        }
    }

    public Portal getPortal(Player player) {
        for (Portal portal : vClasses.getPortals()) {
            if(BoundingBox.of(portal.startBlock(), portal.endBlock().add(1,1,1))
                    .contains(player.getLocation().toVector())) {
                return portal;
            }
        }
        return null;
    }

//
//    //Check if the player is in a portal and get which one is it
//    public Portal getPortal(Location loc) {
//        for (Portal portal : vClasses.getPortals()) {
//            Set<Block> portalBlocks = findPortal(portal.startBlock(), portal.endBlock());
//            System.out.println(Arrays.stream(portalBlocks.toArray()).toList());
//            if(portalBlocks.contains(loc.getBlock())) return portal;
//        }
//        return null;
//    }
//
//    public Set<Block> findPortal(Location start, Location end) {
//        Set<Block> portal = new HashSet<>();
//        World world = start.getWorld();
//
//        for (int x = start.getBlockX();
//             x == end.getBlockX();
//             x += (start.getBlockX() < end.getBlockX())? 1: -1) {
//
//            for (int y = start.getBlockY();
//                 y == end.getBlockY();
//                 y += (start.getBlockY() < end.getBlockY())? 1: -1) {
//
//                for (int z = start.getBlockZ();
//                     z == end.getBlockZ();
//                     z += (start.getBlockZ() < end.getBlockZ())? 1: -1) {
//                    Block block = world.getBlockAt(x, y, z);
//                    if (block.getType() == Material.NETHER_PORTAL)
//                        portal.add(block);
//                }
//            }
//        }
//        return portal;
//    }

}
