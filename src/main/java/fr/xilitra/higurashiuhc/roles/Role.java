package fr.xilitra.higurashiuhc.roles;

import fr.xilitra.higurashiuhc.game.Gender;
import fr.xilitra.higurashiuhc.game.clans.Clans;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.utils.DeathReason;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class Role {

   private final String name;
   private final Gender sexe;
   private String displayName;
   private final List<HPlayer> players = new ArrayList<>();
   private final int maxPlayers;
   private final Clans defaultClans;


   public Role(String name, Gender sexe, Clans clans, int maxPlayers) {
      this.name = name;
      this.sexe = sexe;
      this.displayName = name;
      this.defaultClans = clans;
      this.maxPlayers = maxPlayers;
   }

   public String getName() {
      return name;
   }

   public abstract String getDecription();

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
   public HPlayer getPlayer() {
      return players.get(0);
   }

   public boolean hasPlayer(HPlayer player){
      return players.contains(player);
   }

   public List<HPlayer> getPlayerList(){
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

   public abstract void onKill(HPlayer killer, HPlayer killed, DeathReason deathReason);

   public abstract void onDeath(HPlayer killed, DeathReason deathReason);

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

}
