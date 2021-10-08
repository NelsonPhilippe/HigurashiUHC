package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MiyoTakanoAction extends RoleAction implements Listener {

    @EventHandler
    public void onRoleSelected(RoleSelected e) {
        HPlayer player = e.getPlayer();

        if (player.getRole().isRole(this.getLinkedRole()) && player.getPlayer() != null) {

            Role[] mercenenaire = {Role.MERCENAIRE, Role.OKONOGI};
            player.getPlayer().sendMessage(ChatColor.RED + "--Liste des Mercenaires--");

            for (HPlayer hPlayer : HigurashiUHC.getGameManager().getHPlayerList().values()) {
                if (hPlayer.getPlayer() == null)
                    continue;
                for (Role role : mercenenaire) {
                    if (hPlayer.getRole().equals(role)) {

                        player.getPlayer().sendMessage(ChatColor.GREEN + hPlayer.getRole().getName() + " : " + ChatColor.GOLD + hPlayer.getName());

                    }
                }
            }

        }

    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

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

    }

    @Override
    public void onGameStop() {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
