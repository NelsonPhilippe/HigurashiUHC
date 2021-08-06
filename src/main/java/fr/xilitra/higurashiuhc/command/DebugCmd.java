package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
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

                for(Role role : Role.values()){

                    if(roleIsAssigned(role)){

                        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayerWithRole(role);

                        hPlayer.getPlayer().sendMessage(
                                ChatColor.GOLD + hPlayer.getRole().getName()
                                        + " : "
                                        + ChatColor.GREEN  + hPlayer.getName());

                    }else {

                        try {
                            RoleTemplate roleTemplate = (RoleTemplate) role.getRole().newInstance();
                            p.sendMessage(ChatColor.GOLD + roleTemplate.getName());
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

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

                if(HigurashiUHC.getGameManager().getPlayers().containsKey(bTarget.getUniqueId())){

                    HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

                    String role = args[2];


                    if(args.length > 3) {
                        StringBuilder builder = new StringBuilder();

                        for(int i = 2; i < args.length; i++){
                            builder.append(args[i] + " " );
                        }

                        role = builder.toString();
                        System.out.println(role);
                    }



                    for(Role roleList : Role.values()){

                        try {
                            RoleTemplate roleTemplate = (RoleTemplate) roleList.getRole().newInstance();

                            if(role.equalsIgnoreCase(roleTemplate.getName() + " ")){
                                hPlayer.setRole(roleTemplate);
                                p.sendMessage(ChatColor.GREEN + "Vous venez d'assigner le role " + role + " à " + args[1]);
                                return true;
                            }

                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                    p.sendMessage(ChatColor.RED + "Aucun role de ce nom existe.");
                    return true;
                }

            }

        }

        return false;
    }

    private boolean roleIsAssigned(Role role){

        try{
            HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayerWithRole(role);

            if(hPlayer == null){
                return false;
            }
        }catch(NullPointerException e){
            return false;
        }

        return true;

    }
}
