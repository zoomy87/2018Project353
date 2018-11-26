/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author ericz
 */
public interface DAO {
    public int create(Object obj);
    public int update(Object obj);
    public Object getOne(String id);
    public ArrayList getAll(String query, String id);
    
}
