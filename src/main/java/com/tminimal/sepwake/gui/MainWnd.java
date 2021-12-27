package com.tminimal.sepwake.gui;

import javax.swing.*;
import java.awt.*;

public class MainWnd extends JFrame {
    private JPanel myPanel;
    private JButton button1;
    private JRadioButton radioButton1;
    private JLabel Label1;

    public MainWnd() {
        this.setContentPane(new JPanel());
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
