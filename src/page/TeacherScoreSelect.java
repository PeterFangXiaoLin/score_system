package page;

import DAO.imp.CourseDAOImp;
import gui.GradeTableModel;
import orm.Course;
import statics.Fontsize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class TeacherScoreSelect {
    private JFrame f;
    private JLabel label;
    private JComboBox<String> comboBox;
    private JButton bSelect, bReturn;
    private JPanel panel;
    private JTable table;
    private GradeTableModel gradeTableModel;

    public TeacherScoreSelect() {
        f = new JFrame("成绩查询");
        f.setSize(1200, 900);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        label = new JLabel("请选择课程编号:");
        label.setBounds(390, 100, 160, 30);
        label.setFont(Fontsize.font);

        List<Course> courseList = new CourseDAOImp().list();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            list.add(String.valueOf(courseList.get(i).getCourse_id()));
        }
        comboBox = new JComboBox<>(list.toArray(new String[0]));
        comboBox.setFont(Fontsize.font);
        comboBox.setBounds(560, 100, 80, 30);

        bSelect = new JButton("查询");
        bSelect.setBounds(650, 100, 80, 30);
        bSelect.setFont(Fontsize.font);
        bSelect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                String sel = (String) comboBox.getSelectedItem();
                gradeTableModel = new GradeTableModel(Integer.parseInt(sel));
                table = new JTable(gradeTableModel);
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (!gradeTableModel.reports.isEmpty())
                    table.getSelectionModel().setSelectionInterval(0, 0);

                table.getTableHeader().setReorderingAllowed(false);
                table.getTableHeader().setResizingAllowed(false);
                table.getTableHeader().setFont(Fontsize.font);

                JScrollPane sp = new JScrollPane(table);
                panel.add(sp, BorderLayout.CENTER);
                panel.updateUI();
            }
        });

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(100, 200, 1000, 500);

        bReturn = new JButton("返回");
        bReturn.setBounds(550, 750, 100, 30);
        bReturn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });
        bReturn.setFont(Fontsize.font);

        f.add(label);
        f.add(comboBox);
        f.add(bSelect);
        f.add(panel);
        f.add(bReturn);
        f.setVisible(true);
    }

}
