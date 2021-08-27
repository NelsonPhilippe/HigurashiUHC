package fr.xilitra.higurashiuhc.api;

public enum MariedReason {
    DOLL_TRAGEDY("DOLLTRAGEDY");
    final String name;
    MariedReason(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public boolean isMariedReason(MariedReason... mrList){

        for(MariedReason mr : mrList)
            if(getName().equals(mr.getName()))
                return true;

            return false;

    }
}
