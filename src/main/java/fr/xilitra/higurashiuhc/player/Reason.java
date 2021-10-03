package fr.xilitra.higurashiuhc.player;

public enum Reason {
    DOLL_TRAGEDY("DOLLTRAGEDY"),
    SATOSHI_HOJO("SATOSHI_HOJO"),
    OYASHIRO_TRAGEDY_EPISODE("OYATRAGEDYEP"),
    OYASHIRO_TRAGEDY("OYATRAGEDY");
    final String name;

    Reason(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isReason(Reason... mrList) {

        for (Reason mr : mrList)
            if (getName().equals(mr.getName()))
                return true;

        return false;

    }
}
