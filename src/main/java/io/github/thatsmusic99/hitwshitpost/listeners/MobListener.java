package io.github.thatsmusic99.hitwshitpost.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobListener implements Listener {
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
    public void creeperExplosion(EntityExplodeEvent e) {
        if (e.getEntity() instanceof Creeper) {
            e.blockList().clear();
        };
    }
}
