/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;

/**
 *
 * @author ericz
 */
public class DBName {
    private static final String DBNAME= "jdbc:derby://localhost:1527/ProjectLinkedU";
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    
    public static String getDBName(){
        return DBNAME;
    }
    
    public static Connection connect2DB()
    {
        DBHelper.loadDriver(DRIVER);
        return DBHelper.connect2DB(DBNAME, "itkstu", "student");
    }
}
