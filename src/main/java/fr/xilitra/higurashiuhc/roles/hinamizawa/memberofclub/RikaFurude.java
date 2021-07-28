package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Clans;
import fr.xilitra.higurashiuhc.game.clans.Mercenaire;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Hinamizawa;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.HideNametag;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class RikaFurude extends RoleTemplate implements Listener {

    private int lives;
    private boolean ressucite = false;

    public RikaFurude() {
        super("Rika Furude", Gender.FEMME);
        this.lives = 3;
        this.clan = HigurashiUHC.getGameManager().getHinamizawa();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player player = e.getEntity();

        Player killer = null;
        HPlayer killerHPlayer = null;

        if(e.getEntity().getKiller() != null){
            killer = e.getEntity().getKiller();
            killerHPlayer = HigurashiUHC.getGameManager().getPlayer(killer.getUniqueId());
        }

        HPlayer hp = HigurashiUHC.getGameManager().getPlayer(player.getUniqueId());

        if(killerHPlayer != null){
            if(killerHPlayer.getRole().getClan() == HigurashiUHC.getGameManager().getMercenaire()){
                player.setGameMode(GameMode.SPECTATOR);
                HigurashiUHC.getGameManager().startRikaDeathTask();
                for(HPlayer miyo : HigurashiUHC.getGameManager().getPlayers().values()){
                    if(miyo.getRole().getClass().equals(Role.MIYO_TAKANO.getRole())){
                        Bukkit.broadcastMessage("Miyo Takano est " + miyo.getName());
                    }
                }
                return;
            }
        }

        for(HPlayer players : HigurashiUHC.getGameManager().getPlayers().values()){
            if(players.getRole().getClass().equals(Role.HANYU.getRole())){
                if(players.getPlayer().getGameMode() == GameMode.SPECTATOR){
                    player.setGameMode(GameMode.SPECTATOR);
                    HigurashiUHC.getGameManager().startRikaDeathTask();
                    return;
                }
            }
        }

        if(hp.getRole().getClass().equals(Role.RIKA_FURUDE.getRole())){
            RikaFurude rikaFurude = (RikaFurude) hp.getRole();

            rikaFurude.remove1Live();

            if(rikaFurude.getLives() == 2){
                player.setHealth(5);
                player.setMaxHealth(5);
                //player.teleport();
            }

           if(rikaFurude.getLives() <= 1){

               for (ItemStack itemStack : player.getInventory().getContents()) {
                   player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                   player.getInventory().removeItem(itemStack);
               }
               for (ItemStack itemStack : player.getInventory().getArmorContents()) {
                   player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                   player.getInventory().removeItem(itemStack);
               }

               player.setGameMode(GameMode.SPECTATOR);
               HigurashiUHC.getGameManager().startRikaDeathTask();

               for(HPlayer players : HigurashiUHC.getGameManager().getPlayers().values()) {

                   HideNametag.unhide(player, players.getPlayer());
               }
               return;
           }

            for(HPlayer players : HigurashiUHC.getGameManager().getPlayers().values()){

                HideNametag.hide(player, players.getPlayer());
                players.getPlayer().playSound(players.getPlayer().getLocation(), Sound.ENDERDRAGON_DEATH, 5, 5);

            }

           //player.teleport(new Location());

        }

    }

    @EventHandler
    public void onRoleSelected(RoleSelected event){
        HPlayer player = event.getPlayer();

        Player bPlayer = player.getPlayer();

        bPlayer.setHealthScale(8);
        bPlayer.setHealth(8);
        bPlayer.setMaxHealth(8);
    }

    public void resurrection(HPlayer rikaFurudePlayer, HPlayer resuPlayer){
        Player rika = rikaFurudePlayer.getPlayer();
        Player target = resuPlayer.getPlayer();

        Role[] roles = {Role.SATOKO_HOJO, Role.KEIICHI_MAEBARA, Role.MION_SONOZAKI, Role.SHION_SONOSAKI, Role.RENA_RYUGU};

        HigurashiUHC.getGameManager().getPlayers().values().forEach(player -> {
            if(!player.getRole().getClass().equals(Role.HANYU.getRole())){
                if(player.getPlayer().getGameMode() != GameMode.SPECTATOR){
                    for(Role role : roles){
                        if(resuPlayer.getRole().getClass().equals(role.getRole())){
                            if(rikaFurudePlayer.getRole().getClass().equals(Role.RIKA_FURUDE.getRole())){
                                RikaFurude rikaFurude = (RikaFurude) rikaFurudePlayer.getRole();

                                if(rikaFurude.getLives() <= 1){
                                    rika.sendMessage("Vous n'avez pas assez de vie pour réssuciter " + target.getName());
                                    return;
                                }

                                rikaFurude.remove1Live();
                                ressucite = true;
                            }
                        }
                    }
                }
            }
        });



    }

    public boolean getRessucite(){
        return ressucite;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    public void remove1Live(){
        this.lives = this.lives - 1;
    }

    public int getLives(){
        return lives;
    }
}
