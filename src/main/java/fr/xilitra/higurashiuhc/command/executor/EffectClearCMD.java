package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public class EffectClearCMD implements CommandsExecutor {
    @Override
    public boolean onCommand(HPlayer hPlayer, String[] strings) {
        Player player = hPlayer.getPlayer();
        if (player == null)
            return false;

        if (strings.length != 2) {
            player.sendMessage("Syntax: /h " + Commands.EFFECT_CLEAR.getInitials() + " (joueur)");
            return false;
        }

        Player playerTarget = Bukkit.getPlayer(strings[1]);
        if (playerTarget == null) {
            player.sendMessage("Cible introuvable");
            return false;
        }

        Collection<PotionEffect> playerEffectList = playerTarget.getActivePotionEffects();
        player.sendMessage("Le joueur a " + playerEffectList.size() + " effect");

        playerEffectList.forEach(playerEffect -> playerTarget.removePotionEffect(playerEffect.getType()));
        return true;
    }
}
