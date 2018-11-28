/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import Model.User;

import DAO.DAO;
import DAO.ImageDAO;
import DAO.UniDAO;
import Model.Image;
import java.io.IOException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ericz
 */
@ManagedBean
@SessionScoped
public class ProfileController {

    private UploadedFile file;
    private Image image;
    private int imageId;
    private User user;
    private final LoginController loginSession;
    private final FacesContext facesContext;
    private HttpSession session;

    public String upload() throws IOException {
        //System.out.println(user.getUsername());
        if (file != null) {
            user = new User();
            user.setUsername("test");
            DAO dao = new ImageDAO();
            //System.out.println(user.getUsername());
            imageId = dao.create(file, user.getUsername());
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            // image.setImage((StreamedContent)dao.getOne(""+imageId));
        }
        return "index.xhtml";
    }
    
    public ProfileController(){
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        loginSession = (LoginController) session.getAttribute("loginController");
        user = loginSession.getModel();
       //System.out.println("MediaController created");
    }
    
    

    public String universityReturn(String uniId) {
        String src;
        String school;
        DAO uniDB = new UniDAO();
        src = "https://www.google.com/maps/embed/v1/place?key=AIzaSyBfcRh_4s3ZdcGnTdhGEWnSjyghyoh7vc0&q=" + uniDB.getOne(uniId);
        return src;
    }

    public void test() {
        DAO dao = new ImageDAO();
        dao.create(new Object(), "hello");
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getImage() {
        return image.getImage();
    }

    public void setImage(StreamedContent image) {
        this.image.setImage(image);
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
