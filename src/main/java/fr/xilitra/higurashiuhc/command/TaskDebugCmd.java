package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import fr.xilitra.higurashiuhc.game.task.TaskReminder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TaskDebugCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) return false;

        Player p = (Player) sender;

        if (!p.isOp()) return false;

        if (strings.length == 0) {
            p.sendMessage("Merci de faire /taskdebug list");
            return false;
        }

        if (strings[0].equalsIgnoreCase("list")) {
            p.sendMessage(ChatColor.WHITE + "List generated task: ");
            for (TaskExecutor taskExecutor : TaskReminder.getTaskList()) {
                p.sendMessage("Task: " + taskExecutor.getClass().getSimpleName() + " id: " + taskExecutor.getTaskID());
            }
            return true;
        }

        return false;
    }
}
