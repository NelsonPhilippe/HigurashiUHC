package fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.game.task.taskClass.KiichiroTaskExecutor;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KiichiroKimiyoshiAction implements RoleAction {

    KiichiroTaskExecutor kt = null;

    @Override
    public Role getLinkedRole(){
        return Role.KIICHIRO_KIMIYOSHI;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §9Kiichiro Kimiyoshi (garçon) : \n" +
                "\n" +
                "§9Kimiyoshi doit gagner avec §9Hinamizawa. \n" +
                "§6Avec la commande §5'/h "+ Commands.HEAL.getInitials() +" <joueur>' il pourra régénérer 2 cœurs à un joueur en échange d’un de ses cœurs qu’il perdra de manière définitive.\n" +
                "§6Lorsqu’il lui restera un cœur, il ne pourra plus utiliser son pouvoir. \n" +
                "§9Kimiyoshi §6verra également la vie au-dessus de la tête d’un joueur aléatoire dans la partie. \n" +
                "\n" +
                "§6Vous voyez la vie de: §7'joueur'.";
    }


    @Override
    public void onGameStart() {
        if (this.getLinkedRole().getHPlayer() == null)
            return;

        List<HPlayer> hPlayerList = new ArrayList<>(HigurashiUHC.getGameManager().getHPlayerList().values());

        kt = new KiichiroTaskExecutor(hPlayerList.get(new Random().nextInt(hPlayerList.size())));
        kt.runTaskTimer(0L, 1L);
    }

}
