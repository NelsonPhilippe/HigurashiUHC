package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ShionSonozakiAction implements RoleAction, Listener {

    @Override
    public Role getLinkedRole(){
        return Role.SHION_SONOSAKI;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §9Shion Sonozaki (fille) : \n" +
                "\n" +
                "§9Shion§6 doit gagner avec §9Hinamizawa§6 tout en faisant partie du §bClub§6 et du clan §3Sonozaki§6." +
                "§6Elle possède 2 cœurs supplémentaires qu’elle a en commun avec §9Mion Sonozaki§6." +
                "§6Si §9Mion§6 meurt, elle perd son bonus de 2 cœurs." +
                "§6Si §eKumagai§6 vous espionne, §9Shion§6 aura 50% de chances de se faire voir comme faisant partie du clan §3Sonozaki§6 ou du §bClub§6.";
    }

    public static void removeHearth(HPlayer deathPlayer, HPlayer playerAlive) {
        if (deathPlayer == null) return;

        if (playerAlive != null && playerAlive.getPlayer() != null && playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR) {

            playerAlive.getPlayer().setMaxHealth(20);

        }

    }

    @EventHandler
    public void onRoleSelected(RoleSelected e) {
        HPlayer player = e.getPlayer();

        if (player.getPlayer() != null && player.getRole().isRole(Role.SHION_SONOSAKI)) {
            player.getPlayer().setMaxHealth(24);
            player.getPlayer().setHealth(24);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){

        Entity damaged = event.getEntity();

        if(!(damaged instanceof Player)) return;

        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(event.getEntity().getUniqueId());
        HPlayer mionPlayer = Role.MION_SONOZAKI.getHPlayer();
        double damage = event.getDamage();

        if(hPlayer == null || mionPlayer == null) return;

        if(hPlayer.getRole().isRole(Role.SHION_SONOSAKI)){

            if(hPlayer.getPlayer().getHealth() >= 20){
                if(mionPlayer.getPlayer().getHealth() >= 20){
                    mionPlayer.getPlayer().damage(damage);
                }
            }

        }
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

        if (killed.getPlayer() == null)
            return;

        if (killer.getPlayer() == null)
            return;

        if (killed.hasMarriedReason(Reason.DOLL_TRAGEDY) && HigurashiUHC.getGameManager().isWataState(WataEnum.AFTER))
            killer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, 1), true);

    }

    @Override
    public boolean onDeath(HPlayer killed, DeathReason dr) {

        HPlayer playerAlive = Role.MION_SONOZAKI.getHPlayer();
        if (playerAlive != null) {

            if (playerAlive.getPlayer() != null && playerAlive.getPlayer().getGameMode() != GameMode.SPECTATOR)
                removeHearth(killed, playerAlive);

            HPlayer kasai = Role.KASAI.getHPlayer();

            if (kasai != null && kasai.getPlayer() != null)
                if (killed.getKiller() != null)
                    kasai.getPlayer().sendMessage(killed.getName() + " à été tué par " + killed.getKiller().getName());
        }

        HPlayer kasai = Role.KASAI.getHPlayer();
        if(kasai != null) {

            Player kasaiPlayer = kasai.getPlayer();
            if (kasaiPlayer != null) {
                kasaiPlayer.sendMessage("§3§oShion §7§ovient d’être tuée, voici le rôle du joueur qui à tué §3§oShion: §6§o'rôle'");
            }

        }

        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();
        if(rika == null)
            return true;

        Player rikaPlayer = rika.getPlayer();
        if(rikaPlayer == null)
            return true;

        TextComponent textComponent = new TextComponent("§aShion §7est mort, si ");
        TextComponent click = new TextComponent("§6§nvous cliquez sur ce message");
        click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/h "+ Commands.RESSUCITE.getInitials() +" "+killed.getName()));
        textComponent.addExtra(click);
        textComponent.addExtra(new TextComponent(", §aShion §7ressuscitera mais vous perdrez une de vos vies. "));

        return true;
    }

    @Override
    public void onGameStart() {
        HPlayer hPlayer = this.getLinkedRole().getHPlayer();
        if (hPlayer == null)
            return;
        Player player = hPlayer.getPlayer();
        if (player == null)
            return;
        player.setMaxHealth(24);
    }

}
