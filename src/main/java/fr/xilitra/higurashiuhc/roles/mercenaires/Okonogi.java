package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class Okonogi extends Role {

    private List<HPlayer> hPlayerList = new ArrayList<>();

    public Okonogi() {
        super("Okonogi", Gender.HOMME, MercenaireClan.getClans());
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

    @Override
    public void onKill(HPlayer killed) {

    }

    @Override
    public void onDeath(EntityDamageEvent.DamageCause killer) {

    }
}
