package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class NullRole extends Role{
    public NullRole() {
        super("Je suis null", Gender.HOMME, null, 9999);
    }

    @Override
    public String getDecription() {
        return "Rôle de merde";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason deathReason) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason deathReason) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
