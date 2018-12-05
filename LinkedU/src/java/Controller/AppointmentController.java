/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
import org.primefaces.event.SelectEvent;

/**
 *
 * @author ericz
 */
@ManagedBean
@SessionScoped
public class AppointmentController {

    private String response;
    private User apptModel;
    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public AppointmentController() {
        apptModel = new User();
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
        sendEmail();
        return "appointmentSuccess";
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

    public void sendEmail() {

        // Recipient's email ID needs to be mentioned.
        String to = apptModel.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "mlisows@ilstu.edu";

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
                return new PasswordAuthentication("mlisows@ilstu.edu", "DRoseis#04!");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Thank you scheduling an appointment!");

            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();

            String file = "I:/NetBeansApps/Lab3WebApp/web/resources/images/generic-logo.jpg";

            DataSource fds = new FileDataSource(file);

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setFileName(fds.getName());

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent(getResponse(), "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            // put everything together
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            //System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
