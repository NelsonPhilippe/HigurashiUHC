package fr.xilitra.higurashiuhc.api;

import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Clans;

public abstract class RoleTemplate<T extends Clans> {

   private String name;
   private Gender sexe;
   protected T clan;


   public RoleTemplate(String name, Gender sexe) {
      this.name = name;
      this.sexe = sexe;
   }

   public String getName() {
      return name;
   }

   public Gender getSexe() {
      return sexe;
   }

   public T getClan() {
      return clan;
   }
}
