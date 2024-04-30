package statics;

import javax.swing.*;

public class IsFloat {
    public static boolean isFloat(JTextField tf, String msg) {
        String value = tf.getText();
        if (0 == value.length()) {
            JOptionPane.showMessageDialog(null, msg + "不能为空");
            tf.grabFocus();
            return false;
        }

        try {
            Float.parseFloat(value);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, msg + "只能是数字");
            tf.grabFocus();
            return false;
        }
        return true;
    }
}
