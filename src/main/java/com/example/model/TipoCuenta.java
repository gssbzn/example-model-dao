package com.example.model;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public enum TipoCuenta {
    ACREENCIA("acreencia");
    private final String value;

    private TipoCuenta(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static boolean isValid(String test){
        if(test == null) return true;
        for(TipoCuenta v : TipoCuenta.values()){
            if(v.getValue().equals(test)) return true;
        }
        return false;
    }
}
