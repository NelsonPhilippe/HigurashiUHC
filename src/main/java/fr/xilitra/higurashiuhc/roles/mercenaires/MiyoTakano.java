package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MiyoTakano extends RoleTemplate implements Listener {

    private int order = 2;

    public MiyoTakano() {
        super("Miyo Takano", Gender.FEMME);
    }


    @EventHandler
    public void onRoleSelected(RoleSelected e){
        HPlayer player = e.getPlayer();

        if(player.getRole().getName().equalsIgnoreCase(this.getName())){

            Role[] mercenenaire = {Role.MERCENAIRE, Role.OKONOGI};

            for(HPlayer hPlayer : HigurashiUHC.getGameManager().getPlayers().values()){
                for(Role role : mercenenaire){
                    if(hPlayer.getRole().getClass().getName().equalsIgnoreCase(role.getRole().getClass().getName())){

                        player.getPlayer().sendMessage(ChatColor.RED + "--Liste des Mercenaires--");
                        player.getPlayer().sendMessage(ChatColor.GREEN + hPlayer.getRole().getName() + " : " + ChatColor.GOLD + hPlayer.getName());

                    }
                }
            }

        }

    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
