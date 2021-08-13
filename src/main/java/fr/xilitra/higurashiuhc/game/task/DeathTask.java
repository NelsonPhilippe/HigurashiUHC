package fr.xilitra.higurashiuhc.game.task;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathTask extends BukkitRunnable {

    private int time = 5;
    private Player p;
    private boolean isStarted = false;

    public DeathTask(Player p) {
        this.p = p;
    }

    @Override
    public void run() {

        isStarted = true;

        if(time == 0){
            for(ItemStack item : p.getInventory().getContents()){
                World world = p.getWorld();

                world.dropItem(p.getLocation(), item);
                p.getInventory().remove(item);
            }

            isStarted = false;
            this.cancel();
        }

        time--;
    }

    public boolean isStarted() {
        return isStarted;
    }
}
