package com.tminimal.sepwake.gui;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public JButton btn = new JButton("my name is button");
    public JLabel label = new JLabel("hello world");
    public JCheckBox box = new JCheckBox("hello checkbox");
    private int cnt = 0;

    public MainPanel() {

        this.setLayout(new GridLayout(11, 1));
        this.add(box);

        box.addActionListener(e -> {
            if (box.isSelected()) {
                javax.swing.JOptionPane.showMessageDialog(this, "im selected");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "im deselected");
            }
        });

        for (int i=0; i<10; i++) {
            JButton b = new JButton((i+1) + "번째 버튼");
            this.add(b);
            b.addActionListener(e -> {
                b.setText(String.valueOf(cnt++));
                javax.swing.JOptionPane.showMessageDialog(this, b.getText());
            });
        }

    }

}
