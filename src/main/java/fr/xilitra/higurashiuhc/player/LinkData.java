package fr.xilitra.higurashiuhc.player;

import fr.xilitra.higurashiuhc.HigurashiUHC;

public class LinkData {

    private final HPlayer linkedPlayer;
    private final HPlayer ogPlayer;
    public Reason deathLinkReason = null;
    public Reason mariedReason = null;
    public Reason winLinkReason = null;
    public Reason damageCancelled = null;

    public LinkData(HPlayer original, HPlayer linkedPlayer) {
        this.ogPlayer = original;
        this.linkedPlayer = linkedPlayer;
    }

    public boolean isDeathLinked() {
        return deathLinkReason != null;
    }

    public void setDeathLinked(Reason reason, boolean both) {
        deathLinkReason = reason;
        if(reason == null)
        HigurashiUHC.getGameManager().log(ogPlayer.getName() + " -> "+linkedPlayer.getName()+ " Death Linked removed");
        else HigurashiUHC.getGameManager().log(ogPlayer.getName() + " -> "+linkedPlayer.getName()+ " Death Linked reason set: "+ reason.getName());
        if (both)
            linkedPlayer.getLinkData(ogPlayer).setDeathLinked(reason, false);
    }

    public Reason getDeathLinkReason() {
        return deathLinkReason;
    }


    public boolean isMariedLinked() {
        return mariedReason != null;
    }

    public void setMariedLinked(Reason reason, boolean both) {
        mariedReason = reason;
        if(reason == null)
            HigurashiUHC.getGameManager().log(ogPlayer.getName() + " -> "+linkedPlayer.getName()+ " Mary Linked removed");
        else HigurashiUHC.getGameManager().log(ogPlayer.getName() + " -> "+linkedPlayer.getName()+ " Mary Linked reason set: "+ reason.getName());
        if (both)
            linkedPlayer.getLinkData(ogPlayer).setMariedLinked(reason, false);
    }

    public Reason getMariedLinkReason() {
        return mariedReason;
    }


    public boolean isWinLinked() {
        return winLinkReason != null;
    }

    public void setWinLinked(Reason reason, boolean both) {
        winLinkReason = reason;
        if(reason == null)
            HigurashiUHC.getGameManager().log(ogPlayer.getName() + " -> "+linkedPlayer.getName()+ " Win Linked removed");
        else HigurashiUHC.getGameManager().log(ogPlayer.getName() + " -> "+linkedPlayer.getName()+ " Win Linked reason set: "+ reason.getName());
        if (both)
            linkedPlayer.getLinkData(ogPlayer).setWinLinked(reason, false);
    }

    public Reason getWinLinkReason() {
        return winLinkReason;
    }

    public boolean isDamageCancelled() {
        return damageCancelled != null;
    }

    public void setDamagelessLinked(Reason reason, boolean both) {
        damageCancelled = reason;
        if(reason == null)
            HigurashiUHC.getGameManager().log(ogPlayer.getName() + " -> "+linkedPlayer.getName()+ " Damageless Linked removed");
        else HigurashiUHC.getGameManager().log(ogPlayer.getName() + " -> "+linkedPlayer.getName()+ " Damageless Linked reason set: "+ reason.getName());
        if (both)
            linkedPlayer.getLinkData(ogPlayer).setDamagelessLinked(reason, false);
    }

    public Reason getDamagelessReason() {
        return winLinkReason;
    }

    public HPlayer getOgPlayer() {
        return ogPlayer;
    }

    public HPlayer getLinkedPlayer() {
        return linkedPlayer;
    }

}
