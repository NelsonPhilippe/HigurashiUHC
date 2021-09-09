package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.JavaTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class DimensionDeathRikaTask extends JavaTask {

    private int time = 30;
    private HPlayer rika;
    private HPlayer hanyu;

    public DimensionDeathRikaTask(HPlayer rika, HPlayer hanyu) {
        super("ddrt-"+rika.getName()+"->"+hanyu.getName());
        this.rika = rika;
        this.hanyu = hanyu;
    }

    @Override
    public void run() {

        if(time == 0){

            double borderSize = HigurashiUHC.getGameManager().getWorldBorder();
            double negativeBorder = -(borderSize / 2) +1;
            double positiveBorder = (borderSize / 2) - 1;

            int randomX = (int) (Math.random() * (positiveBorder - negativeBorder + 1) + negativeBorder);
            int randomZ = (int) (Math.random() * (positiveBorder - negativeBorder + 1) + negativeBorder);



            int yMin = 63;
            Location fLocR = new Location(Bukkit.getWorld("world"), randomX, yMin, randomZ);

            int randomXH = (int) (Math.random() * (positiveBorder - negativeBorder + 1) + negativeBorder);
            int randomZH = (int) (Math.random() * (positiveBorder - negativeBorder + 1) + negativeBorder);

            Location fLocH = new Location(Bukkit.getWorld("world"), randomXH, yMin, randomZH);


            while (fLocR.getBlock().getType() != Material.AIR){
                fLocR.setY(fLocR.getBlockY() + 2);
            }

            while (fLocH.getBlock().getType() != Material.AIR){
                fLocH.setY(fLocH.getBlockY() + 2);
            }

            rika.getPlayer().teleport(fLocR);
            hanyu.getPlayer().teleport(fLocH);

            this.stopTask();
        }

    }
}