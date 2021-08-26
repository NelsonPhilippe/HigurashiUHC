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
}
