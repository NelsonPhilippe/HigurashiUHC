package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HealCmd implements CommandsExecutor {
    @Override
    public boolean onCommand(HPlayer hPlayer, String[] strings) {
        Player p = hPlayer.getPlayer();
        if(p==null)
            return false;
        if(strings.length == 2){
            Player target = Bukkit.getPlayer(strings[1]);

            if(target == null){
                p.sendMessage("Cible non trouvé");
                return false;
            }

            if(p.getHealth() <= 1){
                p.sendMessage("Vous ne possédez pas assez de vie pour en donner.");
                return false;
            }

            p.setMaxHealth(p.getMaxHealth() - 1);
            target.setHealth(target.getHealth() + 2);
            p.sendMessage("Vie restaurée");
            return true;
        }
        return false;
    }
}
