/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEVTOUTOU.Services;

import PIDEVTOUTOU.Entities.Categorie;
import PIDEVTOUTOU.Entities.SousCategorie;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import PIDEVTOUTOU.Entities.etablissement;
import PIDEVTOUTOU.Utils.Myconnexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Emir
 */
public class AddEtablissement {

    Connection cn = Myconnexion.getInstance().getConnection();
    Myconnexion cnx = Myconnexion.getInstance();

    public void AddRestaurant(etablissement E) throws IOException {
        try {
            String requete
                    = "INSERT INTO etablissement ("
                    + "name,"
                    + "address,"
                    + "description,"
                    + "phone,"
                    + "email,"
                    + "website,"
                    + "facebook,"
                    + "categorie,"
                    + "lundisamedio,"
                    + "lundisamedif,"
                    + "dimancheo,"
                    + "dimanchef,"
                    + " parking,"
                    + "cartecredit,"
                    + "chaiseroulante,"
                    + "fumer,"
                    + "terasse,"
                    + "wifi,"
                    + "reservations,"
                    + "place,"
                    + "livraison,"
                    + "climatisation,"
                    + "animaux,"
                    + "alcool,"
                    + "souscat,img1,img2,img3,devis_name,latitude,longitude)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, E.getName());
            st.setString(2, E.getAddress());
            st.setString(3, E.getDescription());
            st.setString(4, E.getPhone());
            st.setString(5, E.getEmail());
            st.setString(6, E.getWebsite());
            st.setString(7, E.getFacebook());
            st.setString(8, E.getCategorie());
            st.setString(9, E.getLundisamedio());
            st.setString(10, E.getLundisamedif());
            st.setString(11, E.getDimancheo());
            st.setString(12, E.getDimanchef());
            st.setBoolean(13, E.isParking());
            st.setBoolean(14, E.isCartecredit());
            st.setBoolean(15, E.isChaiseroulante());
            st.setBoolean(16, E.isFumer());
            st.setBoolean(17, E.isTerasse());
            st.setBoolean(18, E.isWifi());
            st.setBoolean(19, E.isReservations());
            st.setInt(20, E.getPlace());
            st.setBoolean(21, E.isLivraison());
            st.setBoolean(22, E.isClimatisation());
            st.setBoolean(23, E.isAnimaux());
            st.setBoolean(24, E.isAlcool());
            st.setInt(25, E.getSouscat().getId());
            st.setString(26, E.getImg1());
            st.setString(27, E.getImg2());
            st.setString(28, E.getImg3());
            st.setString(29, E.getDevis_name());
            st.setDouble(30, E.getLatitude());
            st.setDouble(31, E.getLongitude());
            st.executeUpdate();
            
//            Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
//        alertSucc.setTitle("Succés");
//        alertSucc.setContentText("Restaurant Ajoutée");
//        alertSucc.setHeaderText(null);
//        alertSucc.show();
System.out.println("tyuio");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }
public ArrayList<String> listsouscat() throws SQLException
{
    ArrayList<String> list = new ArrayList<>();
    String requete = "SELECT nom from sous__categorie where idcategorie=4";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next())
            {
                list.add(rs.getString(1));
            }
 return list;  

 
}
public SousCategorie getsouscat(String scvalue) throws SQLException
{
    String requete = "SELECT * from sous__categorie where nom='" + scvalue + "'";
            PreparedStatement st = cn.prepareStatement(requete);

            ResultSet rs = st.executeQuery(requete);

            SousCategorie sc = new SousCategorie();
            while (rs.next()) {
                sc.setId(rs.getInt(1));
                int i = rs.getInt(2);
                String req = "SELECT * from Categorie where id='" + i + "'";
                PreparedStatement st1 = cn.prepareStatement(req);
                ResultSet rs1 = st1.executeQuery(req);
                Categorie c = new Categorie();
                while (rs1.next()) {
                    c.setId(1);
                    c.setNom(rs.getString(2));
                    c.setDescription(rs.getString(3));
                }
                sc.setIdcategorie(c);
                sc.setNom(rs.getString(3)); // oubien 3 

            }
            return sc;


}
}

