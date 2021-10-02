package fr.xilitra.higurashiuhc.utils;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.item.BaseballBat;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class CustomCraft {

    public static BaseballBat baseballBat = new BaseballBat();


    public static void addRecipeBaseball(){

        ShapedRecipe batte = new ShapedRecipe(baseballBat.getItemStack());

        batte.shape("  &", "*& ", "|* ");
        batte.setIngredient('&', Material.DIAMOND);
        batte.setIngredient('*', Material.IRON_BLOCK);
        batte.setIngredient('|', Material.STICK);

        HigurashiUHC.getInstance().getServer().addRecipe(batte);
    }
}
