package page;

import DAO.imp.AdminAccountDAOImpl;
import DAO.imp.StudentAccountDAOImp;
import DAO.imp.TeacherAccountDAOImpl;
import orm.AdminAccount;
import orm.StudentAccount;
import orm.TeacherAccount;
import statics.Fontsize;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpadtePassword {
    private JFrame f;
    private JLabel l1, l2, l3;
    private JPasswordField p1, p2, p3;

    private JButton b1, b2;

    public UpadtePassword() {
        f = new JFrame("修改密码");

        f.setSize(1200, 900);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        l1 = new JLabel("旧密码：");
        l1.setBounds(200, 200, 200, 30);
        l1.setFont(Fontsize.font);

        p1 = new JPasswordField(100);
        p1.setBounds(400, 200, 200, 30);

        l2 = new JLabel("新密码：");
        l2.setBounds(200, 400, 200, 30);
        l2.setFont(Fontsize.font);

        p2 = new JPasswordField(100);
        p2.setBounds(400, 400, 200, 30);

        l3 = new JLabel("确认新密码：");
        l3.setBounds(200, 600, 200, 30);
        l3.setFont(Fontsize.font);

        p3 = new JPasswordField(100);
        p3.setBounds(400, 600, 200, 30);

        b1 = new JButton("确认修改");
        b1.setBounds(300, 800, 200, 30);
        b1.setFont(Fontsize.font);

        b2 = new JButton("退出");
        b2.setBounds(600, 800, 80, 30);
        b2.setFont(Fontsize.font);
        b2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        f.add(l1);
        f.add(p1);
        f.add(l2);
        f.add(p2);
        f.add(l3);
        f.add(p3);
        f.add(b1);
        f.add(b2);
        f.setVisible(true);
    }
    public void buttonListen (StudentAccount studentAccount){
        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password1 = new String(p1.getPassword());
                String password2 = new String(p2.getPassword());
                String password3 = new String(p3.getPassword());

                if ("".equals(password1) || "".equals(password2) || "".equals(password3)) {
                    JOptionPane.showMessageDialog(f,"密码不能为空");
                } else {
                    if (!password1.equals(studentAccount.getPassword())) {
                        JOptionPane.showMessageDialog(f, "旧密码错误");
                    } else if (!password2.equals(password3)) {
                        JOptionPane.showMessageDialog(f,"两次密码不一致");
                    } else {
                        StudentAccount s = new StudentAccount();
                        s.setStudent_id(studentAccount.getStudent_id());
                        s.setPassword(password2);
                        new StudentAccountDAOImp().update(s);
                        JOptionPane.showMessageDialog(f,"密码修改成功！");
                        f.dispose();
                    }
                }
            }
        });
    }

    public void buttonListen (TeacherAccount teacherAccount){
        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password1 = new String(p1.getPassword());
                String password2 = new String(p2.getPassword());
                String password3 = new String(p3.getPassword());

                if ("".equals(password1) || "".equals(password2) || "".equals(password3)) {
                    JOptionPane.showMessageDialog(f,"密码不能为空");
                } else {
                    if (!password1.equals(teacherAccount.getPassword())) {
                        JOptionPane.showMessageDialog(f, "旧密码错误");
                    } else if (!password2.equals(password3)) {
                        JOptionPane.showMessageDialog(f,"两次密码不一致");
                    } else {
                        TeacherAccount t = new TeacherAccount();
                        t.setTeacher_id(teacherAccount.getTeacher_id());
                        t.setPassword(password2);
                        new TeacherAccountDAOImpl().update(t);
                        JOptionPane.showMessageDialog(f,"密码修改成功！");
                        f.dispose();
                    }
                }
            }
        });
    }

    public void buttonListen (AdminAccount adminAccount) {
        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password1 = new String(p1.getPassword());
                String password2 = new String(p2.getPassword());
                String password3 = new String(p3.getPassword());

                if ("".equals(password1) || "".equals(password2) || "".equals(password3)) {
                    JOptionPane.showMessageDialog(f,"密码不能为空");
                } else {
                    if (!password1.equals(adminAccount.getPassword())) {
                        JOptionPane.showMessageDialog(f, "旧密码错误");
                    } else if (!password2.equals(password3)) {
                        JOptionPane.showMessageDialog(f,"两次密码不一致");
                    } else {
                        AdminAccount a = new AdminAccount();
                        a.setAdmin_id(adminAccount.getAdmin_id());
                        a.setPassword(password2);
                        new AdminAccountDAOImpl().update(a);
                        JOptionPane.showMessageDialog(f,"密码修改成功！");
                        f.dispose();
                    }
                }
            }
        });
    }
}
