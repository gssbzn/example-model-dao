package com.example.factory;

import com.example.dao.ClienteDAO;
import com.example.dao.CuentaDAO;
import com.example.dao.MovimientoDAO;
import com.example.dao.rest.ClienteDAORestImpl;
import com.example.dao.rest.CuentaDAORestImpl;
import com.example.dao.rest.MovimientoDAORestImpl;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class RestfulDAOFactory extends DAOFactory {
	private static String SERVER;
	    
    public RestfulDAOFactory(String server){
    	SERVER = server;
    }
    
	@Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAORestImpl(SERVER);
    }
	
	@Override
    public CuentaDAO getCuentaDAO() {
        return new CuentaDAORestImpl(SERVER);
    }

	@Override
    public MovimientoDAO getMovimientoDAO() {
        return new MovimientoDAORestImpl(SERVER);
    }

}
