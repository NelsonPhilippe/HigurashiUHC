package fr.xilitra.higurashiuhc.roles.hinamizawa;

import fr.xilitra.higurashiuhc.clans.hinamizawa.Hinamizawa;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.clans.hinamizawa.Sonozaki;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class Villageois extends Role {
    public Villageois() {
        super("Villageois", Gender.NON_GENRE, Hinamizawa.getClans(), 1);
    }


    @Override
    public String getDecription() {
        return "null";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}