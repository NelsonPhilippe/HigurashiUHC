package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.game.task.taskClass.MiyoTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MiyoTakanoAction extends RoleAction implements Listener {

    public MiyoTask miyoTask = new MiyoTask();

    @Override
    public String getDescription() {
        return "§6Vous êtes §4Miyo Takano (fille) : \n" +
                "\n" +
                "§4Miyo Takano §6doit gagner avec les §4Mercenaires. \n" +
                "§4Miyo Takano §6connaît la liste des §4mercenaires. \n" +
                "§4Miyo Takano §6a le pouvoir de donner l’ordre d’assassiner quelqu’un avec la commande §5“/h assassiner <pseudo-mercenaire> <pseudo-victime>” §6(elle ne peut pas donner de cible à §4Okonogi). \n" +
                "§6Elle peut donner 2 ordres par jour. \n" +
                "§4Miyo Takano §6a aussi le pouvoir de connaître le rôle d’un joueur aléatoirement dans la partie lorsqu’un §4mercenaire §6meurt. \n" +
                "§6Au début de la nuit de Watanagashi, §4Takano §6apprend l’identité de §2Tomitake §6et connait sa position exacte en temps réel durant la Watanagashi, après la Watanagashi elle ne voit plus la position de §2Tomitake. \n" +
                "§6Si §9Rika §6meurt définitivement de la main des §4mercenaires §6avant Watanagashi, alors §4Miyo Takano §6sera dévoilé aux yeux de tous.";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGameStop() {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

    @EventHandler
    public void onWataChange(WatanagashiChangeEvent watanagashiChangeEvent) {
        if (watanagashiChangeEvent.getWataEnum() == WataEnum.DURING) {

            HPlayer miyo = getLinkedRole().getHPlayer();
            if (miyo == null)
                return;

            if (miyo.getPlayer() == null)
                return;

            miyoTask.runTaskTimer(0L, 1000L);

            HPlayer tomitake = Role.JIRO_TOMITAKE.getHPlayer();

            if (tomitake == null) return;

            Player bTomitake = tomitake.getPlayer();

            if (bTomitake == null) return;

            miyo.getPlayer().sendMessage(tomitake.getRole().getName() + " est " + bTomitake.getName());

        } else if (watanagashiChangeEvent.getWataEnum() == WataEnum.AFTER)
            this.miyoTask.stopTask();
    }

    @EventHandler
    public void onEpisodeChange(EpisodeUpdate episodeUpdate) {

        HPlayer miyoTakanoAction = Role.MIYO_TAKANO.getHPlayer();

        if (miyoTakanoAction != null)
            miyoTakanoAction.setCommandAccess(Commands.ASSASSINER, 2);

    }

}
