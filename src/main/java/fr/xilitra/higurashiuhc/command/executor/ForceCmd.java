package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ForceCmd implements CommandsExecutor {
    @Override
    public boolean onCommand(HPlayer hPlayer, String[] strings) {
        Player p = hPlayer.getPlayer();
        if(p == null)
            return false;
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000, 1));
        p.sendMessage("Votre force a été boosté!");
        return true;
    }
}
