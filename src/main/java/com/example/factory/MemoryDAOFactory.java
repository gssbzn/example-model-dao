package com.example.factory;

import com.example.dao.ClienteDAO;
import com.example.dao.CuentaDAO;
import com.example.dao.MovimientoDAO;
import com.example.dao.memory.ClienteDAOMemoryImpl;
import com.example.dao.memory.CuentaDAOMemoryImpl;
import com.example.dao.memory.MovimientoDAOMemoryImpl;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class MemoryDAOFactory extends DAOFactory {
	
	@Override
    public ClienteDAO getClienteDAO() {
        return ClienteDAOMemoryImpl.getInstance();
    }
	
	@Override
    public CuentaDAO getCuentaDAO() {
        return CuentaDAOMemoryImpl.getInstance();
    }

	@Override
    public MovimientoDAO getMovimientoDAO() {
        return MovimientoDAOMemoryImpl.getInstance();
    }

}
