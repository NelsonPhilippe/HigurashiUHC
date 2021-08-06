package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.Hanyu;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HigurashiCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("pense")){

                HPlayer rena = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RENA_RYUGU);

                if(rena == null || rena.getPlayer().getUniqueId() != p.getUniqueId()) return true;

                if(((RenaRyugu) rena.getRole()).gethPlayerPense() == null) return true;

                Player target = Bukkit.getPlayer(args[1]);

                if(target == null) {
                    p.sendMessage(ChatColor.RED + "Le joueur n'est pas connecté.");
                    return true;
                }

                for(HPlayer hPlayer : HigurashiUHC.getGameManager().getPlayers().values()){

                    if(hPlayer.getName().equalsIgnoreCase(args[1])){
                        ((RenaRyugu) rena.getRole()).setHPlayerPense(hPlayer);
                        rena.getPlayer().sendMessage("Vous lisez dans les pensé de " + args[1]);
                        break;
                    }

                }

                return true;

            }


        }
        if(args[0].equalsIgnoreCase("dimension")){
            HPlayer hanyu = HigurashiUHC.getGameManager().getPlayerWithRole(Role.HANYU);

            if(hanyu == null || hanyu.getPlayer().getUniqueId() != p.getUniqueId()) return true;

            Hanyu hanyuRole = (Hanyu) hanyu.getRole();

            if(hanyuRole.isDimensionIsUsed()) {

                p.sendMessage("Vous avez déjà utilisé la téléportation a votre dimension.");
                return true;
            }

            if(args.length == 2){
                if(args[1].equalsIgnoreCase("rika")){
                    HPlayer rika = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RIKA_FURUDE);
                    if(rika == null) return true;

                    int x = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.x");
                    int y = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.y");
                    int z = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.z");
                    float pitch = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.pitch");
                    String world = HigurashiUHC.getInstance().getConfig().getString("hanyu.dimension.spawn-location.rika.world");
                    Location loc = new Location(Bukkit.getWorld(world), x, y, z);
                    rika.getPlayer().teleport(loc);

                    return true;
                }
            }

            int x = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.x");
            int y = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.y");
            int z = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.z");
            float pitch = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.pitch");
            String world = HigurashiUHC.getInstance().getConfig().getString("hanyu.dimension.spawn-location.hanyu.world");
            Location loc = new Location(Bukkit.getWorld(world), x, y, z);
            hanyu.getPlayer().teleport(loc);


        }

        return false;
    }
}
