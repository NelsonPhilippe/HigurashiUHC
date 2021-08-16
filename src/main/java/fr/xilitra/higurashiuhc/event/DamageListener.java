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

            HPlayer player = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RENA_RYUGU);

            if(player != null){

                RenaRyugu renaRyugu = (RenaRyugu) player.getRole();

                if(renaRyugu.gethPlayerPense() != null){

                    System.out.println(renaRyugu.gethPlayerPense().getUuid().toString());


                    if(renaRyugu.gethPlayerPense().getUuid().equals(damager.getUniqueId())){


                        if(renaRyugu.isPenseIsUsed()){
                            return;
                        }


                        player.getPlayer().sendMessage(p.getName() + " à frappé un joueur.");
                        renaRyugu.setPenseIsUsed(true);

                    }

                }
            }



        }
    }

    @EventHandler
    public void linkMionShionHearth(EntityDamageEvent e){

        if(!(e.getEntity() instanceof Player)) return;


        Player p = (Player) e.getEntity();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        double damage = limitDamege(e.getDamage());

        if(p.getHealth() <= 20) return;

        if(hPlayer.getRole().getName().equalsIgnoreCase("Mion Sonozaki")){


            HPlayer shionPlayer = HigurashiUHC.getGameManager().getPlayerWithRole(Role.SHION_SONOSAKI);

            if(shionPlayer == null) return;

            if(shionPlayer.getPlayer().getHealth() <= 20) return;

            shionPlayer.getPlayer().damage(damage);

        }

        if(hPlayer.getRole().getName().equalsIgnoreCase("Shion Sonozaki")){
            HPlayer mionPlayer = HigurashiUHC.getGameManager().getPlayerWithRole(Role.MION_SONOZAKI);

            if(mionPlayer == null) return;

            if(mionPlayer.getPlayer().getHealth() <= 20) return;


            mionPlayer.getPlayer().damage(damage);
        }
    }

    private void setLiveMionShion(EntityDamageByEntityEvent e, Player p, HPlayer hPlayer, String role1, String role2) {
        if(hPlayer.getRole().getName().equalsIgnoreCase(role1)){

            if(p.getHealth() > 20){
                for(HPlayer hPlayers : HigurashiUHC.getGameManager().getPlayers().values()){

                    if(hPlayers.getRole().getName().equalsIgnoreCase(role2)){
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
