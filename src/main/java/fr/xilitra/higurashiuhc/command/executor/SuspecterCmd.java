package fr.xilitra.higurashiuhc.command.executor;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.command.CommandsExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.InfoData;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishiAction;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class SuspecterCmd extends CommandsExecutor {

    public SuspecterCmd() {
        super("[Suspecter]");
    }

    @Override
    public boolean onCommand(HPlayer hPlayer, Player p, String[] strings) {

        if(!HigurashiUHC.getGameManager().isWataState(WataEnum.BEFORE)){
            p.sendMessage("Erreur, Watanagashi en cours ou passé.");
            return true;
        }

        if (strings.length == 2) {

            HPlayer targetHPlayer = HigurashiUHC.getGameManager().getHPlayer(strings[1]);

            if (targetHPlayer == null) {
                sendError(p, "Le joueur n'existe pas");
                return false;
            }

            Role kuraudoRole = hPlayer.getRole();
            KuraudoOishiAction kuraudoOishiAction = (KuraudoOishiAction) kuraudoRole.getRoleAction();

            if (kuraudoOishiAction.getCountSuspect() >= 3) {
                sendError(p, "Vous avez déjà suspecté 3 joeurs différents vous ne pouvez plus suspecté quelqu'un");
                return false;
            }

            if (kuraudoOishiAction.isCoupableDesigned()) {
                sendError(p, "Vous avez déjà désigné un coupable");
                return false;
            }

            Bukkit.broadcastMessage(p.getName() + " est " + kuraudoRole.getName());

            Map<String, Object> infos = targetHPlayer.getInfoData().getDataInfos();
            Random random = new Random();

            List<InfoData.InfoList> infoList = new ArrayList<>(Arrays.asList(InfoData.InfoList.values()));

            sendOkay(p,"Le joueur suspecté est " + targetHPlayer.getName() + " :");

            for (int i = 0; i < random.nextInt(5 - 2) + 2; i++) {

                InfoData.InfoList info = infoList.get(random.nextInt(infos.size()));
                infoList.remove(info);

                if (info.equals(InfoData.InfoList.EFFECT)) {

                    if (p.getActivePotionEffects().size() > 0) {
                        int randomEffect = random.nextInt(p.getActivePotionEffects().size());
                        sendOkay(p, "- " + info.getType() + " : " + p.getActivePotionEffects().toArray()[randomEffect]);
                    }

                } else
                    sendOkay(p, "- " + info.getType() + " : " + infos.get(info.name()));

            }

            TextComponent textComponent = new TextComponent("Désigner le joueur ");
            TextComponent coupableClickable = new TextComponent("[Coupable]");

            textComponent.addExtra(coupableClickable);

            coupableClickable.setBold(true);
            coupableClickable.setColor(net.md_5.bungee.api.ChatColor.GOLD);

            coupableClickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/h " + Commands.COUPABLE.getInitials() + targetHPlayer.getName()));

            p.spigot().sendMessage(textComponent);

            kuraudoOishiAction.addSuspect(targetHPlayer);
            return true;
        }
        sendError(p,"Merci de faire /h "+Commands.SUSPECTER.getInitials()+" <joueur>");
        return false;
    }
}
