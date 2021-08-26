package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.MariedReason;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.roles.RoleList;
import org.bukkit.entity.Player;

public class Doll extends Scenario {

    private HigurashiUHC plugin;
    private GameManager gameManager;
    private Integer appliedSolution = null;

    public Doll() {
        super("Poupée");
        this.plugin = HigurashiUHC.getInstance();
        this.gameManager = HigurashiUHC.getGameManager();
    }

    @Override
    public void solution(int solutionN, Object... o) {
        this.appliedSolution = solutionN;
        if(solutionN == 1){
            RoleList.MION_SONOZAKI.getRole().setMalediction(true);
            RoleList.MION_SONOZAKI.getRole().setLinkedToDeathWith(RoleList.KEIICHI_MAEBARA);
        }else if(solutionN == 2){
            RoleList.SHION_SONOSAKI.getRole().setMalediction(true);
            RoleList.SHION_SONOSAKI.getRole().setLinkedToDeathWith(RoleList.KEIICHI_MAEBARA);
        }else if(solutionN == 3){
            RoleList.MION_SONOZAKI.getRole().setMarriedWith(RoleList.KEIICHI_MAEBARA, MariedReason.DOLL_TRAGEDY);
            RoleList.KEIICHI_MAEBARA.getRole().setMarriedWith(RoleList.MION_SONOZAKI, MariedReason.DOLL_TRAGEDY);
        }else{
            Player player = RoleList.KEIICHI_MAEBARA.getRole().getPlayer().getPlayer();
            player.setMaxHealth(player.getMaxHealth()-5);
            RoleList.KEIICHI_MAEBARA.getRole().setMalediction(true);
        }
    }

    public Integer getAppliedSolution(){
        return appliedSolution;
    }

}
