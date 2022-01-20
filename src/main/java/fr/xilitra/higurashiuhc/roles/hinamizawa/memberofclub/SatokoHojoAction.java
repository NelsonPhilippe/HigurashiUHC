package fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.traps.Traps;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SatokoHojoAction implements RoleAction, Listener {

    public List<Location> blockTraps = new ArrayList<>();

    @Override
    public Role getLinkedRole(){
        return Role.SATOKO_HOJO;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {

        if (!(e.getDamager() instanceof Snowball)) return;

        Snowball snowball = (Snowball) e.getDamager();

        if (!(snowball.getShooter() instanceof Player)) return;

        if (!(e.getEntity() instanceof Player)) return;

        Player shooter = (Player) snowball.getShooter();
        Player victim = (Player) e.getEntity();

        HPlayer hPlayerShooter = HigurashiUHC.getGameManager().getHPlayer(shooter.getUniqueId());
        if (hPlayerShooter == null)
            return;

        System.out.println(snowball.getCustomName());

        if (snowball.getCustomName().equalsIgnoreCase(Traps.slowBall.getLore())) {

            if (hPlayerShooter.getRole() != null && hPlayerShooter.getRole().isRole(Role.SATOKO_HOJO)) {

                victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 2, true));

            }

        }

    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        if (hPlayer == null)
            return;

        if (hPlayer.getRole() == null) {
            return;
        }

        if (!(hPlayer.getRole().isRole(Role.SATOKO_HOJO))) return;


        if (p.getItemInHand() == null || !p.getItemInHand().getItemMeta().hasLore() || p.getItemInHand().getType() == Material.AIR)
            return;

        ItemStack item = e.getItem();


        if (item.getItemMeta().getLore().get(0).equals(Traps.hoeTrap.getLore())) {

            Block block = e.getClickedBlock();
            Material type = block.getType();
            Location loc = block.getLocation();

            if (block.getType() == Material.DIRT || block.getType() == Material.GRASS || block.getType() == Material.SOIL) {

                Bukkit.getScheduler().runTask(HigurashiUHC.getInstance(), () -> block.setType(type));

            }

            blockTraps.add(loc);

        }
    }

    @EventHandler
    public void onProjectileLaunchEvent(ProjectileLaunchEvent e) {

        if (!(e.getEntity() instanceof Snowball)) return;

        Snowball snowball = (Snowball) e.getEntity();

        if (!(snowball.getShooter() instanceof Player)) return;

        Player shooter = (Player) snowball.getShooter();
        HPlayer hPlayerShooter = HigurashiUHC.getGameManager().getHPlayer(shooter.getUniqueId());
        if (hPlayerShooter == null)
            return;

        if (hPlayerShooter.getRole().isRole(Role.SATOKO_HOJO)) {


            snowball.setCustomName(Traps.slowBall.getLore());
            snowball.setCustomNameVisible(false);


        }

    }

    @EventHandler
    public void onRoleSelected(RoleSelected e) {

        HPlayer player = e.getPlayer();
        if (player.getPlayer() == null)
            return;

        if (player.getRole().isRole(Role.SATOKO_HOJO)) {

            Random random = new Random();

            int itemNumber = random.nextInt(3);

            ItemStack[] itemStacks = {Traps.slowBall.getItemStack(), Traps.hoeTrap.getItemStack(), Traps.fireCracker.getItemStack()};

            player.getPlayer().getInventory().addItem(itemStacks[itemNumber]);

        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        HPlayer hPlayer = HigurashiUHC.getGameManager().getHPlayer(p.getUniqueId());

        if (hPlayer == null)
            return;

        if (hPlayer.getRole().isRole(Role.SATOKO_HOJO)) {

            ItemStack itemStack = e.getItemInHand();

            if (!itemStack.getItemMeta().hasLore()) return;

            if (itemStack.getItemMeta().getLore().get(0).equals(Traps.fireCracker.getLore())) {

                System.out.println(itemStack.getItemMeta().getDisplayName());
                itemStack.setAmount(itemStack.getAmount() - 1);
            }

        }

    }

    public void removeTraps(HPlayer hPlayer) {
        if (hPlayer.getPlayer() == null)
            return;
        if (hPlayer.getRole().isRole(Role.SATOKO_HOJO)) {
            Inventory inventory = hPlayer.getPlayer().getInventory();

            for (ItemStack item : inventory.getContents()) {
                if (item.getItemMeta().getLore().get(0).equals(Traps.fireCracker.getLore())) {
                    inventory.remove(item);
                }
            }
        }
    }

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

        removeTraps(killed);

        HPlayer rika = Role.RIKA_FURUDE.getHPlayer();
        if(rika == null)
            return;

        Player rikaPlayer = rika.getPlayer();
        if(rikaPlayer == null)
            return;

        TextComponent textComponent = new TextComponent("§eSatoko §7est mort, si ");
        TextComponent click = new TextComponent("§6§nvous cliquez sur ce message");
        click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/h r "+killed.getName()));
        textComponent.addExtra(click);
        textComponent.addExtra(new TextComponent(", §eSatoko §7ressuscitera mais vous perdrez une de vos vies. "));

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
    public String getDescription() {
        return "§6 Vous êtes §9 Satoko Hojo (fille) : \n" +
                "\n" +
                "§9 Satoko §6 doit gagner avec §9 Hinamizawa §6 tout en faisant partie du §b Club. \n" +
                "§6Elle possède aléatoirement un type de piège parmi les trois disponibles.\n" +
                "\n" +
                "§6Vous possédez le piège de §7 défense: §6 Il est constitué de 3 boules de neiges infligeant l’effet slowness 2 pendant 5 secondes\n" +
                "\n" +
                "§6Vous possédez le piège §8d’attaque: §6il est constitué de 3 TNT prêtes à l'explosion lorsque celles-ci sont placées.\n" +
                "\n" +
                "§6Vous possédez le piège §0d’information: §6 Il est constitué d’une houe en bois permettant de piéger 3 fois dans la partie un bloc d’herbe où lorsqu'un joueur marche dessus, son camp est dévoilé à Satoko.";
    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }
}
