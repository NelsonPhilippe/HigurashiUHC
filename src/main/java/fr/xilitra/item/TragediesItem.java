package fr.xilitra.item;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TragediesItem {

    private String name;
    private boolean enchant;
    private Material material;
    private int size;
    private ItemStack stack;

    public TragediesItem(String name, boolean enchant, int size, Material material) {
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
            addGlow(item);
        }
    }

    public static ItemStack addGlow(ItemStack item){
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) tag = nmsStack.getTag();
        NBTTagList ench = new NBTTagList();
        tag.set("ench", ench);
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
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
