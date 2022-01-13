package fr.xilitra.higurashiuhc.clans;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.InfoData;
import fr.xilitra.higurashiuhc.roles.Role;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public enum Clans {

    HINAMIZAWA(
            0,
            "Hinamizawa",
            null
    ),

    MEMBER_OF_CLUB(
            1,
            "MemberOfClub",
            0
    ),

    SONOZAKI(
            2,
            "Sonozaki",
            0
    ),

    MERCENAIRE(
            3,
            "Mercenaire",
            null
    ),

    NEUTRE(
            4,
            "Neutre",
            null
    ),

    POLICE(
            5,
            "Police",
            0
    );

    private final int id;
    private final String name;
    private final List<UUID> playerList = new ArrayList<>();
    private Integer majorClans;

    Clans(int id, String name, Integer majorClansID) {
        this.id = id;
        this.name = name;
        this.majorClans = majorClansID;
    }

    public static Clans getClans(int id) {
        for (Clans clans : values())
            if (id == clans.getId())
                return clans;
        return null;
    }

    public static Clans getClans(HPlayer hPlayer) {
        for (Clans clans : values())
            if (clans.hisInClans(hPlayer, false))
                return clans;
        return null;
    }

    public static Clans getClans(String clansName) {
        for (Clans clans : values())
            if (clans.getName().equals(clansName))
                return clans;
        return null;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isClans(Clans clans) {
        return clans.getId() == getId();
    }


    public List<HPlayer> getHPlayerList() {
        return getHPlayerList(false);
    }

    public List<HPlayer> getHPlayerList(boolean subClans) {

        GameManager gm = HigurashiUHC.getGameManager();
        return new ArrayList<HPlayer>() {{
            for (UUID uuid : getUUIDList(subClans))
                add(gm.getHPlayer(uuid));
        }};

    }

    public List<UUID> getUUIDList() {
        return getUUIDList(false);
    }

    public List<UUID> getUUIDList(boolean minorClans) {
        if(!minorClans)
        return playerList;
        else return new ArrayList<UUID>(){{
           addAll(getUUIDList(false));
           for(Clans clans : getMinorClans())
               addAll(clans.getUUIDList(true));
        }};
    }

    public List<Role> getRoleList() {

        return new ArrayList<Role>() {{
            for (HPlayer hPlayer : getHPlayerList())
                add(hPlayer.getRole());
        }};

    }

    public void addPlayer(HPlayer p) {
        Clans prevClans = getClans(p);
        if (prevClans != null)
            prevClans.removePlayer(p);
        this.playerList.add(p.getUUID());
        p.getInfoData().setDataInfo(InfoData.InfoList.CLAN.name(), getName());
        Player player = p.getPlayer();
        if(player != null)
            player.sendMessage("§7Vous avez rejoins le clan d’§9" + getName() + "§7. ");
    }

    public void removePlayer(HPlayer p) {
        this.playerList.remove(p.getUUID());
        Player player = p.getPlayer();
        if(player != null)
            player.sendMessage("§7Vous avez quitté le clan d’§9" + getName() + "§7. ");
    }

    public boolean hisInClans(HPlayer player, boolean checkMinor) {
        if (playerList.contains(player.getUUID()))
            return true;
        if (checkMinor)
            for (Clans minorClans : getMinorClans())
                if (minorClans.hisInClans(player, true))
                    return true;
        return false;
    }


    public Clans getMajorClans() {
        if (majorClans != null)
            Clans.getClans(majorClans);
        return this;
    }

    public List<Clans> getMinorClans() {
        List<Clans> minorClans = new ArrayList<>();
        for (Clans clans : Clans.values())
            if (isClans(clans.getMajorClans()) && !isClans(clans))
                minorClans.add(clans);
        return minorClans;
    }

    public boolean hasMinorClans(Clans clans) {
        return getMinorClans().contains(clans);
    }

    public boolean isMinorClans() {
        return !getMajorClans().isClans(this);
    }

    public void setMajorClans(Clans clans){
        if(clans == null){
            this.majorClans = null;
            return;
        }
        this.majorClans = clans.getId();
    }

}
