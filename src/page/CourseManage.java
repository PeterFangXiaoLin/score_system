package page;

import DAO.imp.CourseDAOImp;
import gui.CourseTableModel;
import orm.Course;
import orm.Student;
import statics.Fontsize;
import statics.IsEmpty;
import statics.IsFloat;
import statics.IsNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class CourseManage {
    private JFrame f;
    private JLabel l1, l2, l3, l4, l5, l6, label;
    private JTextField t1, t2, t3, t4, t5, t6, textField;
    private static JTable table1;
    private JTable table2;

    private static CourseTableModel courseTableModel = new CourseTableModel();
    private CourseTableModel courseTableModel2 = new CourseTableModel();

    private JButton bInsert, bDelete, bEdit, bSelect, bReturn;

    private JPanel p1, p2, p3;

    private JScrollPane sp1, sp2;

    public CourseManage() {
        f = new JFrame("课程管理页面");
        f.setSize(1200, 900);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        l1 = new JLabel("课程名:");
        l1.setFont(Fontsize.font);
        l1.setBounds(30, 50, 100, 30);
        t1 = new JTextField(100);
        t1.setBounds(140, 50, 100, 30);

        l2 = new JLabel("理论学时:");
        l2.setFont(Fontsize.font);
        l2.setBounds(30, 90, 100, 30);
        t2 = new JTextField(100);
        t2.setBounds(140, 90, 100, 30);

        l3 = new JLabel("实践学时:");
        l3.setFont(Fontsize.font);
        l3.setBounds(30, 130, 100, 30);
        t3 = new JTextField(100);
        t3.setBounds(140, 130, 100, 30);

        l4 = new JLabel("学分:");
        l4.setFont(Fontsize.font);
        l4.setBounds(30, 170, 100, 30);
        t4 = new JTextField(100);
        t4.setBounds(140, 170, 100, 30);

        l5 = new JLabel("课程类型:");
        l5.setFont(Fontsize.font);
        l5.setBounds(30, 210, 100, 30);
        t5 = new JTextField(100);
        t5.setBounds(140, 210, 100, 30);

        l6 = new JLabel("教师姓名:");
        l6.setFont(Fontsize.font);
        l6.setBounds(30, 250, 100, 30);
        t6 = new JTextField(100);
        t6.setBounds(140, 250, 100, 30);

        bInsert = new JButton("插入");
        bInsert.setBounds(100, 300, 100, 30);
        bInsert.setFont(Fontsize.font);
        bInsert.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!IsEmpty.checkEmpty(t1, "课程名")) return;
                if (!IsNumber.isNumber(t2, "理论学时")) return;
                if (!IsNumber.isNumber(t3, "实践学时")) return;
                if (!IsFloat.isFloat(t4, "学分")) return;
                if (!IsEmpty.checkEmpty(t5, "课程类型")) return;
                if (!IsEmpty.checkEmpty(t6, "教师姓名")) return;

                String name = t1.getText();
                String theory = t2.getText();
                String practice = t3.getText();
                String score = t4.getText();
                String type = t5.getText();
                String teacher_name = t6.getText();
                Course course = new Course();
                course.setCourse_name(name);
                course.setCourse_theory(Integer.parseInt(theory));
                course.setCourse_practice(Integer.parseInt(practice));
                course.setCourse_score(Float.parseFloat(score));
                course.setCourse_type(type);
                course.setTeacher_name(teacher_name);
                new CourseDAOImp().add(course);
                updateTable();
                JOptionPane.showMessageDialog(f, "插入成功");
            }
        });

        f.add(l1);
        f.add(t1);
        f.add(l2);
        f.add(t2);
        f.add(l3);
        f.add(t3);
        f.add(l4);
        f.add(t4);
        f.add(l5);
        f.add(t5);
        f.add(l6);
        f.add(t6);
        f.add(bInsert);

        p1 = new JPanel();
        p1.setBackground(Color.GRAY);
        p1.setBounds(300, 10, 800, 500);
        p1.setLayout(new BorderLayout());

        table1 = new JTable(courseTableModel);

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (!courseTableModel.courses.isEmpty())
            table1.getSelectionModel().setSelectionInterval(0, 0);

        table1.getTableHeader().setReorderingAllowed(false);
        table1.getTableHeader().setResizingAllowed(false);
        table1.getTableHeader().setFont(Fontsize.font);

        sp1 = new JScrollPane(table1);

        p1.add(sp1, BorderLayout.CENTER);
        bDelete = new JButton("删除");
        bDelete.setFont(Fontsize.font);
        bDelete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table1.getSelectedRow();
                if (-1 == index) {
                    JOptionPane.showMessageDialog(f, "删除前需要先选中一行");
                    return;
                }

                if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(f, "确认要删除？"))
                    return;

                Course course = courseTableModel.courses.get(index);
                int id = course.getCourse_id();
                new CourseDAOImp().delete(id);

                updateTable();
                JOptionPane.showMessageDialog(f, "删除成功");
            }
        });

        bEdit = new JButton("编辑");
        bEdit.setFont(Fontsize.font);
        bEdit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table1.getSelectedRow();
                if (-1 == index) {
                    JOptionPane.showMessageDialog(f, "编辑前需要先选中一行");
                    return;
                }

                Course course = courseTableModel.courses.get(index);

                EditDialog ed = new EditDialog(f);
                ed.tfName.setText(course.getCourse_name());
                ed.tfTheory.setText(String.valueOf(course.getCourse_theory()));
                ed.tfPractice.setText(String.valueOf(course.getCourse_practice()));
                ed.tfScore.setText(String.valueOf(course.getCourse_score()));
                ed.tfType.setText(course.getCourse_type());
                ed.tfTeacher_name.setText(course.getTeacher_name());

                ed.setVisible(true);
            }
        });

        p3 = new JPanel();
        p3.add(bDelete);
        p3.add(bEdit);

        p1.add(p3, BorderLayout.SOUTH);
        f.add(p1);

        label = new JLabel("查询编号:");
        label.setBounds(20, 550, 120, 30);
        label.setFont(Fontsize.font);
        textField = new JTextField(100);
        textField.setBounds(150, 550, 150, 30);
        textField.setFont(Fontsize.font);

        bSelect = new JButton("查询");
        bSelect.setFont(Fontsize.font);
        bSelect.setBounds(310, 550, 100, 30);

        p2 = new JPanel();
        p2.setBounds(200, 590, 800, 200);
        p2.setBackground(Color.GRAY);
        courseTableModel2.courses = Arrays.asList();
        table2 = new JTable(courseTableModel2);
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (!courseTableModel2.courses.isEmpty())
            table2.getSelectionModel().setSelectionInterval(0, 0);

        table2.getTableHeader().setReorderingAllowed(false);
        table2.getTableHeader().setResizingAllowed(false);
        table2.getTableHeader().setFont(Fontsize.font);
        sp2 = new JScrollPane(table2);
        p2.setLayout(new BorderLayout());
        p2.add(sp2, BorderLayout.CENTER);
        f.add(p2);

        bSelect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!IsNumber.isNumber(textField, "编号")) return;
                String id = textField.getText();
                Course course = new CourseDAOImp().get(Integer.parseInt(id));
                if (course == null) {
                    JOptionPane.showMessageDialog(f, "查不到该课程");
                } else {
                    courseTableModel2.courses = Arrays.asList(course);
                    table2.updateUI();
                }
            }
        });

        f.add(label);
        f.add(textField);
        f.add(bSelect);

        bReturn = new JButton("返回");
        bReturn.setFont(Fontsize.font);
        bReturn.setBounds(500, 810, 200, 30);
        bReturn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });
        f.add(bReturn);
        f.setVisible(true);
    }

    public static void updateTable() {
        courseTableModel.courses = new CourseDAOImp().list();
        table1.updateUI();
        if (!courseTableModel.courses.isEmpty())
            table1.getSelectionModel().setSelectionInterval(0, 0);
    }

    static class EditDialog extends JDialog {
        JLabel lName = new JLabel("课程名");
        JLabel lTheory = new JLabel("理论学时");
        JLabel lPractice = new JLabel("实践学时");
        JLabel lScore = new JLabel("学分");
        JLabel lType = new JLabel("课程类型");
        JLabel lTeacher_name = new JLabel("教师姓名");

        JTextField tfName = new JTextField();
        JTextField tfTheory = new JTextField();
        JTextField tfPractice = new JTextField();
        JTextField tfScore = new JTextField();
        JTextField tfType = new JTextField();
        JTextField tfTeacher_name = new JTextField();

        JButton bSubmit = new JButton("提交");

        EditDialog(JFrame f) {
            super(f);

            this.setModal(true);
            int gap = 10;
            this.setLayout(null);

            JPanel pInput = new JPanel();
            JPanel pSubmit = new JPanel();

            pInput.setLayout(new GridLayout(6, 2, gap, gap));
            pInput.add(lName);
            pInput.add(tfName);
            pInput.add(lTheory);
            pInput.add(tfTheory);
            pInput.add(lPractice);
            pInput.add(tfPractice);
            pInput.add(lScore);
            pInput.add(tfScore);
            pInput.add(lType);
            pInput.add(tfType);
            pInput.add(lTeacher_name);
            pInput.add(tfTeacher_name);

            pSubmit.add(bSubmit);

            this.setLayout(new BorderLayout());
            this.add(pInput, BorderLayout.CENTER);
            this.add(pSubmit, BorderLayout.SOUTH);

            this.setSize(600, 550);
            this.setLocationRelativeTo(f);
            bSubmit.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!IsEmpty.checkEmpty(tfName, "课程名")) return;
                    if (!IsNumber.isNumber(tfTheory, "理论学时")) return;
                    if (!IsNumber.isNumber(tfPractice, "实践学时")) return;
                    if (!IsFloat.isFloat(tfScore, "学分")) return;
                    if (!IsEmpty.checkEmpty(tfType, "课程类型")) return;
                    if (!IsEmpty.checkEmpty(tfTeacher_name, "教师姓名")) return;

                    int index = table1.getSelectedRow();
                    int id = courseTableModel.courses.get(index).getCourse_id();

                    String name = tfName.getText();
                    int theory = Integer.parseInt(tfTheory.getText());
                    int practice = Integer.parseInt(tfPractice.getText());
                    float score = Float.parseFloat(tfScore.getText());
                    String type = tfType.getText();
                    String teacher_name = tfTeacher_name.getText();

                    Course course = new Course();
                    course.setCourse_id(id);
                    course.setCourse_name(name);
                    course.setCourse_theory(theory);
                    course.setCourse_practice(practice);
                    course.setCourse_score(score);
                    course.setCourse_type(type);
                    course.setTeacher_name(teacher_name);
                    new CourseDAOImp().update(course);
                    JOptionPane.showMessageDialog(f, "提交成功");

                    EditDialog.this.setVisible(false);
                    updateTable();
                }
            });
        }
    }

}
