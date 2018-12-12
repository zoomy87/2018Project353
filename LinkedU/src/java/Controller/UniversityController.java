/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentDAO;
import DAO.StudentDAO;
import DAO.UniDAO;
import DAO.UserDAO;
import Model.Appointment;
import Model.Student;
import Model.University;
import Model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    private User user;
    private University uni;
    FacesContext facesContext;
    HttpSession session;
    LoginController logControl;
    String dateString;
            
    
    public UniversityController(){
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        logControl = (LoginController) session.getAttribute("loginController");
        logControl.retriveUser();
        user= logControl.DAOUser;
        dateString= " ";
        
       
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public University getUni() {
        return uni;
    }

    public void setUni(University uni) {
        this.uni = uni;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
    
    
}
