/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import PIDEV.Entities.events;
import PIDEV.Utils.MyConnexion;

/**
 *
 * @author dahem
 */
public class editevent {

    public void modifierevenement(events etab, int item) throws SQLException, IOException {
        Connection cn = MyConnexion.getInstance().getConnection();
        String req = "UPDATE events SET "
                + "name = ?, adresse = ?,"
                + "description = ?,"
                + "dateEvenement = ?,"
                + "nbrplace = ?,"
                + "numTel = ?,"
                +"adressemail = ?,"
                + "adressefacebook = ?,"
                + "adressetwitter = ?,"
                + "parking = ?,"
                + "image = ?,"
                + "image1 = ?,"
                + "image2 = ?,"
                + "devis_name = ?,"
                + "type = ?,"
                + "fumer = ?,"
                + "wifi = ?,"
                + " EspaceEenfant = ?,"
                + "ascenseur = ?,"
                + "Cartebancaire = ?,"
                + "Espacefamilial = ? where id=?";

        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, etab.getName());
        pre.setString(2, etab.getAdresse());
        pre.setString(3, etab.getDescription());
        pre.setDate(4, etab.getDateEvenement());
        pre.setInt(5, etab.getNbrplace());
        pre.setInt(6, etab.getNumTel());
        pre.setString(7, etab.getAdressefacebook());
        

        pre.setString(8, etab.getAdressetwitter());

        pre.setBoolean(9, etab.getParking());
        pre.setBoolean(10, etab.getFumer());
        pre.setBoolean(11, etab.getAscenseur());
        pre.setBoolean(12, etab.getCartebancaire());
        pre.setBoolean(13, etab.getEspaceEenfant());
        pre.setBoolean(14, etab.getEspacefamilial());
        pre.setBoolean(15, etab.getWifi());
        pre.setInt(16, item);
        pre.setString(17, etab.getDevis_name());
        pre.setString(18, etab.getImage());
        pre.setString(19, etab.getImage1());
        pre.setString(20, etab.getImage2());
        pre.setString(21, etab.getType());
        pre.setString(22, etab.getAdressemail());

        pre.executeUpdate();
        System.out.println("event modifié");

    }

    public void increment(events e) throws SQLException {
        Connection cn = MyConnexion.getInstance().getConnection();
        String req = "UPDATE events SET nbrparticipant = ?,nbrplacerestant=? where id=?";
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setInt(1, e.getNbrparticipant());
        pre.setInt(2, e.getNbrplacerestant());
        pre.setInt(3, e.getId());
        pre.executeUpdate();
    }
}
