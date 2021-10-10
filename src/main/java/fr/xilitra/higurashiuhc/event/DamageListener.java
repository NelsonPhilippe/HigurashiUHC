package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.InfoData;
import fr.xilitra.higurashiuhc.player.LinkData;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyuguAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.SatokoHojoAction;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DamageListener implements Listener {

    public static void playDeath(HPlayer hPlayer, DeathReason deathReason) {

        if (hPlayer == null) return;

        if (hPlayer.getPlayerState().isState(PlayerState.SPECTATE, PlayerState.WAITING_DEATH))
            return;

        Player p = hPlayer.getPlayer();

        if (p == null) return;

        p.setGameMode(GameMode.SPECTATOR);
        hPlayer.setPlayerState(PlayerState.WAITING_DEATH);

        HigurashiUHC.getGameManager().startRikaDeathTask();

        hPlayer.getRole().getRoleAction().onDeath(hPlayer, deathReason);

        ((SatokoHojoAction) Role.SATOKO_HOJO.getRoleAction()).removeTraps(hPlayer);
        if (hPlayer.getClans() != null)
            hPlayer.getClans().removePlayer(hPlayer);

        Entity killer = hPlayer.getKiller();
        if (killer instanceof Player) {

            Clans clans = Clans.getClans(hPlayer);
            if (clans != null && clans.isClans(Clans.MEMBER_OF_CLUB)) {

                ((Player) killer).setMaxHealth(((Player) killer).getMaxHealth() + 1);
                ((Player) killer).removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);

                List<HPlayer> playerInClan = Clans.MEMBER_OF_CLUB.getHPlayerList();
                boolean allKilledBY = true;

                for (HPlayer hPlayer1 : playerInClan) {
                    if (hPlayer1.getKiller() != killer) {
                        allKilledBY = false;
                        break;
                    }
                }

                if (allKilledBY)
                    ((Player) killer).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 1));

            }

        }

        if (hPlayer.getRole().isRole(Role.SATOKO_HOJO, Role.KEIICHI_MAEBARA, Role.MION_SONOZAKI, Role.SHION_SONOSAKI, Role.RENA_RYUGU)) {

            TextComponent textClick = new TextComponent(ChatColor.DARK_PURPLE + "[ressuciter]");
            TextComponent text = new TextComponent(hPlayer.getRole().getName() + " vien de mourrir ");

            text.addExtra(textClick);

            textClick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "h r " + hPlayer.getName()));

            HPlayer hpr = Role.RIKA_FURUDE.getHPlayer();

            if (hpr != null && hpr.hasCommandAccess(Commands.RESSUCITE) && hpr.getPlayerState() == PlayerState.INGAME && hpr.getPlayer() != null)
                hpr.getPlayer().spigot().sendMessage(text);

        }

        if (Clans.MERCENAIRE.hisInClans(hPlayer, false)) {

            int random = new Random().nextInt(HigurashiUHC.getGameManager().getHPlayerList().size()) - 1;

            List<HPlayer> hPlayerList = new ArrayList<>(HigurashiUHC.getGameManager().getHPlayerList().values());

            HPlayer miyo = Role.MIYO_TAKANO.getHPlayer();

            if (miyo != null && miyo.getPlayer() != null)
                miyo.getPlayer().sendMessage(hPlayerList.get(random).getName() + " est " + hPlayerList.get(random).getRole().getName());

        }

        if (hPlayer.hisMarried()) {

            for (HPlayer married : hPlayer.getMarriedPlayerList()) {

                LinkData linkData = hPlayer.getLinkData(married);
                Reason mariedReason = linkData.getMariedLinkReason();
                linkData.setMariedLinked(null, true);

                if (mariedReason.isReason(Reason.DOLL_TRAGEDY))
                    married.addMaledictionReason(Reason.DOLL_TRAGEDY);

            }

        }

        if (killer instanceof Player) {

            HPlayer killerHplayer = HigurashiUHC.getGameManager().getHPlayer(killer.getUniqueId());

            if (killerHplayer == null)
                return;

            killerHplayer.getRole().getRoleAction().onKill(killerHplayer, hPlayer, deathReason);

            killerHplayer.getInfoData().setDataInfo(InfoData.InfoList.KILL.name(),
                    String.valueOf(Integer.parseInt((String) killerHplayer.getInfoData().getDataInfo(InfoData.InfoList.KILL.name())) + 1));

        }

        Sound sound = Sound.valueOf(HigurashiUHC.getInstance().getConfig().getString("game.deathsound"));
        Bukkit.getOnlinePlayers().forEach((player) -> player.playSound(player.getLocation(), sound, 1, 1));
        hPlayer.getDeathLinkPlayer().forEach((playerDL) -> playDeath(playerDL, DeathReason.DEATH_LINKED));

    }

    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();

        if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {

            if (!(e.getDamager() instanceof Player)) return;

            Player damager = (Player) e.getDamager();

            ItemStack item = damager.getItemInHand();

            if (item.isSimilar(CustomCraft.baseballBat.getItemStack())) {

                if (CustomCraft.baseballBat.isUsedOnEpisode()) {
                    damager.sendMessage("Vous avez déjà utilisé la batte de baseball pour cet episode.");
                    CustomCraft.baseballBat.setUsedOnEpisode(true);
                    return;
                }

                if (CustomCraft.baseballBat.getUsed() == 0) {
                    damager.sendMessage("Vous avez déja utilisé 3 fois la batte de baseball");
                    return;
                }

                if (CustomCraft.baseballBat.getUsed() == 1) {
                    damager.getItemInHand().setType(Material.AIR);
                    damager.playSound(damager.getLocation(), Sound.ITEM_BREAK, 1, 1);
                    return;
                }

                for (HPlayer players : HigurashiUHC.getGameManager().getHPlayerList().values()) {
                    if (players.getPlayer() != null && players.getPlayer().getLocation().distanceSquared(damager.getLocation()) < 5 * 5) {
                        if (players.getPlayer() != damager) {

                            players.getPlayer().damage(5);
                            e.setDamage(5);
                            CustomCraft.baseballBat.setUsed(CustomCraft.baseballBat.getUsed() - 1);

                        }
                    }
                }
            }

            HPlayer player = Role.RENA_RYUGU.getHPlayer();

            if (player != null && player.getPlayer() != null) {

                RenaRyuguAction renaRyuguAction = (RenaRyuguAction) player.getRole().getRoleAction();

                if (renaRyuguAction.gethPlayerPense() != null) {

                    System.out.println(renaRyuguAction.gethPlayerPense().getUUID().toString());

                    if (renaRyuguAction.gethPlayerPense().getUUID().equals(damager.getUniqueId())) {

                        if (!renaRyuguAction.isPenseIsUsed()) {

                            player.getPlayer().sendMessage(p.getName() + " à frappé un joueur.");
                            renaRyuguAction.setPenseIsUsed(true);

                        }

                    }

                }

            }

        }

    }

    /*private void setLiveMionShion(EntityDamageByEntityEvent e, Player p, HPlayer hPlayer, String role1, String role2) {
        if(hPlayer.getRole().getName().equalsIgnoreCase(role1)){

            if(p.getHealth() > 20){

                RoleList roleList = RoleList.getRoleList(role2);
                if(roleList == null) return;

                if(roleList.getRole().getHPlayer() == null || roleList.getRole().getHPlayer().getPlayer() == null)
                    return;

                double damage = limitDamage(e.getDamage());
                for(HPlayer r2P : roleList.getRole().getHPlayerList()) {
                    if (r2P.getPlayer() == null) continue;
                    r2P.getPlayer().damage(damage);
                }

            }

        }

    }*/

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());
        if (hPlayer == null)
            return;

        double damage = limitDamage(e.getDamage());

        if (p.getHealth() <= 20) {

            if (p.getHealth() - e.getFinalDamage() <= 5 && p.getHealth() - e.getFinalDamage() > 0) {

                if (hPlayer.hasMarriedReason(Reason.DOLL_TRAGEDY)) {
                    hPlayer.getMarriedPlayer(Reason.DOLL_TRAGEDY).forEach((player) -> {
                        if (player.getPlayer() != null)
                            player.getPlayer().sendMessage("Ton amoureux(se): " + hPlayer.getName() + " à le malheur de passer en-dessous de 5 coeurs");
                    });
                }

            }

            if (hPlayer.getRole().isRole(Role.MION_SONOZAKI)) {

                HPlayer shionPlayer = Role.SHION_SONOSAKI.getHPlayer();
                if (shionPlayer == null || shionPlayer.getPlayer() == null) return;

                if (shionPlayer.getPlayer().getHealth() <= 20) return;

                shionPlayer.getPlayer().damage(damage);

            }

            if (hPlayer.getRole().isRole(Role.SHION_SONOSAKI)) {

                HPlayer mionPlayer = Role.MION_SONOZAKI.getHPlayer();

                if (mionPlayer == null || mionPlayer.getPlayer() == null) return;

                if (mionPlayer.getPlayer().getHealth() <= 20) return;

                mionPlayer.getPlayer().damage(damage);

            }

        }

        Oyashiro oyashiro = (Oyashiro) ScenarioList.OYASHIRO.getScenario();
        if (oyashiro.getKeiichiBossBar() != null)
            oyashiro.addKeiichiProggress(3);

        if (e instanceof EntityDamageByEntityEvent) onPlayerDamageByEntity((EntityDamageByEntityEvent) e);
        if (!e.isCancelled() && p.getHealth() - e.getFinalDamage() <= 0) {
            e.setCancelled(true);
            if (e instanceof EntityDamageByEntityEvent) {
                Role killerRole = null;
                DeathReason dr = DeathReason.ENTITY_ATTACK;
                if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
                    dr = DeathReason.PLAYER_ATTACK;
                    HPlayer killer = HigurashiUHC.getGameManager().getHPlayer(((EntityDamageByEntityEvent) e).getDamager().getUniqueId());
                    if (killer != null)
                        killerRole = killer.getRole();
                }
                hPlayer.setKiller(((EntityDamageByEntityEvent) e).getDamager(), killerRole);
                playDeath(hPlayer, dr);
            } else
                playDeath(hPlayer, DeathReason.WORLD_DAMAGE);
        }

    }

    private double limitDamage(double damage) {
        return damage > 4 ? 4.0d : damage;
    }

}
