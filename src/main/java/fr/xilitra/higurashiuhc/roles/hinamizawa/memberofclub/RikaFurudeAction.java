package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.task.taskClass.CloseRikaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.HideNametag;
import fr.xilitra.higurashiuhc.utils.WataEnum;
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

public class RikaFurudeAction extends RoleAction implements Listener {

    private final CloseRikaTask watanagashiTask = new CloseRikaTask();
    private int lives;
    private boolean ressucite = false;

    public RikaFurudeAction() {
        this.lives = 3;
    }

    @EventHandler
    public void onRoleSelected(RoleSelected event) {
        HPlayer player = event.getPlayer();

        Player bPlayer = player.getPlayer();

        if (bPlayer != null && player.getRole().isRole(Role.RIKA_FURUDE)) {
            bPlayer.setHealth(16);
            bPlayer.setMaxHealth(16);
        }


    }

    @EventHandler
    public void onWataChange(WatanagashiChangeEvent event) {
        if (event.getWataEnum() == WataEnum.AFTER)
            this.watanagashiTask.runTaskTimer(1000, 1000);
        else
            this.watanagashiTask.stopTask();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        if (hPlayer == null) return;

        if (hPlayer.getRole().isRole(Role.RIKA_FURUDE)) {
            HPlayer hanyu = Role.HANYU.getHPlayer();

            if (hanyu == null || hanyu.getPlayer() == null) return;

            HanyuAction hanyuActionRole = (HanyuAction) hanyu.getRole().getRoleAction();

            Location hanyuLocation = hanyu.getPlayer().getLocation();
            Location rikaLocation = p.getLocation();

            if (hanyuLocation.distanceSquared(rikaLocation) < 30 * 30) {

                if (hanyuActionRole.isInvisible()) return;

                hanyuActionRole.setInvisible(true);

                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.hidePlayer(p);
                }

            } else {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.showPlayer(p);
                }
            }
        }

    }

    public void resurrection(HPlayer rikaFurudePlayer, HPlayer resuPlayer) {
        Player rika = rikaFurudePlayer.getPlayer();
        Player target = resuPlayer.getPlayer();

        if (rika == null || target == null) return;

        if (!resuPlayer.getRole().isRole(Role.SATOKO_HOJO, Role.KEIICHI_MAEBARA, Role.MION_SONOZAKI, Role.SHION_SONOSAKI, Role.RENA_RYUGU))
            return;

        if (Role.HANYU.getHPlayer() != null && !Role.HANYU.getHPlayer().getPlayerState().isState(PlayerState.WAITING_DEATH, PlayerState.SPECTATE)) {
            if (resuPlayer.getPlayerState().isState(PlayerState.WAITING_DEATH)) {
                if (rikaFurudePlayer.getRole().isRole(Role.RIKA_FURUDE)) {
                    RikaFurudeAction rikaFurudeAction = (RikaFurudeAction) rikaFurudePlayer.getRole().getRoleAction();

                    if (rikaFurudeAction.getLives() <= 1) {
                        rika.sendMessage("Vous n'avez pas assez de vie pour réssuciter " + target.getName());
                        return;
                    }

                    rikaFurudeAction.remove1Live();
                    resuPlayer.setPlayerState(PlayerState.INGAME);
                    target.setGameMode(GameMode.SURVIVAL);
                    target.setHealth(target.getMaxHealth());
                    ressucite = true;
                }
            }
        }
    }

    public boolean getRessucite() {
        return ressucite;
    }

    public void remove1Live() {
        this.lives--;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {
        HPlayer miyo = Role.MIYO_TAKANO.getHPlayer();

        if (miyo != null) {

            Bukkit.broadcastMessage(miyo.getName() + " est " + miyo.getName());

        }
    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

        if (killed.getPlayer() == null)
            return;

        Entity killer = killed.getKiller();
        HPlayer killerHPlayer = null;
        Player player = killed.getPlayer();

        if (killer != null)
            killerHPlayer = HigurashiUHC.getGameManager().getHPlayer(killer.getUniqueId());

        if (killer instanceof Player) {

            if (killerHPlayer != null && HigurashiUHC.getGameManager().isWataState(WataEnum.BEFORE)) {
                if (killerHPlayer.getClans().isClans(Clans.MERCENAIRE)) {
                    HigurashiUHC.getGameManager().startRikaDeathTask();
                    for (HPlayer miyo : HigurashiUHC.getGameManager().getHPlayerList().values()) {
                        if (miyo.getRole().isRole(Role.MIYO_TAKANO)) {
                            Bukkit.broadcastMessage("Miyo Takano est " + miyo.getName());
                        }
                    }
                    return;
                }
            }

            HPlayer hanyu = Role.HANYU.getHPlayer();

            if (hanyu != null && hanyu.getPlayer() != null) {
                if (hanyu.getPlayer().getGameMode() == GameMode.SPECTATOR) {
                    HigurashiUHC.getGameManager().startRikaDeathTask();
                    return;
                }
            }

            if (killed.getRole().isRole(Role.RIKA_FURUDE)) {
                RikaFurudeAction rikaFurudeAction = (RikaFurudeAction) killed.getRole().getRoleAction();

                rikaFurudeAction.remove1Live();

                if (rikaFurudeAction.getLives() == 2) {
                    killed.getPlayer().setMaxHealth(16);
                    killed.getPlayer().setHealth(16);
                }

                if (rikaFurudeAction.getLives() == 1) {

                    killed.getPlayer().setMaxHealth(10);
                    killed.getPlayer().setHealth(10);

                    for (HPlayer players : HigurashiUHC.getGameManager().getHPlayerList().values()) {
                        HideNametag.unhide(killed.getPlayer(), players.getPlayer());
                    }
                    return;
                }

                if (rikaFurudeAction.getLives() == 0) {
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

                        if (players.getPlayer() != null)
                            players.getPlayer().playSound(players.getPlayer().getLocation(), Sound.ENDERDRAGON_DEATH, 5, 5);

                    }

                    HigurashiUHC.getGameManager().startRikaDeathTask();
                    return;

                }
                //player.teleport(new Location());

            }

        }

        HPlayer hanyu = Role.HANYU.getHPlayer();

        if (hanyu == null) return;

        if (hanyu.getPlayerState() == PlayerState.INGAME) {

            TextComponent textClick = new TextComponent(ChatColor.GOLD + "[Oui]");
            textClick.setBold(true);
            TextComponent message = new TextComponent("Voulez vous teleporter Rika dans la dimension : ");
            message.addExtra(textClick);

            textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "t rika"));

        }
    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {

        HPlayer hanyu = Role.HANYU.getHPlayer();
        HPlayer rika = getLinkedRole().getHPlayer();
        if (hanyu != null && rika != null && rika.getPlayer() != null) {
            rika.getPlayer().sendMessage("Hanyu est incarnée par: " + hanyu.getName());
        }

    }

    @Override
    public void onGameStop() {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
