package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Police;
import fr.xilitra.higurashiuhc.utils.DeathReason;
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
    public String getDecription() {
        return "null";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }
}
