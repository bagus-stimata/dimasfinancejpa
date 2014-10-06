package org.blitar.bawasda.simhp.model.modelenum;

public enum EnumHelpOverlayTipe {
    SHORTCUT(1, "SHORTCUT", "Shortcut key"),
    HELP1(2, "HELP1", "Help 1"),
    HELP2(3, "HELP2", "Help 2");
    
    private int intCode;
    private String stringCode;
    private String description;
    
    private EnumHelpOverlayTipe(int intCode, String strCode, String description){
        this.intCode = intCode;
        this.stringCode = strCode;
        this.description = description;    
    }
    public String getStrCode(){
        return stringCode;
    }
    public int getIntCode(){
        return intCode;
    }
    public String getDescription(){
        return description;
    }

}
