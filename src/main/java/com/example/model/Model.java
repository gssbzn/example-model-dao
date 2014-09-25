package com.example.model;

import java.io.Serializable;

/**
 * 
 * @author Gustavo Bazan
 *
 * @param <K> Tipo del ID del modelo
 */
public interface Model<K> extends Serializable {
	
    public K getId();

    public void setId(K id);
    
}
