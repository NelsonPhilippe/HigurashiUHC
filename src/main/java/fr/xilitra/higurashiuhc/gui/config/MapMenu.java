package fr.xilitra.higurashiuhc.gui.config;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.gui.GuiMenu;
import fr.xilitra.higurashiuhc.item.config.VillageItem;
import fr.xilitra.higurashiuhc.utils.VillageGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MapMenu extends GuiMenu implements Listener {

    public static MapMenu mapMenu = new MapMenu();

    public MapMenu() {
        super("Map Config", 9);
        this.addItem(1, VillageItem.villageItem.getItemStack());
        this.create();
    }


    @EventHandler
    public void onClickInventory(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if(!p.isOp()) return;

        ItemStack itemStack = e.getCurrentItem();

        if(itemStack.getType() == Material.AIR || !itemStack.getItemMeta().hasLore()) return;

        if(itemStack.getItemMeta().getLore().get(0).equalsIgnoreCase(VillageItem.villageItem.getLore())){
            p.closeInventory();
            p.sendMessage("Vous venez d'ajouté un village sur la map");

            int xy_max = HigurashiUHC.getInstance().getConfig().getInt("worldborder");
            int xy_min = HigurashiUHC.getInstance().getConfig().getInt("worldborder-min");
            int range = xy_max - xy_min + 1;

            int x = (int)(Math.random() * range) + xy_min;
            int z = (int)(Math.random() * range) + xy_min;
            int size = (int) (Math.random() * (150 - 50) + 1) + 50;
            int randomBiome = new Random().nextInt(3);

            System.out.println("Le village se trouve en : (" + x + "," + z + ")");

            //VillageGenerator.generate(p.getWorld(), x, z, size, randomBiome);
        }

    }
}
