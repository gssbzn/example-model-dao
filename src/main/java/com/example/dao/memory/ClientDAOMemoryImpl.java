package com.example.dao.memory;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.dao.ClientDAO;
import com.example.model.Client;

/**
 * Client DAO Memory Implementation.
 * 
 * @author Gustavo Bazan
 *
 */
public class ClientDAOMemoryImpl implements ClientDAO {
    /** Singleton instance */
	private static final ClientDAOMemoryImpl INSTANCE = new ClientDAOMemoryImpl();
    /** Logger */
    private static final Logger logger = Logger.getLogger(ClientDAOMemoryImpl.class.getCanonicalName());
    /** Data */
    private static Vector<Client> clients;
    /** Threadsafe ID control */
    private static AtomicInteger LAST_ID;   

    private ClientDAOMemoryImpl(){
        clients = new Vector<>();
        LAST_ID = new AtomicInteger(0);       
    }

    /**
     * 
     * @return Singleton instance
     */
    public static ClientDAOMemoryImpl getInstance() {
        return INSTANCE;
    }
	
    /**
     * 
     * @return new ID
     */
    private Integer incrementCount() {
        return LAST_ID.incrementAndGet();
    }
	
    /**
     * Create a new Client
     * 
     * @return the new client
     */
    @Override
    public Client create(Client client) {
        client.setId(incrementCount().toString());
        clients.add(client);        	
        logger.info(client.toString());
        return client;
    }

    @Override
    public boolean update(Client client) {
    	int index = clients.indexOf(client);
        if(index < 0)
            return false;
        clients.remove(index);
        if(index < clients.size())
            clients.add(index, client);
        else
            clients.add(client);
        return true;
    }

    @Override
    public boolean remove(Client client) {		
        return clients.remove(client);
    }

    @Override
    public Client find(String id) {        
        //Optional<Client> client = clients.stream().parallel().filter(c->c.getId().equals(id)).findFirst();
    	Client client = null;
    	for(Client o : clients){
	        if(o.getId().equals(id)){
	        	client = o;
	            break;
	        }
	    }
        return client;
    }

    @Override
    public Client first() {
    	return clients.firstElement();
        
    }

    @Override
    public Client last() {    	
        return clients.lastElement();
    }
    
    @Override
    public List<Client> findAll() {		
        return clients.stream().collect(Collectors.toList());
    }

    @Override
    public Integer count(){
        return clients.size();
    }

    public void empty(){
        clients.clear();
        LAST_ID = new AtomicInteger(0);       
    }
	
}
