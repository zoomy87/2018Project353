/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controller.UserController;
import Model.Image;
import com.oracle.webservices.api.message.PropertySet.Property;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ericz
 */
@ManagedBean
@SessionScoped
public class ImageDAO implements DAO{

    public ImageDAO(){
        
    }
    
    
    @Override
    public int create(Object File, String username)
    {
        UploadedFile file= (UploadedFile)File;
        int rowCount = 0;
        int imgID = -1;
        
        try
        {
            Connection DBConn= DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "INSERT INTO IMAGES VALUES (default, ?, '" + username + "')";
            System.out.println("IMAGEDAOIMPL: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setBinaryStream(1, file.getInputstream());
            rowCount = stmt.executeUpdate();
            if (rowCount == 1)
            {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next())
                {
                    imgID = rs.getInt(1);
                }

//                userController.setProfilePictureId(imgID, username);
                //System.out.println(imgID);
            }

            DBConn.close();
        } 
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return imgID;
    
    }

    @Override
    public int update(Object File, String id) {
        UploadedFile file= (UploadedFile)File;
        
        int rowCount = 0;
        int imgID = -1;
        
        try
        {
            Connection DBConn= DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "update IMAGES set IMAGE= ? where imageid= ?";
                    
            System.out.println("IMAGEDAOIMPL: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setBinaryStream(1, file.getInputstream());
            stmt.setString(2, id);
            rowCount = stmt.executeUpdate();
            if (rowCount == 1)
            {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next())
                {
                    imgID = rs.getInt(1);
                }

//                userController.setProfilePictureId(imgID, username);
                //System.out.println(imgID);
            }

            DBConn.close();
        } 
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return imgID;
    }

    @Override
    public Object getOne(Object id) {
        Image i= null;
        
        try
        {
            Connection DBConn= DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "select image, imageid from IMAGES where imageid= ?";
                    
            System.out.println("IMAGEDAOIMPL: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
            stmt.setString(1, ""+ id);
           
            ResultSet rs= stmt.executeQuery();
            if (rs.next())
            {
                i= new Image();
                System.out.println("imagDAO: assigning image");
                i.setImage(new DefaultStreamedContent(new ByteArrayInputStream(rs.getBytes("image"))));
                System.out.println("imageDAO: before id assigned");
                i.setImageId(rs.getInt("imageid"));
                System.out.println("imagDAO: "+i.getImageId());
            }

//                userController.setProfilePictureId(imgID, username);
                //System.out.println(imgID);
            

            DBConn.close();
        } 
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return i;
    }

    @Override
    public ArrayList getAll(String userName) {
        ArrayList<Image> list= new ArrayList<>();
        try
        {
            Connection DBConn= DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "select * from IMAGES where username= ?";
                    
            System.out.println("IMAGEDAO: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
            stmt.setString(1, userName);
           
            ResultSet rs= stmt.executeQuery();
            while (rs.next())
            {
                Image i= new Image();
                i.setImage((StreamedContent) rs.getBinaryStream("image"));
                i.setImageId(rs.getInt("imageid"));
                list.add(i);
            }

//                userController.setProfilePictureId(imgID, username);
                //System.out.println(imgID);
            

            DBConn.close();
        } 
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void delete(Object img, String username) {
        Image image= (Image) img;
        try
        {
            Connection DBConn= DBName.connect2DB();
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "delete from images where imageid= ?";
                    
            System.out.println("IMAGEDAO: " + insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert);
            stmt.setString(1, ""+image.getImageId());
           
            int rowDeleted= stmt.executeUpdate();
           

//                userController.setProfilePictureId(imgID, username);
                //System.out.println(imgID);
            

            DBConn.close();
        } 
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

   
    @Override
    public Object getProfileUser(Object username){
        return null;
    }
}
