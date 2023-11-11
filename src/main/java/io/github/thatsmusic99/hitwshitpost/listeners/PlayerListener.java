package io.github.thatsmusic99.hitwshitpost.listeners;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {
    int sec = 20;
    PotionEffect jumpBoost = new PotionEffect(PotionEffectType.JUMP, PotionEffect.INFINITE_DURATION, 1, false, false);
    PotionEffect springy = new PotionEffect(PotionEffectType.JUMP, (sec * 8), 3, false, false);
    PotionEffect speedBoost = new PotionEffect(PotionEffectType.SPEED, (sec * 8), 4, false, false);
    PotionEffect legDay = new PotionEffect(PotionEffectType.SLOW, (sec * 10), 2, false, false);

    PotionEffect gravity = new PotionEffect(PotionEffectType.SLOW_FALLING, (sec * 15), 1, false, false);

    // Speedboost Trap
    public void applySpeed() {
        for (Player p : Bukkit.getOnlinePlayers())  {
            p.addPotionEffect(speedBoost);
        }
    }

    public void applySlow() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(legDay);
        }
    }

    public void applySpringy() {
        for (Player p : Bukkit.getOnlinePlayers())  {
            p.removePotionEffect(PotionEffectType.JUMP);
            p.addPotionEffect(springy);

            Bukkit.getScheduler().runTaskLater(HITWShitpost.get(), new Runnable() {
                @Override
                public void run() {
                    p.addPotionEffect(jumpBoost);
                }
            }, 400L);
        }
    }

    public void applyGravity() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.addPotionEffect(gravity);
        }
    }

    // Join Event
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        jumpBoost.apply(player);

        Bukkit.getScheduler().runTaskLater(HITWShitpost.get(), new Runnable() {
            @Override
            public void run() {
                applyGravity();
            }
        }, 100L);
    }
    // Quit Event - making sure to remove the jump boost from the player.
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (player.hasPotionEffect(PotionEffectType.JUMP)) {
            player.removePotionEffect(PotionEffectType.JUMP);
        }
    }
    // Event for when the player dies or drinks milk, that the jump boost gets re-applied.
    @EventHandler
    public void playerDrinkEvent(EntityPotionEffectEvent e) {
        if (e.getEntity() instanceof Player player) {
            if (e.getCause().equals(EntityPotionEffectEvent.Cause.MILK) || e.getCause().equals(EntityPotionEffectEvent.Cause.DEATH)) {
                Bukkit.getScheduler().runTaskLater(HITWShitpost.get(), new Runnable() {
                    @Override
                    public void run() {
                        jumpBoost.apply(player);
                    }
                    }, 5L);
            }
        }
    }
}
