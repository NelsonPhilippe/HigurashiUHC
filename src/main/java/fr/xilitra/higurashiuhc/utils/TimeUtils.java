package fr.xilitra.higurashiuhc.utils;

public class TimeUtils {

    public static void decountTime(int seconds, int minutes, int hours){

    }

    public static String formatTime(int seconds){
        int s = seconds;
        int m = 0;
        String formatSeconds;
        String formatMinutes;
        String time = "00:00";

        if(s > 59){
            s = 0;
            m++;
        }


        if(s < 9){
            formatSeconds = "0" + s;
        }else{
            formatSeconds = String.valueOf(s);
        }

        if(m < 9){
            formatMinutes = "0" + m;
        }else {
            formatMinutes = String.valueOf(m);
        }

        time = formatMinutes + ":" + formatSeconds;

        return time;
    }

}
