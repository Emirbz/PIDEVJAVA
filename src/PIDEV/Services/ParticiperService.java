/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import PIDEV.Utils.MyConnexion;
import PIDEV.Entities.events;

/**
 *
 * @author dahem
 */
public class ParticiperService {
        Connection cn = MyConnexion.getInstance().getConnection();
    MyConnexion cnx = MyConnexion.getInstance();
    
    public void participerr(int id_event , int id_user , java.sql.Date date){
         try {
         
        
            String requete
                    = "INSERT INTO participation (event_id, user_id, created_date) VALUES (?,?,?)";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setInt(1, id_event );
            st.setInt(2, id_user );
            st.setDate(3,  (java.sql.Date) date);
          
            st.executeUpdate();
            System.out.println("part ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    
    
    
    public boolean getParticip(int id)
    {
        boolean test = false;
        List<Integer> l=new ArrayList<Integer>();
        try {
            PreparedStatement myStm = cn.prepareStatement("SELECT * from participation where event_id= ?");
            myStm.setInt(1, id);
            ResultSet myRes = myStm.executeQuery();
            while(myRes.next())
            {
                l.add(myRes.getInt("user_id"));
           
            }
            if(l.size()>0){
                test = true;
            }
            else{
                test = false;
            }
            return test;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return test;
    }
}
