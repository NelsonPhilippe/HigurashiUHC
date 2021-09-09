package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DeathTask extends BukkitTask {

    private int time = 5;
    private final Player p;

    public DeathTask(Player p) {
        this.p = p;
    }

    @Override
    public void run() {

        if(time == 0){
            for(ItemStack item : p.getInventory().getContents()){
                World world = p.getWorld();

                world.dropItem(p.getLocation(), item);
                p.getInventory().remove(item);
            }

            this.stopTask();
        }

        time--;
    }

}
