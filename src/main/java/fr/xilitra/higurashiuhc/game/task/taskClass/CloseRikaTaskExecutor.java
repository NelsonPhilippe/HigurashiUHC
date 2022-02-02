package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.HanyuAction;
import fr.xilitra.higurashiuhc.utils.MathMain;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class CloseRikaTaskExecutor extends TaskExecutor {

    List<String> playerWithEffect = new ArrayList<>();
    public int hanyuInvisible = 0;

    @Override
    public void onExecute() {

        if (hanyuInvisible != 0)
            hanyuInvisible--;

        Role rf = Role.RIKA_FURUDE;
        if (rf.getHPlayer() == null)
            return;

        Player rikaPlayer = rf.getHPlayer().getPlayer();
        if (rikaPlayer == null)
            return;

        Location rikaLocation = rikaPlayer.getLocation();

        HPlayer hanyu = Role.HANYU.getHPlayer();
        if(hanyu != null) {
            HanyuAction hanyuAction = (HanyuAction) Role.HANYU.getRoleAction();
            Player hanyuPlayer = hanyu.getPlayer();
            if (hanyuPlayer != null && hanyuInvisible == 0){

                double away = MathMain.calculLength(hanyuPlayer.getLocation(), rikaLocation, true);
                hanyuAction.setInvisible(away <= 30);

            }else{
                hanyuAction.setInvisible(false);
            }
        }

        if(!HigurashiUHC.getGameManager().isWataState(WataEnum.AFTER))
            return;

        for (HPlayer hPlayer : Clans.MEMBER_OF_CLUB.getHPlayerList()) {

            Player player = hPlayer.getPlayer();
            if (player == null || hPlayer.hasMalediction() || player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE))
                continue;

            Location playerLocation = player.getLocation();

            if (playerLocation.getWorld().getName().equals(rikaLocation.getWorld().getName())) {
                playerWithEffect.remove(hPlayer.getName());
                continue;
            }

            if (MathMain.calculLength(rikaLocation, playerLocation, true) <= 20) {
                if(playerWithEffect.contains(hPlayer.getName()))
                    continue;
                playerWithEffect.add(hPlayer.getName());
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 1));
            }else
                playerWithEffect.remove(hPlayer.getName());

        }

    }
}
