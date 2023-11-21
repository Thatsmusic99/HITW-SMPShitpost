package io.github.thatsmusic99.hitwshitpost.listeners;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;

public class EntityListener implements Listener {
    @EventHandler
    public void mobDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Blaze blaze) {
            // Mainly used to prevent the spawned mob from dropping blaze rods outside of the nether.
            if (blaze.getWorld().getName().equals(Bukkit.getServer().getWorlds().get(0).getName())) {
                e.getDrops().clear();
            }
        }
    }
    // This event shall act as a forbidden MobGriefing for the Creeper
    @EventHandler
    public void creeperExplosion(EntityExplodeEvent e) {
        if (e.getEntity() instanceof Creeper) {
            HITWShitpost.get().getLogger().info("BOOM!");
            e.blockList().clear();
        }
    }

    // Prevent item frames from being destroyed by creepers
    @EventHandler
    public void itemFrameExplosion(HangingBreakByEntityEvent e) {
        if (e.getEntity() instanceof ItemFrame && e.getCause() == HangingBreakEvent.RemoveCause.EXPLOSION) {
            e.setCancelled(true);
        }
    }
}
