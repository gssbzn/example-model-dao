package com.example.model;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public enum TipoMovimiento {
    DEPOSITO("+"),
    RETIRO("-");
    private final String value;

    private TipoMovimiento(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static boolean isValid(String test){
        if(test == null) return true;
        for(TipoMovimiento v : TipoMovimiento.values()){
            if(v.getValue().equals(test)) return true;
        }
        return false;
    }
}
