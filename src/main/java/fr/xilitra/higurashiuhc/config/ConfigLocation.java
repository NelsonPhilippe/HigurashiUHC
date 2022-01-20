package fr.xilitra.higurashiuhc.config;

import org.bukkit.Sound;

public enum ConfigLocation {

    /// Not used
    ROLE_SATOKO_BLOCK("rl_sato_bl", 50),

    ROLE_RIKA_HANYU_SECONDS("rl_rika_hanyu", 120),

    ROLE_TOMITAKE_KROLE_EPISODE("rl_tomi_kr", 2),
    ROLE_ORYO_VOTE_SECONDS("rl_oryo_vote", 600),
    ROLE_ASAKA_STUNT_SECONDS("rl_asaka_stunt", 3),
    ROLE_ASAKA_MATRAQUE_SECONDS("rl_asaka_matraque", 300),
    ROLE_RIKA_WEAKNESS("rl_rika_weak", 2),
    ROLE_RENA_MALDAY_EPISODE("rl_rena_md", 2),

    /// Not used
    ROLE_ADVERT("rl_advert", false),

    ROLE_DISPLAY_ONDEATH("rl_death_display", true),

    EPISODE_WATANAGASHI("ep_wata", 6),

    EPISODE_TRAGEDY_OYASHIRO("ep_tra_oya", 3),

    /// Not used
    TIME_MALEDICTION_SECONDS("mal_time", 600),

    SCENARIO_DOLL("scen_doll", false),
    SCENARIO_MISTREATMENT("scen_mst", false),
    SCENARIO_OYASHIRO("scen_oya", false),

    SOUND_ONDEATH("sound_ds", Sound.WITHER_SPAWN.name()),
    SOUND_ONSTART("sound_ss", Sound.GHAST_MOAN.name());

    final String name;
    final Object base;
    ConfigLocation(String name, Object base){
        this.name = name;
        this.base = base;
    }

    public String getName(){
        return name;
    }

    public Object getDefaultValue(){
        return base;
    }

}
