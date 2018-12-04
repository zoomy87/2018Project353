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
import DAO.UserDAO;
import Model.Image;
import Model.Profile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
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
    private Image vidUrl;
    private int imageId;
    private final LoginController loginSession;
    private final FacesContext facesContext;
    private final HttpSession session;
    User user;
    Profile DAOProfile;
    UserDAO DAOImpl;

    public void retrieveProfile() {
        DAOImpl = new UserDAO();
        DAOProfile = DAOImpl.getProfileUser(user.getUsername());
    }

    public ProfileController() {
        image = new Image();
        vidUrl = new Image();
        user = new User();
        DAOProfile = new Profile();
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        loginSession = (LoginController) session.getAttribute("loginController");
        user = loginSession.getDAOUser();
    }

    public String upload() throws IOException {

        if (file != null) {
            this.image = new Image();
            DAO dao = new ImageDAO();
            imageId = dao.create(file, user.getUsername());
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.image = (Image) dao.getOne("" + imageId);

            return "display.xhtml";
        } else {
            FacesMessage message = new FacesMessage("Unsuccesful", "File not uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        }

    }

    public String universityReturn(String uniId) {
        String src;
        String school;
        DAO uniDB = new UniDAO();
        src = "https://www.google.com/maps/embed/v1/place?key=AIzaSyBfcRh_4s3ZdcGnTdhGEWnSjyghyoh7vc0&q=" + uniDB.getOne(uniId);
        return src;
    }

    public String test() {
        DAO dao = new ImageDAO();
        Image i = (Image) dao.getOne("" + 4);
        image = i;
        System.out.println("" + i.getImageId());
        return "display.xhtml";
    }

    public Profile getDAOProfile() {
        return DAOProfile;
    }

    public void setDAOProfile(Profile DAOProfile) {
        this.DAOProfile = DAOProfile;
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

    public Image getvidUrl() {
        return vidUrl;
    }

    public void setVidUrl(Image vidUrl) {
        this.vidUrl = vidUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String goToProfile() {
        return "profile.xhtml?faces-redirect=true";
    }
}
