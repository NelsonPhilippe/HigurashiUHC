package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Hinamizawa;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.mercenaires.Okonogi;
import org.bukkit.Location;

import java.util.TimerTask;

public class ChatTask extends TimerTask {

    private int time = 30;
    private HPlayer target;

    public ChatTask(HPlayer target) {
        this.target = target;
    }

    @Override
    public void run() {


        HPlayer okonogi = RoleList.OKONOGI.getRole().getPlayer();
        Okonogi okonogiRole = (Okonogi) okonogi.getRoleList().getRole();


        if(okonogi == null){
            this.cancel();
        }

        if(okonogiRole.gethPlayerList().contains(target)){
            this.cancel();
        }

        if(this.target == null){
            this.cancel();
        }

        Location okoLoc = okonogi.getPlayer().getLocation();
        Location hPlayerLoc = target.getPlayer().getLocation();

        if(okoLoc.distanceSquared(hPlayerLoc) > 20 * 20){
            okonogi.getPlayer().sendMessage("Vous êtes trop éloigné de la cible.");
            this.cancel();
        }


        if(time == 0){

            okonogiRole.addPlayer(target);

            int mercenary = 0;

            for(HPlayer hPlayer : okonogiRole.gethPlayerList()){

                if(hPlayer.getClans().getMajorClans().equals(Hinamizawa.getClans())){

                    mercenary++;

                }

            }

            if(mercenary == MercenaireClan.getClans().getRoles().size()){
                okonogi.getPlayer().setMaxHealth(okonogi.getPlayer().getMaxHealth() + 1);
            }

            this.cancel();
        }
        time--;
    }
}
