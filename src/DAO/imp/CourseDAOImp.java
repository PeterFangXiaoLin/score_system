package DAO.imp;

import DAO.CourseDAO;
import database.DBcon;
import lombok.Data;
import orm.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImp implements CourseDAO {
    @Override
    public void add(Course course) {
        String sql = "insert into course values(null, ?,?,?,?,?,?)";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, course.getCourse_name());
            ps.setInt(2, course.getCourse_theory());
            ps.setInt(3, course.getCourse_practice());
            ps.setFloat(4, course.getCourse_score());
            ps.setString(5, course.getCourse_type());
            ps.setString(6, course.getTeacher_name());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Course course) {
        String sql = "update course set course_name=?,course_theory=?,course_practice=?,course_score=?,course_type=?,teacher_name=? where course_id=?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, course.getCourse_name());
            ps.setInt(2,course.getCourse_theory());
            ps.setInt(3,course.getCourse_practice());
            ps.setFloat(4,course.getCourse_score());
            ps.setString(5,course.getCourse_type());
            ps.setString(6,course.getTeacher_name());
            ps.setInt(7, course.getCourse_id());

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
            String sql = "delete from course where course_id = " + id;
            s.execute(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course get(int id) {
        Course course = null;

        try {
            Connection c = DBcon.getConnection();
            Statement s = c.createStatement();
            String sql = "select * from course where course_id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                course = new Course();
                course.setCourse_name(rs.getString(2));
                course.setCourse_theory(rs.getInt(3));
                course.setCourse_practice(rs.getInt(4));
                course.setCourse_score(rs.getFloat(5));
                course.setCourse_type(rs.getString(6));
                course.setTeacher_name(rs.getString(7));
                course.setCourse_id(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public List<Course> list() {
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourse_id(rs.getInt(1));
                course.setCourse_name(rs.getString(2));
                course.setCourse_theory(rs.getInt(3));
                course.setCourse_practice(rs.getInt(4));
                course.setCourse_score(rs.getFloat(5));
                course.setCourse_type(rs.getString(6));
                course.setTeacher_name(rs.getString(7));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public List<Course> list(String teacher_name) {
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course where teacher_name = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, teacher_name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourse_id(rs.getInt(1));
                course.setCourse_name(rs.getString(2));
                course.setCourse_theory(rs.getInt(3));
                course.setCourse_practice(rs.getInt(4));
                course.setCourse_score(rs.getFloat(5));
                course.setCourse_type(rs.getString(6));
                course.setTeacher_name(rs.getString(7));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
