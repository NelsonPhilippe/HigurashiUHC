package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.HideNametag;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
    public void onRoleSelected(RoleSelected event){
        HPlayer player = event.getPlayer();

        Player bPlayer = player.getPlayer();

        if(bPlayer != null && player.getRole().isRole(RoleList.RIKA_FURUDE.getRole())){
            bPlayer.setHealth(16);
            bPlayer.setMaxHealth(16);
        }


    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        if(hPlayer == null || hPlayer.getRole()== null) return;

        if(hPlayer.getRole().equals(RoleList.RIKA_FURUDE.getRole())){
            HPlayer hanyu =  RoleList.HANYU.getRole().getHPlayer();

            if(hanyu == null || hanyu.getPlayer() == null) return;

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

        if(rika == null || target == null) return;

        if(!resuPlayer.getRole().isRole(RoleList.SATOKO_HOJO.getRole(), RoleList.KEIICHI_MAEBARA.getRole(), RoleList.MION_SONOZAKI.getRole(), RoleList.SHION_SONOSAKI.getRole(), RoleList.RENA_RYUGU.getRole()))
            return;

        if (RoleList.HANYU.getRole().getHPlayer() != null && !RoleList.HANYU.getRole().getHPlayer().getPlayerState().isState(PlayerState.WAITING_DEATH, PlayerState.SPECTATE)) {
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
    public String getDecription() {
        return "null";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {
        HPlayer miyo = RoleList.MIYO_TAKANO.getRole().getHPlayer();

        if(miyo != null){

            Bukkit.broadcastMessage(miyo.getRole().getName() + " est " + miyo.getName());

        }
    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

        if(killed.getPlayer() == null)
            return;

        Entity killer = killed.getKiller();
        HPlayer killerHPlayer = null;
        Player player = killed.getPlayer();

        if(killer != null)
            killerHPlayer = HigurashiUHC.getGameManager().getHPlayer(killer.getUniqueId());

        if(killer instanceof Player) {

            if (killerHPlayer != null) {
                if (killerHPlayer.getClans() == MercenaireClan.getClans()) {
                    HigurashiUHC.getGameManager().startRikaDeathTask();
                    for (HPlayer miyo : HigurashiUHC.getGameManager().getHPlayerList().values()) {
                        if (miyo.getRole().equals(RoleList.MIYO_TAKANO.getRole())) {
                            Bukkit.broadcastMessage("Miyo Takano est " + miyo.getName());
                        }
                    }
                    return;
                }
            }

            HPlayer hanyu = RoleList.HANYU.getRole().getHPlayer();

            if (hanyu != null && hanyu.getPlayer() != null) {
                if (hanyu.getPlayer().getGameMode() == GameMode.SPECTATOR) {
                    HigurashiUHC.getGameManager().startRikaDeathTask();
                    return;
                }
            }

            if (killed.getRole().equals(RoleList.RIKA_FURUDE.getRole())) {
                RikaFurude rikaFurude = (RikaFurude) killed.getRole();

                rikaFurude.remove1Live();

                if (rikaFurude.getLives() == 2) {
                    killed.getPlayer().setMaxHealth(16);
                    killed.getPlayer().setHealth(16);
                }

                if (rikaFurude.getLives() == 1) {

                    killed.getPlayer().setMaxHealth(10);
                    killed.getPlayer().setHealth(10);

                    for (HPlayer players : HigurashiUHC.getGameManager().getHPlayerList().values()) {
                        HideNametag.unhide(killed.getPlayer(), players.getPlayer());
                    }
                    return;
                }

                if (rikaFurude.getLives() == 0) {
                    if (player.getPlayer().getInventory().getContents().length > 0) {
                        for (ItemStack itemStack : player.getInventory().getContents()) {
                            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                            player.getInventory().removeItem(itemStack);
                        }
                    }

                    if (player.getPlayer().getInventory().getArmorContents().length > 0) {
                        for (ItemStack itemStack : player.getInventory().getArmorContents()) {
                            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                            player.getInventory().removeItem(itemStack);
                        }
                    }

                    for (HPlayer players : HigurashiUHC.getGameManager().getHPlayerList().values()) {

                        if(players.getPlayer() != null)
                        players.getPlayer().playSound(players.getPlayer().getLocation(), Sound.ENDERDRAGON_DEATH, 5, 5);

                    }

                    HigurashiUHC.getGameManager().startRikaDeathTask();
                    return;

                }
                //player.teleport(new Location());

            }

        }

        HPlayer hanyu =  RoleList.HANYU.getRole().getHPlayer();

        if(hanyu == null) return;

        if(hanyu.getPlayerState() == PlayerState.INGAME){

            TextComponent textClick = new TextComponent(ChatColor.GOLD + "[Oui]");
            textClick.setBold(true);
            TextComponent message = new TextComponent("Voulez vous teleporter Rika dans la dimension : ");
            message.addExtra(textClick);

            textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "t rika"));

        }
    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
