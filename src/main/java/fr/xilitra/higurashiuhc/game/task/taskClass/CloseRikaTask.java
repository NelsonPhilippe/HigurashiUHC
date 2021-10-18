package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.MathMain;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class CloseRikaTask extends BukkitTask {

    List<String> playerWithEffect = new ArrayList<>();

    @Override
    public void execute() {

        Role rf = Role.RIKA_FURUDE;
        if (rf.getHPlayer() == null)
            return;

        Player rikaPlayer = rf.getHPlayer().getPlayer();
        if (rikaPlayer == null)
            return;

        Location rikaLocation = rikaPlayer.getLocation();

        for (HPlayer hPlayer : Clans.MEMBER_OF_CLUB.getHPlayerList()) {

            Player player = hPlayer.getPlayer();
            if (player == null || hPlayer.hasMalediction() || player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE))
                return;

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
