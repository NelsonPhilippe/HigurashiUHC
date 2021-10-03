package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.roles.RoleAction;
import fr.xilitra.higurashiuhc.utils.DeathReason;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class SatoshiHojoAction extends RoleAction {

    @Override
    public void onKill(HPlayer killer, HPlayer killed, DeathReason dr) {

        List<Role> interestRole = new ArrayList<Role>() {{
            add(Role.KEIICHI_MAEBARA);
            add(Role.RENA_RYUGU);
            add(Role.SHION_SONOSAKI);
            add(Role.SATOKO_HOJO);
            add(Role.MION_SONOZAKI);
        }};

        if (!killed.getRole().isRole(interestRole)) return;
        if (killed.hasMalediction()) return;

        Player player = killer.getPlayer();
        if (player == null) return;

        player.setMaxHealth(player.getMaxHealth() + 1);
        int kill = 0;
        for (Role role : interestRole) {

            HPlayer hPlayer = role.getHPlayer();
            if (hPlayer == null || hPlayer.getKiller() == null || !(hPlayer.getKiller() instanceof Player) || hPlayer.hasMalediction())
                continue;

            if (hPlayer.getKiller().getUniqueId().toString().equals(killer.getUuid().toString()))
                kill += 1;

        }

        if (kill >= 4)
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 1));

    }

    @Override
    public void onDeath(HPlayer killed, DeathReason dr) {

    }

    @Override
    public void playerLeave(Player p) {

    }

    @Override
    public boolean acceptReconnect(Player p) {
        return false;
    }

}
