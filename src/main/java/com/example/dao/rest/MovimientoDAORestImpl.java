package com.example.dao.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import com.example.dao.MovimientoDAO;
import com.example.model.Cliente;
import com.example.model.Cuenta;
import com.example.model.Movimiento;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class MovimientoDAORestImpl implements MovimientoDAO {

    private static final Logger logger = Logger.getLogger(MovimientoDAORestImpl.class.toString());
    private final String SERVER;   
    
    public MovimientoDAORestImpl(String server){
    	SERVER = server;
    }
	
    @Override
    public Movimiento create(Movimiento movimiento) {
    	Cuenta cuenta = movimiento.getCuenta();
    	Cliente cliente = cuenta.getCliente();
    	
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	Future<Movimiento> future = null; 
    	
    	/*final Future<Response> responseFuture = target.path("clientes/"+cliente.getId()+"/cuentas/"+cuenta.getId()+"/movimientos/async")
    			.request("application/xml").async().post(Entity.xml(movimiento), new InvocationCallback<Response>() {
    	            @Override
    	            public void completed(Response response) {
    	                logger.info("Response status code "
    	                        + response.getStatus() + " received.");
    	            }
    	 
    	            @Override
    	            public void failed(Throwable throwable) {
    	                logger.severe("Invocation failed.");
    	                throwable.printStackTrace();
    	            }
    	        });*/
    	try {
    		future = target.path("clientes/"+cliente.getId()+"/cuentas/"+cuenta.getId()+"/movimientos/async").request("application/xml").async().post(Entity.xml(movimiento), Movimiento.class);
    	} catch(Throwable ignored){
    		ignored.printStackTrace();
    	}
    	
    	if (future != null){
    		try {
        		movimiento = future.get(15, TimeUnit.SECONDS);
        	} catch (TimeoutException timeout) {
        		logger.severe("request timed out");
        	} catch (InterruptedException ie) {
        		logger.severe("Request was interrupted");
        	} catch (ExecutionException ee) {
        		logger.severe("Otro");
        		Throwable cause = ee.getCause();
        		cause.printStackTrace();
        	}
    	}
    	
    	
        return movimiento;
    }

    @Override
    public boolean update(Movimiento movimiento) {
        return false;
    }

    @Override
    public boolean remove(Movimiento movimiento) {		
    	return false;
    }

    @Override
    public Movimiento find(Integer id) {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);    	
    	Movimiento movimiento = (Movimiento) target.path("clientes/0/cuentas/0/movimientos").request("application/xml").get(Movimiento.class);
        logger.info(movimiento.toString());
        return movimiento;
    }

    @Override
    public Movimiento first() {		 
        return null;
    }

    @Override
    public Movimiento last() {
        return null;
    }

    @Override
    public List<Movimiento> findAll() {		
        return null;
    }

    @Override
    public Integer count(){
        return null;
    }

	@Override
	public List<Movimiento> findMovimientosCuenta(Integer cuenta_id) {
		WebTarget target = ClientBuilder.newClient().target(SERVER);
    	GenericType<ArrayList<Movimiento>> genericRootElement = new GenericType<ArrayList<Movimiento>>() {};
		ArrayList<Movimiento> movimientos = target.path("clientes/0/cuentas/"+cuenta_id+"/movimientos").request().get(genericRootElement);
		return movimientos;
	}

}
