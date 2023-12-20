package io.github.thatsmusic99.hitwshitpost.items;


import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RedyableTerracotta {
    public RedyableTerracotta() {
        RecipeChoice allTerraTypes = new RecipeChoice.MaterialChoice( //Yep, a list of all terracottas
                Material.TERRACOTTA,
                Material.WHITE_TERRACOTTA,
                Material.ORANGE_TERRACOTTA,
                Material.MAGENTA_TERRACOTTA,
                Material.LIGHT_BLUE_TERRACOTTA,
                Material.YELLOW_TERRACOTTA,
                Material.LIME_TERRACOTTA,
                Material.PINK_TERRACOTTA,
                Material.GRAY_TERRACOTTA,
                Material.LIGHT_GRAY_TERRACOTTA,
                Material.CYAN_TERRACOTTA,
                Material.PURPLE_TERRACOTTA,
                Material.BLUE_TERRACOTTA,
                Material.BROWN_TERRACOTTA,
                Material.GREEN_TERRACOTTA,
                Material.RED_TERRACOTTA,
                Material.BLACK_TERRACOTTA
        );
        for (DyeColor dyeColor : DyeColor.values()) {
            Material dyeItem = Material.getMaterial(dyeColor.toString() + "_DYE");
            String color = dyeColor.toString() + "_TERRACOTTA";
            HITWShitpost.get().getLogger().info(color);
            NamespacedKey theColor = new NamespacedKey(HITWShitpost.get(), color);
            ShapelessRecipe theRecipe = new ShapelessRecipe(theColor, new ItemStack(Objects.requireNonNull(Material.getMaterial(color))));

            theRecipe.addIngredient(allTerraTypes);
            if (dyeItem != null) {
                theRecipe.addIngredient(dyeItem);
            }

            Bukkit.addRecipe(theRecipe);
        }
    }
}
