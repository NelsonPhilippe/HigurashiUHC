package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.mercenaires.Mercenaire;
import fr.xilitra.higurashiuhc.roles.mercenaires.Okonogi;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();

        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        p.setGameMode(GameMode.SPECTATOR);
        HigurashiUHC.getGameManager().startRikaDeathTask();

        RoleList[] rolesRikaResu = {RoleList.SATOKO_HOJO, RoleList.KEIICHI_MAEBARA, RoleList.MION_SONOZAKI, RoleList.SHION_SONOSAKI, RoleList.RENA_RYUGU};

        for(RoleList role : rolesRikaResu){
            if(role.getRole().getName().equals(hPlayer.getRoleList().getRole().getName())){
                TextComponent textClick = new TextComponent(ChatColor.DARK_PURPLE + "[ressuciter]");
                TextComponent text = new TextComponent(hPlayer.getRoleList().getRole().getName() + " vien de mourrir ");

                text.addExtra(textClick);

                textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ressucite " + hPlayer.getName()));

                HPlayer hpr =  RoleList.RIKA_FURUDE.getRole().getPlayer();

                if(hpr != null){
                        hpr.getPlayer().spigot().sendMessage(text);
                }
                break;
            }
        }

        if(hPlayer.getRoleList().getRole().getName().equalsIgnoreCase("Rika Furude")){

            HPlayer hanyu =  RoleList.HANYU.getRole().getPlayer();

            if(hanyu == null) return;

            if(hanyu.getPlayer().getGameMode() != GameMode.SPECTATOR){

                TextComponent textClick = new TextComponent(ChatColor.GOLD + "[Oui]");
                textClick.setBold(true);
                TextComponent message = new TextComponent("Voulez vous teleporter Rika dans la dimension : ");
                message.addExtra(textClick);

                textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "t rika"));

            }

        }

        Player killer = e.getEntity().getKiller();
        HPlayer killerHplayer = HigurashiUHC.getGameManager().getPlayer(killer.getUniqueId());

        killerHplayer.getInfo().put(KuraudoOishi.infoList.KILL,
                String.valueOf(Integer.parseInt(killerHplayer.getInfo().get(KuraudoOishi.infoList.KILL)) + 1));

        if(killerHplayer.getRoleList().getRole().getName().equalsIgnoreCase(RoleList.MERCENAIRE.getRole().getName())){

            Mercenaire mercenaire = (Mercenaire) killerHplayer.getRoleList().getRole();

            if(mercenaire.getCible() != null){

                if(hPlayer == mercenaire.getCible()){

                    killer.setMaxHealth(killer.getMaxHealth() + 1);
                    mercenaire.setCible(null);

                }

            }

        }

        if(hPlayer.getRoleList().getRole().getClans().getName().equalsIgnoreCase("Mercenaire")){

            int random = new Random().nextInt(HigurashiUHC.getGameManager().getPlayers().size()) - 1;

            List<HPlayer> hPlayerList = new ArrayList<>();

            for(HPlayer hPlayer1 : HigurashiUHC.getGameManager().getPlayers().values()){
                hPlayerList.add(hPlayer1);
            }

            HPlayer miyo = RoleList.MIYO_TAKANO.getRole().getPlayer();

            if(miyo != null) {

                miyo.getPlayer().sendMessage(hPlayerList.get(random).getName() + " est " + hPlayerList.get(random).getRoleList().getRole().getName());

            }

        }

        if(hPlayer.getRoleList().getRole().equals(RoleList.RIKA_FURUDE.getRole())){

            HPlayer miyo = RoleList.MIYO_TAKANO.getRole().getPlayer();

            if(miyo != null){

                Bukkit.broadcastMessage(miyo.getRoleList().getRole().getName() + " est " + miyo.getName());

            }

        }

    }
}
