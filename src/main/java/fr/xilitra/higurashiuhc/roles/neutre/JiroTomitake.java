package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Neutre;
import org.bukkit.event.entity.EntityDamageEvent;

public class JiroTomitake extends Role {
    public JiroTomitake() {
        super("Jiro Tomitake", Gender.HOMME, Neutre.getClans(), 1);
    }

    @Override
    public void onKill(EntityDamageEvent de, HPlayer killer, HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent de, HPlayer killed) {

    }
}
