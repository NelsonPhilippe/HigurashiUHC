package fr.xilitra.higurashiuhc.api;

import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Clans;
import fr.xilitra.higurashiuhc.game.clans.ClansManager;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.RoleList;

public abstract class Role {

   private String name;
   private Gender sexe;
   private boolean malediction;
   private String displayName;
   private HPlayer player;
   private RoleList linkedToDeath = null;
   private RoleList mariedWith = null;
   private MariedReason mariedReason = null;


   public Role(String name, Gender sexe, Clans clans) {
      this.name = name;
      this.sexe = sexe;
      this.malediction = false;
      setClans(clans);
   }

   public void setLinkedToDeathWith(RoleList roleList){
      linkedToDeath = roleList;
   }

   public RoleList linkedToDeathWith(){
      return linkedToDeath;
   }

   public void setMarriedWith(RoleList roleList, MariedReason mr){
      mariedWith = roleList;
      this.mariedReason = mr;
   }

   public MariedReason getMariedReason(){
      return mariedReason;
   }

   public RoleList getMarriedWith(){
      return mariedWith;
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

   public boolean isRole(Role role){
      return getName().equals(role.getName());
   }

   public boolean isRole(Role... roles){
      for(Role role : roles)
         if(getName().equals(role.getName()))
            return true;
         return false;
   }

}
