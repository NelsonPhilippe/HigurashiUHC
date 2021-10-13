package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Doll extends Scenario implements Listener {

    private Integer appliedSolution = null;

    public Doll() {
        super("Poupée");
    }

    @Override
    public void solution(int solutionN, Object... o) {
        this.appliedSolution = solutionN;
        if (solutionN == 1) {
            if (Role.MION_SONOZAKI.getHPlayer() == null)
                return;
            Role.MION_SONOZAKI.getHPlayer().addMaledictionReason(Reason.DOLL_TRAGEDY);
            Role.MION_SONOZAKI.getHPlayer().getLinkData((HPlayer) o[0]).setDeathLinked(Reason.DOLL_TRAGEDY, false);
        } else if (solutionN == 2) {
            if (Role.SHION_SONOSAKI.getHPlayer() == null)
                return;
            Role.SHION_SONOSAKI.getHPlayer().addMaledictionReason(Reason.DOLL_TRAGEDY);
            Role.SHION_SONOSAKI.getHPlayer().getLinkData((HPlayer) o[0]).setDeathLinked(Reason.DOLL_TRAGEDY, false);
        } else if (solutionN == 3) {
            if (Role.MION_SONOZAKI.getHPlayer() == null)
                return;
            Role.MION_SONOZAKI.getHPlayer().getLinkData((HPlayer) o[0]).setMariedLinked(Reason.DOLL_TRAGEDY, true);
        } else {
            HPlayer hPlayer = Role.KEIICHI_MAEBARA.getHPlayer();
            if (hPlayer == null || hPlayer.getPlayer() == null)
                return;
            Player player = hPlayer.getPlayer();
            player.setMaxHealth(player.getMaxHealth() - 5);
            Role.KEIICHI_MAEBARA.getHPlayer().addMaledictionReason(Reason.DOLL_TRAGEDY);
        }
    }

    @Override
    public Integer getSolutionNumber() {
        return appliedSolution;
    }

    @Override
    protected void scenarioStateChange(boolean b) {

    }

    @EventHandler
    public void episodeListener(EpisodeUpdate episodeUpdate) {
        HPlayer keiichi = Role.KEIICHI_MAEBARA.getHPlayer();
        if (keiichi != null && keiichi.getPlayer() != null)
            if (keiichi.getPlayer().getMaxHealth() < 20) {
                keiichi.getPlayer().sendMessage("DOLL) Un episode est passé, tu viens de gagner un coeur de plus.");
                keiichi.getPlayer().setMaxHealth(keiichi.getPlayer().getMaxHealth() + 1);
            }

    }

}
