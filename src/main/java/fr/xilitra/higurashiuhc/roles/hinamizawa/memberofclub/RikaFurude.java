package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.utils.HideNametag;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class RikaFurude extends Role implements Listener {

    private int lives;
    private boolean ressucite = false;

    public RikaFurude() {
        super("Rika Furude", Gender.FEMME, MemberOfClub.getClans(), 1);
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
            if(killerHPlayer.getClans() == MercenaireClan.getClans()){
                player.setGameMode(GameMode.SPECTATOR);
                HigurashiUHC.getGameManager().startRikaDeathTask();
                for(HPlayer miyo : HigurashiUHC.getGameManager().getPlayerList().values()){
                    if(miyo.getRole().equals(RoleList.MIYO_TAKANO.getRole())){
                        Bukkit.broadcastMessage("Miyo Takano est " + miyo.getName());
                    }
                }
                return;
            }
        }

        HPlayer hanyu = RoleList.HANYU.getRole().getPlayer();

        if(hanyu != null){
            if(hanyu.getPlayer().getGameMode() == GameMode.SPECTATOR){
                player.setGameMode(GameMode.SPECTATOR);
                HigurashiUHC.getGameManager().startRikaDeathTask();
                return;
            }
        }

        if(hp.getRole().equals(RoleList.RIKA_FURUDE.getRole())){
            RikaFurude rikaFurude = (RikaFurude) hp.getRole();

            rikaFurude.remove1Live();

            if(rikaFurude.getLives() == 2){
                player.setHealth(16);
                player.setMaxHealth(16);
                //player.teleport();
            }

           if(rikaFurude.getLives() == 1){

               player.setHealth(10);
               player.setMaxHealth(10);

               for(HPlayer players : HigurashiUHC.getGameManager().getPlayerList().values()) {

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

               for(HPlayer players : HigurashiUHC.getGameManager().getPlayerList().values()){

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

        if(player.getRole().getName().equalsIgnoreCase(RoleList.RIKA_FURUDE.getRole().getName())){
            bPlayer.setHealth(16);
            bPlayer.setMaxHealth(16);
        }


    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        if(hPlayer.getRole()== null) return;

        if(hPlayer.getRole().equals(RoleList.RIKA_FURUDE.getRole())){
            HPlayer hanyu =  RoleList.HANYU.getRole().getPlayer();

            if(hanyu == null) return;

            Hanyu hanyuRole = (Hanyu) hanyu.getRole();

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

    public void resurrection(HPlayer rikaFurudePlayer, HPlayer resuPlayer) {
        Player rika = rikaFurudePlayer.getPlayer();
        Player target = resuPlayer.getPlayer();

        if(!resuPlayer.getRole().isRole(RoleList.SATOKO_HOJO.getRole(), RoleList.KEIICHI_MAEBARA.getRole(), RoleList.MION_SONOZAKI.getRole(), RoleList.SHION_SONOSAKI.getRole(), RoleList.RENA_RYUGU.getRole()))
            return;

        if (!RoleList.HANYU.getRole().getPlayer().getPlayerState().isState(PlayerState.WAITING_DEATH, PlayerState.SPECTATE)) {
            if (resuPlayer.getPlayerState().isState(PlayerState.WAITING_DEATH)) {
                if (rikaFurudePlayer.getRole().equals(RoleList.RIKA_FURUDE.getRole())) {
                    RikaFurude rikaFurude = (RikaFurude) rikaFurudePlayer.getRole();

                    if (rikaFurude.getLives() <= 1) {
                        rika.sendMessage("Vous n'avez pas assez de vie pour réssuciter " + target.getName());
                        return;
                    }

                    rikaFurude.remove1Live();
                    resuPlayer.setPlayerState(PlayerState.INGAME);
                    target.setGameMode(GameMode.SURVIVAL);
                    target.setHealth(target.getMaxHealth());
                    ressucite = true;
                }
            }
        }
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

    @Override
    public void onKill(EntityDamageEvent de, HPlayer killer, HPlayer killed) {
        HPlayer miyo = RoleList.MIYO_TAKANO.getRole().getPlayer();

        if(miyo != null){

            Bukkit.broadcastMessage(miyo.getRole().getName() + " est " + miyo.getName());

        }
    }

    @Override
    public void onDeath(EntityDamageEvent de, HPlayer killed) {
        HPlayer hanyu =  RoleList.HANYU.getRole().getPlayer();

        if(hanyu == null) return;

        if(hanyu.getPlayer().getGameMode() != GameMode.SPECTATOR){

            TextComponent textClick = new TextComponent(ChatColor.GOLD + "[Oui]");
            textClick.setBold(true);
            TextComponent message = new TextComponent("Voulez vous teleporter Rika dans la dimension : ");
            message.addExtra(textClick);

            textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "t rika"));

        }
    }
}
