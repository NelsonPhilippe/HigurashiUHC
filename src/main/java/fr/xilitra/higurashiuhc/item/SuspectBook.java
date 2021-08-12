package fr.xilitra.higurashiuhc.item;

import org.bukkit.Material;

import java.util.UUID;

public class SuspectBook extends ItemBuilder {

    public static SuspectBook suspectBook = new SuspectBook();

    public SuspectBook() {
        super("Suspect Book", true, 1, Material.BOOK);
        this.setLore(UUID.randomUUID().toString());
    }
}
