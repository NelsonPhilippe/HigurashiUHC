package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.taskClass.CouldownMatraque;
import fr.xilitra.higurashiuhc.game.task.taskClass.StuntTask;
import fr.xilitra.higurashiuhc.item.MatraqueItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class AkasakaAction extends RoleAction implements Listener {

    private int countCompare = 0;

    @EventHandler
    public void onPlayerDamage(PlayerItemDamageEvent event){
        Player player = event.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(player.getUniqueId());
        if(hPlayer == null)
            return;

        ItemStack item = event.getItem();

        if(item.getItemMeta().getLore().get(0).equals(MatraqueItem.matraqueItem.getLore())){

            if(MatraqueItem.matraqueItem.isUse()){
                return;
            }

            hPlayer.setPlayerDontMove(true);
            new StuntTask(hPlayer).runTaskTimer(1000,1000);
            new CouldownMatraque().runTaskLater(HigurashiUHC.getInstance().getConfig().getInt("role.akasaka.matraque") * 1000L);
        }

    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(player.getUniqueId());

        if(hPlayer == null)
            return;

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
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
