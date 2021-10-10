package fr.xilitra.higurashiuhc.event.gamestate;

import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class GameStateChangeListener implements Listener {

    @EventHandler
    public void onStateChange(GameStateChangeEvent gsce) {

        if (gsce.getNewGameState() != GameStates.GAME)
            return;

        List<HPlayer> mercenaireList = Clans.MERCENAIRE.getHPlayerList();

        StringBuilder sb = new StringBuilder("Liste des Mercenaires:");
        mercenaireList.forEach((hPlayer) -> sb.append("\n").append(hPlayer.getName()));

        String message = sb.toString();

        mercenaireList.forEach((hPlayer) -> {
            Player player = hPlayer.getPlayer();
            if (player != null)
                player.sendMessage(message);
        });

    }

}
