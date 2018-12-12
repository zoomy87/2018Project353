/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AppointmentDAO;
import DAO.StudentDAO;
import DAO.UserDAO;
import Model.Appointment;
import Model.Student;
import Model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.jboss.logging.Logger;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author ericz
 */
@ManagedBean
@SessionScoped
public class AppointmentController {

    private Logger log= Logger.getLogger(AppointmentController.class);
    private String response;
    private String username;
    private Appointment app;
    private User apptModel;
    private Date date;
    private String dateString;
    private final SimpleDateFormat SDF= new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    
    @ManagedProperty(value="#{profileController}")
    private ProfileController pc;
    
    public AppointmentController(){
        app= new Appointment();
        apptModel = new User();
    
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public ProfileController getPc() {
        return pc;
    }

    public void setPc(ProfileController pc) {
        this.pc = pc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Appointment getApp() {
        return app;
    }

    public void setApp(Appointment app) {
        this.app = app;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        dateString= date.toString();
        log.info(dateString);
        app.setDate(dateString);
        
    }

    public User getApptModel() {
        return apptModel;
    }

    public void setApptModel(User apptModel) {
        this.apptModel = apptModel;
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext;
        facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public String click() {
//        sendEmail();
        return "appointmentSuccess";
    }
    
    public void makeAppointment() throws ParseException{
        StudentDAO sd= new StudentDAO();
        UserDAO ud= new UserDAO();
        Student s= (Student) sd.getOne(username);
        app.setStudentId(s.getProfileId());
        app.setUniversityId(pc.getUniSearchProfile().getProfileId());
        AppointmentDAO d= new AppointmentDAO();
        d.create(app,"");
        User user= ud.getOneUsername(username);
        user.email(this.appointmentEmailMessage(user));
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Appointment was a success"));
        log.info("The dateString: "+ dateString);
        System.out.println(date.toString());
        
    }
    
    public MimeMessage appointmentEmailMessage(User user){
        // Recipient's email ID needs to be mentioned.
        String to = user.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "ejzumba@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "outlook.office365.com";

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
                return new PasswordAuthentication("ejzumba@ilstu.edu", "Vincent128706");
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
            
            
        } catch (MessagingException mex) {
            log.log(Logger.Level.FATAL, "Messaging Exception: "+mex.getMessage());
        }
        return message;
    }

    public String getResponse() {
        String resultStr = "";
        resultStr += "Hello " + apptModel.getfName() + " " + apptModel.getlName()
                + ", you have successfully scheduled an appointment." + "<br/>";
        resultStr += "This is a reminder for your appointment"
                + "<br/>" + "Your schedule time and date are: " + getDate() + "<br/>";
        resultStr += "Thank you for taking the time to register! We hope you have a great day!";

        response = resultStr;

        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

//    public void sendEmail() {
//
//        // Recipient's email ID needs to be mentioned.
//        String to = apptModel.getEmail();
//
//        // Sender's email ID needs to be mentioned
//        String from = "";
//
//        // Assuming you are sending email from this host
//        String host = "outlook.office365.com";
//
//        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.setProperty("mail.smtp.host", host);
//        properties.setProperty("mail.smtp.starttls.enable", "true");
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.port", "587");
//        // Get the default Session object.
//        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("", "");
//            }
//        });
//
//        try {
//            // Create a default MimeMessage object.
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(to));
//
//            // Set Subject: header field
//            message.setSubject("Thank you scheduling an appointment!");
//
//            MimeMultipart multipart = new MimeMultipart("related");
//            BodyPart messageBodyPart = new MimeBodyPart();
//
//            String file = "I:/NetBeansApps/Lab3WebApp/web/resources/images/generic-logo.jpg";
//
//            DataSource fds = new FileDataSource(file);
//
//            messageBodyPart.setDataHandler(new DataHandler(fds));
//            messageBodyPart.setFileName(fds.getName());
//
//            // add image to the multipart
//            multipart.addBodyPart(messageBodyPart);
//
//            messageBodyPart = new MimeBodyPart();
//
//            messageBodyPart.setContent(getResponse(), "text/html");
//            // add it
//            multipart.addBodyPart(messageBodyPart);
//
//            // put everything together
//            message.setContent(multipart);
//
//            // Send message
//            Transport.send(message);
//            //System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }
}
