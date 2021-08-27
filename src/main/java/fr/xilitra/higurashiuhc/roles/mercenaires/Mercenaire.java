package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;

public class Mercenaire extends Role {

    private HPlayer cible;

    public Mercenaire() {
        super("Mercenaire", Gender.NON_GENRE, MercenaireClan.getClans());
    }

    public HPlayer getCible() {
        return cible;
    }

    public void setCible(HPlayer cible) {
        this.cible = cible;
    }

    @Override
    public void onKill(HPlayer killed) {
        if(getCible() != null){

            if(killed == getCible()){

                getPlayer().getPlayer().setMaxHealth(getPlayer().getPlayer().getMaxHealth() + 1);
                setCible(null);

            }

        }
    }

    @Override
    public void onDeath(EntityDamageEvent.DamageCause killer) {

    }

}
