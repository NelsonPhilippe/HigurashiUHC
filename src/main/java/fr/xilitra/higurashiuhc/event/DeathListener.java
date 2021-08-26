package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.mercenaires.Mercenaire;
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
        HigurashiUHC.getGameManager().setPlayerState(hPlayer, PlayerState.WAITING_DEATH);
        HigurashiUHC.getGameManager().startRikaDeathTask();

        if(hPlayer.getRoleList().getRole().isRole(RoleList.SATOKO_HOJO.getRole(), RoleList.KEIICHI_MAEBARA.getRole(), RoleList.MION_SONOZAKI.getRole(), RoleList.SHION_SONOSAKI.getRole(), RoleList.RENA_RYUGU.getRole())){
            TextComponent textClick = new TextComponent(ChatColor.DARK_PURPLE + "[ressuciter]");
            TextComponent text = new TextComponent(hPlayer.getRoleList().getRole().getName() + " vien de mourrir ");

            text.addExtra(textClick);

            textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ressucite " + hPlayer.getName()));

            HPlayer hpr =  RoleList.RIKA_FURUDE.getRole().getPlayer();

            if(hpr != null){
                hpr.getPlayer().spigot().sendMessage(text);
            }
        }

        if(hPlayer.getRoleList().getRole().isRole(RoleList.RIKA_FURUDE.getRole())){

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

        if(MercenaireClan.getClans().hisInClans(hPlayer.getRoleList().getRole())){

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
