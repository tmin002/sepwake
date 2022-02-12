package com.tminimal.sepwake;

import com.tminimal.sepwake.alarm.*;
import com.tminimal.sepwake.config.ConfigLoader;
import com.tminimal.sepwake.config.ConfigSaver;
import com.tminimal.sepwake.gui.msgbox.MsgBox;
import com.tminimal.sepwake.gui.wnd.MainWnd;
import com.tminimal.sepwake.gui.wnd.Window;
import com.tminimal.sepwake.ring.RingManager;
import com.tminimal.sepwake.ring.alarmScript;
import com.tminimal.sepwake.tts.MacosTTS;
import com.tminimal.sepwake.tts.TTS;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class SepWake {

    public static final String SEPWAKE_PWD = System.getProperty("user.dir");
    public static TTS defaultTTS;

    public static void main(String[] args){
        try {
            setup();
            MainWnd w = new MainWnd();

        } catch (Exception e) {
            String msg = "Exception occured!\n" + e.getMessage() + "\n";
            d.s(msg);
            e.printStackTrace();
            MsgBox.show(msg);
            System.exit(1);
        }
    }

    private static void setup() throws Exception {

        OSdetect.OStype type = OSdetect.detectOS();
        if (type == OSdetect.OStype.macOS) {
            defaultTTS = new MacosTTS();
        } else {
            MsgBox.show("This environment is not supported yet. (macOS only)");
            System.exit(1);
        }

        alarmScript.loadAlarmScript();
        RingManager.setRingEventListeners();
        ConfigLoader.loadConfig();
    }
}
