package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.mercenaires.OkonogiAction;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(player.getUniqueId());

        if (hPlayer == null)
            return;

        OkonogiAction okonogiAction = (OkonogiAction) hPlayer.getRole().getRoleAction();

        if (hPlayer.isChatOkonogi()) {

            String message = event.getMessage();

            if (message.charAt(0) == '!') {

                message = message.replace("!", "");

                for (HPlayer hPlayers : okonogiAction.getLinkedRole().getHPlayerList())
                    if (hPlayers.getPlayer() != null)
                        if (hPlayers.getClans().isClans(Clans.MERCENAIRE))
                            hPlayers.getPlayer().sendMessage("[Chat Mercenaire] <" + hPlayer.getName() + "> : " + message);
                        else
                            hPlayers.getPlayer().sendMessage("[Chat Mercenaire] " + message);

                for (HPlayer hplayers : HigurashiUHC.getGameManager().getHPlayerList().values())
                    if (hplayers.getPlayer() != null && hplayers.getPlayer().getGameMode() == GameMode.SPECTATOR)
                        hplayers.getPlayer().sendMessage("[Chat Mercenaire] " + message);

                event.setCancelled(true);

            }

            event.setCancelled(true);

            return;
        }

        event.setCancelled(true);


    }

}
