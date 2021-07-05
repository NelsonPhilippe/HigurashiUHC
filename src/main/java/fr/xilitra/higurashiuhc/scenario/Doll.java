package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.api.Scenario;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doll extends Scenario {

    private Player player;

    public Doll(String name, Role ...roles) {
        super("Poupée", Arrays.asList(roles));
    }

    @Override
    public void solution() {

    }
}
