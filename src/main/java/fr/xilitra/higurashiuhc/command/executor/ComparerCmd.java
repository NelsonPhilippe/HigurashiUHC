package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.kit.KitList;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.police.KumagaiAction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComparerCmd extends CommandsExecutor {

    public ComparerCmd() {
        super("[Compare]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {

        if (strings.length == 3) {

            KumagaiAction kumagaiAction = (KumagaiAction) hPlayer.getRole().getRoleAction();

            HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(strings[1]);

            if (targetHPlayer == null) {
                sendError(p, "Cible Introuvable");
                return false;
            }

            List<String> clans = Arrays.asList("Sonozaki", "Club", "Police", "Mercenaire", "Neutre");

            if (kumagaiAction.getCompareClanUsed().contains(strings[2])) {
                sendError(p, "Vous avez déja comparer un joueur avec ce clan");
                return false;
            }

            if (clans.contains(strings[2])) {

                kumagaiAction.addClanToCompareUsed(strings[2]);

                for (String clan : clans) {

                    if (targetHPlayer.getRole().isRole(Role.MION_SONOZAKI, Role.SHION_SONOSAKI)) {

                        Random random = new Random();
                        int randomClan = random.nextInt(100);

                        if (randomClan > 50)
                            p.sendMessage(strings[2] + " est dans le camp Sonozaki");
                        else p.sendMessage(strings[2] + " est dans le camp Club");
                        return true;

                    }

                    if (targetHPlayer.getClans().getName().equalsIgnoreCase(clan) ||
                            targetHPlayer.getClans().getName().equalsIgnoreCase("Club")) {

                        if (targetHPlayer.hasKit()) {

                            if (targetHPlayer.getKit() == KitList.JARDINIER) {

                                int percent = new Random().nextInt(100);

                                if (percent <= 70) {
                                    int random = new Random().nextInt(clan.length() - 1);

                                    while (clans.get(random).equalsIgnoreCase("Mercenaire")) {
                                        random = new Random().nextInt(clan.length() - 1);
                                    }
                                    sendOkay(p, strings[1] + " est dans le camp " + clans.get(random));
                                    return true;
                                }

                            }

                        }

                        sendOkay(p, strings[1] + " est dans le camp " + clan);
                        return true;
                    }
                }

                sendError(p, "Le joueur n'est pas dans ce clan.");
                return false;

            }

            sendError(p, "Vous n'êtes pas autorizer à comparer avec le clan: "+strings[2]+" vous pouvez avec:");
            for(String clan : clans){
                p.sendMessage(clan);
            }
            return false;
        }

        sendError(p,"Merci de faire /h "+ Commands.COMPARER.getInitials()+" <joueur> <clans>");
        return false;

    }
}
