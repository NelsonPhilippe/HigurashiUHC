package fr.xilitra.higurashiuhc.item.config;

import fr.xilitra.higurashiuhc.item.ItemBuilder;
import org.bukkit.Material;

public class ScenarioItem extends ItemBuilder {

    public static ScenarioItem scenarioItem = new ScenarioItem();

    public ScenarioItem() {
        super("Scenario", true, 1, Material.BOOK_AND_QUILL);
    }
}
