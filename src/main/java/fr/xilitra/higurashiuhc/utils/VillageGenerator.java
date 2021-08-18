package fr.xilitra.higurashiuhc.utils;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import net.minecraft.server.v1_8_R3.StructureBoundingBox;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldGenVillage;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Random;

public class VillageGenerator {

    public static void generate(org.bukkit.World world, int x, int z, int size, int radius) {


        try {
            Class<?> wg = WorldGenVillage.WorldGenVillageStart.class;
            Class<?> cw = CraftWorld.class;
            Class<?> sb = StructureBoundingBox.class;
            Class<Integer> Int = Integer.TYPE;
            Method cwm = cw.getMethod("getHandle", new Class[0]);
            Method wgm = wg.getMethod("a", new Class[] { World.class, Random.class, sb });

            Constructor<?> sbc = sb.getConstructor(new Class[] { Int, Int, Int, Int });
            Object sbo = sbc.newInstance(new Object[] { Integer.valueOf(x - radius), Integer.valueOf(x - radius),
                    Integer.valueOf(x + radius), Integer.valueOf(z + radius) });

            Constructor<?> wgc = wg
                    .getConstructor(new Class[] { World.class, Random.class, Int, Int, Int });

            Object wgo = wgc.newInstance(new Object[] { cwm.invoke(cw.cast(world), new Object[0]), new Random(),
                    Integer.valueOf(x >> 4), Integer.valueOf(z >> 4), Integer.valueOf(size) });

            wgm.invoke(wgo, new Object[] { cwm.invoke(cw.cast(world), new Object[0]), new Random(), sbo });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
