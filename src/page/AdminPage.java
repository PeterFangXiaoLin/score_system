package page;

import orm.AdminAccount;
import statics.Fontsize;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AdminPage {
    private JFrame f;
    private JButton b1, b2, b3, b4, b5;

    public AdminPage(AdminAccount adminAccount) {
        f = new JFrame("管理员页面");
        f.setSize(1200, 900);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        b1 = new JButton("学生管理");
        b1.setBounds(100, 300, 150, 200);
        b1.setFont(Fontsize.font);
        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentManage();
            }
        });

        b2 = new JButton("教师管理");
        b2.setBounds(300, 300, 150, 200);
        b2.setFont(Fontsize.font);
        b2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeacherManage();
            }
        });

        b3 = new JButton("课程管理");
        b3.setBounds(500, 300, 150, 200);
        b3.setFont(Fontsize.font);
        b3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CourseManage();
            }
        });

        b4 = new JButton("修改密码");
        b4.setBounds(700, 300, 150, 200);
        b4.setFont(Fontsize.font);
        b4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpadtePassword upadtePassword = new UpadtePassword();
                upadtePassword.buttonListen(adminAccount);
            }
        });

        b5 = new JButton("退出");
        b5.setBounds(900, 300, 150, 200);
        b5.setFont(Fontsize.font);
        b5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.setVisible(true);
    }

}
