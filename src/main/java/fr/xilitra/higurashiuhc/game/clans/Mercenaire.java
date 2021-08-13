package fr.xilitra.higurashiuhc.game.clans;

import fr.xilitra.higurashiuhc.roles.mercenaires.MiyoTakano;
import fr.xilitra.higurashiuhc.roles.mercenaires.Okonogi;
import org.bukkit.entity.Player;

public class Mercenaire extends Clans{
    public Mercenaire(String name) {
        super(name);
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    public void removePlayer(Player p){
        this.players.remove(p);
    }

    public enum roleList{
        MERCENAIRE(fr.xilitra.higurashiuhc.roles.mercenaires.Mercenaire.class),
        MIYO_TANAKO(MiyoTakano.class),
        OKONOJI(Okonogi.class);

        Class role;

        roleList(Class role){
            this.role = role;
        }

        public Class getRole() {
            return role;
        }
    }
}
