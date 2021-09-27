package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.SatokoHojo;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RikaDeathTask extends BukkitTask {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.rika.weakness") * 60;

    @Override
    public void run() {

        if(!isRunning()){
            for(HPlayer player : HigurashiUHC.getGameManager().getHPlayerList().values()){
                if(player.getPlayer() != null && player.getClans().isClans(Clans.HINAMIZAWA)){
                    player.getPlayer().sendMessage("Rika est morte il vous reste " + time + " avant de subir un malus.");
                }
            }
        }

        if(time == 0){
            for(HPlayer hPlayer : HigurashiUHC.getGameManager().getHPlayerList().values()){
                if(hPlayer.getPlayer() != null && hPlayer.getClans().isClans(Clans.HINAMIZAWA)){
                    hPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1, true, false));
                    hPlayer.getPlayer().playSound(hPlayer.getPlayer().getLocation(), Sound.GHAST_CHARGE, 5, 5);
                }

                ((SatokoHojo) RoleList.SATOKO_HOJO.getRole()).removeTraps(hPlayer);
            }



            this.stopTask();
        }

        time--;
    }

}
