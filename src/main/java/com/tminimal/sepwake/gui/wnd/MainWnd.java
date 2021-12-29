package com.tminimal.sepwake.gui.wnd;

import com.tminimal.sepwake.gui.MainPanel;
import javax.swing.*;

public class MainWnd extends Window {

    public MainWnd() {
        super("Main Window", new MainPanel(), 300, 300);
        this.setVisible(true);
        System.out.println(UIManager.getLookAndFeel().getName());

    }
}
