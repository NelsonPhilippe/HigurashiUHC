package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RenaRyuguAction extends RoleAction implements Listener {

    private HPlayer hPlayerPense;
    private boolean penseIsUsed;

    @Override
    public String getDescription() {
        return "§6Vous êtes §9Rena Ryugu (fille) :\n" +
                "\n" +
                "§9Rena§6 doit gagner avec §9Hinamizawa§6 tout en faisant partie du §bClub§6." +
                "§9Rena§6 peut utiliser une fois dans la partie la commande §5“/h pense <joueur>”§6." +
                "§6Cette commande lui permettra de savoir si le joueur affecté inflige des dégâts à un autre joueur en temps réel." +
                "§6De plus, si elle tue la personne atteinte de la malédiction, elle gagne l’effet speed permanent. ";
    }

    public RenaRyuguAction() {
        this.penseIsUsed = false;
    }

    public void setHPlayerPense(HPlayer player) {
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
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

        if (ScenarioList.OYASHIRO.isActive())
            ScenarioList.OYASHIRO.getScenario().solution(4);

        if (killed.hasMalediction() && killed.getKiller() instanceof Player) {

            Player killer = (Player) killed.getKiller();
            HPlayer killerHPlayer = Role.RENA_RYUGU.getHPlayer();

            if (killerHPlayer != null) {

                killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false));

            }

        }

    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGameStop() {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

}
