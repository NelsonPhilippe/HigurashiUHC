package fr.xilitra.higurashiuhc.game;

import fr.xilitra.higurashiuhc.HigurashiUHC;
import fr.xilitra.higurashiuhc.api.RoleTemplate;
import fr.xilitra.higurashiuhc.event.higurashi.RoleSelected;
import fr.xilitra.higurashiuhc.game.clans.Mercenaire;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.Hinamizawa;
import fr.xilitra.higurashiuhc.game.clans.hinamizawa.MemberOfClub;
import fr.xilitra.higurashiuhc.game.task.GameTask;
import fr.xilitra.higurashiuhc.game.task.RikaDeathTask;
import fr.xilitra.higurashiuhc.game.task.StartTask;
import fr.xilitra.higurashiuhc.item.MatraqueItem;
import fr.xilitra.higurashiuhc.player.HPlayer;
import fr.xilitra.higurashiuhc.roles.Role;
import fr.xilitra.higurashiuhc.scenario.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import java.util.*;

public class GameManager {

    private GameStates states;
    private Map<UUID, HPlayer> spectator = new HashMap<>();
    private Map<UUID, HPlayer > players = new HashMap<>();
    private Scenario scenario;
    private int episode = 0;
    private Hinamizawa hinamizawa = new Hinamizawa("Hinamizawa");
    private Mercenaire mercenaire = new Mercenaire("Mercenaire");
    private Runnable rikaDeathTask = new RikaDeathTask();

    public void config(){
        setStates(GameStates.CONFIG);
    }

    public void start() {
        this.setStates(GameStates.START);

        List<Role> roles = new ArrayList();

        roles.addAll(Arrays.asList(Role.values()));


        for(HPlayer player : this.players.values()){

            player.getPlayer().getInventory().clear();

            int number = new Random().nextInt(roles.size()) - 1;

            Role role = roles.get(number);

            roles.remove(number);


            Object roletemplate = null;
            try {
                roletemplate = role.getRole().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


            player.setRole((RoleTemplate) roletemplate);
            players.replace(player.getUuid(), player);


            if(role.getRole().equals(Role.AKASAKA.getRole())) {
                player.getPlayer().getInventory().addItem(MatraqueItem.matraqueItem.getItemStack());
            }

            for(MemberOfClub.roleList rolelist : MemberOfClub.roleList.values()){

                if(rolelist.getRole() == role.getRole()){
                    hinamizawa.addPlayerToSubClans("Membre du club", player.getPlayer());
                    break;
                }
            }

            for(Mercenaire.roleList roleList : Mercenaire.roleList.values()){
                if(roleList.getRole() == role.getRole()){
                    mercenaire.addPlayer(player.getPlayer());
                }
            }

            if(role.getClass().equals(Role.MION_SONOZAKI.getRole()) || role.getClass().equals(Role.SHION_SONOSAKI.getRole())){
                player.getPlayer().setHealth(22);
            }


            Bukkit.getPluginManager().callEvent(new RoleSelected(player));
        }
        TimerTask task = new StartTask();
        Timer run = new Timer("Start");
        run.scheduleAtFixedRate(task, 1000, 1000);

        World world = Bukkit.getWorld("world");

        WorldBorder border = world.getWorldBorder();
        border.setSize(HigurashiUHC.getInstance().getConfig().getInt("worldborder"));
        border.setCenter(0.0, 0.0);

    }

    public void game(){
        TimerTask timerTask = new GameTask();
        Timer timer = new Timer("Game");
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
        this.setStates(GameStates.GAME);
    }

    public void startRikaDeathTask(){
        if(!((RikaDeathTask) rikaDeathTask).isStarted()){
            Bukkit.getScheduler().runTask(HigurashiUHC.getInstance(), rikaDeathTask);
        }
    }


    public GameStates getStates() {
        return states;
    }

    public Map<UUID, HPlayer> getSpectator(){
        return spectator;
    }

    public Scenario getSelectedScenario(){
        return scenario;
    }

    public boolean scenarioIsSelected(){
        return scenario != null;
    }

    public void setStates(GameStates states) {
        this.states = states;
    }

    public Map<UUID, HPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(HPlayer player){
        players.put(player.getUuid(), player);
    }

    public void removePlayer(HPlayer player){
        players.remove(player.getUuid());
    }

    public void removePlayer(UUID player){
        players.remove(player);
    }

    public void setScenario(Scenario scenario){
        this.scenario = scenario;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int ep){
        this.episode = ep;
    }

    public Hinamizawa getHinamizawa() {
        return hinamizawa;
    }

    public Mercenaire getMercenaire(){
        return mercenaire;
    }

    public HPlayer getPlayer(UUID uuid){
        return players.get(uuid);
    }

    public HPlayer getPlayerWithRole(Role role){
        try{
            for(HPlayer player : players.values()){

                if(player.getRole() != null) {


                    if (role.getRole().equals(player.getRole().getClass())) {
                        return player;
                    }
                }
            }
        }catch (NullPointerException e){
            return null;
        }

        return null;
    }
}
