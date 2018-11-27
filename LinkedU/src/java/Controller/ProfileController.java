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
import Model.Image;
import java.io.IOException;

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
    
     public String upload() throws IOException {
        //System.out.println(user.getUsername());
        if (file != null)
        {
            user= new User();
            user.setUsername("ejzumba");
            DAO dao = new ImageDAO();
            //System.out.println(user.getUsername());
            imageId = dao.create(file, user.getUsername());
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
           // image.setImage((StreamedContent)dao.getOne(""+imageId));
        }
        return "index.xhtml";
    }
     
    public void test(){
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
