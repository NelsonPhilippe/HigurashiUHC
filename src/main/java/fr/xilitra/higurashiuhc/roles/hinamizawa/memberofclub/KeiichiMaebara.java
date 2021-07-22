package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.item.ItemBuilder;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ShapedRecipe;

public class KeiichiMaebara extends RoleTemplate {

    public KeiichiMaebara() {
        super("Keiichi Maebara", Gender.HOMME);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player p = event.getEntity();

        HPlayer player = HigurashiUHC.getGameManager().getPlayers().get(p.getUniqueId());

        if(player.getRole().getClass().equals(Role.HANYU.getRole())){
            p.setGameMode(GameMode.SPECTATOR);
        }
    }


}
