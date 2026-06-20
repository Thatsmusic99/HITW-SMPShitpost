package io.github.thatsmusic99.hitwshitpost.listeners;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;

import java.util.Objects;

public class EntityListener implements Listener {
    @EventHandler
    public void mobDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Blaze blaze) {
            // Mainly used to prevent the spawned mob from dropping blaze rods outside of the nether.
            if (blaze.getWorld().getName().equals(Bukkit.getServer().getWorlds().getFirst().getName())) {
                e.getDrops().clear();
            }
        }
    }
    // This event shall act as a forbidden MobGriefing for mobs
    @EventHandler
    public void creeperExplosion(EntityExplodeEvent e) {
        if (e.getEntity() instanceof Creeper) {
            e.blockList().clear();
        }
        // Make chasts not be able to destroy blocks with their explosives.
        if (e.getEntity() instanceof Fireball) {
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

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Snowball snowball && e.getHitEntity() instanceof Player player) {
            player.knockback(0.5, snowball.getLocation().getDirection().getX(), snowball.getLocation().getDirection().getZ());
            player.damage(0.1);
        }
    }

    @EventHandler
    public void fireCreation(BlockIgniteEvent e) {
        if (e.getCause() == BlockIgniteEvent.IgniteCause.FIREBALL) {
            e.setCancelled(true);
        }
    }

    public void noSplitting(SlimeSplitEvent e) {
        if (Objects.requireNonNull(e.getEntity().getAttribute(Attribute.ATTACK_DAMAGE)).getBaseValue() == 0) {
            e.setCancelled(true);
        }
    }
}
