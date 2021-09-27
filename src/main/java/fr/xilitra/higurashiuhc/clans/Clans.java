package fr.xilitra.higurashiuhc.clans;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.game.GameManager;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;

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
            null
    );

    public static Clans getClans(int id){
        for(Clans clans : values())
            if(id == clans.getId())
                return clans;
        return null;
    }

    public static Clans getClans(HPlayer hPlayer){
        for(Clans clans : values())
            if(clans.hisInClans(hPlayer))
                return clans;
        return null;
    }

    public static Clans getClans(String clansName){
        for(Clans clans : values())
            if(clans.getName().equals(clansName))
                return clans;
        return null;
    }

    private final int id;
    private final String name;
    private final List<UUID> playerList = new ArrayList<>();
    private final Integer majorClans;


    Clans(int id, String name, Integer majorClansID){
        this.id = id;
        this.name = name;
        this.majorClans = majorClansID;
    }


    public String getName() {
        return name;
    }

    public List<HPlayer> getPlayerList() {

        GameManager gm = HigurashiUHC.getGameManager();
        return new ArrayList<HPlayer>(){{
            for(UUID uuid : getPlayerListUUID())
                add(gm.getHPlayer(uuid));
        }};

    }

    public List<UUID> getPlayerListUUID() {
        return playerList;
    }

    public void addPlayer(HPlayer p){
        Clans prevClans = getClans(p);
        if(prevClans != null)
            prevClans.removePlayer(p);
        this.playerList.add(p.getUuid());
    }

    public void removePlayer(HPlayer p){
        this.playerList.remove(p.getUuid());
    }

    public boolean hisInClans(HPlayer player){
        return isClans(player.getClans());
    }

    public boolean isClans(Clans clans){
        return clans.getId() == getId();
    }

    public boolean isClans(Clans clans, boolean checkMajorClan){
        if(isClans(clans))
            return true;
        Clans majorClans = getMajorClans();
        if(checkMajorClan && majorClans != null)
            return getMajorClans().isClans(clans);
        return false;
    }

    public boolean isSubClans(){
        return getMajorClans() != null;
    }

    public Clans getMajorClans(){
        if(majorClans != null)
            Clans.getClans(majorClans);
        return null;
    }

    public int getId(){
        return id;
    }

    public List<Role> getRoleList(){

        return new ArrayList<Role>(){{
            for (HPlayer hPlayer : getPlayerList())
                add(hPlayer.getRole());
        }};

    }

}
