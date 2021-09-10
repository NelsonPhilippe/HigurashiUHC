package fr.xilitra.higurashiuhc.game;

public enum PlayerState {

    SPECTATE("SPECTATE"),
    WAITING_DEATH("WAIT DEATH"),
    INGAME("INGAME"),
    DISCONNECTED("DISCONNECT"),
    WAITING_ROLE("WAIT_ROLE");

    final String name;

    PlayerState(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean isState(PlayerState... playerState){

        for(PlayerState newPlayer : playerState)
            if(newPlayer.getName().equals(getName()))
                return true;

            return false;

    }

}
