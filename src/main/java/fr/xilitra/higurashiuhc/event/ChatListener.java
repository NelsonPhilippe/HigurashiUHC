package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.clans.Mercenaire;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.mercenaires.Okonogi;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(player.getUniqueId());

        Okonogi okonogi = (Okonogi) hPlayer.getRoleList().getRole();

        if(hPlayer.isChatOkonogi() ){

            String message = event.getMessage();

            if(message.charAt(0) == '!'){

                message = message.replace("!", "");

                for(HPlayer hPlayers : okonogi.gethPlayerList()){

                    if(hPlayers.getClans().equals(Mercenaire.getClans())){
                        hPlayers.getPlayer().sendMessage("[Chat Mercenaire] <" + hPlayer.getName() + "> : " + message);
                    }else {
                        hPlayers.getPlayer().sendMessage("[Chat Mercenaire] " + message);

                    }

                }

                for (HPlayer hplayers : HigurashiUHC.getGameManager().getPlayers().values()){
                    if(hplayers.getPlayer().getGameMode() == GameMode.SPECTATOR){
                        hplayers.getPlayer().sendMessage("[Chat Mercenaire] " + message);
                    }
                }

                event.setCancelled(true);

            }

            event.setCancelled(true);

            return;
        }

        event.setCancelled(true);


    }

}
