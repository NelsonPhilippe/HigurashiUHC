package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.clans.Clans;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Hinamizawa;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.Hanyu;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.AkaneSonozaki;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.Kasai;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozaki;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HigurashiCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("pense")){

                HPlayer rena = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RENA_RYUGU);

                if(rena == null || rena.getPlayer().getUniqueId() != p.getUniqueId()) return true;

                if(((RenaRyugu) rena.getRole()).gethPlayerPense() == null) return true;

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

            if(hanyuRole.isDimensionIsUsed()) {

                p.sendMessage("Vous avez déjà utilisé la téléportation a votre dimension.");
                return true;
            }

            if(args.length == 2){
                if(args[1].equalsIgnoreCase("rika")){
                    HPlayer rika = HigurashiUHC.getGameManager().getPlayerWithRole(Role.RIKA_FURUDE);
                    if(rika == null) return true;

                    int x = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.x");
                    int y = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.y");
                    int z = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.z");
                    float pitch = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.rika.pitch");
                    String world = HigurashiUHC.getInstance().getConfig().getString("hanyu.dimension.spawn-location.rika.world");
                    Location loc = new Location(Bukkit.getWorld(world), x, y, z);
                    rika.getPlayer().teleport(loc);

                    return true;
                }
            }

            int x = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.x");
            int y = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.y");
            int z = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.z");
            float pitch = HigurashiUHC.getInstance().getConfig().getInt("hanyu.dimension.spawn-location.hanyu.pitch");
            String world = HigurashiUHC.getInstance().getConfig().getString("hanyu.dimension.spawn-location.hanyu.world");
            Location loc = new Location(Bukkit.getWorld(world), x, y, z);
            hanyu.getPlayer().teleport(loc);


        }

        if(args[0].equalsIgnoreCase("ban")){
            if(args.length == 2){

                HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

                if(!hPlayer.getRole().getClass().equals(Role.ORYO_SONOZAKI.getRole())) return true;

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
                        message.addExtra(clickable.getText());
                        BaseComponent component = message;

                        clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "vote " + targetHPlayer.getUuid().toString()));
                        allPlayer.sendMessage(component.toLegacyText());

                        oryoSonozaki.setAsVoted(true);

                        return true;

                    }

                }
            }
        }

        if(args[0].equalsIgnoreCase("force")){
            HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

            if(hPlayer.getRole().getClass().equals(Role.KASAI.getRole())){

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

                if(!hPlayer.getRole().getClass().equals(Role.AKANE_SONOZAKI.getRole())) return true;
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

                if(!hPlayer.getRole().getClass().equals(Role.KIICHIRO_KIMIYOSHI.getRole())) return true;

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
        return false;
    }
}
