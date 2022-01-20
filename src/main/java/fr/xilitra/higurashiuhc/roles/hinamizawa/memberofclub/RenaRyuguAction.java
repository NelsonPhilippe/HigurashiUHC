package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RenaRyuguAction implements RoleAction, Listener {

    private HPlayer hPlayerPense;
    private boolean penseIsUsed;

    @Override
    public Role getLinkedRole(){
        return Role.RENA_RYUGU;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §9Rena Ryugu (fille) :\n" +
                "\n" +
                "§9Rena§6 doit gagner avec §9Hinamizawa§6 tout en faisant partie du §bClub§6." +
                "§9Rena§6 peut utiliser une fois dans la partie la commande §5'/h pense <joueur>'§6." +
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
        if (killed.getRole().isRole(Role.KEIICHI_MAEBARA)) {
            ScenarioList.OYASHIRO.getScenario().solution(2, Role.RENA_RYUGU);
        }
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

        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();
        if(rika == null)
            return;

        Player rikaPlayer = rika.getPlayer();
        if(rikaPlayer == null)
            return;

        TextComponent textComponent = new TextComponent("§cRena §7est mort, si ");
        TextComponent click = new TextComponent("§6§nvous cliquez sur ce message");
        click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/h r "+killed.getName()));
        textComponent.addExtra(click);
        textComponent.addExtra(new TextComponent(", §cRena §7ressuscitera mais vous perdrez une de vos vies. "));

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

    @Override
    public void onMaledictionReceived(HPlayer hPlayer, Reason mr){

        Player rena = hPlayer.getPlayer();
        if(rena == null)
            return;

        rena.sendMessage("§7Vous pouvez faire en sorte que §9Keiichi §7soit également atteint de la malédiction, pour cela vous possédez la commande §5'/h paranoia' §7à n’importe qu’elle moment ce qui fera monter considérablement la jauge de §9Keiichi§7. \n" +
                "§4§lATTENTION, §7vous aussi possédez une jauge et si celle-ci tombe à 0, vous ne serez plus atteint de la malédiction. ");

    }

}
