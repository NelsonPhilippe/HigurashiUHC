package fr.xilitra.higurashiuhc.utils;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HideNametag {

    public static Map<ArrayList<UUID>,ArmorStand> hiddenmap = new HashMap<>();

    public static void hide(Player player, Player hideFrom) {
        if(player == hideFrom)
            return;
        //Add Players to the List
        ArrayList<UUID> list = new ArrayList<UUID>();
        list.add(player.getUniqueId());
        list.add(hideFrom.getUniqueId());
        if(hiddenmap.containsKey(list)) {
            return;
        }
        //spawning the Armorstand & setting it up
        Location pos = player.getLocation();
        ArmorStand stand = player.getWorld().spawn(pos, ArmorStand.class);
        stand.setVisible(false);
        stand.setMarker(true);
        stand.setGravity(false);
        stand.setCustomNameVisible(false);

        player.setPassenger(stand);
        //Packet packing
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) stand);
        //"Registering" the Stand and the Players
        hiddenmap.put(list, stand);
        //Packet sending
        CraftPlayer ply = (CraftPlayer) hideFrom;
        ply.getHandle().playerConnection.sendPacket(packet);

    }

    public static void unhide(Player player, Player hiddenFrom) {
        if(player == hiddenFrom)
            return;
        ArrayList<UUID> list = new ArrayList<UUID>();
        list.add(player.getUniqueId());
        list.add(hiddenFrom.getUniqueId());
        if(!(hiddenmap.containsKey(list))) {
            return;
        }
        ArmorStand stand = hiddenmap.get(list);
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(stand.getEntityId());
        CraftPlayer ply = (CraftPlayer) hiddenFrom;
        ply.getHandle().playerConnection.sendPacket(packet);
        hiddenmap.remove(list);
    }
}
