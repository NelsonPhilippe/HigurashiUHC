package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Sonozaki;
import org.bukkit.event.entity.EntityDamageEvent;

public class Villageois extends Role {
    public Villageois() {
        super("Villageois", Gender.NON_GENRE, Sonozaki.getClans(), 1);
    }


    @Override
    public void onKill(HPlayer killer, HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent.DamageCause killer, HPlayer killed) {

    }
}
