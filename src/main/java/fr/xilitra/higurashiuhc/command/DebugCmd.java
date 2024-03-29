package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.GameStates;
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

        if (!(sender instanceof Player)) return false;

        Player p = (Player) sender;

        if (!p.isOp()) return false;

        if (args.length == 1) {

            if (args[0].equalsIgnoreCase("listroles")) {

                p.sendMessage(ChatColor.DARK_PURPLE + "--- Liste des roles ---");

                for (Role role : Role.values()) {

                    if (role.isAssigned()) {

                        HPlayer hPlayer = role.getHPlayer();
                        if (hPlayer != null)
                            p.sendMessage(
                                    ChatColor.GOLD + hPlayer.getRole().getName()
                                            + " : "
                                            + ChatColor.GREEN + hPlayer.getName());

                    } else
                        p.sendMessage(ChatColor.GOLD + role.getName());

                }
                return true;
            } else if (args[0].equalsIgnoreCase("addEP")) {

                if (HigurashiUHC.getGameManager().getStates() == GameStates.GAME) {

                    int ep = HigurashiUHC.getGameManager().getEpisode() + 1;
                    HigurashiUHC.getGameManager().setEpisode(ep);

                } else p.sendMessage(ChatColor.RED + "Game not started");

                return true;

            } else if (args[0].equalsIgnoreCase("getEP")) {
                HigurashiUHC.getGameManager().broadcast("Episode: " + HigurashiUHC.getGameManager().getEpisode());
                return true;
            }

        }

        if (args.length >= 3) {

            if (args[0].equalsIgnoreCase("giveroles")) {

                String target = args[1];
                Player bTarget = Bukkit.getPlayer(target);

                if (bTarget == null) {
                    p.sendMessage(ChatColor.RED + "Le joueur n'est pas connecté.");
                    return true;
                }

                if (HigurashiUHC.getGameManager().getHPlayerList().containsKey(bTarget.getUniqueId())) {


                    HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(bTarget.getUniqueId());

                    if (hPlayer == null) return false;

                    System.out.println(hPlayer.getName());

                    String role = args[2];


                    if (args.length >= 4) {
                        StringBuilder builder = new StringBuilder();

                        for (int i = 2; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }

                        role = builder.toString();
                    }

                    System.out.println(role);


                    for (Role roleList : Role.values()) {


                        if (role.equalsIgnoreCase(roleList.getName() + " ") || role.equalsIgnoreCase(roleList.getName())) {
                            hPlayer.setRole(roleList, true);
                            Bukkit.broadcastMessage(hPlayer.getName() + " est devenu " + hPlayer.getRole().getName());
                            p.sendMessage(ChatColor.GREEN + "Vous venez d'assigner le role " + role + "à " + args[1]);
                            HigurashiUHC.getGameManager().getHPlayerList().put(hPlayer.getUUID(), hPlayer);
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
