package com.example.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import com.example.model.Cliente;
import com.example.model.Cuenta;
import com.example.model.Movimiento;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class CuentaDAOMemoryImpl implements CuentaDAO {
    private static final CuentaDAOMemoryImpl INSTANCE = new CuentaDAOMemoryImpl();
	
    private static final Logger logger = Logger.getLogger(CuentaDAOMemoryImpl.class.toString());
    private static final Vector<Cuenta> cuentas = new Vector<>();
    private static AtomicInteger LAST_ID;
	
    private CuentaDAOMemoryImpl(){
        LAST_ID = new AtomicInteger(0);
    }

    public static CuentaDAOMemoryImpl getInstance() {
        return INSTANCE;
    }
	
    private Integer incrementCount() {
        return LAST_ID.incrementAndGet();
    }

    @Override
    public Cuenta create(Cuenta cuenta) {
        cuenta.setId(incrementCount());
        cuentas.add(cuenta);
        logger.info(cuenta.toString());
        return cuenta;
    }

    @Override
    public boolean update(Cuenta cuenta) {
        int index = cuentas.indexOf(cuenta);
        if(index < 0)
            return false;
        cuentas.remove(index);
        if(index < cuentas.size())
            cuentas.add(index, cuenta);
        else
            cuentas.add(cuenta);
        return true;
    }

    @Override
    public boolean remove(Cuenta cuenta) {		
        return cuentas.remove(cuenta);
    }

    @Override
    public Cuenta find(Integer id) {
        Cuenta cuenta = null;
        for(Cuenta o : cuentas){
            if(o.getId().equals(id)){
                cuenta = o;
                break;
            }
        }
        return cuenta;
    }

    @Override
    public Cuenta first() {		 
        try {
            return cuentas.firstElement();
        } catch (NoSuchElementException ex){
            logger.warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public Cuenta last() {
        try {
            return cuentas.lastElement();
        } catch (NoSuchElementException ex){
            logger.warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Cuenta> findAll() {		
        return cuentas;
    }

    @Override
    public Integer count(){
        return cuentas.size();
    }
	
    @Override
    public List<Cuenta> findCuentasCliente(Integer cliente_id) {
        ClienteDAO clienteDao = DAOFactory.getClienteDAO();		
        Cliente cliente = clienteDao.find(cliente_id);
        List<Cuenta> cuentasTemp = new  ArrayList<Cuenta>();

        for(Cuenta cuenta : cuentas){
            if(cuenta.getCliente().equals(cliente))
                cuentasTemp.add(cuenta);
        }
        return cuentasTemp;
    }

    public void empty(){
        cuentas.clear();
        LAST_ID = new AtomicInteger(0);
    }
    
    @Override
    public void actualizarCuenta(Movimiento mov){
    	Cuenta cuenta = mov.getCuenta();
    	BigDecimal saldo = cuenta.getSaldo();
    	if(mov.getTipo().equals("+")){
    		saldo = saldo.add(mov.getMonto());
    	}else if(mov.getTipo().equals("-")) {
    		saldo = saldo.subtract(mov.getMonto());
    	}
    	cuenta.setSaldo(saldo);
    	update(cuenta);
    }
}
