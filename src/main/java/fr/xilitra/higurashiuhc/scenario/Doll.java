package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Doll extends Scenario implements Listener {

    private Integer appliedSolution = null;

    public Doll() {
        super("Poupée");
    }

    @Override
    public void solution(int solutionN, Object... o) {
        this.appliedSolution = solutionN;
        if (solutionN == 1) {
            if (Role.MION_SONOZAKI.getHPlayer() == null)
                return;
            Role.MION_SONOZAKI.getHPlayer().addMaledictionReason(Reason.DOLL_TRAGEDY);
            Role.MION_SONOZAKI.getHPlayer().getLinkData((HPlayer) o[0]).setDeathLinked(Reason.DOLL_TRAGEDY, false);
            HPlayer shion = Role.SHION_SONOSAKI.getHPlayer();
            if (shion == null || shion.getPlayer() == null)
                return;
            shion.getPlayer().sendMessage("§9Keiichi §7vient de vous donner la §6§opoupée, §7ceci est une preuve de son amitié.");
            HPlayer keiichi = Role.KEIICHI_MAEBARA.getHPlayer();
            if (keiichi == null || keiichi.getPlayer() == null)
                return;
            keiichi.getPlayer().sendMessage("§7Vous avez donné la §6§opoupée §7à §9Shion ! \n" +
                    "\n" +
                    "§7Par conséquent, §9Mion §7est maintenant atteinte de la §5§nmalédiction. \n" +
                    "§7Vous êtes lié à §9Mion §7tant qu’elle n’aura pas tué de joueur. \n" +
                    "prenez les responsabilités de votre décision.\n");
        } else if (solutionN == 2) {
            if (Role.SHION_SONOSAKI.getHPlayer() == null)
                return;
            Role.SHION_SONOSAKI.getHPlayer().addMaledictionReason(Reason.DOLL_TRAGEDY);
            Role.SHION_SONOSAKI.getHPlayer().getLinkData((HPlayer) o[0]).setDeathLinked(Reason.DOLL_TRAGEDY, false);
            HPlayer rena = Role.RENA_RYUGU.getHPlayer();
            if (rena == null || rena.getPlayer() == null)
                return;
            rena.getPlayer().sendMessage("§9Keiichi §7vient de vous donner la §6§opoupée, §7ceci est une preuve de son amitié.");
            HPlayer keiichi = Role.KEIICHI_MAEBARA.getHPlayer();
            if (keiichi == null || keiichi.getPlayer() == null)
                return;
            keiichi.getPlayer().sendMessage("§7Vous avez donné la §6§opoupée §7à §9Rena ! \n" +
                    "\n" +
                    "§7Par conséquent, §9Shion §7est maintenant atteinte de la §5§nmalédiction. \n" +
                    "§7Vous êtes lié à §9Shion §7tant qu’elle n’aura pas tué de joueur. \n" +
                    "prenez les responsabilités de votre décision.\n");
        } else if (solutionN == 3) {
            if (Role.MION_SONOZAKI.getHPlayer() == null)
                return;
            Role.MION_SONOZAKI.getHPlayer().getLinkData((HPlayer) o[0]).setMariedLinked(Reason.DOLL_TRAGEDY, true);
            HPlayer shion = Role.SHION_SONOSAKI.getHPlayer();
            if (shion == null || shion.getPlayer() == null)
                return;
            shion.getPlayer().sendMessage("§9Keiichi §7à donné la §6§opoupée §7à §9Rena §7! ");
            HPlayer keiichi = Role.KEIICHI_MAEBARA.getHPlayer();
            if (keiichi == null || keiichi.getPlayer() == null)
                return;
            keiichi.getPlayer().sendMessage("§d♥ Vous avez donné la §6§opoupée §dà §9Mion §det avouez de ce fait vos sentiments ! ♥\n" +
                    "\n" +
                    "§dVous êtes dorénavant en couple avec §9Mion. \n" +
                    "§dVous devez gagner à deux et être les derniers en vie, si l’un de vous tombe à 5 cœurs, votre moitié sera avertie du danger. \n" +
                    "§dSi l’un de vous meurt, l’autre joueur sera automatiquement atteint de la malédiction et devra gagner seul. \n" +
                    "§dProtégez-vous entre vous pour prouver votre amour au monde entier.\n");
        } else {
            HPlayer hPlayer = Role.KEIICHI_MAEBARA.getHPlayer();
            if (hPlayer == null || hPlayer.getPlayer() == null)
                return;
            Player player = hPlayer.getPlayer();
            player.setMaxHealth(player.getMaxHealth() - 5);
            Role.KEIICHI_MAEBARA.getHPlayer().addMaledictionReason(Reason.DOLL_TRAGEDY);
            player.sendMessage("§7Vous n’avez donné la §6§opoupée §7à personne. \n" +
                    "\n" +
                    "§7Vous êtes donc de ce fait atteint de la §5§nmalédiction §7et vous perdez 5 cœurs ! \n" +
                    "§7Vous avez rendu beaucoup de joueurs tristes et vous n’avez pas su faire confiance à votre entourage. \n" +
                    "§7Dommage.");
        }
    }

    @Override
    public Integer getSolutionNumber() {
        return appliedSolution;
    }

    @Override
    protected void scenarioStateChange(boolean b) {

    }

    @EventHandler
    public void episodeListener(EpisodeUpdate episodeUpdate) {
        HPlayer keiichi = Role.KEIICHI_MAEBARA.getHPlayer();
        if (keiichi != null && keiichi.getPlayer() != null)
            if (keiichi.getPlayer().getMaxHealth() < 20) {
                keiichi.getPlayer().sendMessage("DOLL) Un episode est passé, tu viens de gagner un coeur de plus.");
                keiichi.getPlayer().setMaxHealth(keiichi.getPlayer().getMaxHealth() + 1);
            }

    }

}
