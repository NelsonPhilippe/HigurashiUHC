package fr.xilitra.higurashiuhc.api;

import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Clans;

public abstract class RoleTemplate<T extends Clans> {

   private String name;
   private Gender sexe;
   protected T clan;
   private boolean malediction;


   public RoleTemplate(String name, Gender sexe) {
      this.name = name;
      this.sexe = sexe;
      this.malediction = false;
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

   public boolean isMalediction() {
      return malediction;
   }

   public void setMalediction(boolean malediction) {
      this.malediction = malediction;
   }
}
