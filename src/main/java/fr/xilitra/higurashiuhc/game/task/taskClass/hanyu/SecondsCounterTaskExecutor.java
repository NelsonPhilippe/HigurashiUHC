package fr.xilitra.higurashiuhc.game.task.taskClass.hanyu;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class SecondsCounterTaskExecutor extends TaskExecutor {

    public boolean active = false;

    @Override
    public void onExecute() {
        active = false;
    }

    @Override
    public void onStart() {
        active = true;
        HPlayer hanyu = Role.HANYU.getHPlayer();

        if (hanyu == null) return;

        if (hanyu.getPlayerState() == PlayerState.INGAME) {

            TextComponent textClick = new TextComponent(ChatColor.GOLD + "[Oui]");
            textClick.setBold(true);
            TextComponent message = new TextComponent("Rika est morte, voulez vous la teleporter Rika dans la dimension : ");
            message.addExtra(textClick);

            textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/h " + Commands.DIMENSION_DEATH.getInitials()));

        }
    }
}
