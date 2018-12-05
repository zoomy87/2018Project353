/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//import javax.enterprise.context.SessionScoped;
import Model.TextSender;
import java.io.Serializable;
import java.util.List;
//import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author billylim
 */
//@Named(value = "sMSController")
@ManagedBean
@SessionScoped
public class SMSController implements Serializable {

    private java.util.List<java.lang.String> cellPhoneCarriers;
    private String cellPhoneCarrierChosen;
    private String cellPhoneNumber;
    private String phone;

    /**
     * Creates a new instance of SMSController
     */
    public SMSController() {
    }

    public List<String> getCellPhoneCarriers() {
        cellPhoneCarriers = TextSender.getCarriers();
        return cellPhoneCarriers;
    }

    public void setCellPhoneCarriers(List<String> cellPhoneCarriers) {
        this.cellPhoneCarriers = cellPhoneCarriers;
    }

    /**
     * @return the cellPhoneNumber
     */
    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    /**
     * @param cellPhoneNumber the cellPhoneNumber to set
     */
    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    /**
     * @return the cellPhoneCarrierChosen
     */
    public String getCellPhoneCarrierChosen() {
        return cellPhoneCarrierChosen;
    }

    /**
     * @param cellPhoneCarrierChosen the cellPhoneCarrierChosen to set
     */
    public void setCellPhoneCarrierChosen(String cellPhoneCarrierChosen) {
        this.cellPhoneCarrierChosen = cellPhoneCarrierChosen;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void buttonAction(ActionEvent actionEvent) {
        
        // The following converts phone in (ddd) ddd-dddd format to dddddddddd.
        String phoneDigitsOnly = "";
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (Character.isDigit(c))
                phoneDigitsOnly += c;
        }
        int status = TextSender.sendSMS(cellPhoneCarrierChosen, phoneDigitsOnly, "Hello");
        if (status != -1) 
            addMessage("Text Message Sent!");
        else
            addMessage("Error Sending Text Message!");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);

    }
}
