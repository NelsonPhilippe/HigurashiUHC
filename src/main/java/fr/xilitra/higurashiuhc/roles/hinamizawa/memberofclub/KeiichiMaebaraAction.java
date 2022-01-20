package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.event.watanagashi.WatanagashiChangeEvent;
import fr.xilitra.higurashiuhc.item.config.DollItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.scenario.ScenarioList;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import fr.xilitra.higurashiuhc.utils.WataEnum;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KeiichiMaebaraAction implements RoleAction, Listener {

    @Override
    public Role getLinkedRole(){
        return Role.KEIICHI_MAEBARA;
    }

    @Override
    public String getDescription() {
        return "§6Vous êtes §9Keiichi Maebara (garçon) :\n" +
                "\n" +
                "§9Keiichi§6 doit gagner avec §9Hinamizawa§6 tout en faisant partie du §bClub§6." +
                "§9Keiichi§6 peut crafter la batte de baseball." +
                "§6Lorsqu’il frappe un joueur avec la batte, il inflige des dégâts de zone enlevant 2 cœurs dans un rayon de 5 blocs du joueur touché." +
                "§6Il ne peut l’utiliser que 3 fois dans la partie et une fois par épisode maximum." +
                "§6Attention, la batte de Baseball peut également être obtenue par §2Satoshi§6." +
                "§6Le premier à réaliser le craft obtiendra la batte de baseball. ";
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {
        if (killed.getRole().isRole(Role.RENA_RYUGU)) {
            ScenarioList.OYASHIRO.getScenario().solution(4, Role.RENA_RYUGU);
        }
    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

        if (ScenarioList.OYASHIRO.isActive())
            ScenarioList.OYASHIRO.getScenario().solution(2);

        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();
        if(rika == null)
            return;

        Player rikaPlayer = rika.getPlayer();
        if(rikaPlayer == null)
            return;

        TextComponent textComponent = new TextComponent("§4Keiichi §7est mort, si ");
        TextComponent click = new TextComponent("§6§nvous cliquez sur ce message");
        click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/h "+ Commands.RESSUCITE.getInitials() +" "+killed.getName()));
        textComponent.addExtra(click);
        textComponent.addExtra(new TextComponent(", §4Keiichi §7ressuscitera mais vous perdrez une de vos vies. "));

    }

    @Override
    public void onLeaveRole(HPlayer hPlayer) {

    }

    @Override
    public void onJoinRole(HPlayer hPlayer) {

    }

    @Override
    public void onGameStart() {
        if (ScenarioList.DOLL.isActive()) {
            HPlayer hPlayer = this.getLinkedRole().getHPlayer();
            if (hPlayer == null)
                return;
            Player player = hPlayer.getPlayer();
            if (player == null)
                return;
            player.getInventory().addItem(DollItem.dollItem.getItemStack());

            player.sendMessage("§7[§6Vous venez de recevoir la poupée§7] \n" +
                    "\n" +
                    "§7La §d§opoupée §7ne vous convient pas, vous décidez de l’offrir à quelqu’un d’autre. \n" +
                    "§7Pour cela il suffit de frapper un joueur avec la §d§opoupée §7dans les mains. \n" +
                    "§4FAITES ATTENTION ! \n" +
                    "§7§lChoisissez la bonne personne à qui donner la §d§opoupée§7, cela pourrait se retourner contre vous ou peut-être même vous arranger. \n" +
                    "§7Si vous ne donnez pas la §d§opoupée §7ou que vous la donnez à une mauvaise personne avant la Watanagashi, vous serez atteint de la malédiction et perdrez 5 cœurs permanents que vous régénérez tous les épisodes. ");

        }
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

    @EventHandler
    public void onWataChange(WatanagashiChangeEvent watanagashiChangeEvent) {

        if (watanagashiChangeEvent.getWataEnum() != WataEnum.DURING)
            return;

        KeiichiMaebaraAction role = (KeiichiMaebaraAction) Role.KEIICHI_MAEBARA.getRoleAction();
        HPlayer hPlayer = role.getLinkedRole().getHPlayer();
        if (hPlayer == null)
            return;

        if (hPlayer.hasMarriedReason(Reason.DOLL_TRAGEDY)) {
            Role shion = Role.SHION_SONOSAKI;
            if (shion.getHPlayer() != null && shion.getHPlayer().getPlayer() != null)
                shion.getHPlayer().getPlayer().sendMessage("Je te donne une petite info: " + hPlayer.getName() + " est marié à " + hPlayer.getMarriedPlayer(Reason.DOLL_TRAGEDY).get(0).getName());
        }

        Player keiichiPlayer = hPlayer.getPlayer();
        if (keiichiPlayer == null)
            return;

        Inventory inventory = keiichiPlayer.getInventory();

        for (ItemStack itemStack : inventory.getContents()) {

            if (itemStack.getItemMeta().getLore().get(0).equalsIgnoreCase(DollItem.dollItem.getLore())) {

                ScenarioList.DOLL.getScenario().solution(4);
                itemStack.setType(Material.AIR);
                break;

            }

        }

    }

}
