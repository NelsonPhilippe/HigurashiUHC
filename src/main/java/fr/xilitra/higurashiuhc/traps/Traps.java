package fr.xilitra.higurashiuhc.traps;

import fr.xilitra.higurashiuhc.item.FireCracker;
import fr.xilitra.higurashiuhc.item.SlowBall;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Traps {

    public static SlowBall slowBall = new SlowBall();
    public static FireCracker fireCracker = new FireCracker();

    public static void defense(Player target){
        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,100, 2, false));
    }

}
