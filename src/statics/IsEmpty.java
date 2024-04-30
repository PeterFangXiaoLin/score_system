package statics;

import javax.swing.*;

public class IsEmpty {
    public static boolean checkEmpty(JTextField tf, String msg) {
        String value = tf.getText();
        if (0 == value.length()) {
            JOptionPane.showMessageDialog(null, msg+"不能为空");
            tf.grabFocus();
            return false;
        }
        return true;
    }
}
