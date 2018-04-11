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
public class TestUserReclamation {

    public boolean test(int i, int j) throws SQLException {
        int t=0;
        Connection cn = MyConnexion.getInstance().getConnection();
        String req = "Select * from reclamation where idobj ='" + i + "'and iduser='" + j + "'";
        Statement s=cn.createStatement();
        ResultSet rs=s.executeQuery(req);
        while(rs.next()){
            t++;
        }
        return t==0;
    }
}
