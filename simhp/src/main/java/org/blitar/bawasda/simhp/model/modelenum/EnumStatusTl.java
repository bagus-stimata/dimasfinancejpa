package org.blitar.bawasda.simhp.model.modelenum;

public enum EnumStatusTl {
    BELUM(0, "B", "Belum ditindak lanjuti. Syarat: RTL belum TL semua"),
    PROSES(1, "P", "Proses lindak lanjut. Syarat: RTL Sudah ada TL tapi masih belum selesai semua"),
    SELESAI(2, "S", "Selesai ditindak lanjuti. Syarat: RTL sudah TL semua"),
    TD(3, "TD", "Tidak dapat ditindak lanjuti"),
    OTHER(7, "OTHER", "Other Reserved");
    
    private int intCode;
    private String stringCode;
    private String description;
    
    private EnumStatusTl(int intCode, String strCode, String description){
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
