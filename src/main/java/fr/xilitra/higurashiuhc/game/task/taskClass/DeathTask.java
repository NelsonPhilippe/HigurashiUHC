package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.JavaTask;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DeathTask extends JavaTask {

    private int time = 5;
    private Player p;

    public DeathTask(Player p) {
        super("deathtask-"+p.getName());
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
