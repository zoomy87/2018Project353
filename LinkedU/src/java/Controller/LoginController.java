/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import Model.User;
import DAO.DAO;
import DAO.UserDAO;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author ejzumba
 */
//@Named(value = "loginController")
@SessionScoped
@ManagedBean
public class LoginController {

    User model;
    User DAOUser;
    
//    @ManagedProperty("userDAO")
    UserDAO DAOImpl;
    Map<String, Integer> loginTries = new HashMap<>();

    ;

    public User getDAOUser() {
        return DAOUser;
    }

    public void setDAOUser(User DAOUser) {
        this.DAOUser = DAOUser;
    }

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        model = new User();
    }

    public User getModel() {
        return model;
    }

    public void setModel(User model) {
        this.model = model;
    }

    public String authenticate() {
        DAOImpl = new UserDAO();
        String pass = DAOImpl.getPass(model.getEmail());
        String retVal = "";
        int x = 1;

        if (pass.equals(model.getPassword())) {
            retVal = "LoginGood.xhtml";
        }
        if (!loginTries.containsKey(model.getEmail())) {
            loginTries.put(model.getEmail(), x);
            FacesContext.getCurrentInstance().addMessage("login:inputPassword", new FacesMessage("Email or Password do not match"));
        } else {
            x = loginTries.get(model.getEmail());
            loginTries.put(model.getEmail(), ++x);
            FacesContext.getCurrentInstance().addMessage("login:inputPassword", new FacesMessage("Email or Password do not match"));
            if (x >= 3) {
                retVal = "LoginBad.xhtml";
            }
        }
        return retVal;
    }

    public void retriveUser() {
        DAOImpl = new UserDAO();
        DAOUser = DAOImpl.getOne(model.getEmail());
    }

    public String createUser() {
        String response = "";
        DAOImpl = new UserDAO();
        boolean isEmail = DAOImpl.checkUserEmail(model);
        boolean isUserID = DAOImpl.checkUserID(model);
        boolean isPasswordMatch = model.getPassword().equals(model.getConfirmPassword());
        if (isEmail) {
            FacesContext.getCurrentInstance().addMessage("signUp:email", new FacesMessage("Email is already used"));
        }
        if (isUserID) {
            FacesContext.getCurrentInstance().addMessage("signUp:userID", new FacesMessage("User ID is already taken"));
        }

        if (!isPasswordMatch) {
            FacesContext.getCurrentInstance().addMessage("signUp:password", new FacesMessage("Passwords do not match"));
        }

        if (!isUserID && !isEmail && isPasswordMatch) {
            DAOImpl.create(model, model.getUsername());
            response = "echo.xhtml";
            System.out.println(model.getEmail());
//            model.sendEmail();
        }

        return response;
//         
    }

    public String response() {
        String response;
        DAOImpl = new UserDAO();

        if (!DAOImpl.checkUserEmail(model)) {
            DAOImpl.create(model, model.getUsername());
        } else {
            FacesContext.getCurrentInstance().addMessage("signUp:email", new FacesMessage("Email already exists in database"));
        }

        return "";
    }
    
    public void updateUser(){
        String response = "";
        DAOImpl = new UserDAO();
        boolean isEmail = DAOImpl.checkUserEmail(model);
        boolean isUserID = DAOImpl.checkUserID(model);
        boolean isPasswordMatch = model.getPassword().equals(model.getConfirmPassword());
        
        if(model.getActiveEmail().equals(model.getEmail())){
            isEmail= false;
        }
        if (isEmail) {
            FacesContext.getCurrentInstance().addMessage("update:email", new FacesMessage("Email is already used"));
        }
        
        if(model.getActiveId().equals(model.getUsername())){
            isUserID= false;
        }    
        if (isUserID) {
            FacesContext.getCurrentInstance().addMessage("update:userID", new FacesMessage("Username is already taken"));
        }

        if (!isPasswordMatch) {
            FacesContext.getCurrentInstance().addMessage("update:password", new FacesMessage("Passwords do not match"));
        }

        if (!isUserID && !isEmail && isPasswordMatch) {
            
        }
    }

}
