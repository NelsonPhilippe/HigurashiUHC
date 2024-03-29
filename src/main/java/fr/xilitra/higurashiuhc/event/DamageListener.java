package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.config.ConfigLocation;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.game.PlayerState;
import fr.xilitra.higurashiuhc.kit.KitList;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.InfoData;
import fr.xilitra.higurashiuhc.player.LinkData;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.HanyuAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyuguAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RikaFurudeAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.SatokoHojoAction;
import fr.xilitra.higurashiuhc.scenario.Oyashiro;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.MathMain;
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

    public static void playDeath(HPlayer victim, DeathReason deathReason) {

        if (victim == null) return;

        if (victim.getPlayerState().isState(PlayerState.SPECTATE, PlayerState.WAITING_DEATH))
            return;

        boolean sendDeath = true;

        Player victimPlayer = victim.getPlayer();

        if (victimPlayer == null) return;

        ((SatokoHojoAction) Role.SATOKO_HOJO.getRoleAction()).removeTraps(victim);

        Entity killer = victim.getKiller();

        if(!victim.getRole().getRoleAction().onDeath(victim, deathReason))
            return;

        victim.setPlayerState(PlayerState.WAITING_DEATH);
        victimPlayer.setGameMode(GameMode.SPECTATOR);

        if (Clans.MERCENAIRE.hisInClans(victim, false)) {

            int random = new Random().nextInt(HigurashiUHC.getGameManager().getHPlayerList().size()) - 1;

            List<HPlayer> hPlayerList = new ArrayList<>(HigurashiUHC.getGameManager().getHPlayerList().values());

            HPlayer miyo = Role.MIYO_TAKANO.getHPlayer();

            if (miyo != null && miyo.getPlayer() != null)
                miyo.getPlayer().sendMessage(hPlayerList.get(random).getName() + " est " + hPlayerList.get(random).getRole().getName());

        }

        if (victim.hisMarried()) {

            for (HPlayer married : victim.getMarriedPlayerList()) {

                LinkData linkData = victim.getLinkData(married);
                Reason mariedReason = linkData.getMariedLinkReason();

                if (mariedReason.isReason(Reason.DOLL_TRAGEDY)) {
                    married.addMaledictionReason(Reason.DOLL_TRAGEDY);
                    linkData.setMariedLinked(null, true);
                }

            }

        }

        if (killer instanceof Player) {

            HPlayer killerHplayer = HigurashiUHC.getGameManager().getHPlayer(killer.getUniqueId());

            if (killerHplayer != null) {
                killerHplayer.getRole().getRoleAction().onKill(killerHplayer, victim, deathReason);

                int kill;
                String value = (String) killerHplayer.getInfoData().getDataInfo(InfoData.InfoList.KILL.name());
                if (value == null)
                    kill = 0;
                else kill = Integer.parseInt(value);

                killerHplayer.getInfoData().setDataInfo(InfoData.InfoList.KILL.name(),
                        String.valueOf(kill + 1));

                if (killerHplayer.hasMalediction() && !killerHplayer.getRole().isRole(Role.RIKA_FURUDE, Role.HANYU)) {
                    List<Role> authorizedEffect = new ArrayList<Role>() {{
                        add(Role.KEIICHI_MAEBARA);
                        add(Role.RENA_RYUGU);
                        add(Role.SATOKO_HOJO);
                        add(Role.MION_SONOZAKI);
                        add(Role.SHION_SONOSAKI);
                    }};

                    if (authorizedEffect.contains(victim.getRole())) {

                        authorizedEffect.remove(killerHplayer.getRole());

                        ((Player) killer).setMaxHealth(((Player) killer).getMaxHealth() + 1);
                        ((Player) killer).removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);

                        boolean allKilledBY = true;

                        for (Role role1 : authorizedEffect) {
                            HPlayer hPlayer1 = role1.getHPlayer();
                            if (hPlayer1 == null)
                                continue;
                            if (hPlayer1.getKiller() != killer) {
                                allKilledBY = false;
                                break;
                            }
                        }

                        if (allKilledBY)
                            ((Player) killer).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));

                    }
                }

                KitList kit = killerHplayer.getKit();
                if (kit == KitList.ASSASSIN)
                    sendDeath = false;

            }

        }

        if (victim.getClans() != null)
            victim.getClans().removePlayer(victim);

        if (victimPlayer.getInventory().getContents().length > 0) {
            for (ItemStack itemStack : victimPlayer.getInventory().getContents()) {
                victimPlayer.getWorld().dropItemNaturally(victimPlayer.getLocation(), itemStack);
                victimPlayer.getInventory().removeItem(itemStack);
            }
        }

        if (victimPlayer.getPlayer().getInventory().getArmorContents().length > 0) {
            for (ItemStack itemStack : victimPlayer.getInventory().getArmorContents()) {
                victimPlayer.getWorld().dropItemNaturally(victimPlayer.getLocation(), itemStack);
                victimPlayer.getInventory().removeItem(itemStack);
            }
        }

        if (sendDeath) {
            if (HigurashiUHC.getGameManager().getConfigGestion().getConfig().getBoolean(ConfigLocation.ROLE_DISPLAY_ONDEATH))
                Bukkit.broadcastMessage("§5---------------------------------------------------------\n" +
                        "§fLe joueur §7§o'" + victim.getName() + "' §fvient d’être §8§m§l:tué§f, il était §6§o'" + victim.getRole().getName() + "'.\n" +
                        "§5---------------------------------------------------------");
            else Bukkit.broadcastMessage("§5---------------------------------------------------------\n" +
                    "§fLe joueur §7§o'" + victim.getName() + "' §fvient d’être §8§m§l:tué§f'.\n" +
                    "§5---------------------------------------------------------");
        }

        Sound sound = Sound.valueOf(HigurashiUHC.getGameManager().getConfigGestion().getConfig().getString(ConfigLocation.SOUND_ONDEATH));
        Bukkit.getOnlinePlayers().forEach((player) -> player.playSound(player.getLocation(), sound, 1, 1));

        victim.getDeathLinkPlayer().forEach((playerDL) -> playDeath(playerDL, DeathReason.DEATH_LINKED));

        if (deathReason != DeathReason.DEATH_LINKED)
            HigurashiUHC.getGameManager().checkWin();

        if(HigurashiUHC.getGameManager().getStates() != GameStates.FINISH)
            if(victim.getRole().isRole(Role.RIKA_FURUDE))
                Bukkit.broadcastMessage("§1✖ §5Rika est morte §1✖\n" +
                        "\n" +
                        "§5Il reste plus que 2 jours à compter de maintenant pour que le village d'§9Hinamizawa §5remporte la partie. ");

    }

    public void onPlayerDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();
        HPlayer attacked = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());
        if (attacked == null)
            return;

        if (attacked.getRole().isRole(Role.HANYU)) {
            ((RikaFurudeAction) Role.RIKA_FURUDE.getRoleAction()).watanagashiTask.hanyuInvisible = 60;
            ((HanyuAction) Role.HANYU.getRoleAction()).setInvisible(false);
        }

        if (!(e.getDamager() instanceof Player)) return;

        Player damager = (Player) e.getDamager();
        HPlayer attacker = HigurashiUHC.getGameManager().getHPlayer(damager.getUniqueId());

        if (attacker == null || attacker.getPlayerState() != PlayerState.INGAME) {
            e.setCancelled(true);
            return;
        }

        if (attacker.getLinkData(attacked).isDamageCancelled() && HigurashiUHC.getGameManager().getHPlayerWithState(PlayerState.INGAME).size() > 2) {
            e.setCancelled(true);
            return;
        }

        ItemStack item = damager.getItemInHand();
        if (item.isSimilar(CustomCraft.baseballBat.getItemStack())) {

            if (CustomCraft.baseballBat.isUsedOnEpisode()) {
                damager.sendMessage("Vous avez déjà utilisé la batte de baseball pour cet episode.");
                return;
            }

            if (CustomCraft.baseballBat.getUsed() == 0) {
                damager.sendMessage("Vous avez déja utilisé 3 fois la batte de baseball");
                return;
            }

            if (CustomCraft.baseballBat.getUsed() == 1) {
                damager.getItemInHand().setType(Material.AIR);
                damager.playSound(damager.getLocation(), Sound.ITEM_BREAK, 1, 1);
            } else
                CustomCraft.baseballBat.setUsed(CustomCraft.baseballBat.getUsed() - 1);

            CustomCraft.baseballBat.setUsedOnEpisode(true);
            e.setDamage(5);

            HigurashiUHC.getGameManager().getHPlayerList().values().forEach(hPlayer -> {
                if (hPlayer.getPlayer() != null
                        && hPlayer.getPlayerState() == PlayerState.INGAME
                        && MathMain.calculLength(hPlayer.getPlayer().getLocation(), damager.getLocation(), true) < 5 * 5
                        && hPlayer.getPlayer().getUniqueId() != damager.getUniqueId()
                ) hPlayer.getPlayer().damage(5);
            });

        }

        HPlayer player = Role.RENA_RYUGU.getHPlayer();

        if (player != null && player.getPlayer() != null) {

            RenaRyuguAction renaRyuguAction = (RenaRyuguAction) player.getRole().getRoleAction();

            if (renaRyuguAction.gethPlayerPense() != null) {

                System.out.println(renaRyuguAction.gethPlayerPense().getUUID().toString());

                if (renaRyuguAction.gethPlayerPense().getUUID().equals(damager.getUniqueId())) {

                    if (!renaRyuguAction.isPenseIsUsed()) {

                        player.getPlayer().sendMessage(e.getDamager().getName() + " à frappé un joueur.");
                        renaRyuguAction.setPenseIsUsed(true);

                    }

                }

            }

        }

    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player)) return;

        if (HigurashiUHC.getGameManager().getStates() != GameStates.GAME) {
            e.setCancelled(true);
            return;
        }

        Player p = (Player) e.getEntity();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());
        if (hPlayer == null)
            return;

        if (e instanceof EntityDamageByEntityEvent) onPlayerDamageByEntity((EntityDamageByEntityEvent) e);

        if (e.isCancelled())
            return;

        if (p.getHealth() <= 20) {

            if (p.getHealth() - e.getFinalDamage() <= 5 && p.getHealth() - e.getFinalDamage() > 0) {

                if (hPlayer.hasMarriedReason(Reason.DOLL_TRAGEDY)) {
                    hPlayer.getMarriedPlayer(Reason.DOLL_TRAGEDY).forEach((player) -> {
                        if (player.getPlayer() != null)
                            player.getPlayer().sendMessage("§dVotre partenaire est descendu en dessous de 5 cœurs. celui-ci est en §4danger.");
                    });
                }

            }

        }

        Oyashiro oyashiro = (Oyashiro) ScenarioList.OYASHIRO.getScenario();
        if (oyashiro.getKeiichiBossBar() != null)
            oyashiro.addKeiichiProggress(3);

        if (!e.isCancelled() && p.getHealth() - e.getFinalDamage() <= 0) {
            e.setCancelled(true);
            DeathReason dr;
            if (e instanceof EntityDamageByEntityEvent) {
                Role killerRole = null;
                if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
                    dr = DeathReason.PLAYER_ATTACK;
                    HPlayer killer = HigurashiUHC.getGameManager().getHPlayer(((EntityDamageByEntityEvent) e).getDamager().getUniqueId());
                    if (killer != null)
                        killerRole = killer.getRole();
                } else dr = DeathReason.ENTITY_ATTACK;
                hPlayer.setKiller(((EntityDamageByEntityEvent) e).getDamager(), killerRole);
            } else dr = DeathReason.WORLD_DAMAGE;
            playDeath(hPlayer, dr);
        }

    }

}
