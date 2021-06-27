package fr.xilitra.higurashiuhc.utils.packets;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private String title;
    private FastBoard board;
    private Player player;

    public Scoreboard(String title, Player player) {
        this.title = title;
        this.player = player;
    }

    public void create(){
        this.board = new FastBoard(player);
        this.board.updateTitle(title);
    }

    public void setLines(String ...lines){
        this.board.updateLines(lines);
    }

    public void setLine(int line, String value){
        List<String> lines = this.board.getLines();
        lines.set(line, value);

        this.board.updateLines(lines);
    }

    public void delete(){
        this.board.delete();
    }
}

