package fr.xilitra.higurashiuhc.game.task.taskClass;

import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class HanyuTaskInvisble extends BukkitTask {

    private int time = 60;

    @Override
    public void execute() {

        HPlayer hPlayer = Role.HANYU.getHPlayer();
        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();

        if (rika == null || rika.getPlayer() == null) {
            return;
        }

        if (hPlayer == null || hPlayer.getPlayer() == null) {
            return;
        }

        Player hanyuPlayer = hPlayer.getPlayer();

        Location loc = hPlayer.getPlayer().getLocation();

        if (loc.distanceSquared(rika.getPlayer().getLocation()) < 30 * 30) {

            for (Player player : Bukkit.getOnlinePlayers()) {

                player.hidePlayer(hPlayer.getPlayer());

            }

            Player rikaPlayer = rika.getPlayer();
            if (rikaPlayer == null)
                return;

            PlayerConnection craftPlayer = ((CraftPlayer) rikaPlayer).getHandle().playerConnection;
            PacketPlayOutWorldParticles packetPlayOutWorldParticles = new PacketPlayOutWorldParticles(EnumParticle.WATER_SPLASH, true, (float) hanyuPlayer.getLocation().getX(), (float) hanyuPlayer.getLocation().getY(), (float) hanyuPlayer.getLocation().getZ(), 255 / 255f, 0 / 255f, 0 / 255f, 0, 10);

            craftPlayer.sendPacket(packetPlayOutWorldParticles);

        }

        if (time == 0)
            this.stopTask();

        time--;
    }
}
