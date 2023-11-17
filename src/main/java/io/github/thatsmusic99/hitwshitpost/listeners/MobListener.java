package io.github.thatsmusic99.hitwshitpost.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Blaze;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobListener implements Listener {
    @EventHandler
    public void mobDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Blaze blaze) {
            // Mainly used to prevent the spawned mob from dropping blaze rods outside of the nether.
            if (!blaze.getWorld().equals(Bukkit.getWorld("paper_1_20_2_2555859_nether"))) {
                e.getDrops().clear();
            }
        }
    }
}
