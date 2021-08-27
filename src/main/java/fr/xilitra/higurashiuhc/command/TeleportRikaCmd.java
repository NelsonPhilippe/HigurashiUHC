package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.DimensionDeathRikaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportRikaCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        if(args.length != 1) return true;

        if(args[0].equalsIgnoreCase("rika")){
            if(hPlayer.getRole().getName().equalsIgnoreCase("Hanyu")){

                HPlayer rika =  RoleList.RIKA_FURUDE.getRole().getPlayer();

                if(rika != null){

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

                    Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), new DimensionDeathRikaTask(rika, hPlayer), 20, 20);

                    p.sendMessage("Attention vous allez être retéléporté dans 30 secondes");

                }

            }
        }

        return false;
    }
}
