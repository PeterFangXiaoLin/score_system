package gui;

import DAO.imp.ReportsDAOImp;
import orm.Reports;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;

public class GradeTableModel extends AbstractTableModel {
    String[] columnNames = {"学号", "课程号", "课程名", "平时成绩", "期末成绩", "总成绩", "绩点", "学分"};
    public List<Reports> reports;

    public GradeTableModel(String type, int id) {
        reports = new ReportsDAOImp().list1(type, id);
    }

    public GradeTableModel(int id) {
        reports = new ReportsDAOImp().list2(id);
    }

    public GradeTableModel() {
        reports = Arrays.asList();
    }

    @Override
    public int getRowCount() {
        return reports.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    // 每一个单元格里的值
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reports r = reports.get(rowIndex);
        if (0 == columnIndex) return r.getStudent_id();
        if (1 == columnIndex) return r.getCourse_id();
        if (2 == columnIndex) return r.getCourse_name();
        if (3 == columnIndex) return r.getUsual_grades();
        if (4 == columnIndex) return r.getFinal_grades();
        if (5 == columnIndex) return r.getTotal_grades();
        if (6 == columnIndex) return r.getGpa();
        if (7 == columnIndex) return r.getScore();
        return null;
    }
}
