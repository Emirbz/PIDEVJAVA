/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Utils.MyConnexion;
import PIDEV.Views.FirstFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Skan
 */
public class DealDetailsService {

    Connection cn = MyConnexion.getInstance().getConnection();

    public int Avgrating(int id) throws SQLException {
        int nbr = 0, total = 0;

        String req = "Select COUNT(*) AS nbruser from note where iddeal='" + id + "'";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            nbr = rs.getInt("nbruser");
        }
        total = Nbrusersrating(id);
        if (nbr == 0) {
            return total;
        }
        return total / nbr;
    }

    public int Nbrusersrating(int id) throws SQLException {
        int nb = 0;
        String reqq = "Select SUM(rating) from note where iddeal='" + id + "'";
        Statement stt = cn.createStatement();
        ResultSet rss = stt.executeQuery(reqq);
        while (rss.next()) {
            nb = rss.getInt(1);
        }
        return nb;
    }

    public int NbrusersAchat(int id) throws SQLException {
        int nb = 0;
        String reqq = "Select count(*) from achat where iddeal='" + id + "'";
        Statement stt = cn.createStatement();
        ResultSet rss = stt.executeQuery(reqq);
        while (rss.next()) {
            nb = rss.getInt(1);
        }
        return nb;
    }

    public boolean testrating(int id, int idu) throws SQLException {
        int i = 0;
        String reqq = "Select * from achat where iddeal='" + id + "' and iduser ='" + idu + "'";
        Statement stt = cn.createStatement();
        ResultSet rss = stt.executeQuery(reqq);
        while (rss.next()) {
            i++;
        }
        return i == 0;
    }

    public void addnote(int id, int i) throws SQLException {
        String re = "Insert into note(iddeal,iduser,rating) values(?,?,?)";
        PreparedStatement ps = cn.prepareStatement(re);
        ps.setInt(1, id);
        ps.setInt(2, FirstFrame.user.getId());
        ps.setInt(3, i);
        ps.executeUpdate();
    }

    public void updaterating(int id) throws SQLException {
        String r = "Update deal set rating = ? where id = ?";
        PreparedStatement ps = cn.prepareStatement(r);
        ps.setInt(1, Avgrating(id));
        ps.setInt(2, id);
        ps.executeUpdate();
    }
}
