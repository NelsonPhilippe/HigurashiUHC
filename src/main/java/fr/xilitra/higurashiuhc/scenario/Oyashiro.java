package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.TaskRunner;
import fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro.KeiichiOyashiroTask;
import fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro.RenaOyashiroTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.KeiichiMaebaraAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyuguAction;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

public class Oyashiro extends Scenario {

    public boolean reveal = false;
    public Integer renaTaskID = null, keiichiTaskID = null;

    public Oyashiro() {
        super("Oyashiro");
    }

    @Override
    public void solution(int solution, Object... o) {
        if(renaTaskID != null)
            TaskRunner.getTask(renaTaskID).stopTask();
        if(keiichiTaskID != null)
            TaskRunner.getTask(keiichiTaskID).stopTask();
        KeiichiMaebaraAction km = (KeiichiMaebaraAction) Role.KEIICHI_MAEBARA.getRoleAction();
        km.getBossBar().getPlayers().forEach((player) -> km.getBossBar().removePlayer(player));
        km.setBossBar(null);
        RenaRyuguAction rr = (RenaRyuguAction) Role.RENA_RYUGU.getRoleAction();
        rr.getBossBar().getPlayers().forEach((player) -> rr.getBossBar().removePlayer(player));
        rr.setBossBar(null);
    }

    @Override
    public Integer getSolutionNumber() {
        return null;
    }

    @Override
    protected void scenarioStateChange(boolean b) {
        if(!b) {
            solution(5);
        }
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

        BossBar kmBB = BossBarAPI.addBar(keiichi.getPlayer(), new TextComponent("Malédiction d'Oyashiro"), BossBarAPI.Color.RED, BossBarAPI.Style.PROGRESS, 100);
        ((KeiichiMaebaraAction)km.getRoleAction()).setBossBar(kmBB);

        BossBar rrBB = BossBarAPI.addBar(rena.getPlayer(), new TextComponent("Malédiction d'Oyashiro"), BossBarAPI.Color.RED, BossBarAPI.Style.PROGRESS, 100);
        ((RenaRyuguAction)rr.getRoleAction()).setBossBar(rrBB);

        RenaOyashiroTask rot = new RenaOyashiroTask();
        rot.runTask(30000,30000);
        renaTaskID = rot.getTaskID();

        KeiichiOyashiroTask kot = new KeiichiOyashiroTask();
        kot.runTask(60000,60000);
        keiichiTaskID = kot.getTaskID();

    }

}
