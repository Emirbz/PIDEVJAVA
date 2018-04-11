/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Catdeal;
import PIDEV.Entities.Deal;
import PIDEV.Entities.User;
import PIDEV.Utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Skan
 */
public class ListDealsService {

    Connection cn = MyConnexion.getInstance().getConnection();

    public ObservableList<Deal> ListerDeals() {
        ObservableList<Deal> myList = FXCollections.observableArrayList();
        try {
            myList.clear();
            String requete = "SELECT * FROM Deal";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Deal d = new Deal();
                d.setNom(rs.getString("nom"));
                d.setDescription(rs.getString("description"));
                d.setNewprix(rs.getDouble("newprix"));
                d.setOldprix(rs.getDouble("oldprix"));
                d.setPromotion(rs.getDouble("promotion"));
                d.setDatecreation(rs.getDate("datecreation"));
                d.setDatefin(rs.getDate("datefin"));
                d.setPlacesdispo(rs.getInt("placesdispo"));
                d.setRating(rs.getInt("rating"));
                d.setId(rs.getInt("id"));
                d.setRegion(rs.getString("region"));
                d.setAdresse(rs.getString("adresse"));
                d.setDevisName(rs.getString("devis_name"));
                String re = "SELECT * FROM catdeal WHERE id='" + rs.getInt("cat") + "'";
                PreparedStatement pst = cn.prepareStatement(re);
                ResultSet rss = pst.executeQuery(re);
                Catdeal cd = new Catdeal();
                while (rss.next()) {
                    cd.setId(rss.getInt("id"));
                    cd.setNom(rss.getString("nom"));
                    cd.setDevisName(rss.getString("devis_name"));
                    //  cd.setUpdatedAt((Date)rss.getDate(4));   
                }
                d.setCat(cd);
                String req = "SELECT * FROM user WHERE id='" + rs.getInt("iduser") + "'";
                PreparedStatement pest = cn.prepareStatement(req);
                ResultSet ress = pest.executeQuery(req);
                User u = new User();
                while (ress.next()) {
                    u.setId(ress.getInt("id"));
                    u.setName(ress.getString("name"));
                    u.setPhone(ress.getString("phone"));
                    u.setSurname(ress.getString("surname"));
                    u.setRole(ress.getString("role"));
                    u.setEmail(ress.getString("email"));
                    u.setUsername(ress.getString("username"));
                    u.setDevis_name(ress.getString("devis_name"));
                    u.setDate(ress.getDate("date"));
                }
                d.setIduser(u);
                myList.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<String> getDealsNames() {
        List<String> myList = new ArrayList<>();
        try {
            myList.clear();
            String requete = "SELECT nom FROM Deal";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                myList.add(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
}
