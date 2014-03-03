package com.github.norbo11.norbzcomm.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class UIHelper {
    public static void showErrorDialog(Component c, Object o) {
        JOptionPane.showMessageDialog(c, o, "Error!", JOptionPane.ERROR_MESSAGE);
    }

    public static void showMessageDialog(Component c, Object o) {
        JOptionPane.showMessageDialog(c, o);
    }
}
