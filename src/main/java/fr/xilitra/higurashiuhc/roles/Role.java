package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
import fr.xilitra.higurashiuhc.command.Commands;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum Role {

    RIKA_FURUDE("Rika Furude", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new RikaFurudeAction(),
            new HashMap<Commands, Integer>() {{
                put(Commands.RESSUCITE, 1);
            }}
    ),

    MION_SONOZAKI("Mion Sonozaki", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new MionSonozakiAction(), null),

    HANYU("Hanyu", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new HanyuAction(),
            new HashMap<Commands, Integer>() {{
                put(Commands.RIKATP, 1);
                put(Commands.DIMENSION, 1);
            }}),

    SATOKO_HOJO("Satoko Hojo", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new SatokoHojoAction(), null),

    KEIICHI_MAEBARA("Keiichi Maebara", Gender.HOMME, Clans.MEMBER_OF_CLUB, 1, new KeiichiMaebaraAction(), null),

    SHION_SONOSAKI("Shion Sonozaki", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new ShionSonozakiAction(), null),

    RENA_RYUGU("Rena Ryugu", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new RenaRyuguAction(),
            new HashMap<Commands, Integer>() {{
                put(Commands.PENSE, 1);
            }}),

    MIYO_TAKANO("Miyo Takano", Gender.FEMME, Clans.MERCENAIRE, 1, new MiyoTakanoAction(), new HashMap<Commands, Integer>() {{
        put(Commands.ASSASSINER, 2);
    }}),

    SATOSHI_HOJO("Satoshi Hojo", Gender.HOMME, Clans.NEUTRE, 1, new SatoshiHojoAction(), null),

    ORYO_SONOZAKI("Oryo Sonozaki", Gender.FEMME, Clans.SONOZAKI, 1, new OryoSonozakiAction(),
            new HashMap<Commands, Integer>() {{
                put(Commands.BAN, 1);
            }}),

    KASAI("Kasai", Gender.HOMME, Clans.SONOZAKI, 1, new KasaiAction(), new HashMap<Commands, Integer>() {{
        put(Commands.FORCE, 1);
    }}),

    AKANE_SONOZAKI("Akane Sonozaki", Gender.FEMME, Clans.SONOZAKI, 1, new AkaneSonozakiAction(),
            new HashMap<Commands, Integer>() {{
                put(Commands.INVERSER, 2);
            }}),

    KIICHIRO_KIMIYOSHI("Kiichiro Kimiyoshi", Gender.HOMME, Clans.SONOZAKI, 1, new KiichiroKimiyoshiAction(),
            new HashMap<Commands, Integer>() {{
                put(Commands.HEAL, -1);
            }}),

    VILLAGEOIS("Villageois", Gender.NON_GENRE, Clans.HINAMIZAWA, 1, new VillageoisAction(), null),

    TEPPEI_HOJO("Teppei Hojo", Gender.HOMME, Clans.NEUTRE, 1, new TeppeiHojoAction(), null),

    NOMURA("Nomura", Gender.FEMME, Clans.NEUTRE, 1, new NomuraAction(), null),

    KYOSUKE_IRIE("Kyosuke Irie", Gender.HOMME, Clans.NEUTRE, 1, new KyosukeIrieAction(), new HashMap<Commands, Integer>() {{
        put(Commands.CLANS, 1);
        put(Commands.EFFECT_LISTENER, 2);
        put(Commands.EFFECT_CLEAR, 1);
    }}),

    KURAUDO_OISHI("Kuraudo Oishi", Gender.HOMME, Clans.POLICE, 1, new KuraudoOishiAction(),
            new HashMap<Commands, Integer>() {{
                put(Commands.COUPABLE, 1);
                put(Commands.SUSPECTER, 1);
            }}),

    KUMAGAI("Kumagai", Gender.HOMME, Clans.POLICE, 1, new KumagaiAction(),
            new HashMap<Commands, Integer>() {{
                put(Commands.COMPARER, 1);
            }}),

    AKASAKA("Akasaka", Gender.HOMME, Clans.POLICE, 1, new AkasakaAction(),
            new HashMap<Commands, Integer>() {{
                put(Commands.RIKA, 3);
            }}),

    POLICIER("Policier", Gender.NON_GENRE, Clans.POLICE, 10, new PolicierRoleAction(),
            new HashMap<Commands, Integer>() {{
                put(Commands.PVCMD, 1);
            }}),

    MERCENAIRE("Mercenaire", Gender.NON_GENRE, Clans.MERCENAIRE, 1000, new MercenaireAction(), null),

    OKONOGI("Okonogi", Gender.HOMME, Clans.MERCENAIRE, 1, new OkonogiAction(), new HashMap<Commands, Integer>() {{
        put(Commands.LIST, -1);
    }}),

    JIRO_TOMITAKE("Jiro Tomitake", Gender.HOMME, Clans.NEUTRE, 1, new JiroTomitakeAction(), null);

    private final String name;
    private final Gender sexe;
    private final List<HPlayer> players = new ArrayList<>();
    private final int maxPlayers;
    private final Clans defaultClans;
    private final RoleAction roleAction;
    private final HashMap<Commands, Integer> defaultCommand;
    private String displayName;

    Role(String name, Gender sexe, Clans clans, int maxPlayers, RoleAction roleAction, HashMap<Commands, Integer> authoriseCommand) {
        this.name = name;
        this.sexe = sexe;
        this.displayName = name;
        this.defaultClans = clans;
        this.maxPlayers = maxPlayers;
        this.roleAction = roleAction;
        if (authoriseCommand == null)
            authoriseCommand = new HashMap<>();
        this.defaultCommand = authoriseCommand;
        if(clans.getMajorClans().isClans(Clans.HINAMIZAWA))
            this.defaultCommand.put(Commands.VOTE, 1);
        if (roleAction instanceof Listener)
            Bukkit.getPluginManager().registerEvents((Listener) roleAction, HigurashiUHC.getInstance());
    }

    public static Role getRole(String role) {
        for (Role roleList : values())
            if (roleList.getName().equals(role))
                return roleList;
        return null;
    }

    public String getName() {
        return name;
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

    public HPlayer getHPlayer() {
        return players.get(0);
    }

    public boolean hasHPlayer(HPlayer player) {
        return players.contains(player);
    }

    public List<HPlayer> getHPlayerList() {
        return players;
    }

    public boolean addPlayer(HPlayer player) {
        if (players.size() < maxPlayers) {
            HigurashiUHC.getGameManager().log("INFO) Player joined role: "+player.getName());
            players.add(player);
            roleAction.onJoinRole(player);
            Bukkit.getPluginManager().callEvent(new RoleSelected(player));
            return true;
        }
        HigurashiUHC.getGameManager().log("MCE) Player joined role: "+player.getName()+" failed");
        return false;
    }

    public boolean removePlayer(HPlayer player) {
        if (players.remove(player)) {
            HigurashiUHC.getGameManager().log("INFO) Player leaved role: "+player.getName());
            roleAction.onLeaveRole(player);
            return true;
        }
        return false;
    }

    public Clans getDefaultClans() {
        return defaultClans;
    }

    public boolean isRole(Role role) {
        return getName().equals(role.getName());
    }

    public boolean isRole(Role... roles) {
        for (Role role : roles)
            if (isRole(role))
                return true;
        return false;
    }

    public boolean isRole(List<Role> roles) {
        for (Role role : roles)
            if (isRole(role))
                return true;
        return false;
    }

    public boolean isAssigned() {
        return !getHPlayerList().isEmpty();
    }

    public RoleAction getRoleAction() {
        return roleAction;
    }

    public HashMap<Commands, Integer> getDefaultCommands() {
        return new HashMap<>(defaultCommand);
    }
}
