package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Hinamizawa;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Sonozaki;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RikaFurude;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CoupableCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        if(hPlayer.getRole().getName().equals(RoleList.KURAUDO_OISHI.getRole().getName())){

            if(args[0].equalsIgnoreCase("c")){

                Player target = Bukkit.getPlayer(args[1]);
                if(target == null) return true;

                HPlayer targetHPlayer = HigurashiUHC.getGameManager().getPlayer(target.getUniqueId());

                HPlayer playerOishi =  RoleList.KURAUDO_OISHI.getRole().getPlayer();
                KuraudoOishi oishi = (KuraudoOishi) playerOishi.getRole();

                if(oishi.isCoupableIsDesigned()){
                    oishi.setCoupableIsDesigned(true);
                }

                playerOishi.getPlayer().sendMessage("Vous avez désigné le joueur " + target.getName() + " coupable");


                if(targetHPlayer.getClans().getName().equalsIgnoreCase(Sonozaki.getClans().getName())){
                    ItemStack arc = new ItemStack(Material.BOW, 1);
                    arc.addEnchantment(Enchantment.ARROW_DAMAGE, 1);

                    p.getInventory().addItem(arc);
                }

                if(targetHPlayer.getClans().getName().equalsIgnoreCase(Hinamizawa.getClans().getName())){
                    p.sendMessage("L'enquête a échoué.");
                }

                if(targetHPlayer.getRole().getName().equals(RoleList.RIKA_FURUDE.getRole().getName())){

                    targetHPlayer.setClans(Hinamizawa.getClans());

                }

                if(targetHPlayer.getRole().getName().equalsIgnoreCase(RoleList.MION_SONOZAKI.getRole().getName())){

                    if(ScenarioList.DOLL.isActive()){
                        playerOishi.getPlayer().sendMessage("Le role du joueur est : " +  targetHPlayer.getRole().getDisplayName());
                        playerOishi.getPlayer().sendMessage("Le clan du joueur est : " + targetHPlayer.getClans().getName());

                        return true;
                    }


                }

                if(targetHPlayer.getRole().getName().equalsIgnoreCase(RoleList.SHION_SONOSAKI.getRole().getName())){
                    if(ScenarioList.DOLL.isActive()){
                        playerOishi.getPlayer().sendMessage("Le role du joueur est : " +  targetHPlayer.getRole().getDisplayName());
                        playerOishi.getPlayer().sendMessage("Le clan du joueur est : " + targetHPlayer.getClans().getName());

                        return true;
                    }

                }

                playerOishi.getPlayer().sendMessage("Le role du joueur est : " +  targetHPlayer.getRole().getName());
                playerOishi.getPlayer().sendMessage("Le clan du joueur est : " + targetHPlayer.getClans().getName());

                return true;
            }

            if(args[0].equalsIgnoreCase("n")){
                p.sendMessage("Vous avez désignez le joueur non coupable");
                return true;
            }

        }

        return false;
    }
}
