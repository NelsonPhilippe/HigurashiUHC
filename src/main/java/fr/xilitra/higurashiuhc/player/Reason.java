package fr.xilitra.higurashiuhc.player;

public enum Reason {
    DOLL_TRAGEDY("DOLLTRAGEDY");
    final String name;
    Reason(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public boolean isReason(Reason... mrList){

        for(Reason mr : mrList)
            if(getName().equals(mr.getName()))
                return true;

            return false;

    }
}
