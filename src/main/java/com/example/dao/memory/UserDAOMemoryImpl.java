package com.example.dao.memory;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.dao.UserDAO;
import com.example.model.User;

/**
 * User DAO Memory Implementation.
 * 
 * @author Gustavo Bazan
 *
 */
public class UserDAOMemoryImpl implements UserDAO {
    /** Singleton instance */
	private static final UserDAOMemoryImpl INSTANCE = new UserDAOMemoryImpl();
    /** Logger */
    private static final Logger logger = Logger.getLogger(UserDAOMemoryImpl.class.getCanonicalName());
    /** Data */
    private static Vector<User> users;
    /** Threadsafe ID control */
    private static AtomicLong LAST_ID;

    private UserDAOMemoryImpl(){
        users = new Vector<>();
        LAST_ID = new AtomicLong(0);
    }
    
    /**
     * 
     * @return Singleton instance
     */
    public static UserDAOMemoryImpl getInstance() {
        return INSTANCE;
    }
	
    /**
     * 
     * @return new ID
     */
    private Long incrementCount() {
        return LAST_ID.incrementAndGet();
    }
	
    /**
     * Create a new User
     * 
     * @return the new user
     */
    @Override
    public User create(User user) {
        user.setId(incrementCount());
        users.add(user);
        logger.info(user.toString());
        return user;
    }

    @Override
    public boolean update(User user) {
    	int index = users.indexOf(user);
        if(index < 0)
            return false;
        users.remove(index);
        if(index < users.size())
        	users.add(index, user);
        else
        	users.add(user);
        return true;
    }

    @Override
    public boolean remove(User user) {		
        return users.remove(user);
    }

    @Override
    public User find(Long id) {        
        //Optional<User> user = users.stream().parallel().filter(c->c.getId().equals(id)).findFirst();
    	User user = null;
    	for(User o : users){
	        if(o.getId().equals(id)){
	        	user = o;
	            break;
	        }
	    }
        return user;
    }

    @Override
    public User first() {
    	return users.firstElement();
        
    }

    @Override
    public User last() {    	
        return users.lastElement();
    }
    
    @Override
    public List<User> findAll() {		
        return users.stream().collect(Collectors.toList());
    }

    @Override
    public Integer count(){
        return users.size();
    }

    public void empty(){
        users.clear();
        LAST_ID = new AtomicLong(0);
    }
	
}
