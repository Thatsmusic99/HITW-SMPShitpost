package io.github.thatsmusic99.hitwshitpost.listeners;

import io.github.thatsmusic99.hitwshitpost.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.util.Vector;

public class CustomItemEvents implements Listener {
    @EventHandler
    public void onConsumption (PlayerItemConsumeEvent e) {
        if (e.getItem().getType() == Material.BAKED_POTATO && ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName()).equals("Hot Potato") && e.getItem().getItemMeta().hasEnchant(Enchantment.ARROW_DAMAGE)) {
            Player player = e.getPlayer();
            player.spawnParticle(Particle.EXPLOSION_NORMAL, player.getLocation(), 10, 3, 3, 3);
            player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_GENERIC_EXPLODE, 2.0f, 1.0f);
            player.setVelocity(new Vector(0, Config.config.getDouble("hotpotato.launchpower"), 0));
        }
    }
}
