package fr.xilitra.higurashiuhc.roles.police;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class KuraudoOishiAction implements RoleAction, Listener {


    private final List<HPlayer> suspect = new ArrayList<>();
    private int countSuspect = 0;
    private boolean coupableIsDesigned = false;

    @Override
    public Role getLinkedRole(){
        return Role.KURAUDO_OISHI;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §eKuraudo Oishi (garçon) : \n" +
                "\n" +
                "§eOishi §6doit gagner avec §9Hinamizawa §6et fait partie du camp de la §epolice. \n" +
                "§eOishi §6a la particularité de suspecter un joueur avec la commande §5'/h "+ Commands.SUSPECTER.getInitials()+
        " <joueur>' §63 fois dans la partie. \n" +
                "§6Lorsqu'il utilise une fois sa commande, §eOishi §6sera connu de tout le monde. \n" +
                "§6Dès que le joueur est suspecté, §eOishi §6aura des informations aléatoirement concernant le joueur pendant 5 minutes, il pourra avoir accès à minimum 2 infos et max 5 infos. \n" +
                "§6Une fois le délai des 5 minutes terminés, il pourra choisir si oui ou non le joueur est coupable. \n" +
                "§6S’il décide que le joueur n'est pas coupable alors il pourra suspecter un autre joueur. \n" +
                "§6Si la personne dite coupable est un membre des §3Sonozaki, §6alors §ela police §6devra gagner seulement avec son camp et §eOishi §6se verra give un arc punch 1. \n" +
                "§6Si la personne dite coupable est un membre d’§9Hinamizawa §6(non §3Sonozaki§6), alors l’enquête aura échoué et par conséquent §eOishi §6perd son pouvoir. \n" +
                "§6En revanche, si le joueur coupable est un membre des §4mercenaires§6§2/neutre§6, la §epolice §6devra gagner avec §9hinamizawa. \n" +
                "§6(un seul coupable par partie, le pouvoir disparaît par la suite) \n" +
                "\n" +
                "§6Si §eOishi §6ne désigne pas de coupable avant watanagashi, alors §eOishi §6perdra ses pouvoirs et tombera automatiquement à 5 cœurs durant toute la partie.";
    }

    public int getCountSuspect() {
        return countSuspect;
    }

    public void setCountSuspect(int countSuspect) {
        this.countSuspect = countSuspect;
    }

    public List<HPlayer> getSuspect() {
        return suspect;
    }

    public void addSuspect(HPlayer hPlayer) {
        this.suspect.add(hPlayer);
    }

    public boolean isCoupableDesigned() {
        return coupableIsDesigned;
    }

    public void setCoupableDesigned(boolean coupableIsDesigned) {
        this.coupableIsDesigned = coupableIsDesigned;
    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

    @EventHandler
    public void wataChange(WatanagashiChangeEvent watanagashiChangeEvent) {
        if (watanagashiChangeEvent.getWataEnum() != WataEnum.DURING)
            return;
        HPlayer player = Role.KURAUDO_OISHI.getHPlayer();

        if (player != null && player.getPlayer() != null && !isCoupableDesigned()) {
            player.getPlayer().setMaxHealth(10);
            player.getPlayer().sendMessage("§e§oVous n’avez accusé personne avant la §5§l§nWatanagashi… \n" +
                    "\n" +
                    "§e§oIl est maintenant trop tard, vous ne pouvez plus utiliser vos suspicions et vous perdez 5 cœurs permanent. \n" +
                    "§e§oLa police fait toujours partie d’§9§oHinamizawa. \n" +
                    "§e§oVous n’avez pas été suffisamment efficace.");
        }
    }

}
