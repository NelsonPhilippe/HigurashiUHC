package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftEvent implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        ItemStack craftedItem = e.getCurrentItem();

        if (craftedItem.isSimilar(CustomCraft.baseballBat.getItemStack())) {
            HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(e.getWhoClicked().getUniqueId());
            if (hPlayer == null)
                return;
            Player player = hPlayer.getPlayer();

            if (player == null || hPlayer.getRole() == null) {
                e.setCancelled(true);
                return;
            }

            if (!hPlayer.getRole().isRole(Role.KEIICHI_MAEBARA, Role.SATOSHI_HOJO)) {
                e.setCancelled(true);
                return;
            }

            if (CustomCraft.baseballBat.isCrafted()) {
                e.setCancelled(true);
                return;
            }

            HPlayer satoshi = Role.SATOSHI_HOJO.getHPlayer();
            HPlayer keiichi = Role.KEIICHI_MAEBARA.getHPlayer();

            if(satoshi != null && satoshi.getPlayer() != null)
                satoshi.getPlayer().playSound(satoshi.getPlayer().getLocation(), Sound.ZOMBIE_WOODBREAK, 1, 1);

            if(keiichi != null && keiichi.getPlayer() != null)
                keiichi.getPlayer().playSound(keiichi.getPlayer().getLocation(), Sound.ZOMBIE_WOODBREAK, 1, 1);

        }
    }
}
