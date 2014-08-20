package com.example.dao.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import com.example.dao.ClienteDAO;
import com.example.model.Cliente;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class ClienteDAORestImpl implements ClienteDAO {

    private static final Logger logger = Logger.getLogger(ClienteDAORestImpl.class.toString());
    private final String SERVER;
    
    public ClienteDAORestImpl(String server){
    	SERVER = server;
    }   
	
    @Override
    public Cliente create(Cliente cliente) {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	cliente = (Cliente) target.path("clientes").request("application/xml").post(Entity.xml(cliente), Cliente.class);
        logger.info(cliente.toString());
        return cliente;
    }

    @Override
    public boolean update(Cliente cliente) {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	try {
    		target.path("clientes/" + cliente.getId()).request("application/xml").put(Entity.xml(cliente), Cliente.class);
    	} catch (NotFoundException ex){
        	logger.warning(ex.getMessage());
        	return false;
        }
        return true;
    }

    @Override
    public boolean remove(Cliente cliente) {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	Boolean res = target.path("clientes/" + cliente.getId()).request().delete(Boolean.class);
        return res;
    }

    @Override
    public Cliente find(Integer id) {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	Cliente cliente = null;
    	try {
        	cliente = target.path("clientes/"+id).request().get(Cliente.class);
        } catch (NotFoundException ex){
        	logger.warning(ex.getMessage());
        }
        return cliente;
    }

    @Override
    public Cliente first() {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	GenericType<ArrayList<Cliente>> genericRootElement = new GenericType<ArrayList<Cliente>>() {};
		List<Cliente> clientes = target.path("clientes").request().get(genericRootElement);
        try {
            return clientes.get(0);
        } catch (IndexOutOfBoundsException ex){
            logger.warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public Cliente last() {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	GenericType<ArrayList<Cliente>> genericRootElement = new GenericType<ArrayList<Cliente>>() {};
		List<Cliente> clientes = target.path("clientes").request().get(genericRootElement);
        try {
            return clientes.get(clientes.size()-1);
        } catch (IndexOutOfBoundsException ex){
            logger.warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> findAll() {
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	GenericType<ArrayList<Cliente>> genericRootElement = new GenericType<ArrayList<Cliente>>() {};
		ArrayList<Cliente> clientes = target.path("clientes").request().get(genericRootElement);
		return clientes;
    }

    @Override
    public Integer count(){
    	WebTarget target = ClientBuilder.newClient().target(SERVER);
    	GenericType<ArrayList<Cliente>> genericRootElement = new GenericType<ArrayList<Cliente>>() {};
		ArrayList<Cliente> clientes = target.path("clientes").request().get(genericRootElement);
        return clientes.size();
    }
	
}
