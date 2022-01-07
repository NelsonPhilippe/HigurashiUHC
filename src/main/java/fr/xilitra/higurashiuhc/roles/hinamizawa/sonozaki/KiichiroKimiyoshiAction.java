package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.taskClass.KiichiroTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KiichiroKimiyoshiAction implements RoleAction {

    KiichiroTask kt = null;

    @Override
    public String getDescription() {
        return "§6Vous êtes §9Kiichiro Kimiyoshi (garçon) : \n" +
                "\n" +
                "§9Kimiyoshi doit gagner avec §9Hinamizawa. \n" +
                "§6Avec la commande §5“/h heal <joueur>” il pourra régénérer 2 cœurs à un joueur en échange d’un de ses cœurs qu’il perdra de manière définitive.\n" +
                "§6Lorsqu’il lui restera un cœur, il ne pourra plus utiliser son pouvoir. \n" +
                "§9Kimiyoshi §6verra également la vie au-dessus de la tête d’un joueur aléatoire dans la partie. \n" +
                "\n" +
                "§6Vous voyez la vie de: §7“joueur”.";
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
        if (Role.getLinkedRole(this).getHPlayer() == null)
            return;

        List<HPlayer> hPlayerList = new ArrayList<>(HigurashiUHC.getGameManager().getHPlayerList().values());

        kt = new KiichiroTask(hPlayerList.get(new Random().nextInt(hPlayerList.size())));
        kt.runTaskTimer(0L, 1000L);
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
}
