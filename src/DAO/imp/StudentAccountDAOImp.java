package DAO.imp;

import DAO.StudentAccountDAO;
import database.DBcon;
import orm.StudentAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentAccountDAOImp implements StudentAccountDAO {
    @Override
    public void update(StudentAccount studentAccount) {
        String sql = "update student_account set student_password = ? where student_id = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, studentAccount.getPassword());
            ps.setInt(2, studentAccount.getStudent_id());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean select(StudentAccount studentAccount) {
        String sql = "select * from student_account where student_id = ? and student_password = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, studentAccount.getStudent_id());
            ps.setString(2, studentAccount.getPassword());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
