package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HanyuTaskInvisble extends JavaTask {

    private int time = 60;

    public HanyuTaskInvisble(Player player){
        super("hanyuInvisible"+"->"+player.getName());
    }

    @Override
    public void run() {


        if(time == 0){

            HPlayer hPlayer = RoleList.HANYU.getRole().getPlayer();
            HPlayer rika = RoleList.RIKA_FURUDE.getRole().getPlayer();

            if(rika == null){
                return;
            }

            if(hPlayer != null){

                Location loc = hPlayer.getPlayer().getLocation();


                if(loc.distanceSquared(rika.getPlayer().getLocation()) < 30 * 30) {

                    for (Player player : Bukkit.getOnlinePlayers()) {

                        player.hidePlayer(hPlayer.getPlayer());

                    }

                }

            }


            this.cancel();
        }

        time--;
    }
}
