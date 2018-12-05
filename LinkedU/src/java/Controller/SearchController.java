/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO;
import DAO.UserDAO;
import Model.User;
import com.sun.istack.logging.Logger;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ericz
 */
@ManagedBean
@SessionScoped
public class SearchController {
    private String searchTopic;
    private List<User> searchResults;
    private static final Logger log= Logger.getLogger(SearchController.class);
    
    public SearchController(){
     
    }
    
    public String searchUserId(){
        DAO userDAO= new UserDAO();
        log.info(searchTopic);
        searchResults= userDAO.searchById(searchTopic);
        
        return "SearchResults?redirect=true";
    }

    public String getSearchTopic() {
        return searchTopic;
    }

    public void setSearchTopic(String searchTopic) {
        this.searchTopic = searchTopic;
    }

    public List<User> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<User> searchResults) {
        this.searchResults = searchResults;
    }
    
     public String searchUserIdASC(){
        UserDAO userDAO= new UserDAO();
        log.info(searchTopic);
        searchResults= userDAO.searchByIdASC(searchTopic);
        
        return "SearchResults?redirect=true";
    }
      public String searchUserIdDESC(){
        UserDAO userDAO= new UserDAO();
        log.info(searchTopic);
        searchResults= userDAO.searchByIdDESC(searchTopic);
        
        return "SearchResults?redirect=true";
    }

}