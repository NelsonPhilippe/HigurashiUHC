package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.utils.TimeUtils;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.Map;
import java.util.UUID;

public class GameTask extends BukkitTask {

    private final int timeEpisode = HigurashiUHC.getInstance().getConfig().getInt("phase-time") * 60;
    private int worldborderActivation = HigurashiUHC.getInstance().getConfig().getInt("activation-time");
    private int time = HigurashiUHC.getInstance().getConfig().getInt("phase-time") * 60;

    @Override
    public void execute() {

        if (worldborderActivation == 0) {
            worldborderActivation--;
            World world = Bukkit.getWorld("world");

            WorldBorder border = world.getWorldBorder();
            double blockReduce = HigurashiUHC.getInstance().getConfig().getInt("wordborder-time-reduce");
            border.setSize(HigurashiUHC.getInstance().getConfig().getInt("worldborder") - blockReduce);
        } else if (worldborderActivation > 0)
            worldborderActivation--;

        if (time == 0) {
            time = timeEpisode;
            HigurashiUHC.getGameManager().setEpisode(HigurashiUHC.getGameManager().getEpisode() + 1);
            if (HigurashiUHC.getGameManager().getWataState() == WataEnum.BEFORE
                    && HigurashiUHC.getGameManager().getEpisode() + HigurashiUHC.getInstance().getConfig().getInt("role.tomitake.knownrole") == GameManager.getWataEpisode()
            ) {
                HPlayer jiro = null;
                for (HPlayer hPlayer : Role.MERCENAIRE.getHPlayerList())
                    if (hPlayer.getInfoData().getBoolean("hiddenJiro")) {
                        jiro = hPlayer;
                        break;
                    }

                if (jiro != null)
                    jiro.setRole(Role.JIRO_TOMITAKE, true);
            }
        }

        time--;
    }
}
