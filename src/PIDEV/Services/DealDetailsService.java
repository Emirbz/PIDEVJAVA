/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import PIDEV.Utils.MyConnexion;
import java.sql.Connection;
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
}
