/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Image;
import Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import org.jboss.logging.Logger;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ericz
 */
public class StudentDAO implements DAO {

    private static final Logger log = Logger.getLogger(StudentDAO.class);
    private Connection DBConn;

    @Override
    public int create(Object Student, String username) {
        Student student = (Student) Student;
        int rowCount = 0;

        try {
            DBConn = DBName.connect2DB();
            String insertString;

            insertString = "INSERT INTO itkstu.student "
                    + "(dateOfBirth, height, weight, address, country, zipcode, phone, school, endYear, "
                    + "sat, act, psat, certification, essay, hobbies, username) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = DBConn.prepareStatement(insertString);

            stmt.setString(1, student.getDateOfBirth());
            stmt.setString(2, student.getHeight());
            stmt.setString(3, student.getWeight());
            stmt.setString(4, student.getStreet() + ", " + student.getCity());
            stmt.setString(5, student.getCountry());
            stmt.setString(6, student.getZipcode());
            stmt.setString(7, student.getPhone());
            stmt.setString(8, student.getSchool());
            stmt.setString(9, student.getEndYear());
            stmt.setString(10, student.getSat());
            stmt.setString(11, student.getAct());
            stmt.setString(12, student.getPsat());
            stmt.setString(13, student.getCertification());
            stmt.setString(14, student.getEssay());
            stmt.setString(15, student.getHobbies());
            stmt.setString(16, student.getSchool());

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
        ArrayList<Student> list = new ArrayList<>();
        try {
            Connection DBConn = DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "select * from student where username= ?";

            log.info(insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setProfileId(rs.getString("profileId"));
                s.setDateOfBirth(rs.getString("dateOfBirth"));
                s.setHeight(rs.getString("height"));
                s.setWeight(rs.getString("weight"));
                s.setAddress(rs.getString("address"));
                s.setCountry(rs.getString("country"));
                s.setZipcode(rs.getString("zipcode"));
                s.setPhone(rs.getString("phone"));
                s.setSchool(rs.getString("school"));
                s.setEndYear(rs.getString("endyear"));
                s.setAct(rs.getString("ACT"));
                s.setSat(rs.getString("sat"));
                s.setPsat(rs.getString("psat"));
                s.setCertification(rs.getString("certification"));
                s.setEssay(rs.getString("essay"));
                s.setUsername(rs.getString("username"));
                s.setHobbies(rs.getString("hobbies"));
                list.add(s);
            }

//                userController.setProfilePictureId(imgID, username);
            //System.out.println(imgID);
            DBConn.close();
        } catch (SQLException e) {
            log.log(Logger.Level.FATAL, e.getMessage());
        }
        return list;
    }

    //@Override
    public ArrayList getAll() {
        ArrayList<Student> list = new ArrayList<>();
        try {
            Connection DBConn = DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "select * from student";

            log.info(insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setProfileId(rs.getString("profileId"));
                s.setDateOfBirth(rs.getString("dateOfBirth"));
                s.setHeight(rs.getString("height"));
                s.setWeight(rs.getString("weight"));
                s.setAddress(rs.getString("address"));
                s.setCountry(rs.getString("country"));
                s.setZipcode(rs.getString("zipcode"));
                s.setPhone(rs.getString("phone"));
                s.setSchool(rs.getString("school"));
                s.setEndYear(rs.getString("endyear"));
                s.setAct(rs.getString("ACT"));
                s.setSat(rs.getString("sat"));
                s.setPsat(rs.getString("psat"));
                s.setCertification(rs.getString("certification"));
                s.setEssay(rs.getString("essay"));
                s.setUsername(rs.getString("username"));
                s.setHobbies(rs.getString("hobbies"));
                list.add(s);
            }
            DBConn.close();
        } catch (SQLException e) {
            log.log(Logger.Level.FATAL, e.getMessage());
        }
        return list;
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
