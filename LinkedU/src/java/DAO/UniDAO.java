/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Image;
import Model.Profile;
import Model.User;
import Model.University;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author mlisows
 */
public class UniDAO implements DAO {

    
    @Override
    public User getOneUsername(Object username){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Profile getProfileUni(Object username){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int create(Object obj, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Object obj, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getOne(Object id) {
        University university = new University();
        try {
            Connection DBConn = DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "SELECT universityname FROM showcaseuniversity Where username = ? ";

            //System.out.println("IMAGEDAOIMPL: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
            stmt.setString(1, (String) id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                university.setUsername(rs.getString("universityName"));
            }

//                userController.setProfilePictureId(imgID, username);
            //System.out.println(imgID);
            DBConn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return university;

    }
    
    public University getOne(String username) {
        University university = new University();
        try {
            Connection DBConn = DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "Select * From University Where username= ? ";

            //System.out.println("IMAGEDAOIMPL: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                university.setUsername(rs.getString("username"));
                university.setProfileId(rs.getInt("profileid"));
                university.setEmail(rs.getString("universityEmail"));
            }

//                userController.setProfilePictureId(imgID, username);
            //System.out.println(imgID);
            DBConn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return university;
    }

    @Override
    public ArrayList getAll(String query) {
        ArrayList retVal= new ArrayList<>();
        try {
            Connection DBConn = DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "Select * From university Where username= ? ";

            //System.out.println("IMAGEDAOIMPL: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
            stmt.setString(1, query);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                University university= new University();
                university.setUsername(rs.getString("username"));
                university.setProfileId(rs.getInt("profileid"));
                university.setEmail(rs.getString("universityEmail"));
                retVal.add(university);
            }

//                userController.setProfilePictureId(imgID, username);
            //System.out.println(imgID);
            DBConn.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return retVal;

    }

    @Override
    public void delete(Object obj, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object getProfileUser(Object username){
        return null;
    }

    @Override
    public ArrayList searchById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
