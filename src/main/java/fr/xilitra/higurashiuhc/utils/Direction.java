package fr.xilitra.higurashiuhc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {

    NORD(337.5, 22.5, "⬆"),
    SUD(157.5, 202.5, "⬇"),
    OUEST(247.5, 292.5, "⬅"),
    EST(67.5, 112.5, "➡"),
    NORD_OUEST(292.5, 337.5, "⬉"),
    NORD_EST(22.5, 67.5, "⬈"),
    SUD_OUEST(202.5, 247.5, "⬋"),
    SUD_EST(112.5, 157.5, "⬊");

    private static List<Direction> calcList(){
        List<Direction> dirList = new ArrayList<>(Arrays.asList(Direction.values()));
        dirList.remove(Direction.NORD);
        return dirList;
    }

    public static Direction getDirection(double angle){

        for(Direction dir : calcList()){

            if(angle >= dir.getMinAngle() && angle <= dir.getMaxAngle())
                return dir;

        }

        return Direction.NORD;

    }

    public static Direction getDirection(float angle){

        return getDirection(Float.valueOf(angle).doubleValue());

    }

    double min, max;
    String symbol;

    Direction(double min, double max, String symbol){
        this.min = min;
        this.max = max;
        this.symbol = symbol;
    }

    public double getMinAngle(){
        return min;
    }

    public double getMaxAngle(){
        return max;
    }

    public String getSymbol(){
        return symbol;
    }

}
