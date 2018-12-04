/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import javax.mail.internet.MimeMessage;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ericz
 */
public class UniversityControllerTest {
    UniversityController uc;
    
    
    public UniversityControllerTest() {
    }
    
    @Test
    public void testEmail(){
        MimeMessage mm= uc.appointmentEmailMessage();
        User u= new User();
        u.email(mm);
        
       assertTrue(mm instanceof MimeMessage);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        uc= new UniversityController();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
