package org.blitar.bawasda.simhp.model.modelenum;

public enum EnumUserOtorize {
    ADMINISTRATOR(0, "ADMINISTRATOR", "ADMINISTRATOR SISTEM"),
    USER1(1, "USER1", "Pengguna Utama yang mempunyai hak akses hampir penuh"),
    USER2(2, "USER2", "Pengguna tingkat menengah"),
    USER3(3, "USER3", "Pengguna paling bawah"),
    MANAGER1(4, "MANAGER1", "Manager Tingkat Pertama/UTAMA (Manager Paling Tinggi)"),
    MANAGER2(5, "MANAGER2", "Manager Tingkat Kedua (dibawah Manager Utama)"),
    GUEST(7, "GUEST", "Guest");
    
    private int intCode;
    private String stringCode;
    private String description;
    
    private EnumUserOtorize(int intCode, String strCode, String description){
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
