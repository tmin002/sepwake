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

    public Time addMinute(int minutesToAdd) {
        this.minutes = minutesModularAdd(this.minutes, minutesToAdd);
        return this;
    }

    @Override
    public String toString() {
        String h = "", m = "";

        if (this.h()<10) h += "0";
        if (this.m()<10) m += "0";
        h += String.valueOf(this.h());
        m += String.valueOf(this.m());

        return h+":"+m;
    }

    // static

    public static int minutesModularAdd(int totalMinutes, int minutesToAdd) {
        return (totalMinutes + minutesToAdd) % (24*60);
    }
    public static int minuteModularSubtract(int eariler, int later) {
        return eariler <= later ?
                later - eariler :
                24*60 - eariler + later;
    }

    public static Time stringToTimeInstance(String timeFormatString) { // "00:00" -> new Time(0, 0)
        String[] split = timeFormatString.split(":");
        return new Time(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }
    public static String parseTimeVerbalString(int minutes) { // new Time(0, 0) -> "오전 12시 0분"
        int m = minutes % 60, h = (minutes - m) / 60;
        String result = h<12 ? "오전 ":"오후 ";
        if (h == 0) h = 12;
        if (h > 12) h -= 12;
        result += h + "시 ";
        result += m + "분";

        return result;
    }
    public static String parsePeriodVerbalString(int minutes) { // new Time(2, 3) -> "2시간 3분"
       int m = minutes % 60, h = (minutes - m) / 60;
       String result = "";
       if (h != 0) result += h + "시간 ";
       result += m + "분";

       return result;
    }
}
