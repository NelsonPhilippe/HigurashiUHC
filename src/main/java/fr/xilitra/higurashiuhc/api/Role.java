package fr.xilitra.higurashiuhc.api;

import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Clans;
import fr.xilitra.higurashiuhc.game.clans.ClansManager;
import fr.xilitra.higurashiuhc.player.HPlayer;

public abstract class Role {

   private String name;
   private Gender sexe;
   private boolean malediction;
   private String displayName;
   private HPlayer player;


   public Role(String name, Gender sexe, Clans clans) {
      this.name = name;
      this.sexe = sexe;
      this.malediction = false;
      setClans(clans);
   }

   public String getName() {
      return name;
   }

   public Gender getSexe() {
      return sexe;
   }

   public void setClans(Clans clans){
      ClansManager.getInstance().setClans(this, clans);
   }

   public Clans getClans(){
      return ClansManager.getInstance().getClans(this);
   }

   public boolean isMalediction() {
      return malediction;
   }

   public void setMalediction(boolean malediction) {
      this.malediction = malediction;
   }

   public String getDisplayName() {
      return displayName;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public HPlayer getPlayer() {
      return player;
   }

   public void setPlayer(HPlayer player){
      this.player = player;
   }

}
