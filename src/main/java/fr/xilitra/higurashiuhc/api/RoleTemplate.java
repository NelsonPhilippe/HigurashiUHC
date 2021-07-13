package fr.xilitra.higurashiuhc.api;

import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.player.HPlayer;

public abstract class RoleTemplate {

   private String name;
   private Gender sexe;


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

   public abstract void rollEffect(HPlayer player);
}
