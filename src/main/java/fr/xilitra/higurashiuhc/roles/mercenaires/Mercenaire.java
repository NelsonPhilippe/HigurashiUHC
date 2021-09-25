package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class Mercenaire extends Role {

    private HPlayer cible;

    public Mercenaire() {
        super("Mercenaire", Gender.NON_GENRE, MercenaireClan.getClans(), 1000);
    }

    public HPlayer getCible() {
        return cible;
    }

    public void setCible(HPlayer cible) {
        this.cible = cible;
    }

    @Override
    public String getDecription() {
        return "null";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {
        if(getCible() != null && getHPlayer() != null && getHPlayer().getPlayer() != null){

            if(killed == getCible()){

                getHPlayer().getPlayer().setMaxHealth(getHPlayer().getPlayer().getMaxHealth() + 1);
                setCible(null);

            }

        }
    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

}
