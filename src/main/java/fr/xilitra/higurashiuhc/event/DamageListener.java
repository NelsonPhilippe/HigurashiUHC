package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class DamageListener implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();

        if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (!(p.getLastDamageCause().getEntity() instanceof Player)) return;

            Player damager = (Player) p.getLastDamageCause().getEntity();

            ItemStack item = damager.getItemInHand();

            if (item.isSimilar(CustomCraft.baseballBat.getItemStack())) {
                for (HPlayer players : HigurashiUHC.getGameManager().getPlayers().values()) {
                    if (players.getPlayer().getLocation().distanceSquared(damager.getLocation()) < 5 * 5) {
                        if (players.getPlayer() != damager) {

                            players.getPlayer().setHealth(players.getPlayer().getHealth() - 4);

                        }
                    }
                }
            }
        }
    }

}
