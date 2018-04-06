/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEVTOUTOU.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import PIDEVTOUTOU.Entities.Categorie;
import PIDEVTOUTOU.Entities.SousCategorie;
import PIDEVTOUTOU.Entities.etablissement;
import PIDEVTOUTOU.Utils.Myconnexion;

/**
 *
 * @author Emir
 */
public class ListEtablissement {
    
    public ObservableList<etablissement> ListRestaurant() {
        ObservableList<etablissement> myList = FXCollections.observableArrayList();
        Connection cn = Myconnexion.getInstance().getConnection();
        try {
            String requete = "SELECT * from etablissement where categorie='Restaurant'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                etablissement etab = new etablissement();
                etab.setName(rs.getString("name"));
                etab.setAddress(rs.getString("address"));
                etab.setEmail(rs.getString("email"));
                etab.setPhone(rs.getString("phone"));
                etab.setLundisamedio(rs.getString("lundisamedio"));
                etab.setLundisamedif(rs.getString("lundisamedif"));
                etab.setDimancheo(rs.getString("dimancheo"));
                etab.setDimanchef(rs.getString("dimanchef"));
                etab.setId(rs.getInt("id"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setDescription(rs.getString("description"));
                etab.setWebsite(rs.getString("website"));
                etab.setFacebook(rs.getString("facebook"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setTotalqualite(rs.getDouble("totalqualite"));
                etab.setTotalservice(rs.getDouble("totalservice"));
                etab.setMoyqualite(rs.getDouble("moyqualite"));
                etab.setMoyservice(rs.getDouble("moyservice"));
                etab.setLatitude(rs.getDouble("latitude"));
                etab.setLongitude(rs.getDouble("longitude"));
                
                
                etab.setCategorie(rs.getString("categorie"));
                etab.setParking(rs.getBoolean("parking"));
                
                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setReservations(rs.getBoolean("reservations"));
                etab.setPlace(rs.getInt("place"));
                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setClimatisation(rs.getBoolean("climatisation"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                Categorie c = new Categorie(4, "Restaurant", "Restaurant");
                String requete2 = "SELECT * from sous__categorie where id='" + rs.getInt("souscat") + "'";
                PreparedStatement st2 = cn.prepareStatement(requete2);
                
                ResultSet rs2 = st2.executeQuery(requete2);
                
                SousCategorie sc = new SousCategorie();
                while (rs2.next()) {
                    sc.setId(rs2.getInt(1));
                    
                    sc.setIdcategorie(c);
                    sc.setNom(rs2.getString(3)); // oubien 3 

                }
                
                etab.setSouscat(sc);
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
}
