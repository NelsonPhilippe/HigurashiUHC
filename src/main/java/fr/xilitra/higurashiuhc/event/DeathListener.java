package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        Bukkit.getScheduler().runTask(HigurashiUHC.getInstance(), hPlayer.getDeathTask());

        Role[] rolesRikaResu = {Role.SATOKO_HOJO, Role.KEIICHI_MAEBARA, Role.MION_SONOZAKI, Role.SHION_SONOSAKI, Role.RENA_RYUGU};

        for(Role role : rolesRikaResu){
            if(role.getRole().equals(hPlayer.getRole().getClass())){
                TextComponent textComponent = new TextComponent(ChatColor.DARK_PURPLE + "[ressuciter]");

                textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ressucite " + hPlayer.getName()));

                HPlayer hpr = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RIKA_FURUDE);

                if(hpr != null){
                        hpr.getPlayer().sendMessage(hPlayer.getName() + " viens de mourrir." + textComponent);
                }


                break;
            }
        }

    }
}
