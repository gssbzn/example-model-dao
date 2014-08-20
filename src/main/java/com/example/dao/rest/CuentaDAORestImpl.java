package com.example.dao.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import com.example.dao.CuentaDAO;
import com.example.model.Cuenta;
import com.example.model.Movimiento;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class CuentaDAORestImpl implements CuentaDAO {
	
    private static final Logger logger = Logger.getLogger(CuentaDAORestImpl.class.toString());
	private final String SERVER;
    
	public CuentaDAORestImpl(String server){
    	SERVER = server;
    }
	
    @Override
    public Cuenta create(Cuenta cuenta) {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	cuenta = (Cuenta) target.path("clientes/"+cuenta.getCliente().getId()+"/cuentas").request("application/xml").post(Entity.xml(cuenta), Cuenta.class);
        logger.info(cuenta.toString());
        return cuenta;
    }

    @Override
    public boolean update(Cuenta cuenta) {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	target.path("clientes/" + cuenta.getCliente().getId()+"/cuentas/"+cuenta.getId()).request("application/xml").put(Entity.xml(cuenta), Cuenta.class);
        return true;
    }

    @Override
    public boolean remove(Cuenta cuenta) {		
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	Boolean res = target.path("clientes/" + cuenta.getCliente().getId()+"/cuentas/"+cuenta.getId()).request().delete(Boolean.class);
    	return res;
    }

    @Override
    public Cuenta find(Integer id) {
    	return null;
    }

    @Override
    public Cuenta first() {		 
        return null;
    }

    @Override
    public Cuenta last() {
    	return null;
    }

    @Override
    public List<Cuenta> findAll() {		
        return null;
    }

    @Override
    public Integer count(){
        return null;
    }
	
    @Override
    public List<Cuenta> findCuentasCliente(Integer cliente_id) {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	GenericType<ArrayList<Cuenta>> genericRootElement = new GenericType<ArrayList<Cuenta>>() {};
		ArrayList<Cuenta> cuentas = target.path("clientes/"+cliente_id+"/cuentas/").request().get(genericRootElement);
        return cuentas;
    }

	@Override
	public void actualizarCuenta(Movimiento mov) {
		// TODO Auto-generated method stub
		
	}

}
