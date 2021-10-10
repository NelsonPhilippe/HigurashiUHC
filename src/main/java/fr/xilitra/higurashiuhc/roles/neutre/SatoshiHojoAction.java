package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.InfoData;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class SatoshiHojoAction extends RoleAction implements Listener {

    public Scoreboard scoreBoard = new Scoreboard();

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

        List<Role> interestRole = new ArrayList<Role>() {{
            add(Role.KEIICHI_MAEBARA);
            add(Role.RENA_RYUGU);
            add(Role.SHION_SONOSAKI);
            add(Role.SATOKO_HOJO);
            add(Role.MION_SONOZAKI);
        }};

        if (!killed.getRole().isRole(interestRole)) return;
        if (killed.hasMalediction()) return;

        Player player = killer.getPlayer();
        if (player == null) return;

        player.setMaxHealth(player.getMaxHealth() + 1);
        int kill = 0;
        for (Role role : interestRole) {

            HPlayer hPlayer = role.getHPlayer();
            if (hPlayer == null || hPlayer.getKiller() == null || !(hPlayer.getKiller() instanceof Player) || hPlayer.hasMalediction())
                continue;

            if (hPlayer.getKiller().getUniqueId().toString().equals(killer.getUUID().toString()))
                kill += 1;

        }

        if (kill >= 4)
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 1));

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {
        HPlayer satoshi = getLinkedRole().getHPlayer();
        if (satoshi != null) {
            satoshi.addMaledictionReason(Reason.SATOSHI_HOJO);
            satoshi.getInfoData().setDataInfo(InfoData.InfoList.CLAN.name(), Clans.HINAMIZAWA.getName());
            if (satoshi.getPlayer() != null) {

                List<UUID> memberOfClub = Clans.MEMBER_OF_CLUB.getUUIDList();
                if (!memberOfClub.isEmpty()) {

                    UUID luckyOrNot = memberOfClub.get(new Random().nextInt(memberOfClub.size()));
                    satoshi.getPlayer().sendMessage("Le joueur: " + Objects.requireNonNull(HigurashiUHC.getGameManager().getHPlayer(luckyOrNot)).getName() + " est un membre du club");

                } else
                    satoshi.getPlayer().sendMessage("Il n'y a aucun membre dans le clan du " + Clans.MEMBER_OF_CLUB.getName());

            }
        }
    }

    @Override
    public void onGameStop() {
        if(getLinkedRole().getHPlayer() == null || getLinkedRole().getHPlayer().getPlayer() == null)
            return;

        if(scoreBoard.getObjective("health") == null)
            return;

        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(0, scoreBoard.getObjective("health"));//Create display packet set to under name mode

        sendPacket(getLinkedRole().getHPlayer().getPlayer(), display);
    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

    @EventHandler
    public void onWataStateChange(WatanagashiChangeEvent wce){
        if(wce.getWataEnum() == WataEnum.AFTER){

            if(getLinkedRole().getHPlayer() == null || getLinkedRole().getHPlayer().getPlayer() == null)
                return;

            scoreBoard.registerObjective("health", IScoreboardCriteria.g);

            PacketPlayOutScoreboardObjective packet = new PacketPlayOutScoreboardObjective(scoreBoard.getObjective("health"), 0);//Create Scoreboard create packet
            PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(2, scoreBoard.getObjective("health"));//Create display packet set to under name mode

            sendPacket(getLinkedRole().getHPlayer().getPlayer(), packet);//Send Scoreboard create packet
            sendPacket(getLinkedRole().getHPlayer().getPlayer(), display);

        }
    }

    public void sendPacket(Player p, Packet<?> packet){
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

}
