package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishiAction;
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

        if(!hPlayer.getRole().isRole(Role.KURAUDO_OISHI))
            return false;

        if(args[0].equalsIgnoreCase("c")){

            HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(args[1]);
            if(targetHPlayer == null) return true;
            
            KuraudoOishiAction oishi = (KuraudoOishiAction) hPlayer.getRole().getRoleAction();

            if(oishi.isCoupableIsDesigned()){
                oishi.setCoupableIsDesigned(true);
            }

            p.sendMessage("Vous avez désigné le joueur " + targetHPlayer.getName() + " coupable");

            if(targetHPlayer.getClans().getName().equalsIgnoreCase(Clans.SONOZAKI.getName())){
                ItemStack arc = new ItemStack(Material.BOW, 1);
                arc.addEnchantment(Enchantment.ARROW_DAMAGE, 1);

                p.getInventory().addItem(arc);
            }

            if(targetHPlayer.getClans().getName().equalsIgnoreCase(Clans.HINAMIZAWA.getName())){
                p.sendMessage("L'enquête a échoué.");
            }

            if(targetHPlayer.getRole().isRole(Role.RIKA_FURUDE))
                Clans.HINAMIZAWA.addPlayer(targetHPlayer);

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
