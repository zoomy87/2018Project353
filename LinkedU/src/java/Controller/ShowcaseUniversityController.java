/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBName;
import Model.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.imageio.ImageIO;

/**
 *
 * @author mlisows
 */
@ManagedBean
@RequestScoped
public class ShowcaseUniversityController {

    private List<String> images;
    private Image showcaseImage;
    private ArrayList results;
    private ArrayList uniResults;

    public List<String> getImages() {
        return images;
    }

    public ArrayList<String> getShowcaseUniversities() {

        results = new ArrayList();

        String selectString = "SELECT * FROM itkstu.ShowcaseUniversity "
                + "WHERE isshowcase = '1'";

        try {
            Connection DBConn = DBName.connect2DB();
            String name = "";
//String name = "";
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);
            while (rs.next()) {

                name = rs.getString("UNIVERSITYNAME");
                //image = rs.getString("IMAGENAME");
                results.add(name);
            }
            stmt.close();
            rs.close();
            DBConn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    public String randomShowcase() {

//        uniResults = new ArrayList();
//        Random random = new Random();
//        StringBuilder string = new StringBuilder();
//
//        for (int i = 0; i < getShowcaseUniversities().size(); i++) {
//            string.append(getShowcaseUniversities().get(i));
//
//
//            String selectString = "SELECT * FROM itkstu.Images "
//                    + "WHERE USERNAME = " + "'" + string + "'";
//
//            try {
//                Connection DBConn = DBName.connect2DB();
//                String name = "";
////String name = "";
//                Statement stmt = DBConn.createStatement();
//                ResultSet rs = stmt.executeQuery(selectString);
//                
//                
//                
//                Blob imageBlob = rs.getBlob("IMAGE");
//                
//                InputStream binaryStream = imageBlob.getBinaryStream(0, imageBlob.length());
//                results.add(binaryStream);
//                stmt.close();
//                rs.close();
//                DBConn.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            
//        }
//return results;
        Random random = new Random();
        String showcase = "APPLY TO " +(getShowcaseUniversities().get(random.nextInt(getShowcaseUniversities().size()))) + " TODAY!";
        return showcase;//showcase;

    }

//    public BufferedImage displayImage() {
//
//        String selectString = "SELECT * FROM itkstu.IMAGES ";
//
//        try {
//            Connection DBConn = DBName.connect2DB();
//            //String name = "";
////String name = "";
//            Statement stmt = DBConn.createStatement();
//            ResultSet rs = stmt.executeQuery(selectString);
//
//            Blob blob = rs.getBlob("IMAGE");
//            InputStream stream = blob.getBinaryStream(0, blob.length());
//            BufferedImage image = ImageIO.read(stream);
//
//            stmt.close();
//            rs.close();
//            DBConn.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return image;
//
//    }

}
