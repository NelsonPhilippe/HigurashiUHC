package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.taskClass.HanyuTaskInvisble;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.Map;

public class HanyuAction implements RoleAction, Listener {

    private final Map<HPlayer, Location> dimensionLastLoc = new HashMap<>();
    private boolean isInvisible;
    private boolean dimensionIsUsed;

    public HanyuAction() {
        this.dimensionIsUsed = false;
    }

    @Override
    public Role getLinkedRole(){
        return Role.HANYU;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §9Hanyu (fille) : \n" +
                "\n" +
                "§9Hanyu§6 doit gagner avec §9Hinamizawa tout en faisant partie du §bClub§6." +
                "§6Elle connaît l’identité de §9Rika§6 et lorsqu’elle se situe dans un rayon de 30 blocs de ce rôle, §9Hanyu§6 devient totalement invisible même avec son armure équipée." +
                "§6Cependant, si un joueur inflige des dégâts à §9Hanyu§6 lorsqu’elle est invisible, elle sera visible aux yeux de tous pendant 1 minute avant de pouvoir redevenir invisible." +
                "§9Hanyu§6 peut se téléporter avec §9Rika§6 dans la dimension avec la commande §5'/h dimension'§6 une seule fois dans la partie." +
                "De plus, lorsque §9Rika§6 perd une vie, dans un intervalle de 10 secondes, Hanyu peut décider de se téléporter avec §9Rika§6 dans la dimension.\n" +
                "\n" +
                "§9Rika§6 est §7'joueur'.";
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Player player = (Player) e.getEntity();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(player.getUniqueId());
        if (hPlayer == null)
            return;

        if (e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;

        Entity entityDamager = e.getDamager();

        if (!(entityDamager instanceof Player)) return;

        Player damager = (Player) entityDamager;
        HPlayer hPlayerDamager = HigurashiUHC.getGameManager().getHPlayer(damager.getUniqueId());

        if (hPlayerDamager == null)
            return;

        if (hPlayerDamager.getRole().equals(Role.HANYU) || hPlayer.getRole().isRole(Role.HANYU)) {

            HanyuAction hanyuAction;
            if (hPlayerDamager.getRole().equals(Role.HANYU))
                hanyuAction = (HanyuAction) hPlayerDamager.getRole().getRoleAction();
            else hanyuAction = (HanyuAction) hPlayer.getRole().getRoleAction();

            if (hanyuAction.isInvisible) {
                hanyuAction.setInvisible(false);

                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.showPlayer(player);
                    new HanyuTaskInvisble().runTaskTimer(1, 1);
                }
            }

        }


    }

    public boolean isInvisible() {
        return isInvisible;
    }

    public void setInvisible(boolean invisible) {
        isInvisible = invisible;
    }

    public boolean isDimensionIsUsed() {
        return dimensionIsUsed;
    }

    public void setDimensionIsUsed(boolean dimensionIsUsed) {
        this.dimensionIsUsed = dimensionIsUsed;
    }

    public Map<HPlayer, Location> getDimensionLastLoc() {
        return dimensionLastLoc;
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {
        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();
        if(rika == null)
            return;
        Player rikaPlayer = rika.getPlayer();
        if(rikaPlayer == null)
            return;
        rikaPlayer.sendMessage("§5Hanyu §7est morte. Désormais, il vous est impossible de ressusciter.");
    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {

        HPlayer hanyu = this.getLinkedRole().getHPlayer();
        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();
        if (hanyu != null && rika != null && hanyu.getPlayer() != null) {
            hanyu.getPlayer().sendMessage("Rika est incarnée par: " + rika.getName());
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
