/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DBName;
import Model.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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

    public List<String> getImages() {
        return images;
    }

   
    public ArrayList<String> getShowcaseUniversities() {

        results = new ArrayList();

        String selectString = "SELECT * FROM itkstu.ShowcaseUniversity "
                + "WHERE isshowcase = '1'";

        try {
            Connection DBConn = DBName.connect2DB();
            String image = "";
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);
            while (rs.next()) {
                // universityName = rs.getString("UNIVERSITYNAME");
                image = rs.getString("IMAGENAME");
                results.add(image);
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


        Random random = new Random();
        
        String showcase = (getShowcaseUniversities().get(random.nextInt(getShowcaseUniversities().size())));
        return showcase;
        
    }

}
