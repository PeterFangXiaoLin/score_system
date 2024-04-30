package page;

import DAO.imp.TeacherDAOImp;
import gui.TeacherTableModel;
import orm.Teacher;
import statics.Fontsize;
import statics.IsEmpty;
import statics.IsNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class TeacherManage {
    private JFrame f;
    private JLabel l1, l2, l3, l4;
    private JTextField t1, t2, t3, t4;
    private JButton bInsert;

    private static TeacherTableModel teacherTableModel = new TeacherTableModel();
    private TeacherTableModel teacherTableModel2 = new TeacherTableModel();
    private static JTable table1;
    private JScrollPane sp1, sp2;
    private JButton bDelete, bEdit;

    private JPanel p1, p2, p3;

    private JLabel label;
    private JTextField textField;
    private JButton bSelect;
    private JTable table2;

    private JButton bReturn;

    public TeacherManage() {
        f = new JFrame("教师管理页面");
        f.setSize(1200, 900);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        l1 = new JLabel("教师姓名:");
        l1.setBounds(30, 30, 100, 30);
        l1.setFont(Fontsize.font);
        t1 = new JTextField(100);
        t1.setBounds(140, 30, 100, 30);

        l2 = new JLabel("教师性别:");
        l2.setBounds(30, 70, 100, 30);
        l2.setFont(Fontsize.font);
        t2 = new JTextField(100);
        t2.setBounds(140, 70, 100, 30);

        l3 = new JLabel("教师年龄:");
        l3.setBounds(30, 110, 100, 30);
        l3.setFont(Fontsize.font);
        t3 = new JTextField(100);
        t3.setBounds(140, 110, 100, 30);

        l4 = new JLabel("教师职称:");
        l4.setBounds(30, 150, 100, 30);
        l4.setFont(Fontsize.font);
        t4 = new JTextField(100);
        t4.setBounds(140, 150, 100,30);

        bInsert = new JButton("插入");
        bInsert.setBounds(120, 200, 100, 30);
        bInsert.setFont(Fontsize.font);
        bInsert.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!IsEmpty.checkEmpty(t1, "教师姓名")) return;
                if (!IsEmpty.checkEmpty(t2, "性别")) return;
                if (!IsNumber.isNumber(t3, "年龄")) return;
                if (!IsEmpty.checkEmpty(t4, "职称")) return;

                String name = t1.getText();
                String sex = t2.getText();
                int age = Integer.parseInt(t3.getText());
                String title = t4.getText();
                Teacher teacher = new Teacher();
                teacher.setTeacherName(name);
                teacher.setTeacherSex(sex);
                teacher.setTeacherAge(age);
                teacher.setTeacherTitle(title);

                new TeacherDAOImp().add(teacher);
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
        f.add(bInsert);

        p1 = new JPanel();
        p1.setBackground(Color.GRAY);
        p1.setBounds(300, 10, 800, 500);
        p1.setLayout(new BorderLayout());

        table1 = new JTable(teacherTableModel);

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (!teacherTableModel.teachers.isEmpty())
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

                Teacher teacher = teacherTableModel.teachers.get(index);
                int id = teacher.getTeacherId();
                boolean check = new TeacherDAOImp().delete(id);
                if (!check) {
                    JOptionPane.showMessageDialog(f, "删除失败，请先删除该老师相关的课程");
                    return;
                }

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

                Teacher teacher = teacherTableModel.teachers.get(index);

                EditDialog ed = new EditDialog(f);
                ed.tfName.setText(teacher.getTeacherName());
                ed.tfAge.setText(String.valueOf(teacher.getTeacherAge()));
                ed.tfSex.setText(teacher.getTeacherSex());
                ed.tfTitle.setText(teacher.getTeacherTitle());

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

        p2 = new JPanel();
        p2.setBounds(200, 590, 800, 200);
        p2.setBackground(Color.GRAY);

        teacherTableModel2.teachers = Arrays.asList();
        table2 = new JTable(teacherTableModel2);
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (!teacherTableModel2.teachers.isEmpty())
            table2.getSelectionModel().setSelectionInterval(0, 0);

        table2.getTableHeader().setReorderingAllowed(false);
        table2.getTableHeader().setResizingAllowed(false);
        table2.getTableHeader().setFont(Fontsize.font);
        sp2 = new JScrollPane(table2);
        p2.setLayout(new BorderLayout());
        p2.add(sp2, BorderLayout.CENTER);
        f.add(p2);

        bSelect = new JButton("查询");
        bSelect.setFont(Fontsize.font);
        bSelect.setBounds(310, 550, 100, 30);
        bSelect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!IsNumber.isNumber(textField, "编号")) return;

                String id = textField.getText();
                Teacher teacher = new TeacherDAOImp().get(Integer.parseInt(id));
                if (teacher == null) {
                    JOptionPane.showMessageDialog(f,"查不到该教师");
                } else {
                    teacherTableModel2.teachers = Arrays.asList(teacher);
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
        teacherTableModel.teachers = new TeacherDAOImp().list();
        table1.updateUI();
        if (!teacherTableModel.teachers.isEmpty())
            table1.getSelectionModel().setSelectionInterval(0, 0);
    }

    static class EditDialog extends JDialog {
        JLabel lName = new JLabel("姓名");
        JLabel lAge = new JLabel("年龄");
        JLabel lSex = new JLabel("性别");
        JLabel lTitle = new JLabel("职称");

        JTextField tfName = new JTextField();
        JTextField tfAge = new JTextField();
        JTextField tfSex = new JTextField();
        JTextField tfTitle = new JTextField();

        JButton bSubmit = new JButton("提交");

        EditDialog(JFrame f) {
            super(f);

            this.setModal(true);
            int gap = 10;
            this.setLayout(null);

            JPanel pInput = new JPanel();
            JPanel pSubmit = new JPanel();

            pInput.setLayout(new GridLayout(4, 2, gap, gap));
            pInput.add(lName);
            pInput.add(tfName);
            pInput.add(lAge);
            pInput.add(tfAge);
            pInput.add(lSex);
            pInput.add(tfSex);
            pInput.add(lTitle);
            pInput.add(tfTitle);

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
                    if (!IsEmpty.checkEmpty(tfTitle, "职称")) return;

                    int index = table1.getSelectedRow();
                    int id = teacherTableModel.teachers.get(index).getTeacherId();

                    String name = tfName.getText();
                    int age = Integer.parseInt(tfAge.getText());
                    String sex = tfSex.getText();
                    String title = tfTitle.getText();

                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(id);
                    teacher.setTeacherName(name);
                    teacher.setTeacherAge(age);
                    teacher.setTeacherSex(sex);
                    teacher.setTeacherTitle(title);
                    new TeacherDAOImp().update(teacher);
                    JOptionPane.showMessageDialog(f, "提交成功");

                    EditDialog.this.setVisible(false);
                    updateTable();
                }
            });
        }
    }

}
