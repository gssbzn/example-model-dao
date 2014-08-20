package com.example.factory;

import com.example.dao.ClienteDAO;
import com.example.dao.CuentaDAO;
import com.example.dao.MovimientoDAO;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public abstract class DAOFactory {
	public enum DAOTYPE {
		MEMORYFACTORY, RESTFULFACTORY;
	}
	
	private static MemoryDAOFactory memoryDAOFactory;
	
	private static RestfulDAOFactory restfulDAOFactory;
	
    public abstract ClienteDAO getClienteDAO();
	
    public abstract CuentaDAO getCuentaDAO();

    public abstract MovimientoDAO getMovimientoDAO();
    
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
