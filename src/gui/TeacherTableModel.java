package gui;

import DAO.imp.TeacherDAOImp;
import orm.Teacher;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TeacherTableModel extends AbstractTableModel {
    String[] columnNames = {"教师编号", "教师姓名", "性别", "年龄", "职称"};
    public List<Teacher> teachers = new TeacherDAOImp().list();

    public int getRowCount() {
        return teachers.size();
    }

    // 返回一共有多少列
    public int getColumnCount() {
        return columnNames.length;
    }

    // 获取每一列的名称
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    // 每一个单元格里的值
    public Object getValueAt(int rowIndex, int columnIndex) {
        Teacher t = teachers.get(rowIndex);
        if (0 == columnIndex) return t.getTeacherId();
        if (1 == columnIndex) return t.getTeacherName();
        if (2 == columnIndex) return t.getTeacherSex();
        if (3 == columnIndex) return t.getTeacherAge();
        if (4 == columnIndex) return t.getTeacherTitle();
        return null;
    }

}
