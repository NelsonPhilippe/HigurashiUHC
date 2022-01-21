package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ForceCmd extends CommandsExecutor {

    public ForceCmd() {
        super("[Force]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000, 0));
        sendOkay(p, "Votre force a été boosté");
        return true;
    }
}
