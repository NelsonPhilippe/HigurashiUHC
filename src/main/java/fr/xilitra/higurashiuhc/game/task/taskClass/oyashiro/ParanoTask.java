package fr.xilitra.higurashiuhc.game.task.taskClass.oyashiro;

import fr.xilitra.higurashiuhc.game.task.BukkitTask;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ParanoTask extends BukkitTask {

    @Override
    public void execute() {

        Effect[] effectList = Effect.values();
        Effect toApply = effectList[new Random().nextInt(effectList.length)];

        HPlayer keiichiHPlayer = Role.KEIICHI_MAEBARA.getHPlayer();
        if(keiichiHPlayer == null)
            return;

        Player keiichiPlayer = keiichiHPlayer.getPlayer();
        if(keiichiPlayer == null)
            return;

        switch (toApply){

            case DAMAGE:{
                keiichiPlayer.damage(0.5);
            }

            case HUNGER:{
                keiichiPlayer.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 200, 1));
            }

            case SHUFFLE:{
                Random random = new Random();
                List<ItemStack> itemStackList = new ArrayList<>(Arrays.asList(keiichiPlayer.getInventory().getContents()));
                ArrayList<Integer> invPositionList = new ArrayList<Integer>(){{
                   for(int pos = 0; pos<itemStackList.size(); pos++)
                       add(pos);
                }};

                Inventory keiichiInv = keiichiPlayer.getInventory();

                keiichiInv.clear();

                while (itemStackList.size() != 0){

                    int itemPosInList = random.nextInt(itemStackList.size());
                    int itemPosInInv = invPositionList.get(random.nextInt(invPositionList.size()));

                    ItemStack item = itemStackList.get(itemPosInList);

                    itemStackList.remove(itemPosInList);
                    invPositionList.remove(itemPosInInv);

                    keiichiInv.setItem(itemPosInInv, item);

                }

            }

            case BLINDNESS:{
                keiichiPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1));
            }

            case SOUND:{
                List<Sound> soundList = Arrays.asList(Sound.HORSE_SKELETON_DEATH, Sound.HORSE_ZOMBIE_DEATH, Sound.PORTAL_TRAVEL);
                keiichiPlayer.playSound(keiichiPlayer.getLocation(), soundList.get(new Random().nextInt(soundList.size())), 1, 1);
            }

        }

    }

    private enum Effect{

        BLINDNESS(),
        DAMAGE(),
        SHUFFLE(),
        SOUND(),
        HUNGER()

    }

}
