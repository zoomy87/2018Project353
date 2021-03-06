/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Profile;
import Model.User;
import java.util.ArrayList;

/**
 *
 * @author ericz
 */
public interface DAO {
    public int create(Object obj, String username);
    public int update(Object obj, String username);
    public Object getOne(Object id);
    public User getOneUsername(Object searchUsername);
    public Object getProfileUser(Object id);
    public Profile getProfileUni(Object username);
    public ArrayList getAll(String username);
    public void delete(Object obj, String username);
    public ArrayList searchById(String id);
    
    
}
