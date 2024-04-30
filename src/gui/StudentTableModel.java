package gui;

import DAO.imp.StudentDAOImp;
import orm.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    String[] columnNames = {"学号","姓名","年龄","性别","年级","专业","班级","学院","已修学分"};

    public List<Student> students = new StudentDAOImp().list();

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student s = students.get(rowIndex);
        if (0 == columnIndex) return s.getStudentId();
        if (1 == columnIndex) return s.getStudentName();
        if (2 == columnIndex) return s.getStudentAge();
        if (3 == columnIndex) return s.getStudentSex();
        if (4 == columnIndex) return s.getStudentGrade();
        if (5 == columnIndex) return s.getStudentMajor();
        if (6 == columnIndex) return s.getStudentClass();
        if (7 == columnIndex) return s.getStudentCollege();
        if (8 == columnIndex) return s.getStudentScore();
        return null;
    }
}
