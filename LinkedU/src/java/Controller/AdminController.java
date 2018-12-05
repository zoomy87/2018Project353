/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBName;
import java.sql.Connection;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ericz
 */
@ManagedBean
@SessionScoped
public class AdminController {

    public int showcaseUniversity(String universityName) {
        int row = 0;
        String query = "UPDATE itkstu.SHOWCASEUNIVERSITY"
                + " SET ISSHOWCASE = '1'"
                + " WHERE UNIVERSITYNAME = '" + universityName + "'";
        System.out.println("SHOWCASEUNIVERSITYDAOIMPL: " + query);
        try {
            Connection DBConn = DBName.connect2DB();
            Statement stmt = DBConn.createStatement();
            row = stmt.executeUpdate(query);
            DBConn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return row;
    }

    public int unshowcaseUniversity(String universityName) {
        int row = 0;
        String query = "UPDATE itkstu.SHOWCASEUNIVERSITY"
                + " SET ISSHOWCASE = '0'"
                + " WHERE UNIVERSITYNAME = '" + universityName + "'";
        System.out.println("SHOWCASEUNIVERSITY: " + query);
        try {
            Connection DBConn = DBName.connect2DB();
            Statement stmt = DBConn.createStatement();
            row = stmt.executeUpdate(query);
            DBConn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return row;
    }

}
