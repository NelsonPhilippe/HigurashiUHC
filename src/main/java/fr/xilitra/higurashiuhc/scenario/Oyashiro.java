package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameStates;
import fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro.KeiichiOyashiroTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro.ParanoTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro.RenaOyashiroTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

public class Oyashiro extends Scenario {

    public boolean reveal = false;
    public RenaOyashiroTask renaTaskID = new RenaOyashiroTask();
    public KeiichiOyashiroTask keiichiTaskID = new KeiichiOyashiroTask();
    public ParanoTask paranoTask = new ParanoTask();
    public BossBar keiichiBossBar = null;
    public BossBar renaBossBar = null;


    public Oyashiro() {
        super("Oyashiro");
    }

    @Override
    public void solution(int solution, Object... o) {

        renaTaskID.stopTask();
        keiichiTaskID.stopTask();
        paranoTask.stopTask();

        keiichiBossBar.getPlayers().forEach((player) -> keiichiBossBar.removePlayer(player));
        keiichiBossBar = null;

        renaBossBar.getPlayers().forEach((player) -> renaBossBar.removePlayer(player));
        renaBossBar = null;

    }

    @Override
    public Integer getSolutionNumber() {
        return null;
    }

    @Override
    protected void scenarioStateChange(boolean b) {
        if(!b && HigurashiUHC.getGameManager().getStates() == GameStates.GAME) {
            solution(5);
        }
    }

    public boolean isReveal(){
        return reveal;
    }

    public void revealOyashiro(){

        if(!(HigurashiUHC.getGameManager().getEpisode() == HigurashiUHC.getInstance().getConfig().getInt("tragedy.oyashiro.episode")) || !ScenarioList.OYASHIRO.isActive())
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

        renaTaskID.runTaskTimer(30000,30000);
        keiichiTaskID.runTaskTimer(60000,60000);

    }

    public BossBar getKeiichiBossBar(){
        return keiichiBossBar;
    }

    public BossBar getRenaBossBar(){
        return renaBossBar;
    }

    public void addKeiichiProggress(float num){
        keiichiBossBar.setProgress(keiichiBossBar.getProgress()+num);
        if(keiichiBossBar.getProgress()>100)
            solution(1);
    }

    public void addRenaProggress(float num){
        renaBossBar.setProgress(renaBossBar.getProgress()+num);
        if(renaBossBar.getProgress()<=0)
            solution(3);
    }

}
