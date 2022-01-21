package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public class EffectCMD extends CommandsExecutor {

    public EffectCMD() {
        super("[Effect]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player player, String[] strings) {

        if (strings.length != 2) {
            sendMessage(player, ChatColor.RED, "Syntax: /h " + Commands.EFFET_LISTENER.getInitials() + " (joueur)");
            return false;
        }

        Player playerTarget = Bukkit.getPlayer(strings[1]);
        if (playerTarget == null) {
            sendMessage(player, ChatColor.RED, "Cible introuvable");
            return false;
        }

        Collection<PotionEffect> playerEffect = playerTarget.getActivePotionEffects();
        if(playerEffect.size() == 0) {
            sendMessage(player, ChatColor.RED, "§a§oLe joueur ne possède aucun effet.");
            return true;
        }

        playerEffect.forEach(potionEffect -> player.sendMessage("Effect: " + potionEffect.getType().getName() + ", puissance: " + potionEffect.getAmplifier()));

        if (hPlayer.hasCommandAccess(Commands.EFFET_CLEAR)) {
            TextComponent textComponent = new TextComponent("Voulez-vous lui retirer touts ses effects? ");
            TextComponent clickOUI = new TextComponent("OUI");
            clickOUI.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, Commands.EFFET_CLEAR.getInitials() + " " + playerTarget.getName()));
            clickOUI.setColor(ChatColor.RED);
            textComponent.addExtra(clickOUI);
            player.spigot().sendMessage(textComponent);
        }

        return true;
    }
}
