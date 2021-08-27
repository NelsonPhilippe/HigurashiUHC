package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Police;
import org.bukkit.event.entity.EntityDamageEvent;

public class Policier extends Role {

    private boolean pvIsUsed;

    public Policier() {
        super("Policier", Gender.NON_GENRE, Police.getClans(), 1);
        pvIsUsed = false;
    }

    public boolean isPvIsUsed() {
        return pvIsUsed;
    }

    public void setPvIsUsed(boolean pvIsUsed) {
        this.pvIsUsed = pvIsUsed;
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent.DamageCause killer, HPlayer killed) {

    }
}
