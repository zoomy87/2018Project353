/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package misc;

import DAO.DBName;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import static java.lang.Thread.sleep;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ericz
 */

public class NewMain {

    private static final String SAMPLE_CSV_FILE_PATH = "I:\\NetBeansApps\\2018Project353\\LinkedU\\src\\java\\misc\\hd2017.csv";
    private static Connection DBConn;
    
    
    public static void main(String[] args) {
        
        Reader reader = null;
        
        try {
            reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH), StandardCharsets.ISO_8859_1);
            CSVReader csvReader = new CSVReader(reader);
            
            //     at java.nio.charset.CoderResult.throwException(CoderResult.java:281)
            //
            String[] nextRecord;
           
            csvReader.readNext();
            
            while ((nextRecord = csvReader.readNext()) != null) {
                input(nextRecord[1]);
                
            }

        } catch (IOException | UncheckedIOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
     
    private static int input(String name){
        int imgID= 0;
         try
        {
             DBConn= DBName.connect2DB();
             
            //String type = file.getFileName().substring(file.getFileName().indexOf("."));
            String insert = "INSERT INTO USERS(USERNAME, PASSWORD, FIRSTNAME, LASTNAME, USERTYPE ) VALUES(?,'test', 'N/A','N/A', 'University')";
            PreparedStatement pstmt = DBConn.prepareStatement(insert);
            pstmt.setString(1, name);
            int rowCount = pstmt.executeUpdate();
            if(rowCount==1){
                insert= "INSERT INTO University(profileid, universityEmail, username) VALUES (default, ?, ?)";
//                System.out.println("NEWMAIN: " + insert);
                PreparedStatement stmt = DBConn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, "admissions@"+name.replaceAll(" ", "")+".edu");
                stmt.setString(2, name);
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
            }else {
                imgID= -1;
                System.out.println("Not inserted: "+name);
            }
            
            sleep(0, 1);

            DBConn.close();
        } 
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.out.println("Not inserted: "+name);
        }
         
        return imgID;
    }
    
}
   
