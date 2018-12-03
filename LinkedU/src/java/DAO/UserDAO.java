/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.User;
import Model.Profile;
import java.util.ArrayList;

/**
 *
 * @author ejzumba
 */
public class UserDAO implements DAO {

    private static final Logger log = Logger.getLogger(UserDAO.class.getName());

   
    @Override
    public int create(Object User, String username) {
        User user= (User)User;
        int rowCount = 0;
        
        try {
            Connection DBConn = DBName.connect2DB();

            String insertString;
            insertString = "INSERT INTO users (firstname, lastname, username, password, securityquestion, securityanswer, email, usertype) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = DBConn.prepareStatement(insertString);
            pstmt.setString(1, user.getfName());
            pstmt.setString(2, user.getlName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getSecQues());
            pstmt.setString(6, user.getSecAns());
            pstmt.setString(7, user.getEmail());
            pstmt.setString(8, user.getUserType());

            rowCount = pstmt.executeUpdate();
            log.info("insert string =" + insertString +"rowCount: "+rowCount);
            DBConn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

   @Override
    public ArrayList getAll(String query) {
        ArrayList<User> list= new ArrayList<>();
        try
        {
            Connection DBConn= DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "SELECT * FROM USERS";
                    
            log.info(insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
            stmt.setString(1, query);
           
            ResultSet rs= stmt.executeQuery();
            while (rs.next())
            {
                User i= new User();
                i.setUsername(rs.getString("username"));
                
                list.add(i);
            }

//                userController.setProfilePictureId(imgID, username);
                //System.out.println(imgID);
            

            DBConn.close();
        } 
        catch (SQLException e)
        {
            log.severe(e.getMessage());
        }
        return list;    
    }

    @Override
    public void delete(Object obj, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean checkUserEmail(User user) {
        boolean userExist = false;
        Statement stmt;
        ResultSet rs;
        String query;
        
        try {
            Connection DBConn = DBName.connect2DB();
            stmt = DBConn.createStatement();
            query = "SELECT * FROM users WHERE email ='" + user.getEmail() + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                userExist = true;
            }
            DBConn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return userExist;
    }

    
    public boolean checkUserID(User user) {
        boolean userExist = false;
        Statement stmt;
        ResultSet rs;
        String query;
        try {
            Connection DBConn = DBName.connect2DB(); {
                stmt = DBConn.createStatement();
                query = "SELECT * FROM users WHERE userid ='" + user.getUsername()+ "'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    userExist = true;
                }
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }

        return userExist;
    }

    @Override
    public User getOne(Object email) {
        String emailQuery = (String) email;
        User retVal = null;

        try {
            Connection DBConn = DBName.connect2DB();

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "select * from users where email ='" + emailQuery + "'";
            ResultSet rs = stmt.executeQuery(insertString);
            if (rs.next()) {
                retVal = new User();
                retVal.setfName(rs.getString("firstname"));
                retVal.setlName(rs.getString("lastname"));
                retVal.setUsername(rs.getString("username"));
                retVal.setPassword(rs.getString("password"));
                retVal.setSecQues(rs.getString("securityquestion"));
                retVal.setSecAns(rs.getString("securityanswer"));
                retVal.setEmail(rs.getString("email"));
                retVal.setActiveId(retVal.getUsername());
            }
        } catch (SQLException ex) {

        }
        return retVal;
    }
    
    @Override
    public Profile getProfileUser(Object username) {
        String usernameQuery = (String) username;
        Profile retVal = null;

        try {
            Connection DBConn = DBName.connect2DB();

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "SELECT * FROM users u INNER JOIN student s ON u.username = s.username WHERE u.username ='" + usernameQuery + "'";
            ResultSet rs = stmt.executeQuery(insertString);
            if (rs.next()) {
                retVal = new Profile();
                retVal.setUsername(rs.getString("username"));
                retVal.setPassword(rs.getString("password"));
                retVal.setfName(rs.getString("firstname"));
                retVal.setlName(rs.getString("lastname"));
                retVal.setEmail(rs.getString("email"));
                retVal.setSecQues(rs.getString("securityquestion"));
                retVal.setSecAns(rs.getString("securityanswer"));
                retVal.setUserType(rs.getString("usertype"));
                retVal.setDateOfBirth(rs.getString("dateofbirth"));
                retVal.setHeight(rs.getInt("height"));
                retVal.setWeight(rs.getInt("weight"));
                retVal.setAddress(rs.getString("address"));
                retVal.setCountry(rs.getString("country"));
                retVal.setZipcode(rs.getString("zipcode"));
                retVal.setPhone(rs.getString("phone"));
                retVal.setSchool(rs.getString("school"));
                retVal.setEndYear(rs.getInt("endyear"));
                retVal.setSat(rs.getInt("sat"));
                retVal.setAct(rs.getInt("act"));
                retVal.setPsat(rs.getInt("psat"));
                retVal.setCertification(rs.getString("certification"));
                retVal.setEssay(rs.getString("essay"));
                retVal.setHobbies(rs.getString("hobbies"));
                retVal.setActiveId(retVal.getUsername());
            }
        } catch (SQLException ex) {

        }
        return retVal;
    }

    
    public String getPass(String email) {
        String retVal = "";

        try {
            Connection DBConn = DBName.connect2DB();

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "select password from users where email ='" + email + "'";
            ResultSet rs = stmt.executeQuery(insertString);
            if (rs.next()) {
                retVal = rs.getString("password");

            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, ex.getMessage());
        }
        return retVal;
    }

    @Override
    public int update(Object aUser, String username) {
        User user= (User) aUser;
        int rowCount = 0;
        try {
            Connection DBConn = DBName.connect2DB();

            String insertString;
            insertString = "UPDATE USERS set fName= ?, lName=?, username=?, password=?, question= ?, answer=?, email=? where username=? ";
            PreparedStatement pstmt = DBConn.prepareStatement(insertString);
            pstmt.setString(1, user.getfName());
            pstmt.setString(2, user.getlName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getSecQues());
            pstmt.setString(6, user.getSecAns());
            pstmt.setString(7, user.getEmail());
            pstmt.setString(8, user.getActiveId());

            rowCount = pstmt.executeUpdate();
            log.info("insert string =" + insertString +"rowCount: "+rowCount);
            DBConn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    @Override
    public ArrayList searchById(String id) {
        ArrayList<User> list= new ArrayList<>();
        try
        {
            Connection DBConn= DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "SELECT * FROM ITKSTU.USERS WHERE LOWER(USERNAME) LIKE LOWER(?)";
                    
            log.info(insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
            stmt.setString(1, "%"+id+"%");
           
            ResultSet rs= stmt.executeQuery();
            log.info("inside SearchById");
            while (rs.next())
            {
                User i= new User();
                i.setUsername(rs.getString("username"));
                
                list.add(i);
            }


            

            DBConn.close();
        } 
        catch (SQLException e)
        {
            log.severe(e.getMessage());
        }
        return list;

    }
    
    
    
    
}
