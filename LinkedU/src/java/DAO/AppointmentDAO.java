/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ejzumba
 */
public class AppointmentDAO implements DAO {
    private static final Logger log= Logger.getLogger(AppointmentDAO.class);
    
    
    @Override
    public int create(Object appointment, String empty) {
        Appointment app= (Appointment) appointment;
        int rowCount = 0;
        int ID = -1;
        
        try
        {
            Connection DBConn= DBName.connect2DB();           
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "INSERT INTO appointment VALUES (default, ?, ?, ?)";
            log.info(insert);
            PreparedStatement stmt = DBConn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, app.getStudentId() );
            stmt.setInt(2, app.getUniversityId());
            stmt.setString(3, app.getDate());
            rowCount = stmt.executeUpdate();
            if (rowCount == 1)
            {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next())
                {
                    ID = rs.getInt(1);
                }

//                userController.setProfilePictureId(imgID, username);
                //System.out.println(imgID);
            }

            DBConn.close();
        } 
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return ID;
    
    
    }

    @Override
    public Object getProfileUser(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
