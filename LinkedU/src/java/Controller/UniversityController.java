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
    private Appointment app;
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
        
        
        app= new Appointment();
        UniDAO da= new UniDAO();
        uni= da.getOne("Illinois State University");
       
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        dateString= date.toString();
        app.setDate(dateString);
        
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
    
    

    public Appointment getApp() {
        return app;
    }

    public void setApp(Appointment app) {
        this.app = app;
    }
    
    public void makeAppointment() throws ParseException{
        SimpleDateFormat sdf= new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        StudentDAO sd= new StudentDAO();
        Student s= (Student) sd.getOne(user.getUsername());
        app.setStudentId(s.getProfileId());
        app.setUniversityId(uni.getProfileId());
        AppointmentDAO d= new AppointmentDAO();
        d.create(app,"");
        user.email(this.appointmentEmailMessage());
        
        log.info("The d Date: "+d);
        System.out.println(date);
        
    }
    
    public MimeMessage appointmentEmailMessage(){
        // Recipient's email ID needs to be mentioned.
        String to = user.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "linkedu2018@gmail.com";

        // Assuming you are sending email from this host
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        
        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("linkedu2018@gmail.com", "notPassword");
            }
        });
        
        MimeMessage message = new MimeMessage(session);
        
        try {
            
            // Create a default MimeMessage object.
            

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Your appointment has been set");

            // Send the actual HTML message, as big as you like
            String content= "<p>Hello "+user.getfName()+" "+user.getlName()+",<br>"
                    + "Your appointment is scheduled for "+ dateString;
                    
            
            message.setContent( content,"text/html");

            // Send message
            
            log.info("Sent message successfully....");
        } catch (MessagingException mex) {
            log.log(Logger.Level.FATAL, "Messaging Exception: "+mex.getMessage());
        }
        return message;
    }
    
}
