package io.github.thatsmusic99.hitwshitpost.listeners;


import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import io.github.thatsmusic99.hitwshitpost.lists.DeathMessages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;
import java.util.Random;


public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        EntityDamageEvent.DamageCause cause = Objects.requireNonNull(e.getPlayer().getLastDamageCause()).getCause();
        HITWShitpost.get().getLogger().info(Objects.requireNonNull(e.getPlayer().getLastDamageCause().getCause().name()));

        Random rand = new Random();

        switch (cause) {
            case LAVA: {
                String message = DeathMessages.LAVA.get(rand.nextInt(DeathMessages.LAVA.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
                break;
            }
            case FALL: {
                String message = DeathMessages.FALL.get(rand.nextInt(DeathMessages.FALL.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
                break;
            }
            case ENTITY_ATTACK: {
                if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent event) {
                    String message = DeathMessages.ENTITY_ATTACK.get(rand.nextInt(DeathMessages.ENTITY_ATTACK.size()));
                    e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName()).replace("{mob}", event.getDamager().getName())));

                }
                break;
            }
            case PROJECTILE: {
                Event killer = player.getLastDamageCause();

                if (player.getLastDamageCause() instanceof EntityDamageByEntityEvent event && event.getDamager() instanceof Arrow arrow && arrow.getShooter() != null && arrow.getShooter() instanceof Entity entity) {
                    String message = DeathMessages.PROJECTILE.get(rand.nextInt(DeathMessages.SHOT.size()));
                    e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName()).replace("{mob}", entity.getName())));
                    break;
                }
                String message = DeathMessages.PROJECTILE.get(rand.nextInt(DeathMessages.PROJECTILE.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
                break;
            }
            case VOID: {
                String message = DeathMessages.VOID.get(rand.nextInt(DeathMessages.VOID.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
                break;
            }
            case POISON: {
                String message = DeathMessages.POISON.get(rand.nextInt(DeathMessages.POISON.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
                break;
            }
            case FIRE: {
                String message = DeathMessages.FIRE.get(rand.nextInt(DeathMessages.FIRE.size()));
                e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player}", player.getName())));
                break;
            }
        }
    }
}
