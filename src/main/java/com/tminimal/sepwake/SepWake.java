package com.tminimal.sepwake;

import com.tminimal.sepwake.alarm.*;
import com.tminimal.sepwake.config.ConfigLoader;
import com.tminimal.sepwake.gui.wnd.MainWnd;
import com.tminimal.sepwake.gui.wnd.Window;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;

public class SepWake {

    public static final String SEPWAKE_PWD = System.getProperty("user.dir");

    public static void main(String[] args){
        setup();
    }

    private static void setup() {
    }
}
