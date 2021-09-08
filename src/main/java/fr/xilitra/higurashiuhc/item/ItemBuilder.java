package fr.xilitra.higurashiuhc.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    private String name;
    private boolean enchant;
    private Material material;
    private int size;
    private ItemStack stack;

    public ItemBuilder(String name, boolean enchant, int size, Material material) {
        this.name = name;
        this.enchant = enchant;
        this.size = size;
        this.material = material;
        create();
    }

    private void create(){
        ItemStack item = new ItemStack(material, size);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        item.setItemMeta(meta);

        if(enchant){
            item = addGlow(item);
        }

        this.stack = item;
    }

    public static ItemStack addGlow(ItemStack item){
        ItemMeta meta = item.getItemMeta();

        meta.addEnchant(Enchantment.LURE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        return item;
    }

    public void setLore(String ...lines){
        ItemMeta meta = stack.getItemMeta();

        meta.setLore(Arrays.asList(lines));

        stack.setItemMeta(meta);
    }

    public String getLore(){
        return this.getItemStack().getItemMeta().getLore().get(0);
    }


    public ItemStack getItemStack() {
        return stack;
    }

    public String getName() {
        return name;
    }

    public boolean isEnchant() {
        return enchant;
    }

    public int getSize() {
        return size;
    }
}
