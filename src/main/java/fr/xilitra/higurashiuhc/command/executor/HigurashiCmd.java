package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.task.taskClass.ChatTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.DimensionTaskTp;
import fr.xilitra.higurashiuhc.game.task.taskClass.PolicierTask;
import fr.xilitra.higurashiuhc.kit.KitList;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.InfoData;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.HanyuAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyuguAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.AkaneSonozakiAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.KasaiAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;
import fr.xilitra.higurashiuhc.roles.mercenaires.MercenaireAction;
import fr.xilitra.higurashiuhc.roles.mercenaires.MiyoTakanoAction;
import fr.xilitra.higurashiuhc.roles.police.AkasakaAction;
import fr.xilitra.higurashiuhc.roles.police.KumagaiAction;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishiAction;
import fr.xilitra.higurashiuhc.roles.police.PolicierRoleAction;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HigurashiCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());
        if(hPlayer == null)
            return false;

        if(args[0].equalsIgnoreCase("suspecter")){

            if(args.length == 2){

                if(!hPlayer.getRole().isRole(Role.KURAUDO_OISHI)) return true;

                HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(args[1]);

                if(targetHPlayer == null){
                    p.sendMessage("Le joueur n'existe pas");
                    return true;
                }

                Role kuraudoRole = hPlayer.getRole();
                KuraudoOishiAction kuraudoOishiAction = (KuraudoOishiAction) kuraudoRole.getRoleAction();

                if(kuraudoOishiAction.getCountSuspect() == 0){
                    Bukkit.broadcastMessage(p.getName() + " est " + kuraudoRole.getName());
                }

                if(kuraudoOishiAction.getCountSuspect() > 3) {
                    Bukkit.broadcastMessage(p.getName() + "est " + kuraudoRole.getName());
                }

                if(kuraudoOishiAction.getCountSuspect() >= 3){
                    p.sendMessage("Vous avez déjà suspecté 3 joeurs différents vous ne pouvez plus suspecté quelqu'un");
                    return true;
                }

                if(kuraudoOishiAction.isCoupableIsDesigned()){
                    p.sendMessage("Vous avez déjà désigné un coupable");
                    return true;
                }

                Map<String, Object> infos = targetHPlayer.getInfoData().getDataInfos();
                Random random = new Random();
                int randomInfo = random.nextInt(infos.size()) - 1;
                int randomInfoSelect = random.nextInt(4);

                InfoData.InfoList[] infoList = InfoData.InfoList.values();

                p.sendMessage("Le joueur suspecté est " + targetHPlayer.getName() + " :");

                for(int i = 0; i < randomInfoSelect; i++){

                    if(targetHPlayer.getRole().getSexe() == Gender.NON_GENRE){
                        i--;
                    }

                    InfoData.InfoList info = infoList[randomInfo];

                    if(info.equals(InfoData.InfoList.EFFECT)){


                        if(p.getActivePotionEffects().size() > 0){
                            int randomEffect = random.nextInt(p.getActivePotionEffects().size());
                            p.sendMessage("- " + info.getType() + " : " + p.getActivePotionEffects().toArray()[randomEffect]);
                        }


                    }else {

                        if(info.equals(InfoData.InfoList.ROLE)){
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

                kuraudoOishiAction.addSuspect(targetHPlayer);
                return true;
            }
        }

        if(args[0].equalsIgnoreCase("comparer")){

            if(args.length == 3){

                if(!hPlayer.getRole().isRole(Role.KUMAGAI)){
                    return true;
                }

                KumagaiAction kumagaiAction = (KumagaiAction) hPlayer.getRole().getRoleAction();

                HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(args[1]);

                if(targetHPlayer == null){
                    p.sendMessage("Cible Introuvable");
                    return true;
                }

                List<String> clans = Arrays.asList("Sonozaki", "Club", "Police", "Mercenaire", "Neutre");

                if(kumagaiAction.getCompareClanUsed().contains(args[2])){
                    p.sendMessage("Vous avez déja comparer un joueur avec ce clan");
                    return true;
                }

                if(clans.contains(args[2])){

                    kumagaiAction.addClanToCompareUsed(args[2]);

                    for(String clan : clans){

                        if(targetHPlayer.getRole().isRole(Role.MION_SONOZAKI, Role.SHION_SONOSAKI)){

                            Random random = new Random();
                            int randomClan = random.nextInt(100);

                            if(randomClan > 50){
                                p.sendMessage(args[2] + " est dans le camp Sonozaki");
                                return true;

                            }

                            p.sendMessage(args[2] + " est dans le camp Club");
                            return true;

                        }

                        if(targetHPlayer.getClans().getName().equalsIgnoreCase(clan) ||
                                targetHPlayer.getClans().getName().equalsIgnoreCase("Club")){

                            if(targetHPlayer.hasKit()){

                                if(targetHPlayer.getKit() == KitList.JARDINIER){

                                    int percent = new Random().nextInt(100);

                                    if(percent <= 70){
                                        int random = new Random().nextInt(clan.length() - 1);

                                        while (clans.get(random).equalsIgnoreCase("Mercenaire")){
                                            random = new Random().nextInt(clan.length() - 1);
                                        }
                                        p.sendMessage(args[2] + " est dans le camp " + clans.get(random));
                                        return true;
                                    }

                                }

                            }

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

                if(!hPlayer.getRole().isRole(Role.AKASAKA)){
                    return true;
                }

                HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(target.getUniqueId());

                if(targetHPlayer == null){
                    p.sendMessage("Cible Introuvable");
                    return true;
                }

                AkasakaAction akasakaAction = (AkasakaAction) targetHPlayer.getRole().getRoleAction();

                akasakaAction.setCountCompare(akasakaAction.getCountCompare() + 1);

                if(targetHPlayer.getRole().getClass().getName().equals(Role.RIKA_FURUDE.getName())){

                    if(akasakaAction.getCountCompare() == 1){
                        p.setMaxHealth(1.5);
                    }

                    if(akasakaAction.getCountCompare() == 2){
                        p.setMaxHealth(1);
                    }

                    if(akasakaAction.getCountCompare() == 3){
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
                Player target = Bukkit.getPlayer(args[1]);

                if(!hPlayer.getRole().isRole(Role.POLICIER)) return true;

                if(target == null){
                    p.sendMessage("Le joueur n'existe pas");
                    return true;
                }

                HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(target.getUniqueId());
                if(targetHPlayer == null){
                    p.sendMessage("Cible Introuvable");
                    return true;
                }

                if(targetHPlayer.getInfoData().getBoolean("PvIsUsed")){
                    p.sendMessage("Vous avez déjà utilisé votre pv");
                    return true;
                }

                target.setMaxHealth(target.getMaxHealth() - 1);
                targetHPlayer.getInfoData().setDataInfo("PvIsUsed", true);
                new PolicierTask(hPlayer).runTask(1000,1000);
                p.sendMessage("Vous venez de mettre un pv à " + target.getName());

                return true;

            }

        }



        return false;
    }
}
