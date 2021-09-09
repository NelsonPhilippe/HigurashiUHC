package fr.xilitra.higurashiuhc.scenario;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.task.TaskRunner;
import fr.xilitra.higurashiuhc.game.task.taskClass.RenaOyashiroTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.RoleList;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.KeiichiMaebara;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.RenaRyugu;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

public class Oyashiro extends Scenario {

    public boolean reveal = false;
    public Integer taskID = null;

    public Oyashiro() {
        super("Oyashiro");
    }

    @Override
    public void solution(int solution, Object... o) {
        if(taskID != null)
            TaskRunner.getTask(taskID).deleteTask();
    }

    @Override
    public Integer getSolutionNumber() {
        return null;
    }

    public void revealOyashiro(){

        if(!(HigurashiUHC.getGameManager().getEpisode() >= HigurashiUHC.getInstance().getConfig().getInt("tragedy.oyashiro.episode") && ScenarioList.OYASHIRO.isActive()))
            return;

        if(HigurashiUHC.getGameManager().getEpisode() == HigurashiUHC.getInstance().getConfig().getInt("tragedy.oyashiro.episode")) {

            RoleList.RENA_RYUGU.getRole().getPlayer().addMaledictionReason(Reason.OYASHIRO_TRAGEDY);
            RoleList.KEIICHI_MAEBARA.getRole().getPlayer().getPlayer().sendMessage(ChatColor.WHITE + "(Oyashiro Tragedy)" + ChatColor.GOLD + "Rena à était atteint de la malédiction.");

        }

        if(!(HigurashiUHC.getGameManager().isWatanagashi() && !reveal))
            return;

        reveal = true;
        KeiichiMaebara km = (KeiichiMaebara) RoleList.KEIICHI_MAEBARA.getRole();
        RenaRyugu rr = (RenaRyugu) RoleList.RENA_RYUGU.getRole();

        HPlayer keiichi = km.getPlayer();
        HPlayer rena = rr.getPlayer();

        keiichi.getPlayer().sendMessage(ChatColor.WHITE + "(Oyashiro Tragedy)" + ChatColor.GOLD + "Le rôle de Rena est joué par "+rena.getName()+".");
        rena.getPlayer().sendMessage(ChatColor.WHITE + "(Oyashiro Tragedy)" + ChatColor.GOLD + "Le rôle de Keiichi est joué par "+keiichi.getName()+".");

        BossBar kmBB = BossBarAPI.addBar(keiichi.getPlayer(), new TextComponent("Malédiction d'Oyashiro"), BossBarAPI.Color.RED, BossBarAPI.Style.PROGRESS, 100);
        km.setBossBar(kmBB);

        BossBar rrBB = BossBarAPI.addBar(rena.getPlayer(), new TextComponent("Malédiction d'Oyashiro"), BossBarAPI.Color.RED, BossBarAPI.Style.PROGRESS, 100);
        rr.setBossBar(rrBB);

        new RenaOyashiroTask().runTask(1000,1000);

    }

}
