package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Mercenaire;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class Okonogi extends Role {

    private List<HPlayer> hPlayerList = new ArrayList<>();

    public Okonogi() {
        super("Okonogi", Gender.HOMME, Mercenaire.getClans());
    }

    public List<HPlayer> gethPlayerList() {
        return hPlayerList;
    }

    public void addPlayer(HPlayer player){
        this.hPlayerList.add(player);
    }

    public void removePlayer(HPlayer player){
        this.hPlayerList.remove(player);
    }
}
