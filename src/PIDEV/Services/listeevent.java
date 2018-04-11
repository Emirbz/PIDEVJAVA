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
import PIDEV.Entities.events;
import PIDEV.Utils.MyConnexion;

/**
 *
 * @author dahem
 */
public class listeevent {

    public ObservableList<events> ListeEvent() {
        ObservableList<events> myList = FXCollections.observableArrayList();
        Connection cn = MyConnexion.getInstance().getConnection();
        myList.clear();
        try {
            String requete = "SELECT * FROM `events` WHERE dateEvenement > SYSDATE() ";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                
                events etab = new events();
                etab.setId(rs.getInt("id"));
                etab.setName(rs.getString("name"));
                etab.setDescription(rs.getString("Description"));
                etab.setType(rs.getString("type"));
                etab.setAdressemail(rs.getString("adressemail"));
                etab.setNumTel(rs.getInt("numTel"));
                etab.setDevis_name(rs.getString("devis_name"));
                etab.setImage(rs.getString("image"));
                etab.setImage1(rs.getString("image1"));
                etab.setImage2(rs.getString("image2"));
                etab.setAdressefacebook(rs.getString("adressefacebook"));
                etab.setAdressetwitter(rs.getString("adressetwitter"));
                etab.setAdresseinstagram(rs.getString("adresseinstagram"));
                etab.setAdresseyoutube(rs.getString("adresseyoutube"));
                etab.setNbrplace(rs.getInt("nbrplace"));
                etab.setNbrparticipant(rs.getInt("nbrparticipant"));
                etab.setNbrplacerestant(rs.getInt("nbrplacerestant"));
                etab.setAdresse(rs.getString("adresse"));
                etab.setDateEvenement(rs.getDate("dateEvenement"));
                etab.setParking(rs.getBoolean("parking"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setCartebancaire(rs.getBoolean("Cartebancaire"));
                etab.setEspaceEenfant(rs.getBoolean("EspaceEenfant"));
                etab.setEspacefamilial(rs.getBoolean("Espacefamilial"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setAscenseur(rs.getBoolean("ascenseur"));
                



/////
                myList.add(etab);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    
}
