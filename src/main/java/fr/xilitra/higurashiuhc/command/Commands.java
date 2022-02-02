package fr.xilitra.higurashiuhc.command;

import fr.xilitra.higurashiuhc.command.executor.*;

import java.util.ArrayList;
import java.util.List;

public enum Commands {

    RESSUCITE("r", new RessuciteCmd(), false),
    VOTE("vote", new VoteCmd(), false),
    BAN("ban", new BanCmd(), false),
    INVERSER("inverser", new InverserCmd(), true),
    PENSE("pense", new PenseCmd(), true),
    DIMENSION("dimension", new DimensionCmd(), false),
    DIMENSION_DEATH("dimension_death", new DimensionDeathCmd(), false),
    FORCE("force", new ForceCmd(), true),
    HEAL("heal", new HealCmd(), true),
    LIST("list", new ListCmd(), false),
    ASSASSINER("assassiner", new AssassinCmd(), false),
    PVCMD("pv", new PvCmd(), false),
    SUSPECTER("suspecter", new SuspecterCmd(), false),
    COMPARER("comparer", new ComparerCmd(), false),
    RIKA("rika", new RikaCmd(), false),
    PARANO("paranoia", new ParanoCmd(), false),
    CLANS("clans", new ClansCmd(), false),
    EFFET_LISTENER("effet", new EffectCMD(), false),
    EFFET_CLEAR("effetclear", new EffectClearCMD(), false),
    COUPABLE("c", new CoupableCmd(), false);

    final String initials;
    final CommandsExecutor commandsExecutor;
    final boolean canStole;

    Commands(String initials, CommandsExecutor ce, boolean canStole) {
        this.initials = initials;
        this.commandsExecutor = ce;
        this.canStole = canStole;
    }

    public static Commands getCommands(String initials) {
        for (Commands commands : values())
            if (initials.equals(commands.initials))
                return commands;
        return null;
    }

    public static List<Commands> getStoleCommande() {
        return new ArrayList<Commands>() {{
            for (Commands commands : values())
                if (commands.canStole())
                    add(commands);
        }};
    }

    public String getInitials() {
        return initials;
    }

    public CommandsExecutor getCommandExecutor() {
        return commandsExecutor;
    }

    public boolean canStole() {
        return canStole;
    }

}
