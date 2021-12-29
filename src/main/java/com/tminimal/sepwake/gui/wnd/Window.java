package com.tminimal.sepwake.gui.wnd;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

   public Window(String title, JPanel panel, int width, int height) {
       this.setTitle(title);
       this.setSize(new Dimension(width, height));
       this.setContentPane(panel);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
