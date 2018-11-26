/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author ericz
 */
public class DBName {
    private static final String DBNAME= "jdbc:derby://localhost:1527/ProjectLinkedU";
    
    
    public static String getDBName(){
        return DBNAME;
    }
    
    
}
