package com.example.factory;

import com.example.dao.ArticleDAO;
import com.example.dao.ClientDAO;
import com.example.dao.ClienteDAO;
import com.example.dao.CuentaDAO;
import com.example.dao.MovimientoDAO;
import com.example.dao.TransactionDAO;
import com.example.dao.UserDAO;
import com.example.dao.memory.ArticleDAOMemoryImpl;
import com.example.dao.memory.ClientDAOMemoryImpl;
import com.example.dao.memory.ClienteDAOMemoryImpl;
import com.example.dao.memory.CuentaDAOMemoryImpl;
import com.example.dao.memory.MovimientoDAOMemoryImpl;
import com.example.dao.memory.TransactionDAOMemoryImpl;
import com.example.dao.memory.UserDAOMemoryImpl;

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

	@Override
	public ClientDAO getClientDAO() {
		return ClientDAOMemoryImpl.getInstance();
	}

	@Override
	public ArticleDAO getArticleDAO() {
		return ArticleDAOMemoryImpl.getInstance();
	}

	@Override
	public TransactionDAO getTransactionDAO() {
		return TransactionDAOMemoryImpl.getInstance();
	}

	@Override
	public UserDAO getUserDAO() {
		return UserDAOMemoryImpl.getInstance();
	}

}
