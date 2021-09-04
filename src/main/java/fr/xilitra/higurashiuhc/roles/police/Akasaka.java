package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Police;
import fr.xilitra.higurashiuhc.game.task.CouldownMatraque;
import fr.xilitra.higurashiuhc.game.task.StuntTask;
import fr.xilitra.higurashiuhc.item.MatraqueItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class Akasaka extends Role implements Listener {

    private int countCompare = 0;

    public Akasaka() {
        super("Akasaka", Gender.HOMME, Police.getClans(), 1);
    }

    @EventHandler
    public void onPlayerDamage(PlayerItemDamageEvent event){
        Player player = event.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(player.getUniqueId());

        ItemStack item = event.getItem();

        if(item.getItemMeta().getLore().get(0).equals(MatraqueItem.matraqueItem.getLore())){

            if(MatraqueItem.matraqueItem.isUse()){
                return;
            }

            hPlayer.setPlayerDontMove(true);
            Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), new StuntTask(hPlayer), 20, 20);
            Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), new CouldownMatraque(), 20, 20);
        }

    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(player.getUniqueId());
        
        if(hPlayer.playerDontMove()){
            event.setCancelled(true);
        }
    }

    public int getCountCompare() {
        return countCompare;
    }

    public void setCountCompare(int countCompare) {
        this.countCompare = countCompare;
    }

    @Override
    public String getDecription() {
        return "null";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }
}
