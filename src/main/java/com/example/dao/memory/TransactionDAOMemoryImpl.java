package com.example.dao.memory;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.dao.TransactionDAO;
import com.example.model.Transaction;

/**
 * Transactions DAO Memory Implementation.
 * 
 * @author Gustavo Bazan
 *
 */
public class TransactionDAOMemoryImpl implements TransactionDAO {
    /** Singleton instance */
	private static final TransactionDAOMemoryImpl INSTANCE = new TransactionDAOMemoryImpl();
    /** Logger */
    private static final Logger logger = Logger.getLogger(TransactionDAOMemoryImpl.class.getCanonicalName());
    /** Data */
    private static Vector<Transaction> transactions;
    /** Threadsafe ID control */
    private static AtomicLong LAST_ID;

    private TransactionDAOMemoryImpl(){
        transactions = new Vector<>();
        LAST_ID = new AtomicLong(0);
    }
    
    /**
     * 
     * @return Singleton instance
     */
    public static TransactionDAOMemoryImpl getInstance() {
        return INSTANCE;
    }
	
    /**
     * 
     * @return new ID
     */
    private Long incrementCount() {
        return LAST_ID.incrementAndGet();
    }
	
    @Override
    public Transaction create(Transaction transaction) {
    	transaction.setId(incrementCount());
    	transactions.add(transaction);
        logger.info(transaction.toString());
        return transaction;
    }

    @Override
    public boolean update(Transaction transaction) {
    	int index = transactions.indexOf(transaction);
        if(index < 0)
            return false;
        transactions.remove(index);
        if(index < transactions.size())
        	transactions.add(index, transaction);
        else
        	transactions.add(transaction);
        return true;
    }

    @Override
    public boolean remove(Transaction transaction) {		
        return transactions.remove(transaction);
    }

    @Override
    public Transaction find(Long id) {        
        //Optional<Transaction> transaction = transactions.stream().parallel().filter(c->c.getId().equals(id)).findFirst();
    	Transaction transaction = null;
    	for(Transaction o : transactions){
	        if(o.getId().equals(id)){
	        	transaction = o;
	            break;
	        }
	    }
    	return transaction;
    }

    @Override
    public Transaction first() {
    	return transactions.firstElement();
        
    }

    @Override
    public Transaction last() {    	
        return transactions.lastElement();
    }
    
    @Override
    public List<Transaction> findAll() {		
        return transactions.stream().collect(Collectors.toList());
    }

    @Override
    public Integer count(){
        return transactions.size();
    }

    public void empty(){
        transactions.clear();
        LAST_ID = new AtomicLong(0);
    }
	
}
