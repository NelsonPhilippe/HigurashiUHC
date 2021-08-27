package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.MariedReason;
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
            RoleList.MION_SONOZAKI.getRole().getPlayer().incrMalediction();
            RoleList.MION_SONOZAKI.getRole().getPlayer().setLinkedToDeathWith((HPlayer) o[0]);
        }else if(solutionN == 2){
            RoleList.SHION_SONOSAKI.getRole().getPlayer().incrMalediction();
            RoleList.SHION_SONOSAKI.getRole().getPlayer().setLinkedToDeathWith((HPlayer) o[0]);
        }else if(solutionN == 3){
            RoleList.MION_SONOZAKI.getRole().getPlayer().setMarriedWith((HPlayer) o[0], MariedReason.DOLL_TRAGEDY);
            RoleList.KEIICHI_MAEBARA.getRole().getPlayer().setMarriedWith(RoleList.MION_SONOZAKI.getRole().getPlayer(), MariedReason.DOLL_TRAGEDY);
        }else{
            Player player = RoleList.KEIICHI_MAEBARA.getRole().getPlayer().getPlayer();
            player.setMaxHealth(player.getMaxHealth()-5);
            RoleList.KEIICHI_MAEBARA.getRole().getPlayer().incrMalediction();
        }
    }

    @Override
    public Integer getSolutionNumber() {
        return appliedSolution;
    }

    public Integer getAppliedSolution(){
        return appliedSolution;
    }

}
