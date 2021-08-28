package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Sonozaki;
import org.bukkit.event.entity.EntityDamageEvent;

public class Kasai extends Role {

    private boolean isGiveForce;

    public Kasai() {
        super("Kasai", Gender.HOMME, Sonozaki.getClans(), 1);
        isGiveForce = false;
    }

    public boolean isGiveForce() {
        return isGiveForce;
    }

    public void setGiveForce(boolean giveForce) {
        isGiveForce = giveForce;
    }

    @Override
    public void onKill(EntityDamageEvent de, HPlayer killer, HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent de, HPlayer killed) {

    }
}
