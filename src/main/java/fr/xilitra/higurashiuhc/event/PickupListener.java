package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PickupListener implements Listener {

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        ItemStack item = e.getItem().getItemStack();

        if(!item.getItemMeta().hasLore()) return;

        if(item.getItemMeta().getLore().get(0).equalsIgnoreCase(CustomCraft.baseballBat.getLore())){
            if(!hPlayer.getRole().getClass().getName().equals(Role.KEIICHI_MAEBARA.getRole().getName()) || !hPlayer.getRole().getClass().getName().equals(Role.SATOSHI_HOJO.getRole().getName())){
                e.setCancelled(true);
            }
        }

    }

}
