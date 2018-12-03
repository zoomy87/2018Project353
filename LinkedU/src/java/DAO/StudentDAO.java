/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import org.jboss.logging.Logger;

/**
 *
 * @author ericz
 */
public class StudentDAO implements DAO {
    
    private static final Logger log= Logger.getLogger(StudentDAO.class);

    private int profileId;
    private String dateOfBirth;
    private int height;
    private int weight;
    private String street;
    private String city;
    private String address;
    private String country;
    private String zipcode;
    private String phone;
    private String school;
    private int endYear;
    private int sat;
    private int act;
    private int psat;
    private String certification;
    private String essay;
    private String hobbies;
    private String username;
    private ArrayList posts;
    private Connection DBConn;

    @Override
    public int create(Object Student, String username) {
        Student student= (Student) Student;
        int rowCount = 0;

        try {
           DBConn= DBName.connect2DB();
            String insertString;
            
            insertString = "INSERT INTO itkstu.student "
                    + "(dateOfBirth, height, weight, address, country, zipcode, phone, school, endYear, "
                    + "sat, act, psat, certification, essay, hobbies, username) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    
            PreparedStatement stmt = DBConn.prepareStatement(city);
            
            stmt.setString(1, student.getDateOfBirth() );
            stmt.setInt(2, student.getHeight() );
            stmt.setInt(3, student.getWeight() );
            stmt.setString(4, student.getStreet()+" "+ student.getCity() );
            stmt.setString(5, student.getCountry() );
            stmt.setString(6, student.getZipcode() );
            stmt.setString(7, student.getPhone() );
            stmt.setString(8, student.getSchool() );
            stmt.setInt(1, student.getEndYear() );
            stmt.setInt(2, student.getSat() );
            stmt.setInt(3, student.getAct() );
            stmt.setString(4, student.getPsat()+" "+ student.getCity() );
            stmt.setString(5, student.getCertification() );
            stmt.setString(6, student.getEssay() );
            stmt.setString(7, student.getHobbies() );
            stmt.setString(8, student.getSchool() );
            
            log.info(insertString);
            rowCount = stmt.executeUpdate(insertString);

            DBConn.close();
        } catch (SQLException e) {
            log.log(Logger.Level.FATAL, e.getMessage());
        }

        return rowCount;
    }

    @Override
    public int update(Object obj, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getOne(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getAll(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object obj, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList searchById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
