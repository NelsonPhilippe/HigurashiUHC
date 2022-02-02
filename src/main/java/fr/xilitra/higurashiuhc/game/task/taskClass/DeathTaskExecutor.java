package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DeathTaskExecutor extends TaskExecutor {

    private final Player p;
    private int time = 5;

    public DeathTaskExecutor(Player p) {
        this.p = p;
    }

    @Override
    public void onExecute() {

        if (time == 0) {
            for (ItemStack item : p.getInventory().getContents()) {
                World world = p.getWorld();

                world.dropItem(p.getLocation(), item);
                p.getInventory().remove(item);
            }

            this.stopTask();
        }

        time--;
    }

}
