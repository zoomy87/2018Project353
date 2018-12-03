/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.jboss.logging.Logger;

/**
 *
 * @author ericz
 */
@ManagedBean
@SessionScoped
public class UniversityController {
    private Date date;
    private String dateString;
    private static final Logger log= Logger.getLogger(UniversityController.class);
    
    public UniversityController(){
        dateString= " ";
        
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        this.dateString= date.toString();
    }
    
    public void makeAppointment(){
        SimpleDateFormat sdf= new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date d;
        try {
//            ParsePosition pp= new ParsePosition();

           d= sdf.parse(dateString);
        } catch (ParseException ex) {
            d= new Date();
        }
        log.info("The d Date: "+d);
        System.out.println(date);
        
    }
    
}
