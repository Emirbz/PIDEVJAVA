/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import PIDEV.Entities.Etablissement;
import PIDEV.Utils.MyConnexion;

/**
 *
 * @author Emir
 */
public class EditEtablissement {
    public void ModifierResto(Etablissement etab,int item) throws SQLException {
        Connection cn = MyConnexion.getInstance().getConnection();
        String req = "UPDATE etablissement SET "
                  + "name = ?,"
                    + "address = ?,"
                    + "description = ?,"
                    + "phone = ?,"
                    + "email = ?,"
                    + "website = ?,"
                    + "facebook = ?,"
                    + "lundisamedio = ? ,"
                    + "lundisamedif = ?,"
                    + "dimancheo = ?,"
                    + "dimanchef = ?,"
                    + " parking = ?,"
                    + "cartecredit = ?,"
                    + "chaiseroulante = ?,"
                    + "fumer = ?,"
                    + "terasse = ?,"
                    + "wifi = ?,"
                    + "reservations = ?,"
                    + "place = ?,"
                    + "livraison = ?,"
                    + "climatisation = ?,"
                    + "animaux = ?,"
                    + "alcool = ? ,"
                + " souscat = ? ,"
                + "img1 = ?,"
                + "img2 = ?,"
                + "img3 = ? ,"
                + "devis_name = ?,"
           
           
                
                + " latitude = ? ,longitude = ?,iduser=? where id=?";
                   
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, etab.getName());
        pre.setString(2, etab.getAddress());
        pre.setString(3, etab.getDescription());
        pre.setString(4, etab.getPhone());
        pre.setString(5, etab.getEmail());
        pre.setString(6, etab.getWebsite());
        pre.setString(7, etab.getFacebook());
        pre.setString(8, etab.getLundisamedio());
        pre.setString(9, etab.getLundisamedif());
        pre.setString(10, etab.getDimancheo());
        pre.setString(11, etab.getDimanchef());
        pre.setBoolean(12, etab.isParking());
        pre.setBoolean(13, etab.isCartecredit());
        pre.setBoolean(14, etab.isChaiseroulante());
        pre.setBoolean(15, etab.isFumer());
        pre.setBoolean(16, etab.isTerasse());
        pre.setBoolean(17, etab.isWifi());
        pre.setBoolean(18, etab.isReservations());
        pre.setInt(19, etab.getPlace());
        pre.setBoolean(20, etab.isLivraison());
        pre.setBoolean(21, etab.isClimatisation());
        pre.setBoolean(22, etab.isAnimaux());
        pre.setBoolean(23, etab.isAlcool());
        pre.setInt(24, etab.getSouscat().getId());
        pre.setString(25, etab.getImg1());
        pre.setString(26, etab.getImg2());
        pre.setString(27, etab.getImg3());
        pre.setString(28, etab.getDevis_name());
        pre.setDouble(29, etab.getLatitude());
        pre.setDouble(30, etab.getLongitude());
        pre.setInt(31, etab.getIduser().getId());
      
        pre.setInt(32,item);
         
       
        pre.executeUpdate();
        
    }
    public void ModifierHotel(Etablissement etab,int item) throws SQLException {
        Connection cn = MyConnexion.getInstance().getConnection();
        String req = "UPDATE etablissement SET "
                  + "name = ?,"
                    + "address = ?,"
                    + "description = ?,"
                    + "phone = ?,"
                    + "email = ?,"
                    + "website = ?,"
                    + "facebook = ?,"
                   
                    + " parking = ?,"
                    + "cartecredit = ?,"
                    + "chaiseroulante = ?,"
                    + "fumer = ?,"
                    + "terasse = ?,"
                    + "wifi = ?,"
                    + "reservations = ?,"
                    + "place = ?,"
                    + "livraison = ?,"
                    + "climatisation = ?,"
                    + "animaux = ?,"
                    + "alcool = ? ,"
                + " souscat = ? ,"
                + "img1 = ?,"
                + "img2 = ?,"
                + "img3 = ? ,"
                + "devis_name = ?,"
           
           
                
                + " latitude = ? ,longitude = ?,iduser=?,checkin=?,checkout=?,etoile=?,nbrchambre=?,prixmoy=?,lpd=?,pc=?,dp=?,allinclusive=? where id=?";
                   
        PreparedStatement pre = cn.prepareStatement(req);
        pre.setString(1, etab.getName());
        pre.setString(2, etab.getAddress());
        pre.setString(3, etab.getDescription());
        pre.setString(4, etab.getPhone());
        pre.setString(5, etab.getEmail());
        pre.setString(6, etab.getWebsite());
        pre.setString(7, etab.getFacebook());
    
        pre.setBoolean(8, etab.isParking());
        pre.setBoolean(9, etab.isCartecredit());
        pre.setBoolean(10, etab.isChaiseroulante());
        pre.setBoolean(11, etab.isFumer());
        pre.setBoolean(12, etab.isTerasse());
        pre.setBoolean(13, etab.isWifi());
        pre.setBoolean(14, etab.isReservations());
        pre.setInt(15, etab.getPlace());
        pre.setBoolean(16, etab.isLivraison());
        pre.setBoolean(17, etab.isClimatisation());
        pre.setBoolean(18, etab.isAnimaux());
        pre.setBoolean(19, etab.isAlcool());
        pre.setInt(20, etab.getSouscat().getId());
        pre.setString(21, etab.getImg1());
        pre.setString(22, etab.getImg2());
        pre.setString(23, etab.getImg3());
        pre.setString(24, etab.getDevis_name());
        pre.setDouble(25, etab.getLatitude());
        pre.setDouble(26, etab.getLongitude());
        pre.setInt(27, etab.getIduser().getId());
        pre.setString(28,etab.getCheckin());
        pre.setString(29,etab.getCheckout());

         pre.setInt(30,etab.getEtoile());
        pre.setInt(31,etab.getNbrchambre());
       
        pre.setInt(32,etab.getPrixmoy());
        pre.setBoolean(33,etab.isLpd());
        pre.setBoolean(34,etab.isPc());
        pre.setBoolean(35,etab.isDp());
        pre.setBoolean(36,etab.isAllinclusive());

        
      
        pre.setInt(37,item);
         
       
        pre.executeUpdate();
        
    }
    
    public void ModifierReview(Etablissement etab,int item) throws SQLException {
        Connection cn = MyConnexion.getInstance().getConnection();
        String req = "UPDATE etablissement SET "
                 
                + "totalqualite=?,"
                + "totalservice=?,"
                + "moyqualite=?,"
                + "moyservice=? where id=?";
                   
        PreparedStatement pre = cn.prepareStatement(req);
        
        pre.setDouble(1, etab.getTotalqualite());
        pre.setDouble(2, etab.getTotalservice());
        pre.setDouble(3, etab.getMoyqualite());
        pre.setDouble(4, etab.getMoyservice());
        pre.setInt(5,item);
         
       
        pre.executeUpdate();
        
    }
    
}
