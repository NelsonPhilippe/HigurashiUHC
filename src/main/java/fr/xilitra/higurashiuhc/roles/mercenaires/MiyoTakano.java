package fr.xilitra.higurashiuhc.roles.mercenaires;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.clans.MercenaireClan;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MiyoTakano extends Role implements Listener {

    private int order = 2;

    public MiyoTakano() {
        super("Miyo Takano", Gender.FEMME, MercenaireClan.getClans(), 1);
    }


    @EventHandler
    public void onRoleSelected(RoleSelected e){
        HPlayer player = e.getPlayer();

        if(player.getRole().equals(this) && player.getPlayer() != null){

            RoleList[] mercenenaire = {RoleList.MERCENAIRE, RoleList.OKONOGI};
            player.getPlayer().sendMessage(ChatColor.RED + "--Liste des Mercenaires--");

            for(HPlayer hPlayer : HigurashiUHC.getGameManager().getHPlayerList().values()){
                if(hPlayer.getPlayer() == null)
                    continue;
                for(RoleList role : mercenenaire){
                    if(hPlayer.getRole().equals(role.getRole())){

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

    @Override
    public String getDecription() {
        return "null";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
