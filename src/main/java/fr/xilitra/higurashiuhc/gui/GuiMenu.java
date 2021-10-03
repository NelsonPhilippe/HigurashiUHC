package fr.xilitra.higurashiuhc.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class GuiMenu implements InventoryHolder {

    private final String name;
    private final int size;
    private final Map<Integer, ItemStack> items = new HashMap<>();
    private Inventory inventory;

    public GuiMenu(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void create() {

        this.inventory = Bukkit.createInventory(this, this.size, this.name);

        for (Map.Entry<Integer, ItemStack> item : items.entrySet()) {
            inventory.setItem(item.getKey(), item.getValue());
        }
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Map<Integer, ItemStack> getItems() {
        return items;
    }

    public void addItem(int slot, ItemStack stack) {
        this.items.put(slot, stack);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
