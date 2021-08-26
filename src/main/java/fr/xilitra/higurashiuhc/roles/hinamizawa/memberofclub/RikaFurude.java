package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Mercenaire;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.utils.HideNametag;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class RikaFurude extends Role implements Listener {

    private int lives;
    private boolean ressucite = false;

    public RikaFurude() {
        super("Rika Furude", Gender.FEMME, MemberOfClub.getClans());
        this.lives = 3;
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
            if(killerHPlayer.getRoleList().getRole().getClans() == Mercenaire.getClans()){
                player.setGameMode(GameMode.SPECTATOR);
                HigurashiUHC.getGameManager().startRikaDeathTask();
                for(HPlayer miyo : HigurashiUHC.getGameManager().getPlayers().values()){
                    if(miyo.getRoleList().getRole().equals(RoleList.MIYO_TAKANO.getRole())){
                        Bukkit.broadcastMessage("Miyo Takano est " + miyo.getName());
                    }
                }
                return;
            }
        }

        for(HPlayer players : HigurashiUHC.getGameManager().getPlayers().values()){
            if(players.getRoleList().getRole().equals(RoleList.HANYU.getRole())){
                if(players.getPlayer().getGameMode() == GameMode.SPECTATOR){
                    player.setGameMode(GameMode.SPECTATOR);
                    HigurashiUHC.getGameManager().startRikaDeathTask();
                    return;
                }
            }
        }

        if(hp.getRoleList().getRole().equals(RoleList.RIKA_FURUDE.getRole())){
            RikaFurude rikaFurude = (RikaFurude) hp.getRoleList().getRole();

            rikaFurude.remove1Live();

            if(rikaFurude.getLives() == 2){
                player.setHealth(16);
                player.setMaxHealth(16);
                //player.teleport();
            }

           if(rikaFurude.getLives() == 1){

               player.setHealth(10);
               player.setMaxHealth(10);

               for(HPlayer players : HigurashiUHC.getGameManager().getPlayers().values()) {

                   HideNametag.unhide(player, players.getPlayer());
               }
               return;
           }

           if(rikaFurude.getLives() == 0){
               if(player.getPlayer().getInventory().getContents().length > 0){
                   for (ItemStack itemStack : player.getInventory().getContents()) {
                       player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                       player.getInventory().removeItem(itemStack);
                   }
               }

               if(player.getPlayer().getInventory().getArmorContents().length > 0){
                   for (ItemStack itemStack : player.getInventory().getArmorContents()) {
                       player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                       player.getInventory().removeItem(itemStack);
                   }
               }

               for(HPlayer players : HigurashiUHC.getGameManager().getPlayers().values()){

                   players.getPlayer().playSound(players.getPlayer().getLocation(), Sound.ENDERDRAGON_DEATH, 5, 5);

               }


               player.getPlayer().setGameMode(GameMode.SPECTATOR);
               HigurashiUHC.getGameManager().startRikaDeathTask();

               return;

           }
           //player.teleport(new Location());

        }

    }

    @EventHandler
    public void onRoleSelected(RoleSelected event){
        HPlayer player = event.getPlayer();

        Player bPlayer = player.getPlayer();

        if(player.getRoleList().getRole().getName().equalsIgnoreCase(RoleList.RIKA_FURUDE.getRole().getName())){
            bPlayer.setHealth(16);
            bPlayer.setMaxHealth(16);
        }


    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        if(hPlayer.getRoleList() == null) return;

        if(hPlayer.getRoleList().getRole().equals(RoleList.RIKA_FURUDE.getRole())){
            HPlayer hanyu = HigurashiUHC.getGameManager().getPlayerWithRole(RoleList.HANYU);

            if(hanyu == null) return;

            Hanyu hanyuRole = (Hanyu) hanyu.getRoleList().getRole();

            Location hanyuLocation = hanyu.getPlayer().getLocation();
            Location rikaLocation = p.getLocation();

            if(hanyuLocation.distanceSquared(rikaLocation) < 30 * 30){

                if(hanyuRole.isInvisible()) return;

                hanyuRole.setInvisible(true);

                for(Player players : Bukkit.getOnlinePlayers()){
                    players.hidePlayer(p);
                }
            }else {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.showPlayer(p);
                }
            }
        }

    }

    public void resurrection(HPlayer rikaFurudePlayer, HPlayer resuPlayer){
        Player rika = rikaFurudePlayer.getPlayer();
        Player target = resuPlayer.getPlayer();

        RoleList[] roles = {RoleList.SATOKO_HOJO, RoleList.KEIICHI_MAEBARA, RoleList.MION_SONOZAKI, RoleList.SHION_SONOSAKI, RoleList.RENA_RYUGU};

        HigurashiUHC.getGameManager().getPlayers().values().forEach(player -> {
            if(!player.getRoleList().getRole().equals(RoleList.HANYU.getRole())){
                if(player.getPlayer().getGameMode() != GameMode.SPECTATOR){
                    for(RoleList role : roles){
                        if(resuPlayer.getRoleList().getRole().equals(role.getRole())){
                            if(rikaFurudePlayer.getRoleList().getRole().equals(RoleList.RIKA_FURUDE.getRole())){
                                RikaFurude rikaFurude = (RikaFurude) rikaFurudePlayer.getRoleList().getRole();

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
        this.lives--;
    }

    public int getLives(){
        return lives;
    }
}
