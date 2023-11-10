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
    PotionEffect jumpBoost = new PotionEffect(PotionEffectType.JUMP, PotionEffect.INFINITE_DURATION, 1, false, false);

    // Join Event
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        jumpBoost.apply(player);
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
                    }, 10L);
            }
        }
    }
}
