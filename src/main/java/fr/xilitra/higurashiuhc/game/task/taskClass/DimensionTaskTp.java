package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class DimensionTaskTp extends BukkitTask {

    private final HPlayer hanyu;
    private final HPlayer rika;
    private final boolean finalTeleportRika;
    private final Location hLoc;
    private final Location rLoc;
    private int time = HigurashiUHC.getInstance().getConfig().getInt("role.rika.hanyu");
    private boolean firstTeleport = false;

    public DimensionTaskTp(HPlayer hanyu, HPlayer rika, boolean finalTeleportRika, Location hLoc, Location rLoc) {
        this.hanyu = hanyu;
        this.rika = rika;
        this.finalTeleportRika = finalTeleportRika;
        this.hLoc = hLoc;
        this.rLoc = rLoc;
    }

    @Override
    public void execute() {

        if (hanyu.getPlayer() == null) {
            this.stopTask();
            return;
        }

        if (rika != null && rika.getPlayer() == null) {
            this.stopTask();
            return;
        }

        if (time == 30) {

            if (!firstTeleport) {
                hanyu.getPlayer().sendMessage("Teleportation dans la dimension dans 30 secondes");

                if (rika != null) {
                    rika.getPlayer().sendMessage("Teleportation dans la dimension dans 30 secondes");
                }
            }

        }

        if (time == 5 || time == 4 || time == 3 || time == 2 || time == 1) {
            hanyu.getPlayer().sendMessage("Teleportation dans " + time);

            if (rika != null && rika.getPlayer() != null) {
                rika.getPlayer().sendMessage("Teleportation dans " + time);
            }
        }

        if (time == 0) {
            if (!firstTeleport) {
                time = 10;
                firstTeleport = true;
                hanyu.getPlayer().teleport(hLoc);

                if (finalTeleportRika && rika != null) {
                    rika.getPlayer().teleport(rLoc);
                }
                return;
            }

            hanyu.getPlayer().sendMessage("Vous allez être retéléporté aléatoirement dans la map");

            double borderSize = HigurashiUHC.getGameManager().getWorldBorder();
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

            hanyu.getPlayer().teleport(fLocH);

            if (finalTeleportRika && rika != null) {
                rika.getPlayer().sendMessage("Vous allez être retéléporté aléatoirement dans la map");
                rika.getPlayer().teleport(fLocR);
            }

            this.stopTask();
        }

        time--;
    }
}
