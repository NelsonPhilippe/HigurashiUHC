package fr.xilitra.higurashiuhc.event;

import fr.xilitra.higurashiuhc.event.higurashi.EpisodeUpdate;
import fr.xilitra.higurashiuhc.utils.CustomCraft;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EpisodeListener implements Listener {

    @EventHandler
    public void onEpisodeUpdated(EpisodeUpdate e){

        CustomCraft.baseballBat.setUsedOnEpisode(false);

    }

}
