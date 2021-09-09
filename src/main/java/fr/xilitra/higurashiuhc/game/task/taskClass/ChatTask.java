package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Hinamizawa;
import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.mercenaires.Okonogi;
import org.bukkit.Location;

public class ChatTask extends JavaTask {

    private int time = 30;
    private final HPlayer target;

    public ChatTask(HPlayer target) {
        super("chattask-"+target.getName());
        this.target = target;
    }

    @Override
    public void run() {


        HPlayer okonogi = RoleList.OKONOGI.getRole().getPlayer();
        Okonogi okonogiRole = (Okonogi) RoleList.OKONOGI.getRole();

        if(okonogiRole.hasPlayer(target)){
            this.stopTask();
            return;
        }

        if(this.target == null){
            this.stopTask();
            return;
        }

        Location okoLoc = okonogi.getPlayer().getLocation();
        Location hPlayerLoc = target.getPlayer().getLocation();

        if(okoLoc.distanceSquared(hPlayerLoc) > 20 * 20){
            okonogi.getPlayer().sendMessage("Vous êtes trop éloigné de la cible.");
            this.stopTask();
        }


        if(time == 0){

            okonogiRole.addPlayer(target);

            int mercenary = 0;

            for(HPlayer hPlayer : okonogiRole.getPlayerList()){

                if(Hinamizawa.getClans().hisInClans(hPlayer)){

                    mercenary++;

                }

            }

            if(mercenary == MercenaireClan.getClans().getPlayerListUUID().size()){
                okonogi.getPlayer().setMaxHealth(okonogi.getPlayer().getMaxHealth() + 1);
            }

            this.stopTask();
        }
        time--;
    }
}
