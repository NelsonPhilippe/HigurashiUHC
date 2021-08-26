package fr.xilitra.higurashiuhc.game.task;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Hinamizawa;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.SatokoHojo;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class RikaDeathTask extends BukkitRunnable {

    private int time = HigurashiUHC.getInstance().getConfig().getInt("rika-death-time") * 60;
    private boolean isStarted = false;

    @Override
    public void run() {

        if(!isStarted){
            for(HPlayer player : HigurashiUHC.getGameManager().getPlayers().values()){

                if(player.getClans() == Hinamizawa.getClans()){
                    player.getPlayer().sendMessage("Rika est morte il vous reste " + time + " avant de subir un malus.");
                }
            }
        }

        this.isStarted = true;

        if(time == 0){
            for(HPlayer hPlayer : HigurashiUHC.getGameManager().getPlayers().values()){
                if(hPlayer.getClans() == Hinamizawa.getClans()){
                    hPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1, true, false));
                    hPlayer.getPlayer().playSound(hPlayer.getPlayer().getLocation(), Sound.GHAST_CHARGE, 5, 5);
                }

                SatokoHojo.removeTraps(hPlayer);
            }



            this.cancel();
        }

        time--;
    }

    public boolean isStarted() {
        return isStarted;
    }
}
