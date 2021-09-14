package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.player.LinkData;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.KeiichiMaebara;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.SatokoHojo;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import fr.xilitra.higurashiuhc.scenario.Oyashiro;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DamageListener implements Listener {

    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();

        if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {

            if (!(e.getDamager() instanceof Player)) return;

            Player damager = (Player) e.getDamager();

            ItemStack item = damager.getItemInHand();

            if (item.isSimilar(CustomCraft.baseballBat.getItemStack())) {

                if(CustomCraft.baseballBat.isUsedOnEpisode()){
                    damager.sendMessage("Vous avez déjà utilisé la batte de baseball pour cet episode.");
                    CustomCraft.baseballBat.setUsedOnEpisode(true);
                    return;
                }

                if(CustomCraft.baseballBat.getUsed() == 0){
                    damager.sendMessage("Vous avez déja utilisé 3 fois la batte de baseball");
                    return;
                }

                if(CustomCraft.baseballBat.getUsed() == 1){
                    damager.getItemInHand().setType(Material.AIR);
                    damager.playSound(damager.getLocation(), Sound.ITEM_BREAK, 1, 1);
                    return;
                }

                for (HPlayer players : HigurashiUHC.getGameManager().getPlayerList().values()) {
                    if (players.getPlayer().getLocation().distanceSquared(damager.getLocation()) < 5 * 5) {
                        if (players.getPlayer() != damager) {

                            players.getPlayer().damage(5);
                            e.setDamage(5);
                            CustomCraft.baseballBat.setUsed(CustomCraft.baseballBat.getUsed() - 1);

                        }
                    }
                }
            }

            HPlayer player =  RoleList.RENA_RYUGU.getRole().getPlayer();

            if(player != null){

                RenaRyugu renaRyugu = (RenaRyugu) player.getRole();

                if(renaRyugu.gethPlayerPense() != null){

                    System.out.println(renaRyugu.gethPlayerPense().getUuid().toString());

                    if(renaRyugu.gethPlayerPense().getUuid().equals(damager.getUniqueId())){

                        if(!renaRyugu.isPenseIsUsed()) {

                            player.getPlayer().sendMessage(p.getName() + " à frappé un joueur.");
                            renaRyugu.setPenseIsUsed(true);

                        }

                    }

                }

            }

        }

    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){

        if(!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        double damage = limitDamage(e.getDamage());

        if(p.getHealth() <= 20) {

            if(p.getHealth() - e.getFinalDamage() <=5 && p.getHealth() - e.getFinalDamage()>0 ){

                if(hPlayer.hasMarriedReason(Reason.DOLL_TRAGEDY)) {
                    hPlayer.getMarriedPlayer(Reason.DOLL_TRAGEDY).forEach((player) -> player.getPlayer().sendMessage("Ton amoureux(se): "+hPlayer.getName()+" à le malheur de passer en-dessous de 5 coeurs"));
                }

            }

            if (hPlayer.getRole().isRole(RoleList.MION_SONOZAKI.getRole())) {

                HPlayer shionPlayer = RoleList.SHION_SONOSAKI.getRole().getPlayer();
                if (shionPlayer == null) return;

                if (shionPlayer.getPlayer().getHealth() <= 20) return;

                shionPlayer.getPlayer().damage(damage);

            }

            if (hPlayer.getRole().isRole(RoleList.SHION_SONOSAKI.getRole())) {

                HPlayer mionPlayer = RoleList.MION_SONOZAKI.getRole().getPlayer();

                if (mionPlayer == null) return;

                if (mionPlayer.getPlayer().getHealth() <= 20) return;

                mionPlayer.getPlayer().damage(damage);

            }

        }

        KeiichiMaebara km = (KeiichiMaebara) RoleList.KEIICHI_MAEBARA.getRole();
        if(km.getBossBar() != null && km.getPlayer() != null && km.getPlayer().getName().equals(hPlayer.getName())){

            float progress = km.getBossBar().getProgress()+3;
            if(progress>=100)
                ScenarioList.OYASHIRO.getScenario().solution(1);
            else
                km.getBossBar().setProgress(progress);

        }

        if (e instanceof EntityDamageByEntityEvent) onPlayerDamageByEntity((EntityDamageByEntityEvent) e);
        if (!e.isCancelled() && p.getHealth() - e.getFinalDamage() <= 0) {
            e.setCancelled(true);
            if(e instanceof EntityDamageByEntityEvent) {
                Role killerRole = null;
                DeathReason dr = DeathReason.ENTITY_ATTACK;
                if(((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
                    dr = DeathReason.PLAYER_ATTACK;
                    killerRole = HigurashiUHC.getGameManager().getPlayer(((EntityDamageByEntityEvent) e).getDamager().getUniqueId()).getRole();
                }
                hPlayer.setKiller(((EntityDamageByEntityEvent) e).getDamager(), killerRole);
                playDeath(hPlayer, dr);
            }else
                playDeath(hPlayer, DeathReason.WORLD_DAMAGE);
        }

    }

    private void setLiveMionShion(EntityDamageByEntityEvent e, Player p, HPlayer hPlayer, String role1, String role2) {
        if(hPlayer.getRole().getName().equalsIgnoreCase(role1)){

            if(p.getHealth() > 20){

                RoleList roleList = RoleList.getRoleList(role2);
                if(roleList == null) return;

                if(roleList.getRole().getPlayer() == null || roleList.getRole().getPlayer().getPlayer() == null)
                    return;

                double damage = limitDamage(e.getDamage());
                for(HPlayer r2P : roleList.getRole().getPlayerList()) {
                    if (r2P.getPlayer() == null) continue;
                    r2P.getPlayer().damage(damage);
                }

            }

        }

    }

    public static void playDeath(HPlayer hPlayer, DeathReason deathReason){

        if(hPlayer == null) return;

        if(hPlayer.getPlayerState().isState(PlayerState.SPECTATE, PlayerState.WAITING_DEATH))
            return;

        Player p = hPlayer.getPlayer();

        if(p == null) return;

        p.setGameMode(GameMode.SPECTATOR);
        hPlayer.setPlayerState(PlayerState.WAITING_DEATH);

        HigurashiUHC.getGameManager().startRikaDeathTask();

        hPlayer.getRole().onDeath(hPlayer, deathReason);

        ((SatokoHojo) RoleList.SATOKO_HOJO.getRole()).removeTraps(hPlayer);

        if(hPlayer.getRole().isRole(RoleList.SATOKO_HOJO.getRole(), RoleList.KEIICHI_MAEBARA.getRole(), RoleList.MION_SONOZAKI.getRole(), RoleList.SHION_SONOSAKI.getRole(), RoleList.RENA_RYUGU.getRole())){

            TextComponent textClick = new TextComponent(ChatColor.DARK_PURPLE + "[ressuciter]");
            TextComponent text = new TextComponent(hPlayer.getRole().getName() + " vien de mourrir ");

            text.addExtra(textClick);

            textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ressucite " + hPlayer.getName()));

            HPlayer hpr = RoleList.RIKA_FURUDE.getRole().getPlayer();

            if(hpr != null && hpr.getPlayer() != null)
                hpr.getPlayer().spigot().sendMessage(text);

        }

        if (MercenaireClan.getClans().hisInClans(hPlayer)) {

            int random = new Random().nextInt(HigurashiUHC.getGameManager().getPlayerList().size()) - 1;

            List<HPlayer> hPlayerList = new ArrayList<>(HigurashiUHC.getGameManager().getPlayerList().values());

            HPlayer miyo = RoleList.MIYO_TAKANO.getRole().getPlayer();

            if (miyo != null && miyo.getPlayer() != null)
                miyo.getPlayer().sendMessage(hPlayerList.get(random).getName() + " est " + hPlayerList.get(random).getRole().getName());

        }

        if (hPlayer.hisMarried()) {

            for(HPlayer married : hPlayer.getMarriedPlayerList()){

                LinkData linkData = hPlayer.getLinkData(married);
                Reason mariedReason = linkData.getMariedLinkReason();
                linkData.setMariedLinked(null, true);

                if (mariedReason.isReason(Reason.DOLL_TRAGEDY))
                    married.incrMalediction(Reason.DOLL_TRAGEDY);

            }

        }

        if(p.getKiller() != null) {

            Player killer = p.getKiller();
            HPlayer killerHplayer = HigurashiUHC.getGameManager().getPlayer(killer.getUniqueId());

            killerHplayer.getRole().onKill(killerHplayer, hPlayer, deathReason);

            killerHplayer.getInfo().put(KuraudoOishi.infoList.KILL,
                    String.valueOf(Integer.parseInt(killerHplayer.getInfo().get(KuraudoOishi.infoList.KILL)) + 1));

        }

        Sound sound = Sound.valueOf(HigurashiUHC.getInstance().getConfig().getString("game.deathsound"));
        Bukkit.getOnlinePlayers().forEach((player) -> player.playSound(player.getLocation(), sound, 1, 1));
        hPlayer.getDeathLinkPlayer().forEach((playerDL) -> playDeath(playerDL, DeathReason.DEATH_LINKED));

    }

    private double limitDamage(double damage){

        if(damage > 4){
            return 4;
        }

        return damage;
    }

}
