package fr.xilitra.higurashiuhc.config.inv;

import fr.redline.invinteract.inv.InventoryCreator;
import fr.redline.invinteract.inv.container.Container;
import fr.redline.invinteract.inv.holder.InventoryInfoHolder;
import fr.redline.invinteract.inv.page.Page;
import fr.redline.invinteract.item.PageOpenerItem;
import fr.xilitra.higurashiuhc.config.inv.item.ScenItem;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class InvCreator extends InventoryCreator {

    Container accueil;
    Container scenario;

    public InvCreator() {
        super("Config", 4);
        Optional<Container> acc = createContainer("Accueil");
        if(!acc.isPresent())
            return;
        accueil = acc.get();
        Optional<Container> scen = createContainer("Scenario");
        if(!scen.isPresent())
            return;
        scenario = scen.get();
        loadScenarioPage();
        loadAccueilPage();
    }

    public void loadAccueilPage(){
        Optional<Page> accPageOpt = accueil.getPage(1);
        if(!accPageOpt.isPresent())
            return;
        Page accPage = accPageOpt.get();

        Optional<Page> accueilPage = scenario.getPage(1);
        if(!accueilPage.isPresent())
            return;

        ItemStack retour = new ItemStack(Material.DIRT);
        ItemMeta retourMeta = retour.getItemMeta();
        retourMeta.setDisplayName("Scenario");
        retour.setItemMeta(retourMeta);
        accPage.addItem(new PageOpenerItem(accueilPage.get(), retour));
    }

    public void loadScenarioPage(){
        Optional<Page> pageOptional = scenario.getPage(1);
        if(!pageOptional.isPresent())
            return;
        Page page = pageOptional.get();
        for (ScenarioList value : ScenarioList.values()) {
            page.addItem(new ScenItem(value));
        }

        scenario.setFooter(true);
        Optional<Page> footer = scenario.getFooter();
        if(!footer.isPresent())
            return;
        Optional<Page> accueilPage = accueil.getPage(1);
        if(!accueilPage.isPresent())
            return;
        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourMeta = retour.getItemMeta();
        retourMeta.setDisplayName("Retour");
        retour.setItemMeta(retourMeta);
        footer.get().addItem(new PageOpenerItem(accueilPage.get(), retour));
    }

    public void open(Player player){
        Optional<Page> page = accueil.getPage(1);
        if(!page.isPresent())
            return;
        player.openInventory(createInventory(player).openPage(page.get()).getInventory());
    }

    @Override
    public void onClickInventory(InventoryInfoHolder inventoryInfoHolder, InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);
    }

    @Override
    public void onCloseInventory(Player player, InventoryInfoHolder inventoryInfoHolder) {

    }

    @Override
    public void onClickUnderInventory(InventoryInfoHolder inventoryInfoHolder, InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);
    }

    @Override
    public void onDrag(InventoryDragEvent inventoryDragEvent) {
        inventoryDragEvent.setCancelled(true);
    }
}
