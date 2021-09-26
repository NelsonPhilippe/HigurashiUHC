package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(!p.isOp()) return true;

        if(args.length == 1){

            if(args[0].equalsIgnoreCase("listroles")){

                p.sendMessage(ChatColor.DARK_PURPLE + "--- Liste des roles ---");

                for(RoleList role : RoleList.values()){

                    if(role.getRole().isAssigned()){

                        HPlayer hPlayer = role.getRole().getHPlayer();
                        if(hPlayer != null)
                            p.sendMessage(
                                ChatColor.GOLD + hPlayer.getRole().getName()
                                        + " : "
                                        + ChatColor.GREEN  + hPlayer.getName());

                    }else
                        p.sendMessage(ChatColor.GOLD + role.getRole().getName());

                }
                return true;
            }

        }

        if(args.length >= 3){

            if(args[0].equalsIgnoreCase("giveroles")){

                String target = args[1];
                Player bTarget = Bukkit.getPlayer(target);

                if(bTarget == null) {
                    p.sendMessage(ChatColor.RED + "Le joueur n'est pas connecté.");
                    return true;
                }

                if(HigurashiUHC.getGameManager().getHPlayerList().containsKey(bTarget.getUniqueId())){


                    HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(bTarget.getUniqueId());

                    if(hPlayer == null) return false;

                    System.out.println(hPlayer.getName());

                    String role = args[2];


                    if(args.length >= 4) {
                        StringBuilder builder = new StringBuilder();

                        for(int i = 2; i < args.length; i++){
                            builder.append(args[i]).append(" ");
                        }

                        role = builder.toString();
                    }

                    System.out.println(role);



                    for(RoleList roleList : RoleList.values()){


                        if(role.equalsIgnoreCase(roleList.getRole().getName() + " ") || role.equalsIgnoreCase(roleList.getRole().getName())){
                            hPlayer.setRole(roleList.getRole());
                            Bukkit.broadcastMessage(hPlayer.getName() + " est devenu " + hPlayer.getRole().getName());
                            p.sendMessage(ChatColor.GREEN + "Vous venez d'assigner le role " + role + "à " + args[1]);
                            HigurashiUHC.getGameManager().getHPlayerList().put(hPlayer.getUuid(), hPlayer);
                            Bukkit.getServer().getPluginManager().callEvent(new RoleSelected(hPlayer));
                            return true;
                        }

                    }

                    p.sendMessage(ChatColor.RED + "Aucun role de ce nom existe.");
                    return true;
                }

            }

        }

        return false;
    }

}
