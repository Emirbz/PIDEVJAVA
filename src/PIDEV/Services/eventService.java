/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.events;
import PIDEV.Utils.MyConnexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author dahem
 */
public class eventService {

    Connection cn = MyConnexion.getInstance().getConnection();
    MyConnexion cnx = MyConnexion.getInstance();

    public void addevent(events event) throws IOException {
        try {

            java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
            //  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          
            String requete
                    = "INSERT INTO events (name, description, dateEvenement, devis_name, updated_at, nbrplace, nbrparticipant, adresse, adressemail, numTel, informationsociale, adressefacebook, adressetwitter, adresseyoutube, adresseinstagram, parking, fumer, wifi, EspaceEenfant, ascenseur, Cartebancaire, Espacefamilial,image,image1,image2,type,nbrplacerestant,iduser) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, event.getName());
            st.setString(2, event.getDescription());
            st.setDate(3, event.getDateEvenement());
            st.setString(4, event.getDevis_name());
            st.setDate(5, event.getUpadted_at());
            st.setInt(6, event.getNbrplace());
            st.setInt(7, event.getNbrparticipant());
            st.setString(8, event.getAdresse());
            st.setString(9, event.getAdressemail());
            st.setInt(10, event.getNumTel());
            st.setString(11, event.getInformationsociale());
            st.setString(12, event.getAdressefacebook());
            st.setString(13, event.getAdressetwitter());
            st.setString(14, event.getAdresseyoutube());
            st.setString(15, event.getAdresseinstagram());
            st.setBoolean(16, event.getParking());
            st.setBoolean(17, event.getFumer());
            st.setBoolean(18, event.getWifi());
            st.setBoolean(19, event.getEspaceEenfant());
            st.setBoolean(20, event.getAscenseur());
            st.setBoolean(21, event.getCartebancaire());
            st.setBoolean(22, event.getEspacefamilial());
            st.setString(23, event.getImage());
            st.setString(24, event.getImage1());
            st.setString(25, event.getImage2());
            st.setString(26, event.getType());
            st.setInt(27, event.getNbrplacerestant());
            st.setInt(28, event.getIduser().getId());
            st.executeUpdate();
            System.out.println("event ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public events getEvent(String nom) {
        try {
            PreparedStatement myStm = cn.prepareStatement("SELECT * from events where name= ?");
            myStm.setString(1, nom);
            ResultSet myRes = myStm.executeQuery();
            events e = new events();
            while (myRes.next()) {
                e.setName(myRes.getString("name"));
                e.setAdresse(myRes.getString("adresse"));
                e.setNumTel(myRes.getInt("numTel"));
                e.setNbrplacerestant(myRes.getInt("nbrplacerestant"));
                e.setAdressefacebook(myRes.getString("adressefacebook"));
                e.setAdresseinstagram(myRes.getString("adresseinstagram"));
                e.setAdressetwitter(myRes.getString("adressetwitter"));
                e.setAdresseyoutube(myRes.getString("adresseyoutube"));
                e.setType(myRes.getString("type"));
                e.setParking(myRes.getBoolean("parking"));
                e.setFumer(myRes.getBoolean("fumer"));
                e.setWifi(myRes.getBoolean("wifi"));

                if (myRes.getInt("EspaceEenfant") == 0) {
                    e.setEspaceEenfant(true);
                } else {
                    e.setEspaceEenfant(false);
                }
                if (myRes.getInt("Espacefamilial") == 0) {
                    e.setEspacefamilial(true);
                } else {
                    e.setEspacefamilial(false);
                }
                if (myRes.getInt("ascenseur") == 0) {
                    e.setAscenseur(true);
                } else {
                    e.setAscenseur(false);
                }
                if (myRes.getInt("Cartebancaire") == 0) {
                    e.setCartebancaire(true);
                } else {
                    e.setCartebancaire(false);
                }

                e.setNbrplace(myRes.getInt("nbrplace"));
                e.setDescription(myRes.getString("Description"));
                e.setAdressemail(myRes.getString("adressemail"));
                e.setDateEvenement(myRes.getDate("dateEvenement"));
                e.setDevis_name(myRes.getString("image"));
                e.setImage(myRes.getString("image1"));
                e.setImage1(myRes.getString("image2"));
                e.setImage2(myRes.getString("image3"));

                break;
            }
            System.out.println(e);
            return e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
