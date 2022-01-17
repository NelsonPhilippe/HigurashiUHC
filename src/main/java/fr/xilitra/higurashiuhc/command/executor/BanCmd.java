package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BanCmd extends CommandsExecutor {

    public BanCmd() {
        super("[Ban]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {

        if (strings.length == 2) {

            OryoSonozakiAction oryoSonozakiAction = (OryoSonozakiAction) hPlayer.getRole().getRoleAction();

            Player target = Bukkit.getPlayer(strings[1]);

            if (target == null) {
                p.sendMessage(ChatColor.RED + "Le joueur n'est pas connecté.");
                return true;
            }

            HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(target.getUniqueId());

            if (targetHPlayer == null || Clans.HINAMIZAWA.hisInClans(targetHPlayer, false)) {
                p.sendMessage("vous ne pouvez pas voter pour un membre de votre clans");
                return false;
            }

            oryoSonozakiAction.setAsVoted(targetHPlayer);
            VoteCmd.voteTask.runTaskTimer(1, 1);

            TextComponent message = new TextComponent("Voulez vous voter pour " + target.getName() + " ");
            TextComponent clickable = new TextComponent("[Cliquez pour un vote positif]");
            clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "h vote"));
            message.addExtra(clickable);

            clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "h " + Commands.VOTE.getInitials()));

            for (HPlayer players : Clans.HINAMIZAWA.getHPlayerList()) {

                Player allPlayer = players.getPlayer();
                if (allPlayer != null)
                    allPlayer.spigot().sendMessage(message);

            }

            return true;

        }

        return false;
    }
}
