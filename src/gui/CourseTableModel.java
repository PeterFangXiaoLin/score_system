package gui;

import DAO.imp.CourseDAOImp;
import orm.Course;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CourseTableModel extends AbstractTableModel {
    String[] columnNames = {"课程号","课程名","理论学时","实践学时","学分","课程类型","任课教师"};
    public List<Course> courses = new CourseDAOImp().list();

    @Override
    public int getRowCount() {
        return courses.size();
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
        Course c = courses.get(rowIndex);
        if (0 == columnIndex) return c.getCourse_id();
        if (1 == columnIndex) return c.getCourse_name();
        if (2 == columnIndex) return c.getCourse_theory();
        if (3 == columnIndex) return c.getCourse_practice();
        if (4 == columnIndex) return c.getCourse_score();
        if (5 == columnIndex) return c.getCourse_type();
        if (6 == columnIndex) return c.getTeacher_name();
        return null;
    }
}
