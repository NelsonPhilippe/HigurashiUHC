package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.item.StartGameItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(p.isOp()){
            ItemStack item = e.getItem();
            String lore = item.getItemMeta().getLore().get(0);

            if(lore.equals(StartGameItem.startGameItem.getLore())){
                HigurashiUHC.getGameManager().start();
                e.setCancelled(true);
            }

        }

    }

}
