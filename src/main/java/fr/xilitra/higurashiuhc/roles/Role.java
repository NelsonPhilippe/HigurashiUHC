package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.hinamizawa.VillageoisAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.memberofclub.*;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.AkaneSonozakiAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.KasaiAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.KiichiroKimiyoshiAction;
import fr.xilitra.higurashiuhc.roles.hinamizawa.sonozaki.OryoSonozakiAction;
import fr.xilitra.higurashiuhc.roles.mercenaires.MercenaireAction;
import fr.xilitra.higurashiuhc.roles.mercenaires.MiyoTakanoAction;
import fr.xilitra.higurashiuhc.roles.mercenaires.OkonogiAction;
import fr.xilitra.higurashiuhc.roles.neutre.*;
import fr.xilitra.higurashiuhc.roles.police.AkasakaAction;
import fr.xilitra.higurashiuhc.roles.police.KumagaiAction;
import fr.xilitra.higurashiuhc.roles.police.KuraudoOishiAction;
import fr.xilitra.higurashiuhc.roles.police.PolicierRoleAction;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum Role {

    RIKA_FURUDE("Rika Furude", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new RikaFurudeAction(),
            new HashMap<Commands, Integer>(){{
                put(Commands.RESSUCITE, 1);
            }}
            ),

    MION_SONOZAKI("Mion Sonozaki", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new MionSonozakiAction(), null),

    HANYU("Hanyu", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new HanyuAction(),
            new HashMap<Commands, Integer>(){{
                put(Commands.RIKATP, 1);
                put(Commands.DIMENSION, 1);
            }}),

    SATOKO_HOJO("Satoko Hojo", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new SatokoHojoAction(), null),

    KEIICHI_MAEBARA("Keiichi Maebara", "null", Gender.HOMME, Clans.MEMBER_OF_CLUB, 1, new KeiichiMaebaraAction(), null),

    SHION_SONOSAKI("Shion Sonozaki", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new ShionSonozakiAction(), null),

    RENA_RYUGU("Rena Ryugu", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new RenaRyuguAction(),
            new HashMap<Commands, Integer>(){{
                put(Commands.PENSE, 1);
            }}),

    MIYO_TAKANO("Miyo Takano", "null", Gender.FEMME, Clans.MERCENAIRE, 1, new MiyoTakanoAction(), new HashMap<Commands, Integer>(){{
        put(Commands.ASSASSINER, 2);
    }}),

    SATOSHI_HOJO("Satoshi Hojo", "null", Gender.HOMME, Clans.NEUTRE, 1, new SatoshiHojoAction(), null),

    ORYO_SONOZAKI("Oryo Sonozaki", "null", Gender.FEMME, Clans.SONOZAKI, 1, new OryoSonozakiAction(),
            new HashMap<Commands, Integer>(){{
                put(Commands.BAN, 1);
            }}),

    KASAI("Kasai", "null", Gender.HOMME, Clans.SONOZAKI, 1, new KasaiAction(), new HashMap<Commands, Integer>(){{
        put(Commands.FORCE, 1);
    }}),

    AKANE_SONOZAKI("Akane Sonozaki", "null", Gender.FEMME, Clans.SONOZAKI, 1, new AkaneSonozakiAction(),
            new HashMap<Commands, Integer>(){{
                put(Commands.INVERSER, 2);
            }}),

    KIICHIRO_KIMIYOSHI("Kiichiro Kimiyoshi", "null", Gender.HOMME, Clans.SONOZAKI, 1, new KiichiroKimiyoshiAction(),
            new HashMap<Commands, Integer>(){{
                put(Commands.HEAL, -1);
            }}),

    VILLAGEOIS("Villageois", "null", Gender.NON_GENRE, Clans.HINAMIZAWA, 1, new VillageoisAction(), null),

    TEPPEI_HOJO("Teppei Hojo", "null", Gender.HOMME, Clans.NEUTRE, 1, new TeppeiHojoAction(), null),

    NOMURA("Nomura", "null", Gender.FEMME, Clans.NEUTRE, 1, new NomuraAction(), null),

    KYOSUKE_IRIE("Kyosuke Irie", "null", Gender.HOMME, Clans.NEUTRE, 1, new KyosukeIrieAction(), null),

    KURAUDO_OISHI("Kuraudo Oishi", "null", Gender.HOMME, Clans.POLICE, 1, new KuraudoOishiAction(),
            new HashMap<Commands, Integer>(){{
              put(Commands.COUPABLE, 1);
              put(Commands.SUSPECTER, 1);
            }}),

    KUMAGAI("Kumagai", "null", Gender.HOMME, Clans.POLICE, 1, new KumagaiAction(),
            new HashMap<Commands, Integer>(){{
                put(Commands.COMPARER, 1);
            }}),

    AKASAKA("Akasaka", "null", Gender.HOMME, Clans.POLICE, 1, new AkasakaAction(),
            new HashMap<Commands, Integer>(){{
                put(Commands.RIKA, 3);
            }}),

    POLICIER("Policier", "null", Gender.NON_GENRE, Clans.POLICE, 10, new PolicierRoleAction(),
            new HashMap<Commands, Integer>(){{
                put(Commands.PVCMD, 1);
            }}),

    MERCENAIRE("Mercenaire", "null", Gender.NON_GENRE, Clans.MERCENAIRE, 1000, new MercenaireAction(), null),

    OKONOGI("Okonogi", "null", Gender.HOMME, Clans.MERCENAIRE, 1, new OkonogiAction(), new HashMap<Commands, Integer>(){{
        put(Commands.LIST, -1);
    }}),

    JIRO_TOMITAKE("Jiro Tomitake", "null", Gender.HOMME, Clans.NEUTRE, 1, new JiroTomitakeAction(), null),

    NULL("Je suis null", "Rôle de merde", Gender.HOMME, null, 9999, new NullRoleAction(), null);

    public static Role getRole(String role){
        for(Role roleList : values())
            if(roleList.getName().equals(role))
                return roleList;
        return null;
    }

    private final String name;
    private final String description;
    private final Gender sexe;
    private String displayName;
    private final List<HPlayer> players = new ArrayList<>();
    private final int maxPlayers;
    private final Clans defaultClans;
    private final RoleAction roleAction;
    private final HashMap<Commands, Integer> commandsIntegerHashMap;


    Role(String name, String description, Gender sexe, Clans clans, int maxPlayers, RoleAction roleAction, HashMap<Commands, Integer> authoriseCommand) {
        this.name = name;
        this.description = description;
        this.sexe = sexe;
        this.displayName = name;
        this.defaultClans = clans;
        this.maxPlayers = maxPlayers;
        this.roleAction = roleAction;
        this.roleAction.setLinkedRole(this);
        if(authoriseCommand == null)
            authoriseCommand = new HashMap<>();
        this.commandsIntegerHashMap = authoriseCommand;
        this.commandsIntegerHashMap.put(Commands.VOTE, 1);
        if(roleAction instanceof Listener)
            Bukkit.getPluginManager().registerEvents((Listener) roleAction, HigurashiUHC.getInstance());
    }

    public String getName() {
        return name;
    }

    public String getDecription(){
        return description;
    }

    public Gender getSexe() {
        return sexe;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Nullable
    public HPlayer getHPlayer() {
        return players.get(0);
    }

    public boolean hasHPlayer(HPlayer player){
        return players.contains(player);
    }

    public List<HPlayer> getHPlayerList(){
        return players;
    }

    public boolean addPlayer(HPlayer player){
        if(players.size()<maxPlayers) {
            players.add(player);
            return true;
        }
        return false;
    }

    public boolean removePlayer(HPlayer player){
        return players.remove(player);
    }

    public Clans getDefaultClans(){
        return defaultClans;
    }

    public boolean isRole(Role role){
        return getName().equals(role.getName());
    }

    public boolean isRole(Role... roles){
        for(Role role : roles)
            if(isRole(role))
                return true;
        return false;
    }

    public boolean isRole(List<Role> roles){
        for(Role role : roles)
            if(isRole(role))
                return true;
        return false;
    }

    public boolean isAssigned(){
        return !getHPlayerList().isEmpty();
    }

    public RoleAction getRoleAction() {
        return roleAction;
    }

    public void removeCommandAccess(Commands commands){
        commandsIntegerHashMap.remove(commands);
    }

    public void useCommand(Commands commands){
        int num = getCommandAccess(commands);
        if(num>0)
            commandsIntegerHashMap.replace(commands, num-1);
    }

    public boolean hasCommandAccess(Commands commands){
        return getCommandAccess(commands) != 0;
    }

    public int getCommandAccess(Commands commands){
        Integer value = commandsIntegerHashMap.get(commands);
        if(value == null)
            return 0;
        return value;
    }

    public void setCommandAccess(Commands commands, Integer number){

        removeCommandAccess(commands);
        commandsIntegerHashMap.put(commands, number);

    }

    public void addCommandAccess(Commands commands){

        setCommandAccess(commands, getCommandAccess(commands)+1);

    }

}
