package statics;

import javax.swing.*;

public class IsNumber {
    public static boolean isNumber(JTextField tf, String msg) {
        String value = tf.getText();
        if (0 == value.length()) {
            JOptionPane.showMessageDialog(null, msg + "不能为空");
            tf.grabFocus();
            return false;
        }

        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, msg + "只能是整数");
            tf.grabFocus();
            return false;
        }
        return true;
    }
}
