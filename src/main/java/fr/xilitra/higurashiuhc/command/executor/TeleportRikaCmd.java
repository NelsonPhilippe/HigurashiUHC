package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.game.task.taskClass.DimensionDeathRikaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class TeleportRikaCmd implements CommandsExecutor {
    @Override
    public boolean onCommand(HPlayer hPlayer, String[] args) {

        Player p = hPlayer.getPlayer();

        if(p == null)
            return false;

        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();

        if(rika != null && rika.getPlayer() != null){

            int x = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.x");
            int y = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.y");
            int z = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.z");
            World world = Bukkit.getWorld(HigurashiUHC.getInstance().getConfig().getString("hanyu.dimension.spawn-location.hanyu.world"));

            int xR = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.x");
            int yR = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.y");
            int zR = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.z");
            World worldR = Bukkit.getWorld(HigurashiUHC.getInstance().getConfig().getString("hanyu.dimension.spawn-location.rika.world"));


            rika.getPlayer().teleport(new Location(worldR, xR, yR, zR));
            p.teleport(new Location(world, x, y, z));

            new DimensionDeathRikaTask(rika, hPlayer).runTask(1000,1000);

            p.sendMessage("Attention vous allez être retéléporté dans 30 secondes");
            return true;

        }else{
            p.sendMessage("Rika Introuvable");
        }

        return false;
    }
}
