package com.tminimal.sepwake.gui.msgbox;

import javax.swing.*;

public class MsgBox {
    private MsgBox() {}

    public static String defaultMsgBoxTitle = "sepwake";

    public static void show(String text) {
        JOptionPane.showConfirmDialog(null, text, defaultMsgBoxTitle, JOptionPane.DEFAULT_OPTION);
    }

    public enum MsgType {
        QUESTION, INFORMATION, ERROR
    }
}
