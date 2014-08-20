package com.example.dao;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class DAOFactory {
	
    public static ClienteDAO getClienteDAO() {
        return ClienteDAORestImpl.getInstance();
    }
	
    public static CuentaDAO getCuentaDAO() {
        return CuentaDAORestImpl.getInstance();
    }

    public static MovimientoDAO getMovimientoDAO() {
        return MovimientoDAORestImpl.getInstance();
    }

}
