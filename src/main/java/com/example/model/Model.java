package com.example.model;

/**
 * 
 * @author Gustavo Bazan
 *
 * @param <K> Tipo del ID del modelo
 */
public interface Model<K> {
	
    public K getId();

    public void setId(K id);
	
}
