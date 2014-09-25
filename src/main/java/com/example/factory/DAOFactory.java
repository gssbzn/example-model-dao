package com.example.factory;

import com.example.dao.ArticleDAO;
import com.example.dao.ClientDAO;
import com.example.dao.ClienteDAO;
import com.example.dao.CuentaDAO;
import com.example.dao.MovimientoDAO;
import com.example.dao.TransactionDAO;
import com.example.dao.UserDAO;

/**
 * Factory de DAOs
 * 
 * @author Gustavo Bazan
 *
 */
public abstract class DAOFactory {
	/** Tipos de implementacion en DAOs */
	public enum DAOTYPE {
		MEMORYFACTORY, RESTFULFACTORY;
	}
	
	/** Implementacion en Memoria */
	private static MemoryDAOFactory memoryDAOFactory;
	/** Implementacion con servicio Web */
	private static RestfulDAOFactory restfulDAOFactory;
	
	/**
	 * 
	 * @return
	 */
    public abstract ClienteDAO getClienteDAO();
	
    /**
     * 
     * @return
     */
    public abstract CuentaDAO getCuentaDAO();

    /**
     * 
     * @return
     */
    public abstract MovimientoDAO getMovimientoDAO();
    
    /**
     * 
     * @return
     */
    public abstract ClientDAO getClientDAO();
    
    /**
     * 
     * @return
     */
    public abstract ArticleDAO getArticleDAO();
    
    /**
     * 
     * @return
     */
    public abstract TransactionDAO getTransactionDAO();
    
    /**
     * 
     * @return
     */
    public abstract UserDAO getUserDAO();
    
    /**
     * Obtener los daos deseados segun el tipo
     * 
     * @param daoType tipo de implementacion a usar
     * @return
     */
    public static DAOFactory getDAOFactory(DAOTYPE daoType) {
    	switch (daoType) {
		case MEMORYFACTORY:
			memoryDAOFactory = new MemoryDAOFactory();
			return memoryDAOFactory;
		case RESTFULFACTORY:
    		//"http://localhost:8080/acreencias/";//"http://acreencias.herokuapp.com/";
			restfulDAOFactory = new RestfulDAOFactory("http://acreencias.herokuapp.com/");
			return restfulDAOFactory;
		default: return null;
			
		}
    }

}
