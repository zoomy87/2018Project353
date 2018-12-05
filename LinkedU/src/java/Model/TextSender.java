/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ericz
 */
public class TextSender {

    public static java.util.List<java.lang.String> getCarriers() {
        edu.ilstu.it.TextSenderService service = new edu.ilstu.it.TextSenderService();
        edu.ilstu.it.TextSender port = service.getTextSenderPort();
        return port.getCarriers();
    }

    public static int sendSMS(java.lang.String carrier, java.lang.String telephoneNum, java.lang.String text) {
        edu.ilstu.it.TextSenderService service = new edu.ilstu.it.TextSenderService();
        edu.ilstu.it.TextSender port = service.getTextSenderPort();
        return port.sendSMS(carrier, telephoneNum, text);
    }
    
}
