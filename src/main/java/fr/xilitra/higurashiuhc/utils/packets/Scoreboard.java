package fr.xilitra.higurashiuhc.utils.packets;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;

public class Scoreboard {

    private final String title;
    private FastBoard board;
    private final Player player;

    public Scoreboard(String title, Player player) {
        this.title = title;
        this.player = player;
        this.create();
    }

    public void create(){
        this.board = new FastBoard(player);
        this.board.updateTitle(title);
    }

    public void setLines(String ...lines){
        this.board.updateLines(lines);
    }

    public void setLine(int line, String value){
        this.board.updateLine(line, value);
    }

    public void delete(){
        this.board.delete();
    }
}

