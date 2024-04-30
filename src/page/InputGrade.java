package page;

import DAO.imp.CourseDAOImp;
import DAO.imp.ReportsDAOImp;
import DAO.imp.TeacherDAOImp;
import gui.GradeTableModel;
import orm.Course;
import orm.Reports;
import orm.Teacher;
import statics.Fontsize;
import statics.IsFloat;
import statics.IsNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InputGrade {
    private JFrame f;
    private JLabel label;
    private JButton bConfirm, bEdit, bReturn;
    private JComboBox<String> comboBox;
    private static GradeTableModel gradeTableModel = new GradeTableModel();
    private JPanel panel;
    private static JTable table;
    private JScrollPane sp;
    private JPanel p;

    public InputGrade(int teacher_id) {
        Teacher teacher = new TeacherDAOImp().get(teacher_id);
        List<Course> courses = new CourseDAOImp().list(teacher.getTeacherName());
        f = new JFrame("录入成绩");
        f.setSize(1200, 900);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        label = new JLabel("请选择课程:");
        label.setBounds(400, 100, 120, 30);
        label.setFont(Fontsize.font);

        List<Course> list = new CourseDAOImp().list();
        List<String> s = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            s.add(String.valueOf(list.get(i).getCourse_id()));
        }
        comboBox = new JComboBox<>(s.toArray(new String[0]));
        comboBox.setFont(Fontsize.font);
        comboBox.setBounds(530, 100, 120, 30);

        bConfirm = new JButton("确认");
        bConfirm.setFont(Fontsize.font);
        bConfirm.setBounds(660, 100, 80, 30);
        bConfirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt((String) comboBox.getSelectedItem());
                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getCourse_id() == id) {
                        gradeTableModel.reports = new ReportsDAOImp().list2(id);
                        updateTable(id);
                    }
                }
            }
        });

        bEdit = new JButton("编辑成绩");
        bEdit.setFont(Fontsize.font);
        bEdit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();
                if (-1 == index) {
                    JOptionPane.showMessageDialog(f, "编辑前需要先选中一行");
                    return;
                }

                Reports reports = gradeTableModel.reports.get(index);

                EditDialog ed = new EditDialog(f, reports.getCourse_id());
                ed.tfUsualGrades.setText(String.valueOf(reports.getUsual_grades()));
                ed.tfFinalGrades.setText(String.valueOf(reports.getFinal_grades()));

                ed.setVisible(true);
            }
        });
        p = new JPanel();
        p.add(bEdit);

        table = new JTable(gradeTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (!gradeTableModel.reports.isEmpty())
            table.getSelectionModel().setSelectionInterval(0, 0);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setFont(Fontsize.font);
        sp = new JScrollPane(table);

        panel = new JPanel();
        panel.setBounds(100, 150, 1000, 600);
        panel.setBackground(Color.GRAY);
        panel.setLayout(new BorderLayout());
        panel.add(sp, BorderLayout.CENTER);
        panel.add(p, BorderLayout.SOUTH);

        bReturn = new JButton("返回");
        bReturn.setFont(Fontsize.font);
        bReturn.setBounds(500, 800, 200, 30);
        bReturn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });
        f.add(bReturn);

        f.add(label);
        f.add(comboBox);
        f.add(bConfirm);
        f.add(panel);

        f.setVisible(true);
    }

    static class EditDialog extends JDialog {
        JLabel lUsualGrades = new JLabel("平时成绩");
        JLabel lFinalGrades = new JLabel("期末成绩");

        JTextField tfUsualGrades = new JTextField();
        JTextField tfFinalGrades = new JTextField();

        JButton bSubmit = new JButton("保存");

        EditDialog(JFrame f, int id) {
            super(f);
            this.setModal(true);
            int gap = 10;
            this.setLayout(null);

            JPanel pInput = new JPanel();
            JPanel pSubmit = new JPanel();

            pInput.setLayout(new GridLayout(2, 2, gap, gap));
            tfFinalGrades.setHorizontalAlignment(JTextField.CENTER);
            tfUsualGrades.setHorizontalAlignment(JTextField.CENTER);
            pInput.add(lUsualGrades);
            pInput.add(tfUsualGrades);
            pInput.add(lFinalGrades);
            pInput.add(tfFinalGrades);

            pSubmit.add(bSubmit);

            this.setLayout(new BorderLayout());
            this.add(pInput, BorderLayout.CENTER);
            this.add(pSubmit, BorderLayout.SOUTH);

            this.setSize(600, 550);
            this.setLocationRelativeTo(f);

            bSubmit.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!IsNumber.isNumber(tfUsualGrades, "平时成绩")) return;
                    if (!IsNumber.isNumber(tfFinalGrades, "期末成绩")) return;

                    int index = table.getSelectedRow();
                    int student_id = gradeTableModel.reports.get(index).getStudent_id();
                    int course_id = gradeTableModel.reports.get(index).getCourse_id();
                    float s = gradeTableModel.reports.get(index).getScore();

                    int usual_grades = Integer.parseInt(tfUsualGrades.getText());
                    int final_grades = Integer.parseInt(tfFinalGrades.getText());

                    Reports reports = new Reports();
                    reports.setUsual_grades(usual_grades);
                    reports.setFinal_grades(final_grades);
                    reports.setStudent_id(student_id);
                    reports.setCourse_id(course_id);
                    reports.setScore(s);
                    /**
                     * 计算绩点，总成绩
                     */

                    new ReportsDAOImp().update(reports);

                    JOptionPane.showMessageDialog(f, "保存成功");
                    EditDialog.this.setVisible(false);
                    updateTable(id);
                }
            });
        }
    }

    public static void updateTable(int id) {
        gradeTableModel.reports = new ReportsDAOImp().list3(id);
        table.updateUI();
        if (!gradeTableModel.reports.isEmpty())
            table.getSelectionModel().setSelectionInterval(0, 0);
    }
}
