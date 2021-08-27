package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class Okonogi extends Role {

    public Okonogi() {
        super("Okonogi", Gender.HOMME, MercenaireClan.getClans(), 1);
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent.DamageCause killer, HPlayer killed) {

    }
}
