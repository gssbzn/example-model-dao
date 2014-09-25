package com.example.dao.memory;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.dao.ArticleDAO;
import com.example.model.Article;

/**
 * Article DAO Memory Implementation.
 * 
 * @author Gustavo Bazan
 *
 */
public class ArticleDAOMemoryImpl implements ArticleDAO {
    /** Singleton instance */
	private static final ArticleDAOMemoryImpl INSTANCE = new ArticleDAOMemoryImpl();
    /** Logger */
    private static final Logger logger = Logger.getLogger(ArticleDAOMemoryImpl.class.getCanonicalName());
    /** Data */
    private static Vector<Article> articles;
    /** Threadsafe ID control */
    private static AtomicLong LAST_ID;

    private ArticleDAOMemoryImpl(){
        articles = new Vector<>();
        LAST_ID = new AtomicLong(0);
    }

    /**
     * 
     * @return Singleton instance
     */
    public static ArticleDAOMemoryImpl getInstance() {
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
    public Article create(Article article) {
        article.setId(incrementCount());
        articles.add(article);
        logger.info(article.toString());
        return article;
    }

    @Override
    public boolean update(Article article) {
    	int index = articles.indexOf(article);
        if(index < 0)
            return false;
        articles.remove(index);
        if(index < articles.size())
        	articles.add(index, article);
        else
        	articles.add(article);
        return true;
    }

    @Override
    public boolean remove(Article article) {		
        return articles.remove(article);
    }

    @Override
    public Article find(Long id) {    	
    	// Optional<Article> article = articles.stream().parallel().filter(c->c.getId().equals(id)).findFirst();
    	Article article = null;
    	for(Article o : articles){
	        if(o.getId().equals(id)){
	        	article = o;
	            break;
	        }
	    }
    	return article;
    }

    @Override
    public Article first() {
    	return articles.firstElement();
        
    }

    @Override
    public Article last() {    	
        return articles.lastElement();
    }
    
    @Override
    public List<Article> findAll() {		
        return articles.stream().collect(Collectors.toList());
    }

    @Override
    public Integer count(){
        return articles.size();
    }

    public void empty(){
        articles.clear();
        LAST_ID = new AtomicLong(0);
    }
	
}
