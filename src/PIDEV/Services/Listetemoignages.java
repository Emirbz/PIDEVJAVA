/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import PIDEV.Utils.MyConnexion;
import PIDEV.Entities.User;
import PIDEV.Entities.temoignages;

/**
 *
 * @author dahem
 */
public class Listetemoignages {

    Connection cn = MyConnexion.getInstance().getConnection();

    public ObservableList<temoignages> Listetemoignages() throws SQLException {
        ObservableList<temoignages> myList = FXCollections.observableArrayList();
        try {
            myList.clear();
            String requete = "SELECT * FROM `temoignages` ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                temoignages tem = new temoignages();
                tem.setId(rs.getInt("id"));
                tem.setName(rs.getString("name"));
                tem.setTitre(rs.getString("titre"));
                tem.setDescription(rs.getString("description"));
                tem.setDatetemoignage(rs.getDate("Datetemoignage"));
                String req = "SELECT * FROM `PIDEV`.`user` WHERE `id` = ' " + rs.getInt("iduser") + " ' ";
                Statement ste = cn.createStatement();
                ResultSet rss = ste.executeQuery(req);
                User u = new User();
                while (rss.next()) {
                    u.setDevis_name(rss.getString("devis_name"));
                    u.setDate(rss.getDate("date"));
                    u.setRole(rss.getString("role"));
                    u.setPhone(rss.getString("phone"));
                    u.setUsername(rss.getString("username"));
                    u.setName(rss.getString("name"));
                    u.setSurname(rss.getString("surname"));
                    u.setEmail(rss.getString("email"));

                }
                tem.setIduser(u);
                myList.add(tem);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

}
