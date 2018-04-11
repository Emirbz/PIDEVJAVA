/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Entities.Deal;
import PIDEV.Utils.MyConnexion;
import PIDEV.Views.FirstFrame;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Skan
 */
public class UpdateDealService {

    Connection cn = MyConnexion.getInstance().getConnection();

    public void UpdateDeal(Deal deal, int item) throws SQLException {
        String req = "UPDATE Deal SET nom=?,description=?,placesdispo=?,oldprix=?,promotion=?,"
                + "region=?,adresse=?,datefin=?,cat=?,devis_name=?"
                + "where id=?";
        PreparedStatement pre = cn.prepareStatement(req);

        pre.setString(1, deal.getNom());
        pre.setString(2, deal.getDescription());
        pre.setInt(3, deal.getPlacesdispo());
        pre.setDouble(4, deal.getOldprix());
        pre.setDouble(5, deal.getPromotion());
        pre.setString(6, deal.getRegion());
        pre.setString(7, deal.getAdresse());
        pre.setDate(8, (Date) deal.getDatefin());
        pre.setInt(9, deal.getCat().getId());
        pre.setString(10, deal.getDevisName());
        pre.setInt(11, item);
        pre.executeUpdate();

    }

    public void achat(Deal d) throws SQLException {
        String re = "UPDATE Deal SET placesdispo=? where iduser=?";
        PreparedStatement pr = cn.prepareStatement(re);
        pr.setInt(1, d.getPlacesdispo() - 1);
        pr.setInt(2, d.getIduser().getId());
        pr.executeUpdate();
        String req = "Insert into achat(iduser,iddeal) values(?,?)";
        PreparedStatement prz = cn.prepareStatement(req);
        prz.setInt(1, FirstFrame.user.getId());
        prz.setInt(2, d.getId());
        prz.executeUpdate();
    }

    public boolean testachat(int id, int idd) throws SQLException {
        int i = 0;
        String r = "Select * from achat where iduser = ? and iddeal = ?";
        PreparedStatement ps = cn.prepareStatement(r);
        ps.setInt(1, id);
        ps.setInt(2, idd);
        ResultSet s = ps.executeQuery();
        while (s.next()) {
            i++;
        }
        return i == 0;

    }
}
