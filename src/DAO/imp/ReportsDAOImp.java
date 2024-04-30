package DAO.imp;

import DAO.ReportsDAO;
import database.DBcon;
import orm.Reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ReportsDAOImp implements ReportsDAO {
    @Override
    public Reports get(int student_id, int course_id) {
        Reports reports = null;
        try {
            Connection c = DBcon.getConnection();
            String sql = "select * from reports where student_id = ? and course_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, student_id);
            ps.setInt(2, course_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                reports = new Reports();
                reports.setStudent_id(student_id);
                reports.setCourse_id(course_id);
                reports.setCourse_name(rs.getString(3));
                reports.setUsual_grades(rs.getInt(4));
                reports.setFinal_grades(rs.getInt(5));
                reports.setTotal_grades(rs.getInt(6));
                reports.setGpa(rs.getFloat(7));
                reports.setScore(rs.getFloat(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public void update(Reports reports) {
        String sql = "update reports set usual_grades = ?, final_grades =?, total_grades = ?, gpa = ?, staus = 1 where student_id = ? and course_id = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, reports.getUsual_grades());
            ps.setInt(2, reports.getFinal_grades());
            double total_grade = reports.getUsual_grades() * 0.3 + reports.getFinal_grades() * 0.7;
            double gpa = 0;
            if (total_grade >= 60) gpa = (total_grade - 60) / 10.0 + 1.0;
            if (total_grade > 0) new StudentDAOImp().update2(reports.getStudent_id(), reports.getScore());
            DecimalFormat df = new DecimalFormat("#.0");
            ps.setInt(3, (int) total_grade);
            ps.setFloat(4, Float.parseFloat(df.format(gpa)));
            ps.setInt(5, reports.getStudent_id());
            ps.setInt(6, reports.getCourse_id());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reports> list1(String type, int student_id) {
        List<Reports> reportsList = new ArrayList<>();
        String sql = null;
        if ("--请选择--".equals(type)) {
            sql = "select * from reports where student_id = ?";
            try {
                Connection c = DBcon.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1, student_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getInt(8) == 0) continue;
                    Reports reports = new Reports();
                    reports.setStudent_id(student_id);
                    reports.setCourse_id(rs.getInt(2));
                    reports.setCourse_name(rs.getString(3));
                    reports.setUsual_grades(rs.getInt(4));
                    reports.setFinal_grades(rs.getInt(5));
                    reports.setTotal_grades(rs.getInt(6));
                    reports.setGpa(rs.getFloat(7));
                    reports.setScore(rs.getFloat(8));
                    reportsList.add(reports);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            sql = "select * from view_reports where student_id = ? and course_type = ?";
            try {
                Connection c = DBcon.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1, student_id);
                ps.setString(2, type);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getInt(8) == 0) continue;
                    Reports reports = new Reports();
                    reports.setStudent_id(student_id);
                    reports.setCourse_id(rs.getInt(2));
                    reports.setCourse_name(rs.getString(3));
                    reports.setUsual_grades(rs.getInt(4));
                    reports.setFinal_grades(rs.getInt(5));
                    reports.setTotal_grades(rs.getInt(6));
                    reports.setGpa(rs.getFloat(7));
                    reports.setScore(rs.getFloat(8));
                    reportsList.add(reports);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reportsList;
    }

    @Override
    public List<Reports> list2(int course_id) {
        List<Reports> listReports = new ArrayList<>();
        String sql = "select * from Reports where course_id = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, course_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(9) == 0) continue;
                Reports reports = new Reports();
                reports.setStudent_id(rs.getInt(1));
                reports.setCourse_id(course_id);
                reports.setCourse_name(rs.getString(3));
                int usual_grades = rs.getInt(4);
                int final_grades = rs.getInt(5);
                int total_grades = rs.getInt(6);
                float gpa = rs.getFloat(7);
                reports.setUsual_grades(usual_grades);
                reports.setFinal_grades(final_grades);
                reports.setTotal_grades(total_grades);
                reports.setGpa(gpa);
                reports.setScore(rs.getFloat(8));
                listReports.add(reports);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReports;
    }

    @Override
    public List<Reports> list3(int course_id) {
        List<Reports> listReports = new ArrayList<>();
        String sql = "select * from Reports where course_id = ?";
        try {
            Connection c = DBcon.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, course_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(9) == 1) continue;
                Reports reports = new Reports();
                reports.setStudent_id(rs.getInt(1));
                reports.setCourse_id(course_id);
                reports.setCourse_name(rs.getString(3));
                reports.setUsual_grades(-1);
                reports.setFinal_grades(-1);
                reports.setTotal_grades(-1);
                reports.setGpa(-1);
                reports.setScore(rs.getFloat(8));
                listReports.add(reports);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listReports;
    }

}
