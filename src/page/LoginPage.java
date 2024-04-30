package page;

import DAO.imp.AdminAccountDAOImpl;
import DAO.imp.StudentAccountDAOImp;
import DAO.imp.TeacherAccountDAOImpl;
import database.DBcon;
import orm.AdminAccount;
import orm.StudentAccount;
import orm.TeacherAccount;
import statics.Fontsize;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage {
    private JFrame f;
    private JComboBox<String> comboBox;
    private JLabel selectLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel panel;
    private ImageIcon background;
    private JLabel label;
    private JLabel titleLabel;

    public LoginPage() {
        f = new JFrame("欢迎登录");

        background = new ImageIcon("src/statics/img.jpg");
        label = new JLabel(background);
        label.setSize(background.getIconWidth(), background.getIconHeight());
        f.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

        panel = (JPanel) f.getContentPane();
        //设置为透明
        panel.setOpaque(false);

        titleLabel = new JLabel("考试成绩管理系统");
        titleLabel.setFont(Fontsize.font2);
        titleLabel.setBounds(600, 100, 800, 100);
        panel.add(titleLabel);


        selectLabel = new JLabel("选择你的身份：");
        selectLabel.setFont(Fontsize.font);
        selectLabel.setBounds(700, 300, 200, 30);
        panel.add(selectLabel);

        comboBox = new JComboBox<>();
        comboBox.addItem("--请选择--");
        comboBox.addItem("管理员");
        comboBox.addItem("教师");
        comboBox.addItem("学生");
        comboBox.setFont(Fontsize.font);
        comboBox.setBounds(900, 300, 200, 30);
        panel.add(comboBox);

        usernameLabel = new JLabel("用户名：");
        usernameLabel.setFont(Fontsize.font);
        usernameLabel.setBounds(700, 400, 200, 30);
        panel.add(usernameLabel);

        usernameField = new JTextField(200);
        usernameField.setBounds(900, 400, 200, 30);
        panel.add(usernameField);

        passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(Fontsize.font);
        passwordLabel.setBounds(700, 500, 200, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(200);
        passwordField.setBounds(900, 500, 200, 30);
        panel.add(passwordField);

        loginButton = new JButton("登录");
        loginButton.setFont(Fontsize.font);
        loginButton.setBounds(800, 600, 200, 50);
        loginButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String st = (String) comboBox.getSelectedItem();

                if ("--请选择--".equals(st)) {
                    JOptionPane.showMessageDialog(f, "请选择你的身份");
                } else {
                    String username = usernameField.getText();
                    String password = String.valueOf(passwordField.getPassword());
                    if ("".equals(username) || "".equals(password)) {
                        JOptionPane.showMessageDialog(f, "账号或密码不能为空！");
                    } else {
                        if ("管理员".equals(st)) {
                            AdminAccount adminAccount = new AdminAccount();
                            adminAccount.setAdmin_id(username);
                            adminAccount.setPassword(password);
                            boolean check = new AdminAccountDAOImpl().select(adminAccount);
                            if (check) {
                                JOptionPane.showMessageDialog(f, "登录成功");
                                f.dispose();
                                AdminPage adminPage = new AdminPage(adminAccount);
                            } else {
                                JOptionPane.showMessageDialog(f, "账户或密码错误，请重新输入！");
                            }
                        } else if ("教师".equals(st)) {
                            TeacherAccount teacherAccount = new TeacherAccount();
                            teacherAccount.setTeacher_id(Integer.parseInt(username));
                            teacherAccount.setPassword(password);
                            boolean check = new TeacherAccountDAOImpl().select(teacherAccount);
                            if (check) {
                                JOptionPane.showMessageDialog(f, "登录成功");
                                f.dispose();
                                TeacherPage teacherPage = new TeacherPage(teacherAccount);
                            } else {
                                JOptionPane.showMessageDialog(f, "账户或密码错误，请重新输入！");
                            }
                        } else if ("学生".equals(st)) {
                            StudentAccount studentAccount = new StudentAccount();
                            studentAccount.setStudent_id(Integer.parseInt(username));
                            studentAccount.setPassword(password);
                            boolean check = new StudentAccountDAOImp().select(studentAccount);
                            if (check) {
                                JOptionPane.showMessageDialog(f, "登录成功");
                                f.dispose();
                                StudentPage studentPage = new StudentPage(studentAccount);
                            } else {
                                JOptionPane.showMessageDialog(f, "账户或密码错误，请重新输入！");
                            }
                        }
                    }
                }
            }
        });

        panel.add(loginButton);

        f.setSize(background.getIconWidth(), background.getIconHeight());
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
