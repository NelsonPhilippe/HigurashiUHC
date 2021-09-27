package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.clans.ClansList;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

public class SatoshiHojo extends Role {
    public SatoshiHojo() {
        super("Satoshi Hojo", Gender.HOMME, ClansList.NEUTRE, 1);
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

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
