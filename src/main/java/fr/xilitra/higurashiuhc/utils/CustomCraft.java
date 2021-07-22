package fr.xilitra.higurashiuhc.utils;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class CustomCraft {

    public static void addRecipeBaseball(){
        ItemBuilder builder = new ItemBuilder("Batte de Baseball", true, 1, Material.WOOD_SWORD);

        ShapedRecipe batte = new ShapedRecipe(builder.getItemStack());

        batte.shape("  &", "*& ", "|* ");
        batte.setIngredient('&', Material.DIAMOND);
        batte.setIngredient('*', Material.IRON_BLOCK);
        batte.setIngredient('|', Material.STICK);

        HigurashiUHC.getInstance().getServer().addRecipe(batte);
    }
}
