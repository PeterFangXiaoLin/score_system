package page;

import orm.TeacherAccount;
import statics.Fontsize;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TeacherPage {
    private JFrame f;

    private JButton b1, b2, b3, b4;

    public TeacherPage(TeacherAccount teacherAccount) {
        f = new JFrame("教师页面");
        f.setSize(1200, 900);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1 = new JButton("查看学生成绩");
        b1.setBounds(50, 300, 200, 200);
        b1.setFont(Fontsize.font);
        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeacherScoreSelect();
            }
        });

        b2 = new JButton("录入成绩");
        b2.setBounds(350, 300, 200, 200);
        b2.setFont(Fontsize.font);
        b2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InputGrade(teacherAccount.getTeacher_id());
            }
        });

        b3 = new JButton("修改密码");
        b3.setBounds(650, 300, 200, 200);
        b3.setFont(Fontsize.font);
        b3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpadtePassword upadtePassword = new UpadtePassword();
            }
        });

        b4 = new JButton("退出");
        b4.setBounds(950, 300, 200, 200);
        b4.setFont(Fontsize.font);
        b4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.setVisible(true);
    }

}
