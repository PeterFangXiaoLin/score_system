package page;

import orm.StudentAccount;
import statics.Fontsize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StudentPage {
    private JFrame f;
    private JButton b1, b2, b3;

    public StudentPage(StudentAccount studentAccount) {
        f = new JFrame("学生页面");
        f.setSize(1200, 900);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1 = new JButton("查询成绩");
        b1.setBounds(100, 300, 300, 300);
        b1.setFont(Fontsize.font);
        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentScoreSelect(studentAccount.getStudent_id());
            }
        });

        b2 = new JButton("修改密码");
        b2.setBounds(450, 300, 300, 300);
        b2.setFont(Fontsize.font);
        b2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpadtePassword upadtePassword = new UpadtePassword();
                upadtePassword.buttonListen(studentAccount);
            }
        });

        b3 = new JButton("退出");
        b3.setBounds(800, 300, 300, 300);
        b3.setFont(Fontsize.font);
        b3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.setVisible(true);
    }

}
