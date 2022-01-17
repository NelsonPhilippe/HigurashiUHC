package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.config.ConfigLocation;
import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.game.task.taskClass.NearKeiichi;
import fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro.KeiichiOyashiroTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro.ParanoTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro.RenaOyashiroTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

public class Oyashiro extends Scenario implements Listener {

    public boolean reveal = false;
    public RenaOyashiroTask renaTaskID = new RenaOyashiroTask();
    public KeiichiOyashiroTask keiichiTaskID = new KeiichiOyashiroTask();
    public NearKeiichi nearKeiichi = new NearKeiichi();
    public ParanoTask paranoTask = new ParanoTask();
    public BossBar keiichiBossBar = null;
    public BossBar renaBossBar = null;
    public Integer solNumber = null;


    public Oyashiro() {
        super("Oyashiro");
    }

    @Override
    public void solution(int solution, Object... o) {

        if (solNumber != null)
            return;

        renaTaskID.stopTask();
        keiichiTaskID.stopTask();
        paranoTask.stopTask();
        nearKeiichi.stopTask();

        keiichiBossBar.getPlayers().forEach((player) -> keiichiBossBar.removePlayer(player));
        keiichiBossBar = null;

        renaBossBar.getPlayers().forEach((player) -> renaBossBar.removePlayer(player));
        renaBossBar = null;

        solNumber = solution;

        if(solution == 1 && Role.RENA_RYUGU.getHPlayer() != null && Role.KEIICHI_MAEBARA.getHPlayer() != null) {
            Role.KEIICHI_MAEBARA.getHPlayer().addMaledictionReason(Reason.OYASHIRO_SOL1);
            Player keiichiPlayer = Role.KEIICHI_MAEBARA.getHPlayer().getPlayer();
            if (keiichiPlayer != null)
                keiichiPlayer.sendMessage("§aVotre jauge §7a atteint sont paroxysme, vous ne pouvez pas attaquer §9Rena §7tant que tous les joueurs sont en vie. \n" +
                        "§7Une fois que vous ne serez plus que deux, le duel au sommet vous attend.");
            Role.RENA_RYUGU.getHPlayer().getLinkData(Role.KEIICHI_MAEBARA.getHPlayer()).setDamagelessLinked(Reason.OYASHIRO_SOL1, true);
            Player renaPlayer = Role.RENA_RYUGU.getHPlayer().getPlayer();
            if (renaPlayer != null)
                renaPlayer.sendMessage("§7Vous avez réussi à faire monter la §ajauge de Keiichi §7au maximum avant que votre §ajauge §7tombe à 0. \n" +
                        "\n" +
                        "§9Keiichi §7est maintenant atteint de la §5§nmalédiction §7ce qui vous fait un allié de taille. \n" +
                        "§7Vous ne pouvez pas vous attaquer tant que tous les joueurs sont en vie. \n" +
                        "Une fois que vous serez que deux,\n" +
                        "\n" +
                        "§4§o§l⚠ le duel au sommet vous attend ⚠");
        }else if (solution == 3 && Role.RENA_RYUGU.getHPlayer() != null)
            Clans.HINAMIZAWA.addPlayer(Role.RENA_RYUGU.getHPlayer());
        else if (solution == 4) {

            HPlayer hPlayer = Role.KEIICHI_MAEBARA.getHPlayer();
            if (hPlayer == null || hPlayer.getPlayer() == null)
                return;

            Player player = hPlayer.getPlayer();
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));

        }

    }

    @Override
    public Integer getSolutionNumber() {
        return solNumber;
    }

    @Override
    protected void scenarioStateChange(boolean b) {
        if (!b && HigurashiUHC.getGameManager().getStates() == GameStates.GAME) {
            solution(5);
        }
    }

    public boolean isReveal() {
        return reveal;
    }

    @EventHandler
    public void revealOyashiro(EpisodeUpdate episodeUpdate) {

        if (!(HigurashiUHC.getGameManager().getEpisode() == HigurashiUHC.getGameManager().getConfigGestion().getConfig().getInt(ConfigLocation.EPISODE_TRAGEDY_OYASHIRO)) || !ScenarioList.OYASHIRO.isActive())
            return;

        Role km = Role.KEIICHI_MAEBARA;
        Role rr = Role.RENA_RYUGU;

        HPlayer keiichi = km.getHPlayer();
        HPlayer rena = rr.getHPlayer();

        if ((rena == null || rena.getPlayer() == null) || (keiichi == null || keiichi.getPlayer() == null) || reveal)
            return;

        reveal = true;

        rena.addMaledictionReason(Reason.OYASHIRO_TRAGEDY);
        keiichi.getPlayer().sendMessage(ChatColor.WHITE + "(Oyashiro Tragedy)" + ChatColor.GOLD + "Rena à était atteint de la malédiction.");

        ///keiichi.getPlayer().sendMessage(ChatColor.WHITE + "(Oyashiro Tragedy)" + ChatColor.GOLD + "Le rôle de Rena est joué par "+rena.getName()+".");
        ///rena.getPlayer().sendMessage(ChatColor.WHITE + "(Oyashiro Tragedy)" + ChatColor.GOLD + "Le rôle de Keiichi est joué par "+keiichi.getName()+".");

        keiichiBossBar = BossBarAPI.addBar(keiichi.getPlayer(), new TextComponent("Malédiction d'Oyashiro"), BossBarAPI.Color.RED, BossBarAPI.Style.PROGRESS, 100);

        renaBossBar = BossBarAPI.addBar(rena.getPlayer(), new TextComponent("Malédiction d'Oyashiro"), BossBarAPI.Color.RED, BossBarAPI.Style.PROGRESS, 100);

        renaTaskID.runTaskTimer(30, 30);
        keiichiTaskID.runTaskTimer(60, 60);
        nearKeiichi.runTaskTimer(600, 600);

    }

    public BossBar getKeiichiBossBar() {
        return keiichiBossBar;
    }

    public BossBar getRenaBossBar() {
        return renaBossBar;
    }

    public void addKeiichiProggress(float num) {
        keiichiBossBar.setProgress(keiichiBossBar.getProgress() + num);
        if (keiichiBossBar.getProgress() >= 100)
            solution(1);
    }

    public void addRenaProggress(float num) {
        renaBossBar.setProgress(renaBossBar.getProgress() + num);
        if (renaBossBar.getProgress() <= 0)
            solution(3);
    }

}
