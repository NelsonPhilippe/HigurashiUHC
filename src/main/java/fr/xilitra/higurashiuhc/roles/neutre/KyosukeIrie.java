package fr.xilitra.higurashiuhc.roles.neutre;

import fr.xilitra.higurashiuhc.api.Role;
import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Neutre;

public class KyosukeIrie extends Role {
    public KyosukeIrie() {
        super("Kyosuke Irie", Gender.HOMME, Neutre.getClans());
    }
}
