package DAO.imp;

import DAO.StudentDAO;
import database.DBcon;
import orm.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO {
    @Override
    public void add(Student student) {
        String sql = "insert into student values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, student.getStudentName());
            ps.setInt(2, student.getStudentAge());
            ps.setString(3, student.getStudentSex());
            ps.setString(4, student.getStudentGrade());
            ps.setString(5, student.getStudentMajor());
            ps.setString(6, student.getStudentClass());
            ps.setString(7, student.getStudentCollege());
            ps.setFloat(8, student.getStudentScore());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student) {
        String sql = "update student set student_name=?,student_age=?,student_sex=?,student_grade=?,student_major=?,student_class=?,student_college=?,student_score=? where student_id=?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, student.getStudentName());
            ps.setInt(2,student.getStudentAge());
            ps.setString(3, student.getStudentSex());
            ps.setString(4,student.getStudentGrade());
            ps.setString(5,student.getStudentMajor());
            ps.setString(6,student.getStudentClass());
            ps.setString(7,student.getStudentCollege());
            ps.setFloat(8, student.getStudentScore());
            ps.setInt(9, student.getStudentId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update2(int student_id, float score) {
        String sql = "update student set student_score = student_score + ? where student_id = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setFloat(1, score);
            ps.setInt(2, student_id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {
        try {
            Connection c = DBcon.getConnection();
            Statement s = c.createStatement();
            String sql = "delete from student where student_id=" + id;
            s.execute(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student get(int id) {
        Student student = null;
        try {
            Connection c = DBcon.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from student where student_id=" + id;
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()){
                student = new Student();
                student.setStudentName(rs.getString(2));
                student.setStudentAge(rs.getInt(3));
                student.setStudentSex(rs.getString(4));
                student.setStudentGrade(rs.getString(5));
                student.setStudentMajor(rs.getString(6));
                student.setStudentClass(rs.getString(7));
                student.setStudentCollege(rs.getString(8));
                student.setStudentScore(rs.getFloat(9));
                student.setStudentId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> list() {
        List<Student> students = new ArrayList<>();

        String sql = "select * from student";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getInt(1));
                s.setStudentName(rs.getString(2));
                s.setStudentAge(rs.getInt(3));
                s.setStudentSex(rs.getString(4));
                s.setStudentGrade(rs.getString(5));
                s.setStudentMajor(rs.getString(6));
                s.setStudentClass(rs.getString(7));
                s.setStudentCollege(rs.getString(8));
                s.setStudentScore(rs.getFloat(9));
                students.add(s);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
