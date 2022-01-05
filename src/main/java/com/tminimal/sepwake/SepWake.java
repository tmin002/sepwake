package com.tminimal.sepwake;

import com.tminimal.sepwake.alarm.*;
import com.tminimal.sepwake.config.ConfigLoader;
import com.tminimal.sepwake.config.ConfigSaver;
import com.tminimal.sepwake.gui.msgbox.MsgBox;
import com.tminimal.sepwake.gui.wnd.MainWnd;
import com.tminimal.sepwake.gui.wnd.Window;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class SepWake {

    public static final String SEPWAKE_PWD = System.getProperty("user.dir");

    public static void main(String[] args){
        try {
            setup();
        } catch (Exception e) {
            String msg = "Exception occured!\n" + e.getMessage() + "\n";
            d.s(msg);
            e.printStackTrace();
            MsgBox.show(msg);
            System.exit(1);
        }
    }

    private static void setup() throws Exception {
        ConfigLoader.loadConfig();
        ConfigSaver.printCurrentConfig();
        ConfigSaver.saveConfig();

    }
}
