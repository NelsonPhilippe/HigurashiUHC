package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.clans.ClansList;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.inventivetalent.bossbar.BossBar;

public class RenaRyugu extends Role implements Listener {

    private HPlayer hPlayerPense;
    private boolean penseIsUsed;
    private BossBar bossBar;

    public RenaRyugu() {
        super("Rena Ryugu", Gender.FEMME, ClansList.MEMBER_OF_CLUB, 1);
        this.penseIsUsed = false;
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

        if(killed.hasMalediction() && killed.getKiller() instanceof Player){

            Player killer = (Player) killed.getKiller();
            HPlayer killerHPlayer =  RoleList.RENA_RYUGU.getRole().getHPlayer();

            if(killerHPlayer != null){

                killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false));

            }

        }

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

    public BossBar getBossBar(){
        return bossBar;
    }

    public void setBossBar(BossBar bossBar){
        this.bossBar = bossBar;
    }

}
