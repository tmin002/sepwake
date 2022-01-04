package com.tminimal.sepwake.alarm;

import java.util.IllegalFormatException;
import java.time.LocalDateTime;

public class Time {

    private int minutes;

    public Time(int h, int m) { this.setTime(h, m); }
    public Time(int m) { this.setTime(m); }

    public int[] getTime() {
        return new int[] {Math.floorDiv(this.minutes, 60), minutes%60};
    }

    public void setTime(int h, int m) { this.minutes = 60*h + m; }
    public void setTime(int m) { this.minutes = m; }

    public int h() { return this.getTime()[0]; } // getHours
    public int m() { return this.getTime()[1]; } // getMinutes
    public int tm() { return minutes; }

    public static Time getCurrentTime() {
        LocalDateTime t = LocalDateTime.now();
        return new Time(t.getHour(), t.getMinute());
    }

    public boolean isSameWith(Time t) { return this.minutes == t.tm(); }
    public boolean isSameWith(int m) { return this.minutes == m; }

    public void addMinute(int minutesToAdd) {
        this.minutes = minutesModularAdd(this.minutes, minutesToAdd);
    }
    public static int minutesModularAdd(int totalMinutes, int minutesToAdd) {
        return (totalMinutes + minutesToAdd) % (24*60);
    }

    public static Time parseTimeString(String timeFormatString) {
        String[] split = timeFormatString.split(":");
        return new Time(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
    public static String parseStringToTime(Time time) {
        String h = "", m = "";

        if (time.h()<10) h += "0";
        if (time.m()<10) m += "0";
        h += String.valueOf(time.h());
        m += String.valueOf(time.m());

        return h+":"+m;
    }
}
