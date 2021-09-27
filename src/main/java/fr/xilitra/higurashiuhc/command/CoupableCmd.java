package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.ClansList;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
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

        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());
        if(hPlayer == null)
            return false;

        if(!hPlayer.getRole().isRole(RoleList.KURAUDO_OISHI.getRole()))
            return false;

        if(args[0].equalsIgnoreCase("c")){

            HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(args[1]);
            if(targetHPlayer == null) return true;
            
            KuraudoOishi oishi = (KuraudoOishi) hPlayer.getRole();

            if(oishi.isCoupableIsDesigned()){
                oishi.setCoupableIsDesigned(true);
            }

            p.sendMessage("Vous avez désigné le joueur " + targetHPlayer.getName() + " coupable");

            if(targetHPlayer.getClans().getName().equalsIgnoreCase(ClansList.SONOZAKI.getName())){
                ItemStack arc = new ItemStack(Material.BOW, 1);
                arc.addEnchantment(Enchantment.ARROW_DAMAGE, 1);

                p.getInventory().addItem(arc);
            }

            if(targetHPlayer.getClans().getName().equalsIgnoreCase(ClansList.HINAMIZAWA.getName())){
                p.sendMessage("L'enquête a échoué.");
            }

            if(targetHPlayer.getRole().isRole(RoleList.RIKA_FURUDE.getRole()))
                ClansList.HINAMIZAWA.addPlayer(targetHPlayer);

            p.sendMessage("Le role du joueur est : " +  targetHPlayer.getRole().getDisplayName());
            p.sendMessage("Le clan du joueur est : " + targetHPlayer.getClans().getName());

            return true;
        }

        if(args[0].equalsIgnoreCase("n")){
            p.sendMessage("Vous avez désignez le joueur non coupable");
            return true;
        }

        return false;
    }
}
