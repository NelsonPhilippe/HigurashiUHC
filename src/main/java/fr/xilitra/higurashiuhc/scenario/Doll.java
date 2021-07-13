package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.Scenario;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Doll extends Scenario {

    private HigurashiUHC plugin;
    private GameManager gameManager;

    public Doll() {
        super("Poupée");
        this.plugin = HigurashiUHC.getInstance();
        this.gameManager = HigurashiUHC.getGameManager();
    }

    @Override
    public void solution() {

    }


}
