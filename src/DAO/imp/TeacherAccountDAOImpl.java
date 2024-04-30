package DAO.imp;

import DAO.TeacherAccountDAO;
import database.DBcon;
import orm.TeacherAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherAccountDAOImpl implements TeacherAccountDAO {
    @Override
    public void update(TeacherAccount teacherAccount) {
        String sql = "update teacher_account set teacher_password = ? where teacher_id = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, teacherAccount.getPassword());
            ps.setInt(2, teacherAccount.getTeacher_id());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean select(TeacherAccount teacherAccount) {
        String sql = "select * from teacher_account where teacher_id = ? and teacher_password = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, teacherAccount.getTeacher_id());
            ps.setString(2, teacherAccount.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
