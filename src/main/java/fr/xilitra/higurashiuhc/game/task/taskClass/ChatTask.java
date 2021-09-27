package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.clans.hinamizawa.Hinamizawa;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.mercenaires.Okonogi;
import org.bukkit.Location;

public class ChatTask extends BukkitTask {

    private int time = 30;
    private final HPlayer target;

    public ChatTask(HPlayer target) {
        this.target = target;
    }

    @Override
    public void run() {

        Okonogi okonogiRole = (Okonogi) RoleList.OKONOGI.getRole();

        if(okonogiRole.hasHPlayer(target)){
            this.stopTask();
            return;
        }

        if(this.target == null){
            this.stopTask();
            return;
        }

        if(target.getPlayer() == null)
            return;

        HPlayer okonogi = RoleList.OKONOGI.getRole().getHPlayer();

        if(okonogi == null || okonogi.getPlayer() == null){
            target.getPlayer().sendMessage("La personne cible est déconnecté");
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

            for(HPlayer hPlayer : okonogiRole.getHPlayerList()){

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
