package fr.xilitra.higurashiuhc.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class MathMain {

    public static double radToDeg(double rad) { return rad*(180/Math.PI); }

    public static double calculDiff(Location from, Location to, boolean high){

        double x = to.getX()-from.getX(), y = to.getY()-from.getY(), z = to.getZ()-from.getZ();
        double firstCalc = Math.sqrt(Math.pow(x, 2)+ Math.pow(y, 2));

        if(!high) return firstCalc;

        return Math.sqrt(Math.pow(firstCalc, 2)+ Math.pow(z, 2));

    }

    public static double vectorAngle(Vector v) {

        /// Found on https://stackoverflow.com/questions/6247153/angle-from-2d-unit-vector

        double x = v.getX();
        double y = v.getY();

        if (x == 0) // special cases
            return (y > 0)? 90
                    : (y == 0)? 0
                    : 270;
        else if (y == 0) // special cases
            return (x >= 0)? 0
                    : 180;
        double ret = radToDeg(Math.atan((float)y/x));
        if (x < 0 && y < 0) // quadrant Ⅲ
            ret = 180 + ret;
        else if (x < 0) // quadrant Ⅱ
            ret = 180 + ret; // it actually substracts
        else if (y < 0) // quadrant Ⅳ
            ret = 270 + (90 + ret); // it actually substracts

        return ret;

    }

    public static Direction calculateDirection(Player from, Player to){

        return calculateDirection(from, to.getLocation());

    }

    /*public static Direction calculateDirection(Player from, Location to){

        /// Vector eye direction
        Vector eyeDirection = from.getEyeLocation().getDirection();

        ///Vector from player to location
        Vector vector = to.toVector().subtract(from.getLocation().toVector());

        ///vector angle eye to target
        double angle = vector.angle(eyeDirection);

        /// Rad to Degree
        angle = angle * 180 / java.lang.Math.PI;

        return Direction.getDirection(angle);

    }
    */

    public static Direction calculateDirection(Player p, Location to){
        Vector vector = to.toVector().subtract(p.getLocation().toVector());
        return Direction.getDirection(calcAngle(p.getEyeLocation().getDirection(), vector, false, false));
    }

    public static Double calcAngle(Location from, Location to, boolean highAtZ, boolean keepNegative) {
        return calcAngle(from.toVector(), to.toVector(), highAtZ, keepNegative);
    }

    public static Double calcAngle(Vector v1, Vector v2, boolean highAtZ, boolean keepNegative){
        double dot, det;
        if(!highAtZ) {
            dot = v1.getX() * v2.getX() + v1.getZ() * v2.getZ();
            det = v1.getX()*v2.getZ() - v1.getZ()*v2.getX();
        }else{
            dot = v1.getX() * v2.getX() + v1.getY() * v2.getY();
            det = v1.getX()*v2.getY() - v1.getY()*v2.getX();
        }
        double deg = radToDeg(Math.atan2(det, dot));
        if(!keepNegative)
            deg += 360;
        return deg;
    }
}
