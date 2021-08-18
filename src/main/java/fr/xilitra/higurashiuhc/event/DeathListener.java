package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RikaFurude;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        p.setGameMode(GameMode.SPECTATOR);
        HigurashiUHC.getGameManager().startRikaDeathTask();

        Role[] rolesRikaResu = {Role.SATOKO_HOJO, Role.KEIICHI_MAEBARA, Role.MION_SONOZAKI, Role.SHION_SONOSAKI, Role.RENA_RYUGU};

        for(Role role : rolesRikaResu){
            if(role.getRole().getName().equals(hPlayer.getRole().getClass().getName())){
                TextComponent textClick = new TextComponent(ChatColor.DARK_PURPLE + "[ressuciter]");
                TextComponent text = new TextComponent(hPlayer.getRole().getName() + " vien de mourrir ");

                text.addExtra(textClick);

                textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ressucite " + hPlayer.getName()));

                HPlayer hpr = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RIKA_FURUDE);

                if(hpr != null){
                        hpr.getPlayer().spigot().sendMessage(text);
                }
                break;
            }
        }

        if(hPlayer.getRole().getName().equalsIgnoreCase("Rika Furude")){

            HPlayer hanyu = HigurashiUHC.getGameManager().getPlayerWithRole(Role.HANYU);

            if(hanyu == null) return;

            if(hanyu.getPlayer().getGameMode() != GameMode.SPECTATOR){

                TextComponent textClick = new TextComponent(ChatColor.GOLD + "[Oui]");
                textClick.setBold(true);
                TextComponent message = new TextComponent("Voulez vous teleporter Rika dans la dimension : ");
                message.addExtra(textClick);

                textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "t rika"));

            }

        }

    }
}
