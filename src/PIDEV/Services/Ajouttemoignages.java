/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Views.FirstFrame;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import PIDEV.Utils.MyConnexion;
import PIDEV.Entities.User;
import PIDEV.Entities.temoignages;

/**
 *
 * @author dahem
 */
public class Ajouttemoignages {

    Connection cn = MyConnexion.getInstance().getConnection();
    MyConnexion cnx = MyConnexion.getInstance();

    public void addtemoignage(temoignages temoignages) throws IOException, SQLException {
        try {
            java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
            User u=new User();
            u.setId(PIDEV.Views.FirstFrame.user.getId());
            String requete
                    = "INSERT INTO temoignages(name,description,titre,Datetemoignage,iduser) VALUES(?,?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, temoignages.getName());
            st.setString(2, temoignages.getDescription());
            st.setString(3, temoignages.getTitre());
            st.setDate(4, date_sql);
            st.setInt(5, u.getId());
            st.executeUpdate();
            System.out.println("témoignage ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public temoignages getTemoignage(String nom) throws SQLException {
        try {
            PreparedStatement myStm = cn.prepareStatement("SELECT * from temoignages where name= ?");
            myStm.setString(1, nom);
            ResultSet myRes = myStm.executeQuery();
            temoignages t = new temoignages();
            while (myRes.next()) {
                t.setName(myRes.getString("name"));
                t.setDescription(myRes.getString("description"));
                t.setTitre(myRes.getString("titre"));
                t.setDatetemoignage(myRes.getDate("datetemoignage"));
                break;
            }
            System.out.println(t);
            return t;
        } catch (Exception t) {
            t.printStackTrace();
        }
        return null;

    }

}
