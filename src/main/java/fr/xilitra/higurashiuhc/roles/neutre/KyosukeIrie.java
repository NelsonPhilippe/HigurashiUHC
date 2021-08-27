package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Neutre;
import org.bukkit.event.entity.EntityDamageEvent;

public class KyosukeIrie extends Role {
    public KyosukeIrie() {
        super("Kyosuke Irie", Gender.HOMME, Neutre.getClans());
    }

    @Override
    public void onKill(HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent.DamageCause killer) {

    }
}
