package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RenaRyugu extends Role implements Listener {

    private HPlayer hPlayerPense;
    private boolean penseIsUsed;

    public RenaRyugu() {
        super("Rena Ryugu", Gender.FEMME, MemberOfClub.getClans(), 1);
        this.penseIsUsed = false;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getPlayer(p.getUniqueId());

        if(hPlayer.hasMalediction()){

            Player killer = p.getKiller();
            HPlayer killerHPlayer =  RoleList.RENA_RYUGU.getRole().getPlayer();

            if(killerHPlayer != null){

                killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false));

            }

        }

    }

    public void setHPlayerPense(HPlayer player){
        this.hPlayerPense = player;
    }

    public HPlayer gethPlayerPense() {
        return hPlayerPense;
    }

    public boolean isPenseIsUsed() {
        return penseIsUsed;
    }

    public void setPenseIsUsed(boolean penseIsUsed) {
        this.penseIsUsed = penseIsUsed;
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
}
