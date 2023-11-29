package io.github.thatsmusic99.hitwshitpost.items;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class HotPotato {
    public HotPotato() {
        ItemStack hotPotato = new ItemStack(Material.BAKED_POTATO);
        ItemMeta meta = hotPotato.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lHot &c&lPotato"));
        hotPotato.setItemMeta(meta);

        hotPotato.setAmount(4);
        hotPotato.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);

        NamespacedKey key = new NamespacedKey(HITWShitpost.get(), "hot_potato");
        ShapedRecipe recipe = new ShapedRecipe(key, hotPotato);

        recipe.shape(
                "ABA",
                "BGB",
                "ABA"
        );

        // A = Air, B = Baked Potato, G = Gunpowder
        recipe.setIngredient('A', Material.AIR);
        recipe.setIngredient('B', Material.BAKED_POTATO);
        recipe.setIngredient('G', Material.GUNPOWDER);

        Bukkit.addRecipe(recipe);
    }


}
