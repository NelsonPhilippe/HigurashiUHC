package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Neutre;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.event.entity.EntityDamageEvent;

public class JiroTomitake extends Role {
    public JiroTomitake() {
        super("Jiro Tomitake", Gender.HOMME, Neutre.getClans(), 1);
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
