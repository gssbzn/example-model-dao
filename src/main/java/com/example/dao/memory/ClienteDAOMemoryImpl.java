package com.example.dao.memory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import com.example.dao.ClienteDAO;
import com.example.model.Cliente;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class ClienteDAOMemoryImpl implements ClienteDAO {
    private static final ClienteDAOMemoryImpl INSTANCE = new ClienteDAOMemoryImpl();

    private static final Logger logger = Logger.getLogger(ClienteDAOMemoryImpl.class.toString());
    private static Vector<Cliente> clientes;
    private static AtomicInteger LAST_ID;

    private ClienteDAOMemoryImpl(){
        clientes = new Vector<>();
        LAST_ID = new AtomicInteger(0);
    }

    public static ClienteDAOMemoryImpl getInstance() {
        return INSTANCE;
    }
	
    private Integer incrementCount() {
        return LAST_ID.incrementAndGet();
    }
	
    @Override
    public Cliente create(Cliente cliente) {
        cliente.setId(incrementCount());
        clientes.add(cliente);
        logger.info(cliente.toString());
        return cliente;
    }

    @Override
    public boolean update(Cliente cliente) {
        int index = clientes.indexOf(cliente);
        if(index < 0)
            return false;
        clientes.remove(index);
        if(index < clientes.size())
            clientes.add(index, cliente);
        else
            clientes.add(cliente);
        return true;
    }

    @Override
    public boolean remove(Cliente cliente) {		
        return clientes.remove(cliente);
    }

    @Override
    public Cliente find(Integer id) {
        Cliente cliente = null;
        //Optional<Cliente>  c = clientes.stream().parallel().filter(c->c.getId().equals(id)).findFirst();
        for(Cliente o : clientes){
            if(o.getId().equals(id)){
                cliente = o;
                break;
            }
        }
        return cliente;
    }

    @Override
    public Cliente first() {		 
        try {
            return clientes.firstElement();
        } catch (NoSuchElementException ex){
            logger.warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public Cliente last() {
        try {
            return clientes.lastElement();
        } catch (NoSuchElementException ex){
            logger.warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> findAll() {		
        return clientes;
    }

    @Override
    public Integer count(){
        return clientes.size();
    }

    public void empty(){
        clientes.clear();
        LAST_ID = new AtomicInteger(0);
    }
	
}
