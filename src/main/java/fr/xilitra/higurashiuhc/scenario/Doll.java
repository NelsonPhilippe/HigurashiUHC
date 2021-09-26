package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.RoleList;
import org.bukkit.entity.Player;

public class Doll extends Scenario {

    private Integer appliedSolution = null;

    public Doll() {
        super("Poupée");
    }

    @Override
    public void solution(int solutionN, Object... o) {
        this.appliedSolution = solutionN;
        if(solutionN == 1){
            if(RoleList.MION_SONOZAKI.getRole().getHPlayer() == null)
                return;
            RoleList.MION_SONOZAKI.getRole().getHPlayer().addMaledictionReason(Reason.DOLL_TRAGEDY);
            RoleList.MION_SONOZAKI.getRole().getHPlayer().getLinkData((HPlayer) o[0]).setDeathLinked(Reason.DOLL_TRAGEDY, false);
        }else if(solutionN == 2){
            if(RoleList.SHION_SONOSAKI.getRole().getHPlayer() == null)
                return;
            RoleList.SHION_SONOSAKI.getRole().getHPlayer().addMaledictionReason(Reason.DOLL_TRAGEDY);
            RoleList.SHION_SONOSAKI.getRole().getHPlayer().getLinkData((HPlayer) o[0]).setDeathLinked(Reason.DOLL_TRAGEDY, false);
        }else if(solutionN == 3){
            if(RoleList.MION_SONOZAKI.getRole().getHPlayer() == null)
                return;
            RoleList.MION_SONOZAKI.getRole().getHPlayer().getLinkData((HPlayer) o[0]).setMariedLinked(Reason.DOLL_TRAGEDY, true);
        }else{
            HPlayer hPlayer = RoleList.KEIICHI_MAEBARA.getRole().getHPlayer();
            if(hPlayer == null || hPlayer.getPlayer() == null)
                return;
            Player player = hPlayer.getPlayer();
            player.setMaxHealth(player.getMaxHealth()-5);
            RoleList.KEIICHI_MAEBARA.getRole().getHPlayer().addMaledictionReason(Reason.DOLL_TRAGEDY);
        }
    }

    @Override
    public Integer getSolutionNumber() {
        return appliedSolution;
    }

    @Override
    protected void scenarioStateChange(boolean b) {

    }

    public Integer getAppliedSolution(){
        return appliedSolution;
    }

}
