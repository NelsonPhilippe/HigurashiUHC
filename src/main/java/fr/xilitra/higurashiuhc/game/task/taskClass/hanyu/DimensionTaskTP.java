package fr.xilitra.higurashiuhc.game.task.taskClass.hanyu;

import fr.xilitra.higurashiuhc.game.task.TaskExecutor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;


public class DimensionTaskTP extends TaskExecutor {

    Player hanyu;
    Player rika;
    World dimension;
    World normalWorld;

    public DimensionTaskTP(Player hanyu, Player rika, World dimension, World normalWorld, int time) {
        this.hanyu = hanyu;
        this.rika = rika;
        this.dimension = dimension;
        this.normalWorld = normalWorld;
        this.runTaskLater(time);
    }

    public static Location getRandomLocation(World world) {

        double borderSize = world.getWorldBorder().getSize();
        double negativeBorder = -(borderSize / 2) + 1;
        double positiveBorder = (borderSize / 2) - 1;

        int randomX = (int) (Math.random() * (positiveBorder - negativeBorder + 1) + negativeBorder);
        int randomZ = (int) (Math.random() * (positiveBorder - negativeBorder + 1) + negativeBorder);

        int minY = 63;

        Location location = new Location(world, randomX, minY, randomZ);

        while (location.getBlock().getType() != Material.AIR) {
            location.setY(location.getBlockY() + 2);
        }

        return location;

    }

    @Override
    public void onExecute() {

        if (hanyu != null && hanyu.isOnline())
            hanyu.teleport(getRandomLocation(normalWorld));

        if (rika != null && rika.isOnline())
            rika.teleport(getRandomLocation(normalWorld));

    }

    @Override
    public void onStart() {

        if (hanyu != null)
            hanyu.teleport(getRandomLocation(dimension));
        if (rika != null)
            rika.teleport(getRandomLocation(dimension));

    }

}
