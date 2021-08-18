package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.task.DimensionTaskTp;
import fr.xilitra.higurashiuhc.game.task.PolicierTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.Hanyu;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.AkaneSonozaki;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.Kasai;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozaki;
import fr.xilitra.higurashiuhc.roles.police.Akasaka;
import fr.xilitra.higurashiuhc.roles.police.Kumagai;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishi;
import fr.xilitra.higurashiuhc.roles.police.Policier;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class HigurashiCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("pense")){

                HPlayer rena = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RENA_RYUGU);

                if(rena == null || rena.getPlayer().getUniqueId() != p.getUniqueId()) return true;

                if(((RenaRyugu) rena.getRole()).gethPlayerPense() != null) return true;

                Player target = Bukkit.getPlayer(args[1]);

                if(target == null) {
                    p.sendMessage(ChatColor.RED + "Le joueur n'est pas connecté.");
                    return true;
                }

                for(HPlayer hPlayer : HigurashiUHC.getGameManager().getPlayers().values()){

                    if(hPlayer.getName().equalsIgnoreCase(args[1])){
                        ((RenaRyugu) rena.getRole()).setHPlayerPense(hPlayer);
                        rena.getPlayer().sendMessage("Vous lisez dans les pensé de " + args[1]);
                        break;
                    }

                }

                return true;

            }


        }
        if(args[0].equalsIgnoreCase("dimension")){
            HPlayer hanyu = HigurashiUHC.getGameManager().getPlayerWithRole(Role.HANYU);

            if(hanyu == null || hanyu.getPlayer().getUniqueId() != p.getUniqueId()) return true;

            Hanyu hanyuRole = (Hanyu) hanyu.getRole();

            System.out.println("test");

            if(hanyuRole.isDimensionIsUsed()) {

                p.sendMessage("Vous avez déjà utilisé la téléportation a votre dimension.");
                return true;
            }

            boolean teleportRika = false;

            int xH = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.x");
            int yH = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.y");
            int zH = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.z");
            float pitchH = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.pitch");
            String worldH = HigurashiUHC.getInstance().getConfig().getString("hanyu.dimension.spawn-location.hanyu.world");

            HPlayer rika = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RIKA_FURUDE);

            int x = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.x");
            int y = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.y");
            int z = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.z");
            float pitch = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.pitch");
            String world = HigurashiUHC.getInstance().getConfig().getString("hanyu.dimension.spawn-location.rika.world");

            if(args.length == 2){


                if(args[1].equalsIgnoreCase("rika")){

                    if(rika != null) {
                        teleportRika = true;
                        rika.getPlayer().sendMessage("Vous allez être téléporté dans la dimension de Hanyu dans 1 minute");
                        hanyu.getPlayer().sendMessage("Vous allez être téléporté dans la dimension avec rika dans 1 minute");

                    }else {

                        p.sendMessage("Rika n'est pas dans la partie.");

                    }

                }



            }else {
                hanyu.getPlayer().sendMessage("Vous allez être téléporté dans la dimension dans 1 minute");

            }


            boolean finalTeleportRika = teleportRika;
            Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), new DimensionTaskTp(hanyu, rika, finalTeleportRika, new Location(Bukkit.getWorld(worldH), xH, yH, zH),  new Location(Bukkit.getWorld(world), x, y, z)), 20 ,20);


            return true;
        }

        if(args[0].equalsIgnoreCase("ban")){
            if(args.length == 2){

                HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

                if(!hPlayer.getRole().getClass().getName().equals(Role.ORYO_SONOZAKI.getRole().getName())) return true;

                OryoSonozaki oryoSonozaki = (OryoSonozaki) hPlayer.getRole();

                if(oryoSonozaki.isAsVoted()){
                    p.sendMessage("Vous avez déjà enclenché un vote précédement");
                    return true;
                }

                for(HPlayer players : HigurashiUHC.getGameManager().getPlayers().values()){

                    Player target = Bukkit.getPlayer(args[1]);

                    if(target == null) {
                        p.sendMessage(ChatColor.RED + "Le joueur n'est pas connecté.");
                        return true;
                    }


                    HPlayer targetHPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

                    if(targetHPlayer.getRole().getClan().getName().equalsIgnoreCase("Hinamizawa")){
                        p.sendMessage("vous ne pouvez pas voter pour un membre de votre clans");
                        return true;
                    }

                    if(players.getRole().getClan().getName().equalsIgnoreCase("Hinamizawa")){

                        Player allPlayer = players.getPlayer();

                        TextComponent message = new TextComponent("Voulez vous voter pour " + target.getName() + " ");
                        TextComponent clickable = new TextComponent("[Cliquez pour un vote positif]" );
                        message.addExtra(clickable);

                        clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "vote " + targetHPlayer.getUuid().toString()));

                        allPlayer.spigot().sendMessage(message);

                        oryoSonozaki.setAsVoted(true);

                        return true;

                    }

                }
            }
        }

        if(args[0].equalsIgnoreCase("force")){
            HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

            if(hPlayer.getRole().getClass().getName().equals(Role.KASAI.getRole().getName())){

                Kasai kasai = (Kasai) hPlayer.getRole();

                if(kasai.isGiveForce()){
                    p.sendMessage("Vous avez déjà bousté votre force!");
                    return true;
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000, 1));
                p.sendMessage("Votre force a été boosté!");
                return true;
            }

        }

        if(args[0].equalsIgnoreCase("inverser")){

            if(args.length == 3){
                Player firstTarget = Bukkit.getPlayer(args[1]);
                Player secondsTarget = Bukkit.getPlayer(args[2]);

                HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

                if(!hPlayer.getRole().getClass().getName().equals(Role.AKANE_SONOZAKI.getRole().getName())) return true;
                AkaneSonozaki akaneSonozaki = (AkaneSonozaki) hPlayer.getRole();

                int time = p.getStatistic(Statistic.PLAY_ONE_TICK);

                int days = time / 20 / 60 / 60 / 24;

                if(akaneSonozaki.getNextDaySwap() < days) {
                    p.sendMessage("Il est encore trop tot pour echanger les joueurs");
                    return true;
                }

                if(akaneSonozaki.getSwapUsed() >= 2){
                    p.sendMessage("Vous avez déjà atteint votre cota d'échange de joueur.");
                    return true;
                }

                Location loc1 = firstTarget.getLocation();
                Location loc2 = secondsTarget.getLocation();

                firstTarget.teleport(loc2);
                secondsTarget.teleport(loc1);

                akaneSonozaki.setNextDaySwap(days + 1);
                akaneSonozaki.setSwapUsed(akaneSonozaki.getSwapUsed() + 1);
            }

        }

        if(args[0].equalsIgnoreCase("heal")){
            if(args.length == 2){
                Player target = Bukkit.getPlayer(args[1]);

                HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

                if(!hPlayer.getRole().getClass().getName().equals(Role.KIICHIRO_KIMIYOSHI.getRole().getName())) return true;

                if(target == null) return true;

                if(target.getHealth() <= 1){
                    p.sendMessage("Vous ne possédez pas assez de vie pour en donner.");
                    return true;
                }

                p.setMaxHealth(p.getMaxHealth() - 1);

                if(target.getHealth() >= 18){
                    target.setHealth(target.getHealth() + 2);
                    return true;
                }
            }
        }

        if(args[0].equalsIgnoreCase("suspecter")){

            if(args.length == 2){

                HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());
                Player target = Bukkit.getPlayer(args[1]);

                if(!hPlayer.getRole().getClass().getName().equals(Role.KURAUDO_OISHI.getRole().getName())) return true;

                if(target == null){
                    p.sendMessage("Le joueur n'exitse pas");
                    return true;
                }

                HPlayer targetHPlayer = HigurashiUHC.getGameManager().getPlayer(target.getUniqueId());

                KuraudoOishi kuraudoOishi = (KuraudoOishi) hPlayer.getRole();

                if(kuraudoOishi.getCountSuspect() == 0){
                    Bukkit.broadcastMessage(p.getName() + " est " + kuraudoOishi.getName());
                }

                if(kuraudoOishi.getCountSuspect() > 3) {
                    Bukkit.broadcastMessage(p.getName() + "est " + kuraudoOishi.getName());
                }

                if(kuraudoOishi.getCountSuspect() >= 3){
                    p.sendMessage("Vous avez déjà suspecté 3 joeurs différents vous ne pouvez plus suspecté quelqu'un");
                    return true;
                }

                if(kuraudoOishi.isCoupableIsDesigned()){
                    p.sendMessage("Vous avez déjà désigné un coupable");
                    return true;
                }

                Map<KuraudoOishi.infoList, String> infos = targetHPlayer.getInfo();
                Random random = new Random();
                int randomInfo = random.nextInt(infos.size()) - 1;
                int randomInfoSelect = random.nextInt(4);

                KuraudoOishi.infoList[] infoList = KuraudoOishi.infoList.values();

                p.sendMessage("Le joueur suspecté est " + target.getName() + " :");


                for(int i = 0; i > randomInfoSelect; i++){

                    if(targetHPlayer.getRole().getSexe() == Gender.NON_GENRE){
                        i--;
                    }

                    KuraudoOishi.infoList info = infoList[randomInfo];

                    if(info.equals(KuraudoOishi.infoList.EFFECT)){


                        if(p.getActivePotionEffects().size() > 0){
                            int randomEffect = random.nextInt(p.getActivePotionEffects().size());
                            p.sendMessage("- " + info.getType() + " : " + p.getActivePotionEffects().toArray()[randomEffect]);

                        }


                    }else {

                        if(info.equals(KuraudoOishi.infoList.ROLE)){
                            char letter = 0;

                            for(int c = 0; c < targetHPlayer.getRole().getName().length(); c++){
                                letter = targetHPlayer.getRole().getName().charAt(c);
                            }

                            p.sendMessage("- " + info.getType() + " : " + letter);

                        }else {
                            p.sendMessage("- " + info.getType() + " : " + infos.get(info));
                        }
                    }

                    p.sendMessage("- " + info.getType() + " : " + infos.get(info));
                }

                TextComponent textComponent = new TextComponent("Désigner le joueur ");
                TextComponent coupableClickable = new TextComponent("[Coupable]");
                TextComponent ou = new TextComponent(" ou ");
                TextComponent nonCoupableClickable = new TextComponent("[Non coupable]");

                textComponent.addExtra(coupableClickable);
                textComponent.addExtra(ou);
                textComponent.addExtra(nonCoupableClickable);

                coupableClickable.setBold(true);
                coupableClickable.setColor(net.md_5.bungee.api.ChatColor.GOLD);

                nonCoupableClickable.setBold(true);
                nonCoupableClickable.setColor(net.md_5.bungee.api.ChatColor.GOLD);

                coupableClickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "coupable c"));

                nonCoupableClickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "coupable n"));

                p.spigot().sendMessage(textComponent);

                kuraudoOishi.addSuspect(targetHPlayer);
                return true;
            }
        }

        if(args[0].equalsIgnoreCase("comparer")){

            if(args.length == 3){

                HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

                if(!hPlayer.getRole().getClass().getName().equals(Role.KUMAGAI.getRole().getName())){
                    return true;
                }

                Kumagai kumagai = (Kumagai) hPlayer.getRole();

                Player target = Bukkit.getPlayer(args[1]);

                if(target == null) return true;

                HPlayer targetHPlayer = HigurashiUHC.getGameManager().getPlayer(target.getUniqueId());

                List<String> clans = Arrays.asList("Sonozaki", "Club", "Police", "Mercenaire", "Neutre");

                if(kumagai.getCompareClanUsed().contains(args[2])){
                    p.sendMessage("Vous avez déja comparer un joueur avec ce clan");
                    return true;
                }

                if(clans.contains(args[2])){

                    kumagai.addClanToCompareUsed(args[2]);

                    for(String clan : clans){

                        if(targetHPlayer.getRole().getClass().getName().equals(Role.MION_SONOZAKI.getRole().getName()) || targetHPlayer.getRole().getClass().getName().equals(Role.SHION_SONOSAKI.getRole().getName())){

                            Random random = new Random();
                            int randomClan = random.nextInt(100);

                            if(randomClan > 50){
                                p.sendMessage(args[2] + " est dans le camp Sonozaki");
                                return true;

                            }

                            p.sendMessage(args[2] + " est dans le camp Member of Club");
                            return true;

                        }

                        if(targetHPlayer.getRole().getClan().getName().equalsIgnoreCase(clan) ||
                                targetHPlayer.getRole().getClan().getName().equalsIgnoreCase("Club")){

                            p.sendMessage(args[2] + " est dans le camp " + clan);
                            return true;
                        }
                    }

                    p.sendMessage("Le joueur n'est pas dans ce clan.");
                    return true;

                }

                p.sendMessage("Ce clan n'existe pas");
                return true;
            }

        }

        if(args[0].equalsIgnoreCase("rika")){

            if(args.length == 2){

                Player target = Bukkit.getPlayer(args[1]);

                if(target == null){

                    return true;
                }

                HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

                if(!hPlayer.getRole().getClass().getName().equals(Role.AKASAKA.getRole().getName())){
                    return true;
                }

                HPlayer targetHPlayer = HigurashiUHC.getGameManager().getPlayer(target.getUniqueId());

                Akasaka akasaka = (Akasaka) targetHPlayer.getRole();

                akasaka.setCountCompare(akasaka.getCountCompare() + 1);

                if(targetHPlayer.getRole().getClass().getName().equals(Role.RIKA_FURUDE.getRole().getName())){

                    if(akasaka.getCountCompare() == 1){
                        p.setMaxHealth(1.5);
                    }

                    if(akasaka.getCountCompare() == 2){
                        p.setMaxHealth(1);
                    }

                    if(akasaka.getCountCompare() == 3){
                        p.setMaxHealth(0.5);
                    }

                    return true;
                }


                p.sendMessage(args[1] + " n'est pas Rika.");
                return true;

            }

        }

        if(args[0].equalsIgnoreCase("pv")){

            if(args.length == 2){
                HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());
                Player target = Bukkit.getPlayer(args[1]);

                if(!hPlayer.getRole().getClass().getName().equals(Role.POLICIER.getRole().getName())) return true;

                if(target == null){

                    p.sendMessage("Le joueur n'existe pas");
                    return true;
                }

                HPlayer targetHPlayer = HigurashiUHC.getGameManager().getPlayer(target.getUniqueId());


                Policier policier = (Policier) targetHPlayer.getRole();

                if(policier.isPvIsUsed()){
                    p.sendMessage("Vous avez déjà utilisé votre pv");
                    return true;
                }

                target.setMaxHealth(target.getMaxHealth() - 1);
                policier.setPvIsUsed(true);
                Bukkit.getScheduler().runTaskTimer(HigurashiUHC.getInstance(), new PolicierTask(hPlayer), 20, 20);
                p.sendMessage("Vous venez de mettre un pv à " + target.getName());

                return true;

            }

        }

        return false;
    }
}
