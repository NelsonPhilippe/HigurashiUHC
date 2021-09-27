package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.clans.ClansList;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.inventivetalent.bossbar.BossBar;

public class KeiichiMaebara extends Role implements Listener {

    private BossBar bossBar = null;

    public KeiichiMaebara() {
        super("Keiichi Maebara", Gender.HOMME, ClansList.MEMBER_OF_CLUB, 1);
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

    public BossBar getBossBar(){
        return bossBar;
    }

    public void setBossBar(BossBar bossBar){
        this.bossBar = bossBar;
    }

}
