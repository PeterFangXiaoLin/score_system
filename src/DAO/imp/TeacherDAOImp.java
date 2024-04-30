package DAO.imp;

import DAO.TeacherDAO;
import database.DBcon;
import orm.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImp implements TeacherDAO {
    @Override
    public void add(Teacher teacher) {
        String sql = "insert into teacher values(null, ?, ?, ?, ?)";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, teacher.getTeacherName());
            ps.setString(2, teacher.getTeacherSex());
            ps.setInt(3, teacher.getTeacherAge());
            ps.setString(4, teacher.getTeacherTitle());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Teacher teacher) {
        try {
            Connection c = DBcon.getConnection();
            String sql1 = "set foreign_key_checks = 0";
            PreparedStatement ps1 = c.prepareStatement(sql1);
            ps1.execute();
            String sql2 = "update teacher set teacher_name = ?, teacher_sex= ?, teacher_age =?, teacher_title = ? where teacher_id = ?";
            PreparedStatement ps2 = c.prepareStatement(sql2);
            ps2.setString(1, teacher.getTeacherName());
            ps2.setString(2, teacher.getTeacherSex());
            ps2.setInt(3, teacher.getTeacherAge());
            ps2.setString(4, teacher.getTeacherTitle());
            ps2.setInt(5, teacher.getTeacherId());
            ps2.execute();
            String sql3 = "set foreign_key_checks = 1";
            PreparedStatement ps3 = c.prepareStatement(sql3);
            ps3.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection c = DBcon.getConnection();
            Statement s = c.createStatement();
            String sql1 = "select teacher.teacher_id from teacher, course where teacher.teacher_name = course.teacher_name and teacher_id = ?";
            PreparedStatement ps = c.prepareStatement(sql1);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
            String sql = "delete from teacher where teacher_id =" + id;
            s.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Teacher get(int id) {
        Teacher t = null;
        try {
            Connection c = DBcon.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from teacher where teacher_id =" + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                t = new Teacher();
                String name = rs.getString(2);
                String sex = rs.getString(3);
                int age = rs.getInt(4);
                String title = rs.getString(5);
                t.setTeacherName(name);
                t.setTeacherSex(sex);
                t.setTeacherAge(age);
                t.setTeacherTitle(title);
                t.setTeacherId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public List<Teacher> list() {
        String sql = "select * from teacher";
        List<Teacher> list = new ArrayList<>();
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setTeacherId(rs.getInt(1));
                t.setTeacherName(rs.getString(2));
                t.setTeacherSex(rs.getString(3));
                t.setTeacherAge(rs.getInt(4));
                t.setTeacherTitle(rs.getString(5));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
