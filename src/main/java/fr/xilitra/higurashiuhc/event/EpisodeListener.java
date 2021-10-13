package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.Reason;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EpisodeListener implements Listener {

    private final ArrayList<Integer> avEPOyashiro = new ArrayList<Integer>() {{
        for (int ep = 3; ep <= 5; ep++)
            add(ep);
    }};

    private final Integer randomEP = avEPOyashiro.get(new Random().nextInt(avEPOyashiro.size()));

    @EventHandler
    public void onEpisodeUpdated(EpisodeUpdate e) {

        CustomCraft.baseballBat.setUsedOnEpisode(false);

        if (randomEP == e.getEpisode()) {

            List<HPlayer> roleLists = Clans.MEMBER_OF_CLUB.getHPlayerList();

            if (!roleLists.isEmpty())
                roleLists.get(new Random().nextInt(roleLists.size())).addMaledictionReason(Reason.OYASHIRO_TRAGEDY_EPISODE);

        }

    }

}
