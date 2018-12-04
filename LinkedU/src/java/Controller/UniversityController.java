/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentDAO;
import DAO.DAO;
import Model.Appointment;
import Model.Image;
import Model.University;
import Model.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.jboss.logging.Logger;

/**
 *
 * @author ericz
 */
@ManagedBean
@SessionScoped
public class UniversityController {
    private Date date;
   // private String dateString;
    private static final Logger log= Logger.getLogger(UniversityController.class);
    private Appointment app;
    private User user;
    private University uni;
    FacesContext facesContext;
    HttpSession session;
    LoginController logControl;
            
    
    public UniversityController(){
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        logControl = (LoginController) session.getAttribute("loginController");
        user = logControl.getModel();
        
    }

//    public String getDateString() {
//        return dateString;
//    }
//
//    public void setDateString(String dateString) {
//        this.dateString = dateString;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        app.setDate(date.toString());
    }

    public Appointment getApp() {
        return app;
    }

    public void setApp(Appointment app) {
        this.app = app;
    }
    
    public void makeAppointment() throws ParseException{
        SimpleDateFormat sdf= new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        AppointmentDAO d= new AppointmentDAO();
        d.create(app, UniversityName);
        
        log.info("The d Date: "+d);
        System.out.println(date);
        
    }
    
}
