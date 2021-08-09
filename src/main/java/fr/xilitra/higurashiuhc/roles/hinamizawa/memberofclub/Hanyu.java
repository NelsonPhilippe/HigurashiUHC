package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Hanyu extends RoleTemplate implements Listener {

    private boolean isInvisible;
    private boolean dimensionIsUsed;
    private Map<HPlayer, Location> dimensionLastLoc = new HashMap<>();

    public Hanyu() {
        super("Hanyu", Gender.FEMME);
        this.dimensionIsUsed = false;
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player p = event.getEntity();

        HPlayer player = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        if(player.getRole().getClass().equals(Role.HANYU.getRole())){
            p.setGameMode(GameMode.SPECTATOR);
        }
    }



    public void setInvisible(boolean invisible) {
        isInvisible = invisible;
    }

    public boolean isInvisible() {
        return isInvisible;
    }

    public boolean isDimensionIsUsed() {
        return dimensionIsUsed;
    }

    public void setDimensionIsUsed(boolean dimensionIsUsed) {
        this.dimensionIsUsed = dimensionIsUsed;
    }

    public Map<HPlayer, Location> getDimensionLastLoc() {
        return dimensionLastLoc;
    }
}
