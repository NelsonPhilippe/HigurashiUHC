package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

import static fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.ShionSonozakiAction.removeHearth;

public class MionSonozakiAction implements RoleAction, Listener {

    @Override
    public Role getLinkedRole(){
        return Role.MION_SONOZAKI;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §9Mion Sonozaki (fille) : \n" +
                "\n" +
                "§9Mion§6 doit gagner avec §9Hinamizawa§6 tout en faisant partie du §bClub§6 et du clan §3Sonozaki§6." +
                "§6Elle possède 2 cœurs supplémentaires qu’elle a en commun avec §9Shion Sonozaki§6." +
                "§6Si §9Shion§6 meurt, elle perd son bonus de 2 cœurs." +
                "§6Si §eKumagai§6 vous espionne, §9Mion§6 aura 50% de chances de se faire voir comme faisant partie du clan §3Sonozaki§6 ou du §bClub§6.";
    }

    @EventHandler
    public void onRoleSelected(RoleSelected e) {
        HPlayer player = e.getPlayer();

        if (player.getPlayer() != null && player.getRole().isRole(Role.MION_SONOZAKI)) {
            player.getPlayer().setMaxHealth(24);
            player.getPlayer().setHealth(24);
        }
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

        if (!killer.hasMaledictionReason(Reason.DOLL_TRAGEDY))
            return;

        if (!killed.getRole().isRole(Role.RENA_RYUGU, Role.SHION_SONOSAKI, Role.SATOKO_HOJO, Role.KEIICHI_MAEBARA))
            return;

        if (killer.hasDeathLinkReason(Reason.DOLL_TRAGEDY)) {
            List<HPlayer> ltd = killer.getDeathLinkPlayer(Reason.DOLL_TRAGEDY);
            ltd.forEach((pl) -> killer.getLinkData(pl).setDeathLinked(null, false));
        }

        List<Role> roleList = new ArrayList<Role>() {{
            add(Role.RENA_RYUGU);
            add(Role.SHION_SONOSAKI);
            add(Role.SATOKO_HOJO);
            add(Role.KEIICHI_MAEBARA);
        }};

        if (killer.getPlayer() != null)
            killer.getPlayer().setMaxHealth(killer.getPlayer().getMaxHealth() + 1);

        for (Role role : roleList) {
            if (role.getHPlayer() == null)
                return;
            Role killerRole = role.getHPlayer().getKillerRole();
            if (killerRole == null)
                return;
            if (killerRole != this.getLinkedRole())
                return;
        }

        killer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 1), true);

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

        HPlayer playerAlive = Role.SHION_SONOSAKI.getHPlayer();

        if (playerAlive != null && playerAlive.getPlayer() != null && killed.getRole().isRole(Role.MION_SONOZAKI)) {

            if (playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR) {
                removeHearth(killed, playerAlive);
            }

        }


        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();
        if(rika == null)
            return;

        Player rikaPlayer = rika.getPlayer();
        if(rikaPlayer == null)
            return;

        TextComponent textComponent = new TextComponent("§aMion §7est mort, si ");
        TextComponent click = new TextComponent("§6§nvous cliquez sur ce message");
        click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/h r "+killed.getName()));
        textComponent.addExtra(click);
        textComponent.addExtra(new TextComponent(", §aMion §7ressuscitera mais vous perdrez une de vos vies. "));

    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {
        HPlayer hPlayer = this.getLinkedRole().getHPlayer();
        if (hPlayer == null)
            return;
        Player player = hPlayer.getPlayer();
        if (player == null)
            return;
        player.setMaxHealth(22);
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
