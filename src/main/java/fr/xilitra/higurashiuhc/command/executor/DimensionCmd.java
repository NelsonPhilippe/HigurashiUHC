package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.DimensionTaskTp;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.HanyuAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DimensionCmd implements CommandsExecutor {
    @Override
    public boolean onCommand(HPlayer hPlayer, String[] args) {

        Player p = hPlayer.getPlayer();
        if(p == null)
            return false;

        boolean teleportRika = false;

        int xH = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.x");
        int yH = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.y");
        int zH = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.z");
        ///float pitchH = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.pitch");
        String worldH = HigurashiUHC.getInstance().getConfig().getString("hanyu.dimension.spawn-location.hanyu.world");

        HPlayer rika =  Role.RIKA_FURUDE.getHPlayer();

        int x = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.x");
        int y = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.y");
        int z = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.z");
        ///float pitch = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.pitch");
        String world = HigurashiUHC.getInstance().getConfig().getString("hanyu.dimension.spawn-location.rika.world");

        if(args.length == 2){

            if(rika != null && rika.getPlayer() != null) {

                teleportRika = true;
                rika.getPlayer().sendMessage("Vous allez être téléporté dans la dimension de Hanyu dans 1 minute");
                p.sendMessage("Vous allez être téléporté dans la dimension avec rika dans 1 minute");

            }else {
                p.sendMessage("Rika n'est pas dans la partie.");
                return false;
            }

        }else{
            p.sendMessage("Vous allez être téléporté dans la dimension dans 1 minute");
        }


        boolean finalTeleportRika = teleportRika;
        ((HanyuAction)Role.HANYU.getRoleAction()).setDimensionIsUsed(true);
        new DimensionTaskTp(hPlayer, rika, finalTeleportRika, new Location(Bukkit.getWorld(worldH), xH, yH, zH),  new Location(Bukkit.getWorld(world), x, y, z)).runTask(1000,1000);

        return true;
    }
}
