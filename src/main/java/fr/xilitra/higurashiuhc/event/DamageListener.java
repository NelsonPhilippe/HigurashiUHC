package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class DamageListener implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();

        if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {

            if (!(e.getDamager() instanceof Player)) return;



            Player damager = (Player) e.getDamager();


            ItemStack item = damager.getItemInHand();

            if (item.isSimilar(CustomCraft.baseballBat.getItemStack())) {

                if(CustomCraft.baseballBat.isUsedOnEpisode()){

                    damager.sendMessage("Vous avez déjà utilisé la batte de baseball pour cet episode.");
                    CustomCraft.baseballBat.setUsedOnEpisode(true);
                    return;
                }

                if(CustomCraft.baseballBat.getUsed() == 0){

                    damager.sendMessage("Vous avez déja utilisé 3 fois la batte de baseball");
                    return;
                }

                if(CustomCraft.baseballBat.getUsed() == 1){
                    damager.getItemInHand().setType(Material.AIR);
                    damager.playSound(damager.getLocation(), Sound.ITEM_BREAK, 1, 1);
                    return;
                }

                for (HPlayer players : HigurashiUHC.getGameManager().getPlayers().values()) {
                    if (players.getPlayer().getLocation().distanceSquared(damager.getLocation()) < 5 * 5) {
                        if (players.getPlayer() != damager) {


                            players.getPlayer().damage(5);
                            e.setDamage(5);
                            CustomCraft.baseballBat.setUsed(CustomCraft.baseballBat.getUsed() - 1);
                        }
                    }
                }
            }

            for(HPlayer player : HigurashiUHC.getGameManager().getPlayers().values()){

                if(!(e.getDamager() instanceof Player)) return;

                if(player.getRole().getClass().equals(Role.RENA_RYUGU.getRole())){

                    RenaRyugu renaRyugu = (RenaRyugu) player.getRole();

                    if(renaRyugu.gethPlayerPense() != null){

                        if(renaRyugu.gethPlayerPense() == HigurashiUHC.getGameManager().getPlayer(p.getLastDamageCause().getEntity().getUniqueId())){

                            if(renaRyugu.isPenseIsUsed()){
                                return;
                            }

                            HPlayer rena = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RENA_RYUGU);

                            rena.getPlayer().sendMessage(p.getName() + " à frappé un joueur.");
                            renaRyugu.setPenseIsUsed(true);

                        }

                    }
                }

            }

            linkMionShionHearth(e);
        }
    }

    private void linkMionShionHearth(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        setLiveMionShion(e, p, hPlayer, Role.MION_SONOZAKI, Role.SHION_SONOSAKI);

        setLiveMionShion(e, p, hPlayer, Role.SHION_SONOSAKI, Role.MION_SONOZAKI);
    }

    private void setLiveMionShion(EntityDamageEvent e, Player p, HPlayer hPlayer, Role shionSonosaki, Role mionSonozaki) {
        if(hPlayer.getRole().getClass().getName().equals(shionSonosaki.getRole().getName())){

            if(p.getHealth() > 20){
                for(HPlayer hPlayers : HigurashiUHC.getGameManager().getPlayers().values()){

                    if(hPlayers.getRole().getClass().equals(mionSonozaki.getRole())){
                        double damage = limitDamege(e.getDamage());
                        hPlayer.getPlayer().damage(damage);
                        break;
                    }

                }
            }
        }

    }

    private double limitDamege(double damage){

        if(damage > 4){
            return 4;
        }

        return damage;
    }

}
