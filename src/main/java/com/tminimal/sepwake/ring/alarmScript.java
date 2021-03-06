package com.tminimal.sepwake.ring;

import com.tminimal.sepwake.SepWake;
import com.tminimal.sepwake.alarm.Alarm;
import com.tminimal.sepwake.alarm.StaticAlarm;
import com.tminimal.sepwake.alarm.Time;
import com.tminimal.sepwake.alarm.TimerAlarm;
import com.tminimal.sepwake.d;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class alarmScript {

    private static String preSleepRawScript = "";
    private static String sleepStartRawScript = "";
    private static String wakeupRawScript = "";

    private final static String SEPWAKE_PRESLEEP_SCRIPT_PATH = SepWake.SEPWAKE_PWD + "/script_presleep.txt";
    private final static String SEPWAKE_SLEEPSTART_SCRIPT_PATH = SepWake.SEPWAKE_PWD + "/script_sleepstart.txt";
    private final static String SEPWAKE_WAKEUP_SCRIPT_PATH = SepWake.SEPWAKE_PWD + "/script_wakeup.txt";

    private final static String PLACEHOLDER_TIME = "%time%";
    private final static String PLACEHOLDER_DATE = "%date%";
    private final static String PLACEHOLDER_COUNT = "%count%";
    private final static String PLACEHOLDER_PRESLEEPTIME = "%presleeptime%";
    private final static String PLACEHOLDER_SLEEPTIME = "%sleeptime%";
    private final static String PLACEHOLDER_TOTALSLEEPTIME = "%totalsleeptime%";

    public static String getPreSleepScript(Alarm a) {
        return getCurrentScript(a, preSleepRawScript);
    }
    public static String getSleepStartScript(Alarm a) {
        return getCurrentScript(a, sleepStartRawScript);
    }
    public static String getWakeupScript(Alarm a) {
        return getCurrentScript(a, wakeupRawScript);
    }

    // 한국어만 지원 예정.
    private static String getCurrentScript(Alarm a, String rawScript) {
        // Get datetime string
        Date now = new Date();
        String date = new SimpleDateFormat("yyyy년 MM월 dd일").format(now);
        String time = new SimpleDateFormat("hh시 mm분").format(now);

        if (LocalDateTime.now().getHour() < 12) {
            time = "오전 " + time;
        } else {
            time = "오후 " + time;
        }

        // Get sleep period string
        String preSleepString = "", sleepString = "", totalString = "";
        if (a instanceof StaticAlarm) {
            StaticAlarm s = (StaticAlarm) a;
            preSleepString = Time.parsePeriodVerbalString(s.getPreSleepPeriod());
            sleepString = Time.parsePeriodVerbalString(s.getSleepPeriod());
            totalString = Time.parsePeriodVerbalString(s.getPreSleepPeriod() + s.getSleepPeriod());
        } else if (a instanceof TimerAlarm){
            TimerAlarm t = (TimerAlarm) a;
            preSleepString = Time.parsePeriodVerbalString(t.preSleepPeriod);
            sleepString = Time.parsePeriodVerbalString(t.sleepPeriod);
            totalString = Time.parsePeriodVerbalString(t.preSleepPeriod + t.sleepPeriod);
        }

        return rawScript
                .replace(PLACEHOLDER_TIME, time)
                .replace(PLACEHOLDER_DATE, date)
                .replace(PLACEHOLDER_COUNT, Integer.toString(a.count))
                .replace(PLACEHOLDER_PRESLEEPTIME, preSleepString)
                .replace(PLACEHOLDER_SLEEPTIME, sleepString)
                .replace(PLACEHOLDER_TOTALSLEEPTIME, totalString);

    }


    public static void loadAlarmScript() throws IOException {
        preSleepRawScript = getRawScript(SEPWAKE_PRESLEEP_SCRIPT_PATH);
        sleepStartRawScript = getRawScript(SEPWAKE_SLEEPSTART_SCRIPT_PATH);
        wakeupRawScript = getRawScript(SEPWAKE_WAKEUP_SCRIPT_PATH);
    }

    private static String getRawScript(String filePath) throws IOException {
        FileReader fr = new FileReader(filePath);
        StringBuilder result = new StringBuilder();

        boolean ignoreCurrentLine = false;
        boolean isPrevNewline = true;
        for (int i = 0; i != -1;)
        {
           i = fr.read();
           char c = (char) i;

           if (isPrevNewline && c == '#') {
               ignoreCurrentLine = true;
           }
           if (!ignoreCurrentLine) {
               result.append(c);
           }
           isPrevNewline = c == '\n';
           if (isPrevNewline) {
               ignoreCurrentLine = false;
           }
        }

        return result.toString();
    }
}
