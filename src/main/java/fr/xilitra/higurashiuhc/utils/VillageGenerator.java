package fr.xilitra.higurashiuhc.utils;

import net.minecraft.server.v1_8_R3.StructureBoundingBox;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldGenVillage;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

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
            Method cwm = cw.getMethod("getHandle");
            Method wgm = wg.getMethod("a", World.class, Random.class, sb);

            Constructor<?> sbc = sb.getConstructor(Int, Int, Int, Int);
            Object sbo = sbc.newInstance(Integer.valueOf(x - radius), Integer.valueOf(x - radius),
                    Integer.valueOf(x + radius), Integer.valueOf(z + radius));

            Constructor<?> wgc = wg
                    .getConstructor(World.class, Random.class, Int, Int, Int);

            Object wgo = wgc.newInstance(cwm.invoke(cw.cast(world)), new Random(),
                    Integer.valueOf(x >> 4), Integer.valueOf(z >> 4), Integer.valueOf(size));

            wgm.invoke(wgo, cwm.invoke(cw.cast(world)), new Random(), sbo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
