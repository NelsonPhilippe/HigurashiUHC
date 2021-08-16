package fr.xilitra.higurashiuhc.utils;

import org.bukkit.World;
import org.bukkit.block.Biome;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Random;

public class VillageGenerator {

    private String nms;
    private String cb;
    private Biome biomes[] = new Biome[] { Biome.PLAINS, Biome.DESERT, Biome.SAVANNA, Biome.TAIGA };


    public void create(World w, int i, int j, int k, int r, int t) {
        if (t < 0 || t > 3)
            throw new ArrayIndexOutOfBoundsException();


        try {
            Class<?> wg = Class.forName(this.nms + "WorldGenVillage$WorldGenVillageStart");
            Class<?> cw = Class.forName(this.cb + "CraftWorld");
            Class<?> sb = Class.forName(this.nms + "StructureBoundingBox");
            Class<Integer> Int = Integer.TYPE;
            Method cwm = cw.getMethod("getHandle", new Class[0]);
            Method wgm = wg.getMethod("a", new Class[] { Class.forName(this.nms + "World"), Random.class, sb });

            Constructor<?> sbc = sb.getConstructor(new Class[] { Int, Int, Int, Int });
            Object sbo = sbc.newInstance(new Object[] { Integer.valueOf(i - r), Integer.valueOf(j - r),
                    Integer.valueOf(i + r), Integer.valueOf(j + r) });

            Constructor<?> wgc = wg
                    .getConstructor(new Class[] { Class.forName(this.nms + "World"), Random.class, Int, Int, Int });

            Object wgo = wgc.newInstance(new Object[] { cwm.invoke(cw.cast(w), new Object[0]), new Random(),
                    Integer.valueOf(i >> 4), Integer.valueOf(j >> 4), Integer.valueOf(k) });

            wgm.invoke(wgo, new Object[] { cwm.invoke(cw.cast(w), new Object[0]), new Random(), sbo });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
