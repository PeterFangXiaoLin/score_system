package DAO.imp;

import DAO.AdminAccountDAO;
import database.DBcon;
import orm.AdminAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminAccountDAOImpl implements AdminAccountDAO {
    @Override
    public void update(AdminAccount adminAccount) {
        String sql = "update Admin set admin_password = ? where admin_id = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, adminAccount.getPassword());
            ps.setString(2, adminAccount.getAdmin_id());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean select(AdminAccount adminAccount) {
        String sql = "select * from Admin where admin_id = ? and admin_password = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, adminAccount.getAdmin_id());
            ps.setString(2, adminAccount.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
