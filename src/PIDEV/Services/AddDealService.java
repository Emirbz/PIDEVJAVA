/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Deal;
import PIDEV.Utils.MyConnexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Skan
 */
public class AddDealService {

    Connection cn = MyConnexion.getInstance().getConnection();

    public void addDeal(Deal d) throws IOException {
        try {
            String requete
                    = "INSERT INTO Deal (nom, oldprix, promotion, description, datefin, region, adresse, placesdispo,"
                    + "datecreation,newprix,devis_name,cat)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = cn.prepareStatement(requete);
            st.setString(1, d.getNom());
            st.setDouble(2, d.getOldprix());
            st.setDouble(3, d.getPromotion());
            st.setString(4, d.getDescription());
            st.setDate(5, (Date) d.getDatefin());
            st.setString(6, d.getRegion());
            st.setString(7, d.getAdresse());
            st.setInt(8, d.getPlacesdispo());
            st.setDate(9, (Date) d.getDatecreation());
            st.setDouble(10, d.getNewprix());
            st.setString(11, d.getDevisName());
            st.setInt(12, d.getCat().getId());
            st.executeUpdate();
            System.out.println("Deal ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
