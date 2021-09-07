package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.event.Listener;

public class KeiichiMaebara extends Role implements Listener {

    public KeiichiMaebara() {
        super("Keiichi Maebara", Gender.HOMME, MemberOfClub.getClans(), 1);
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
