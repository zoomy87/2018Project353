/*
 * DBHelper.java
 *
 * Created on November 1, 2007, 3:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bllim
 */
public class DBHelper {
    
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    
    public static void loadDriver(String driverStr) {
        try {
            Class.forName(driverStr);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static Connection connect2DB(String connectStr, String userName, String password) {
        
        String myDB = connectStr;
        Connection DBConn = null;
        try {
            DBConn = DriverManager.getConnection(myDB, userName, password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return DBConn;
    }
}
