package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.clans.Clans;
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
import java.util.List;

public enum Role {

    RIKA_FURUDE("Rika Furude", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new RikaFurudeAction()),
    MION_SONOZAKI("Mion Sonozaki", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new MionSonozakiAction()),
    HANYU("Hanyu", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new HanyuAction()),
    SATOKO_HOJO("Satoko Hojo", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new SatokoHojoAction()),
    KEIICHI_MAEBARA("Keiichi Maebara", "null", Gender.HOMME, Clans.MEMBER_OF_CLUB, 1, new KeiichiMaebaraAction()),
    SHION_SONOSAKI("Shion Sonozaki", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new ShionSonozakiAction()),
    RENA_RYUGU("Rena Ryugu", "null", Gender.FEMME, Clans.MEMBER_OF_CLUB, 1, new RenaRyuguAction()),
    MIYO_TAKANO("Miyo Takano", "null", Gender.FEMME, Clans.MERCENAIRE, 1, new MiyoTakanoAction()),
    SATOSHI_HOJO("Satoshi Hojo", "null", Gender.HOMME, Clans.NEUTRE, 1, new SatoshiHojoAction()),
    ORYO_SONOZAKI("Oryo Sonozaki", "null", Gender.FEMME, Clans.SONOZAKI, 1, new OryoSonozakiAction()),
    KASAI("Kasai", "null", Gender.HOMME, Clans.SONOZAKI, 1, new KasaiAction()),
    AKANE_SONOZAKI("Akane Sonozaki", "null", Gender.FEMME, Clans.SONOZAKI, 1, new AkaneSonozakiAction()),
    KIICHIRO_KIMIYOSHI("Kiichiro Kimiyoshi", "null", Gender.HOMME, Clans.SONOZAKI, 1, new KiichiroKimiyoshiAction()),
    VILLAGEOIS("Villageois", "null", Gender.NON_GENRE, Clans.HINAMIZAWA, 1, new VillageoisAction()),
    TEPPEI_HOJO("Teppei Hojo", "null", Gender.HOMME, Clans.NEUTRE, 1, new TeppeiHojoAction()),
    NOMURA("Nomura", "null", Gender.FEMME, Clans.NEUTRE, 1, new NomuraAction()),
    KYOSUKE_IRIE("Kyosuke Irie", "null", Gender.HOMME, Clans.NEUTRE, 1, new KyosukeIrieAction()),
    KURAUDO_OISHI("Kuraudo Oishi", "null", Gender.HOMME, Clans.POLICE, 1, new KuraudoOishiAction()),
    KUMAGAI("Kumagai", "null", Gender.HOMME, Clans.POLICE, 1, new KumagaiAction()),
    AKASAKA("Akasaka", "null", Gender.HOMME, Clans.POLICE, 1, new AkasakaAction()),
    POLICIER("Policier", "null", Gender.NON_GENRE, Clans.POLICE, 10, new PolicierRoleAction()),
    MERCENAIRE("Mercenaire", "null", Gender.NON_GENRE, Clans.MERCENAIRE, 1000, new MercenaireAction()),
    OKONOGI("Okonogi", "null", Gender.HOMME, Clans.MERCENAIRE, 1, new OkonogiAction()),
    JIRO_TOMITAKE("Jiro Tomitake", "null", Gender.HOMME, Clans.NEUTRE, 1, new JiroTomitakeAction()),
    NULL("Je suis null", "Rôle de merde", Gender.HOMME, null, 9999, new NullRoleAction());

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


    Role(String name, String description, Gender sexe, Clans clans, int maxPlayers, RoleAction roleAction) {
        this.name = name;
        this.description = description;
        this.sexe = sexe;
        this.displayName = name;
        this.defaultClans = clans;
        this.maxPlayers = maxPlayers;
        this.roleAction = roleAction;
        this.roleAction.setLinkedRole(this);
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
}
