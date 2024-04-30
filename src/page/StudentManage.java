package page;

import DAO.imp.StudentDAOImp;
import gui.StudentTableModel;
import orm.Student;
import statics.Fontsize;
import statics.IsEmpty;
import statics.IsNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class StudentManage {
    private JFrame f;
    private JLabel l1, l2, l3, l4, l5, l6, l7;
    private JTextField t1, t2, t3, t4, t5, t6, t7;
    private JButton bInsert;
    private JPanel p1;
    private static JTable table1;
    private static StudentTableModel studentTableModel = new StudentTableModel();
    private StudentTableModel studentTableModel2 = new StudentTableModel();
    private JScrollPane sp1, sp2;
    private JButton bDelete;
    private JButton bEdit;
    private JLabel label;
    private JTextField textField;
    private JButton bSelect;
    private JPanel p2;

    private JPanel p3;

    private JTable table2;

    private JButton bReturn;

    public StudentManage() {
        f = new JFrame("学生管理页面");
        f.setSize(1200, 900);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        l1 = new JLabel("姓名:");
        l1.setFont(Fontsize.font);
        l1.setBounds(30, 30, 80, 30);
        t1 = new JTextField(100);
        t1.setBounds(120, 30, 100, 30);
        t1.setFont(Fontsize.font);

        l2 = new JLabel("年龄:");
        l2.setFont(Fontsize.font);
        l2.setBounds(30, 70, 80, 30);
        t2 = new JTextField(100);
        t2.setBounds(120, 70, 100, 30);
        t2.setFont(Fontsize.font);

        l3 = new JLabel("性别:");
        l3.setFont(Fontsize.font);
        l3.setBounds(30, 110, 80, 30);
        t3 = new JTextField(100);
        t3.setBounds(120, 110, 100, 30);
        t3.setFont(Fontsize.font);

        l4 = new JLabel("年级:");
        l4.setFont(Fontsize.font);
        l4.setBounds(30, 150, 80, 30);
        t4 = new JTextField(100);
        t4.setBounds(120, 150, 100, 30);
        t4.setFont(Fontsize.font);

        l5 = new JLabel("专业:");
        l5.setFont(Fontsize.font);
        l5.setBounds(30, 190, 80, 30);
        t5 = new JTextField(100);
        t5.setBounds(120, 190, 100, 30);
        t5.setFont(Fontsize.font);

        l6 = new JLabel("班级:");
        l6.setFont(Fontsize.font);
        l6.setBounds(30, 230, 80, 30);
        t6 = new JTextField(100);
        t6.setBounds(120, 230, 100, 30);
        t6.setFont(Fontsize.font);

        l7 = new JLabel("学院:");
        l7.setFont(Fontsize.font);
        l7.setBounds(30, 270, 80, 30);
        t7 = new JTextField(100);
        t7.setBounds(120, 270, 100, 30);
        t7.setFont(Fontsize.font);

        bInsert = new JButton("插入");
        bInsert.setBounds(100, 320, 100, 30);
        bInsert.setFont(Fontsize.font);
        bInsert.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!IsEmpty.checkEmpty(t1, "姓名")) return;
                if (!IsNumber.isNumber(t2, "年龄")) return;
                if (!IsEmpty.checkEmpty(t3, "性别")) return;
                if (!IsEmpty.checkEmpty(t4, "年级")) return;
                if (!IsEmpty.checkEmpty(t5, "专业")) return;
                if (!IsEmpty.checkEmpty(t6, "班级")) return;
                if (!IsEmpty.checkEmpty(t7, "学院")) return;

                String name = t1.getText();
                String age = t2.getText();
                String sex = t3.getText();
                String grade = t4.getText();
                String major = t5.getText();
                String cla = t6.getText();
                String college = t7.getText();
                Student student = new Student();
                student.setStudentName(name);
                student.setStudentAge(Integer.parseInt(age));
                student.setStudentSex(sex);
                student.setStudentGrade(grade);
                student.setStudentMajor(major);
                student.setStudentClass(cla);
                student.setStudentCollege(college);
                new StudentDAOImp().add(student);
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
        f.add(l7);
        f.add(t7);
        f.add(bInsert);

        p1 = new JPanel();
        p1.setBackground(Color.GRAY);
        p1.setBounds(300, 10, 800, 500);
        p1.setLayout(new BorderLayout());

        table1 = new JTable(studentTableModel);
        //设置选择模式为只能选中一行
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (!studentTableModel.students.isEmpty())
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
                //判断是否选中
                int index = table1.getSelectedRow();
                if (-1 == index) {
                    JOptionPane.showMessageDialog(f, "删除前需要先选中一行");
                    return;
                }

                if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(f, "确认要删除？"))
                    return;

                Student student = studentTableModel.students.get(index);
                int id = student.getStudentId();
                new StudentDAOImp().delete(id);

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

                //获取选中的对象
                Student student = studentTableModel.students.get(index);

                EditDialog ed = new EditDialog(f);
                ed.tfName.setText(student.getStudentName());
                ed.tfAge.setText(String.valueOf(student.getStudentAge()));
                ed.tfSex.setText(student.getStudentSex());
                ed.tfGrade.setText(student.getStudentGrade());
                ed.tfMajor.setText(student.getStudentMajor());
                ed.tfClass.setText(student.getStudentClass());
                ed.tfCollege.setText(student.getStudentCollege());

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

        bSelect = new JButton("查询");
        bSelect.setFont(Fontsize.font);
        bSelect.setBounds(310, 550, 100, 30);
        p2 = new JPanel();
        p2.setBounds(200, 590, 800, 200);
        p2.setBackground(Color.GRAY);

        studentTableModel2.students = Arrays.asList();
        table2 = new JTable(studentTableModel2);
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (!studentTableModel.students.isEmpty())
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
                if (!IsNumber.isNumber(textField, "编号")) {
                    return;
                }
                String id = textField.getText();
                Student student = new StudentDAOImp().get(Integer.parseInt(id));
                if (student == null) {
                    JOptionPane.showMessageDialog(f, "查不到该学生");
                } else {
                    studentTableModel2.students = Arrays.asList(student);
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
        studentTableModel.students = new StudentDAOImp().list();
        table1.updateUI();
        if (!studentTableModel.students.isEmpty())
            table1.getSelectionModel().setSelectionInterval(0, 0);
    }

    static class EditDialog extends JDialog {
        JLabel lName = new JLabel("姓名");
        JLabel lAge = new JLabel("年龄");
        JLabel lSex = new JLabel("性别");
        JLabel lGrade = new JLabel("年级");
        JLabel lMajor = new JLabel("专业");
        JLabel lClass = new JLabel("班级");
        JLabel lCollege = new JLabel("学院");

        JTextField tfName = new JTextField();
        JTextField tfAge = new JTextField();
        JTextField tfSex = new JTextField();
        JTextField tfGrade = new JTextField();
        JTextField tfMajor = new JTextField();
        JTextField tfClass = new JTextField();
        JTextField tfCollege = new JTextField();

        JButton bSubmit = new JButton("提交");

        EditDialog(JFrame f) {
            super(f);

            this.setModal(true);
            int gap = 10;
            this.setLayout(null);

            JPanel pInput = new JPanel();
            JPanel pSubmit = new JPanel();

            pInput.setLayout(new GridLayout(7, 2, gap, gap));
            pInput.add(lName);
            pInput.add(tfName);
            pInput.add(lAge);
            pInput.add(tfAge);
            pInput.add(lSex);
            pInput.add(tfSex);
            pInput.add(lGrade);
            pInput.add(tfGrade);
            pInput.add(lMajor);
            pInput.add(tfMajor);
            pInput.add(lClass);
            pInput.add(tfClass);
            pInput.add(lCollege);
            pInput.add(tfCollege);

            pSubmit.add(bSubmit);

            this.setLayout(new BorderLayout());
            this.add(pInput, BorderLayout.CENTER);
            this.add(pSubmit, BorderLayout.SOUTH);

            this.setSize(600, 550);
            this.setLocationRelativeTo(f);
            bSubmit.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!IsEmpty.checkEmpty(tfName, "姓名")) return;
                    if (!IsNumber.isNumber(tfAge, "年龄")) return;
                    if (!IsEmpty.checkEmpty(tfSex, "性别")) return;
                    if (!IsEmpty.checkEmpty(tfGrade, "年级")) return;
                    if (!IsEmpty.checkEmpty(tfMajor, "专业")) return;
                    if (!IsEmpty.checkEmpty(tfClass, "班级")) return;
                    if (!IsEmpty.checkEmpty(tfCollege, "学院")) return;

                    int index = table1.getSelectedRow();
                    int id = studentTableModel.students.get(index).getStudentId();

                    String name = tfName.getText();
                    int age = Integer.parseInt(tfAge.getText());
                    String sex = tfSex.getText();
                    String grade = tfGrade.getText();
                    String major = tfMajor.getText();
                    String cla = tfClass.getText();
                    String college = tfCollege.getText();

                    Student student = new Student();
                    student.setStudentId(id);
                    student.setStudentName(name);
                    student.setStudentAge(age);
                    student.setStudentSex(sex);
                    student.setStudentGrade(grade);
                    student.setStudentMajor(major);
                    student.setStudentClass(cla);
                    student.setStudentCollege(college);
                    new StudentDAOImp().update(student);
                    JOptionPane.showMessageDialog(f, "提交成功");

                    EditDialog.this.setVisible(false);
                    updateTable();
                }
            });
        }
    }
}
