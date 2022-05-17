package io.github.vinesh27.vclasses.events;

import io.github.vinesh27.vclasses.entities.Portal;
import io.github.vinesh27.vclasses.VClasses;
import io.github.vinesh27.vclasses.util.Util;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerBlockEnterEvent implements Listener {
    private final Util util;

    public PlayerBlockEnterEvent(VClasses vClasses) {
        this.util = new Util(vClasses);
    }
    
    @EventHandler
    public void onPlayerEnterPortal(PlayerPortalEvent event) {
//        Portal portal = util.getPortal(event.getFrom());
        Portal portal = util.getPortal(event.getPlayer());
        if(portal == null) {
            System.out.println("Portal is null");
            return;
        }
        event.setCancelled(true);
        util.giveRewards(portal, event.getPlayer());
    }
}
