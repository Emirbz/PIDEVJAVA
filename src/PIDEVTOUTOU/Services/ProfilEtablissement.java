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
import PIDEVTOUTOU.Entities.etablissement;
import PIDEVTOUTOU.Utils.Myconnexion;

/**
 *
 * @author Emir
 */
public class ProfilEtablissement {
    Connection cn = Myconnexion.getInstance().getConnection();
     public etablissement ProfilResto(int id) {
        try {
            String requete = "SELECT * FROM etablissement WHERE id='"+id+"'";
            PreparedStatement st = cn.prepareStatement(requete);
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                etablissement etab = new etablissement();
                etab.setName(rs.getString("name"));
                etab.setDescription(rs.getString("description"));
                etab.setPhone(rs.getString("phone"));
                etab.setEmail(rs.getString("email"));
                etab.setAddress(rs.getString("address"));
                etab.setLundisamedio(rs.getString("lundisamedio"));
                etab.setLundisamedif(rs.getString("lundismaedif"));
                etab.setDimancheo(rs.getString("dimancheo"));
                etab.setDimanchef(rs.getString("dimanchef"));
                etab.setParking(rs.getBoolean("parking"));
                etab.setCartecredit(rs.getBoolean("cartecredit"));
                etab.setChaiseroulante(rs.getBoolean("chaiseroulante"));
                etab.setFumer(rs.getBoolean("fumer"));
                etab.setAlcool(rs.getBoolean("alcool"));
                etab.setTerasse(rs.getBoolean("terasse"));
                etab.setWifi(rs.getBoolean("wifi"));
                etab.setAnimaux(rs.getBoolean("animaux"));
                etab.setLivraison(rs.getBoolean("livraison"));
                etab.setReservations(rs.getBoolean("reservations"));
                etab.setClimatisation(rs.getBoolean("climatisaiton"));
                etab.setImg1(rs.getString("img1"));
                etab.setImg2(rs.getString("img2"));
                etab.setImg3(rs.getString("img3"));
                etab.setImg2(rs.getString("devis_name"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }
}
