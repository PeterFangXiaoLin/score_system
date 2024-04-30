package page;

import gui.GradeTableModel;
import statics.Fontsize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StudentScoreSelect {
    private JFrame f;
    private JPanel panel;
    private JLabel label;
    private JComboBox<String> comboBox;
    private JTable table;
    private JButton bSelect, bReturn;
    private GradeTableModel gradeTableModel;

    public StudentScoreSelect(int id) {
        f = new JFrame("学生成绩查询");
        f.setSize(1200, 900);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        label = new JLabel("课程类型选择:");
        label.setFont(Fontsize.font);
        label.setBounds(400, 100, 150, 30);

        String[] s = {"--请选择--", "公共必修", "专业必修", "专业选修"};
        comboBox = new JComboBox<>(s);
        comboBox.setBounds(560, 100, 140, 30);
        comboBox.setFont(Fontsize.font);

        bSelect = new JButton("查询");
        bSelect.setFont(Fontsize.font);
        bSelect.setBounds(710, 100, 80, 30);
        bSelect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                String sel = (String) comboBox.getSelectedItem();
                gradeTableModel = new GradeTableModel(sel, id);
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
        panel.setBounds(100, 200, 1000, 500);
        panel.setLayout(new BorderLayout());


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
