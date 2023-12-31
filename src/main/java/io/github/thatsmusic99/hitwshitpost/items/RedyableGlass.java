package io.github.thatsmusic99.hitwshitpost.items;

import io.github.thatsmusic99.hitwshitpost.HITWShitpost;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.Objects;

public class RedyableGlass {

    public RedyableGlass() {
        RecipeChoice allGlassBlockTypes = new RecipeChoice.MaterialChoice(
                Material.GLASS,
                Material.WHITE_STAINED_GLASS,
                Material.ORANGE_STAINED_GLASS,
                Material.MAGENTA_STAINED_GLASS,
                Material.LIGHT_BLUE_STAINED_GLASS,
                Material.YELLOW_STAINED_GLASS,
                Material.LIME_STAINED_GLASS,
                Material.PINK_STAINED_GLASS,
                Material.GRAY_STAINED_GLASS,
                Material.LIGHT_GRAY_STAINED_GLASS,
                Material.CYAN_STAINED_GLASS,
                Material.PURPLE_STAINED_GLASS,
                Material.BLUE_STAINED_GLASS,
                Material.BROWN_STAINED_GLASS,
                Material.GREEN_STAINED_GLASS,
                Material.RED_STAINED_GLASS,
                Material.BLACK_STAINED_GLASS
        );

        RecipeChoice allGlassPaneTypes = new RecipeChoice.MaterialChoice(
                Material.GLASS_PANE,
                Material.WHITE_STAINED_GLASS_PANE,
                Material.ORANGE_STAINED_GLASS_PANE,
                Material.MAGENTA_STAINED_GLASS_PANE,
                Material.LIGHT_BLUE_STAINED_GLASS_PANE,
                Material.YELLOW_STAINED_GLASS_PANE,
                Material.LIME_STAINED_GLASS_PANE,
                Material.PINK_STAINED_GLASS_PANE,
                Material.GRAY_STAINED_GLASS_PANE,
                Material.LIGHT_GRAY_STAINED_GLASS_PANE,
                Material.CYAN_STAINED_GLASS_PANE,
                Material.PURPLE_STAINED_GLASS_PANE,
                Material.BLUE_STAINED_GLASS_PANE,
                Material.BROWN_STAINED_GLASS_PANE,
                Material.GREEN_STAINED_GLASS_PANE,
                Material.RED_STAINED_GLASS_PANE,
                Material.BLACK_STAINED_GLASS_PANE
        );

        for (DyeColor dyeColor : DyeColor.values()) {
            Material dyeItem = Material.getMaterial(dyeColor.toString() + "_DYE");
            String blockColor = dyeColor.toString() + "_STAINED_GLASS";
            String paneColor = blockColor + "_PANE";
            NamespacedKey keyBlockColor = new NamespacedKey(HITWShitpost.get(), blockColor);
            NamespacedKey keyPaneColor = new NamespacedKey(HITWShitpost.get(), paneColor);

            ShapelessRecipe blockRecipe = new ShapelessRecipe(keyBlockColor, new ItemStack(Objects.requireNonNull(Material.getMaterial(blockColor))));
            ShapelessRecipe paneRecipe = new ShapelessRecipe(keyPaneColor, new ItemStack(Objects.requireNonNull(Material.getMaterial(paneColor))));

            blockRecipe.addIngredient(allGlassBlockTypes);
            paneRecipe.addIngredient(allGlassPaneTypes);
            if (dyeItem != null) {
                blockRecipe.addIngredient(dyeItem);
                paneRecipe.addIngredient(dyeItem);
            }

            Bukkit.addRecipe(blockRecipe);
            Bukkit.addRecipe(paneRecipe);
        }
    }
}
