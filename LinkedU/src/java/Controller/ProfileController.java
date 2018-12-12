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
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedProperty;
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
    private int imageId;
    private final LoginController loginSession;
    private final FacesContext facesContext;
    private final HttpSession session;
    User user;
    User searchUser;
    Profile DAOProfile;
    Profile uniSearchProfile;
    Profile userSearchProfile;
    UserDAO DAOImpl;
    
    public void retrieveProfile(){
        DAOImpl = new UserDAO();
        DAOProfile = DAOImpl.getProfileUser(user.getUsername());
    }
    
    public String retrieveProfile(String searchUsername){
        UserDAO searchUserDao = new UserDAO();
        searchUser = searchUserDao.getOneUsername(searchUsername);
        
        if(searchUser.getUserType().toLowerCase().equals("university")){
            uniSearchProfile = new Profile();
            uniSearchProfile = DAOImpl.getProfileUni(searchUsername);
            
            return "uniSearchProfile.xhtml?faces-redirect=true";
        }
        
        if(searchUser.getUserType().toLowerCase().equals("student")){
            userSearchProfile = new Profile();
            userSearchProfile = DAOImpl.getProfileUser(searchUsername);
            return "userSearchProfile.xhtml?faces-redirect=true";
        }
        return "searchError.xhtml?faces-redirect=true";
    }
    
    public void gotoProfile(String loggedInUsername){
        UserDAO searchUserDao = new UserDAO();
        searchUser = searchUserDao.getOneUsername(loggedInUsername);
        
        if(searchUser.getUserType().toLowerCase().equals("university")){
            uniSearchProfile = new Profile();
            uniSearchProfile = DAOImpl.getProfileUni(loggedInUsername);
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("uniSearchProfile.xhtmlfaces-redirect=true");
        }
        
        if(searchUser.getUserType().toLowerCase().equals("student")){
            userSearchProfile = DAOImpl.getProfileUser(loggedInUsername);
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("userSearchProfile.xhtmlfaces-redirect=true");
        }
    }
    
    public String uniProfile(){
        
        if(DAOProfile.getUserType().toLowerCase().equals("university")){
            return "uniSearchProfile.xhtml?faces-redirect=true";
        }
        
        return "";
    }
    
    public ProfileController(){
//        DAOImpl = new UserDAO();
        image= new Image();
        user = new User();
        DAOProfile = new Profile();
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);
        loginSession = (LoginController) session.getAttribute("loginController");
        user = loginSession.getDAOUser();
    }
    
    public String upload() throws IOException {
        
        if (file != null)
        {
            this.image= new Image();
            DAO dao = new ImageDAO();
            imageId = dao.create(file, user.getUsername());
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.image= (Image) dao.getOne(""+imageId);
        
            return "display.xhtml";
        }else{
            FacesMessage message = new FacesMessage("Unsuccesful", "File not uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        }
            
    }

    public String universityReturn(String username) {
        String src;
        String school;
        DAO uniDB = new UniDAO();
        src = "https://www.google.com/maps/embed/v1/place?key=AIzaSyBfcRh_4s3ZdcGnTdhGEWnSjyghyoh7vc0&q=" + username;
        return src;
    }
    
    public String universityVideoReturn(String uniId) {
        String src;
        String school;
        DAO uniDB = new UniDAO();
        src = "https://www.youtube.com/embed?listType=search&list=" + uniId + "+tour";
        return src;
    }

    public String test() {
        DAO dao = new ImageDAO();
        Image i= (Image) dao.getOne(""+4);
        image= i;
        System.out.println(""+i.getImageId());
        return "display.xhtml";
    }

    public User getSearchUser() {
        return searchUser;
    }

    public void setSearchUser(User searchUser) {
        this.searchUser = searchUser;
    }

    public Profile getUniSearchProfile() {
        return uniSearchProfile;
    }

    public void setUniSearchProfile(Profile uniSearchProfile) {
        this.uniSearchProfile = uniSearchProfile;
    }

    public Profile getUserSearchProfile() {
        return userSearchProfile;
    }

    public void setUserSearchProfile(Profile userSearchProfile) {
        this.userSearchProfile = userSearchProfile;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
//    public String goToProfile(){
//        return "profile.xhtml?faces-redirect=true";
//    }
}
