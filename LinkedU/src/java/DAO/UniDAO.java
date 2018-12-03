/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Image;
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
    public int create(Object obj, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Object obj, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getOne(Object id) {
        String university = "";
        try {
            Connection DBConn = DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "Select UniversityName From ShowcaseUniversity Where showcaseid= ? ";

            //System.out.println("IMAGEDAOIMPL: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
            stmt.setString(1, (String) id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                university = rs.getString("universityName");
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
    public ArrayList getAll(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object obj, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object getProfileUser(Object username){
        return null;
    }

}
