package fr.xilitra.higurashiuhc.game.clans;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.player.HPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Clans {

    private final String name;
    protected List<Role> roles = new ArrayList<>();

    public Clans(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    protected void addRole(Role p){
        this.roles.add(p);
    }

    protected void removeRole(Role p){
        this.roles.remove(p);
    }

    public abstract boolean isSubClans();

    public abstract Clans getMajorClans();

    public boolean hisInClans(Role role){

        Clans playerClans = role.getClans();

        if(playerClans.isSubClans()){

            return playerClans.getName().equals(getName()) || playerClans.getMajorClans().getName().equals(getName());

        }else return playerClans.getName().equals(getName());
    }

}
