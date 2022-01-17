package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NearKeiichi extends JavaTask {

    @Override
    public void execute() {
        HPlayer keiichi = Role.KEIICHI_MAEBARA.getHPlayer();
        if(keiichi == null) {
            stopTask();
            return;
        }
        Player keiichiPlayer = keiichi.getPlayer();
        if(keiichiPlayer == null)
            return;
        HPlayer rena = Role.RENA_RYUGU.getHPlayer();
        if(rena == null){
            keiichiPlayer.sendMessage("Rena Inexistante");
            stopTask();
            return;
        }
        Player renaPlayer = rena.getPlayer();
        if(renaPlayer == null || rena.getPlayerState().isState(PlayerState.DISCONNECTED, PlayerState.SPECTATE, PlayerState.WAITING_DEATH)){
            keiichiPlayer.sendMessage("Rena est morte");
            stopTask();
            return;
        }

        Location renaLoc = renaPlayer.getLocation();
        Location keiichiLoc = keiichiPlayer.getLocation();
        if(renaLoc.getWorld() != keiichiLoc.getWorld()) {
            keiichiPlayer.sendMessage(ChatColor.GREEN + "Il semblerai que Rena soit a plus de 50 block de vous");
            return;
        }
        Location diff = new Location(renaLoc.getWorld(), renaLoc.getX()-keiichiLoc.getX(), renaLoc.getY()-keiichiLoc.getY(), renaLoc.getZ()-keiichiLoc.getZ());
        double calcDist = Math.sqrt(Math.pow(diff.getX(), 2) + Math.pow(diff.getY(), 2) + Math.pow(diff.getZ(), 2));
        if(calcDist<=50)
            keiichiPlayer.sendMessage(ChatColor.RED + "Il semblerai que Rena soit a moins de 50 block de vous");
        else keiichiPlayer.sendMessage(ChatColor.GREEN + "Il semblerai que Rena soit a plus de 50 block de vous");
    }
}
