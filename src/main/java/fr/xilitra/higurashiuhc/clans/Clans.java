package fr.xilitra.higurashiuhc.clans;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.player.InfoData;
import fr.xilitra.higurashiuhc.roles.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public enum Clans {

    HINAMIZAWA(
            0,
            "Hinamizawa",
            null,
            null,
            new ArrayList<Integer>() {{
                add(5);
            }}
    ),

    MEMBER_OF_CLUB(
            1,
            "MemberOfClub",
            0,
            null,
            null
    ),

    SONOZAKI(
            2,
            "Sonozaki",
            0,
            null,
            null
    ),

    MERCENAIRE(
            3,
            "Mercenaire",
            null,
            null,
            null
    ),

    NEUTRE(
            4,
            "Neutre",
            null,
            null,
            null
    ),

    POLICE(
            5,
            "Police",
            null,
            null,
            new ArrayList<Integer>() {{
                add(0);
            }}
    );

    private final int id;
    private final String name;
    private final List<UUID> playerList = new ArrayList<>();
    private final Integer majorClans;
    private final List<Integer> enemyList = new ArrayList<>();
    private final List<Integer> allyList = new ArrayList<>();
    private List<Clans> minorClans = null;
    Clans(int id, String name, Integer majorClansID, List<Integer> enemyList, List<Integer> allyList) {
        this.id = id;
        this.name = name;
        this.majorClans = majorClansID;
        if (enemyList != null)
            this.enemyList.addAll(enemyList);
        if (allyList != null)
            this.allyList.addAll(allyList);
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

        GameManager gm = HigurashiUHC.getGameManager();
        return new ArrayList<HPlayer>() {{
            for (UUID uuid : getUUIDList())
                add(gm.getHPlayer(uuid));
        }};

    }

    public List<UUID> getUUIDList() {
        return playerList;
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
        this.playerList.add(p.getUuid());
        p.getInfoData().setDataInfo(InfoData.InfoList.CLAN.name(), getName());
    }

    public void removePlayer(HPlayer p) {
        this.playerList.remove(p.getUuid());
    }

    public boolean hisInClans(HPlayer player, boolean checkMinor) {
        if (playerList.contains(player.getUuid()))
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
        if (minorClans != null)
            return minorClans;
        minorClans = new ArrayList<>();
        for (Clans clans : Clans.values())
            if (isClans(clans.getMajorClans()) && !isClans(clans))
                minorClans.add(clans);
        return getMinorClans();
    }

    public boolean hasMinorClans(Clans clans) {
        return getMinorClans().contains(clans);
    }

    public boolean isMinorClans() {
        return !getMajorClans().isClans(this);
    }

    public void setEnemy(Clans clans, boolean both) {
        this.enemyList.add(clans.getId());
        if (both)
            clans.setEnemy(this, false);
    }

    public void setAlly(Clans clans, boolean both) {
        this.allyList.add(clans.getId());
        if (both)
            clans.setAlly(this, false);
    }

    public void removeEnemy(Clans clans, boolean both) {
        this.enemyList.remove(clans.getId());
        if (both)
            clans.removeEnemy(this, false);
    }

    public void removeAlly(Clans clans, boolean both) {
        this.allyList.remove(clans.getId());
        if (both)
            clans.removeAlly(this, false);
    }

    public boolean hisEnemy(Clans clans) {
        return enemyList.contains(clans.getId());
    }

    public boolean hisAlly(Clans clans) {
        return allyList.contains(clans.getId());
    }

}
