package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.mercenaires.OkonogiAction;
import org.bukkit.Location;

public class ChatTask extends BukkitTask {

    private int time = 30;
    private final HPlayer target;

    public ChatTask(HPlayer target) {
        this.target = target;
    }

    @Override
    public void run() {

        OkonogiAction okonogiActionRole = (OkonogiAction) Role.OKONOGI.getRoleAction();

        if(okonogiActionRole.getLinkedRole().hasHPlayer(target)){
            this.stopTask();
            return;
        }

        if(this.target == null){
            this.stopTask();
            return;
        }

        if(target.getPlayer() == null)
            return;

        HPlayer okonogi = Role.OKONOGI.getHPlayer();

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

            okonogiActionRole.getLinkedRole().addPlayer(target);

            int mercenary = Clans.HINAMIZAWA.getUUIDList().size();

            if(mercenary == Clans.MERCENAIRE.getUUIDList().size()){
                okonogi.getPlayer().setMaxHealth(okonogi.getPlayer().getMaxHealth() + 1);
            }

            this.stopTask();
        }
        time--;
    }
}
