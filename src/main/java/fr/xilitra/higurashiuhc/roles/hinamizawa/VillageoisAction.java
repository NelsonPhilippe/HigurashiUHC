package fr.xilitra.higurashiuhc.roles.hinamizawa;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class VillageoisAction implements RoleAction {

    @Override
    public String getDescription() {
        return "\n" +
                "§6Vous êtes §9Villageois (non-genré) : \n" +
                "\n" +
                "§6Vous devez gagner avec §9Hinamizawa. \n" +
                "§6vous ne possédez aucun pouvoir ni aucun effet particulier. ";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {

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
