package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class DimensionDeathRikaTask extends BukkitTask {

    private final HPlayer rika;
    private final HPlayer hanyu;
    private int time = 30;

    public DimensionDeathRikaTask(HPlayer rika, HPlayer hanyu) {
        this.rika = rika;
        this.hanyu = hanyu;
    }

    @Override
    public void execute() {

        if (time == 0) {

            double borderSize = HigurashiUHC.getGameManager().getWorldBorder().getSize();
            double negativeBorder = -(borderSize / 2) + 1;
            double positiveBorder = (borderSize / 2) - 1;

            int randomX = (int) (Math.random() * (positiveBorder - negativeBorder + 1) + negativeBorder);
            int randomZ = (int) (Math.random() * (positiveBorder - negativeBorder + 1) + negativeBorder);


            int yMin = 63;
            Location fLocR = new Location(Bukkit.getWorld("world"), randomX, yMin, randomZ);

            int randomXH = (int) (Math.random() * (positiveBorder - negativeBorder + 1) + negativeBorder);
            int randomZH = (int) (Math.random() * (positiveBorder - negativeBorder + 1) + negativeBorder);

            Location fLocH = new Location(Bukkit.getWorld("world"), randomXH, yMin, randomZH);


            while (fLocR.getBlock().getType() != Material.AIR) {
                fLocR.setY(fLocR.getBlockY() + 2);
            }

            while (fLocH.getBlock().getType() != Material.AIR) {
                fLocH.setY(fLocH.getBlockY() + 2);
            }

            if (rika.getPlayer() != null)
                rika.getPlayer().teleport(fLocR);

            if (hanyu.getPlayer() != null)
                hanyu.getPlayer().teleport(fLocH);

            this.stopTask();
        }

        time--;

    }
}
