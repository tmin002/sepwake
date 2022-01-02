package com.tminimal.sepwake.alarm;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Alarm {

    public String name;
    public String onPreSleepStartScript;
    public String onSleepStartScript;
    public String onRingScript;
    public boolean enabled;
    public boolean preSleepEnabled;
    public int count;

    // Alarm wait thread
    public AlarmThread waitThread;

    // Alarm list
    private static ArrayList<Alarm> alarmList = new ArrayList<>();

    public static Alarm[] getAlarmList() {
        Alarm[] as = new Alarm[alarmList.size()];
        return alarmList.toArray(as);
    }

    public static void addAlarmToList(Alarm alarm) {
       alarmList.add(alarm);
       alarm.waitThread.startWait();
    }

    public static void resetAlarmListTo(ArrayList<Alarm> alarmlist) {
        for (Alarm a : alarmList) {
            a.waitThread.stopWait();
        }
        alarmList = alarmlist;
    }

    // Alarm events
    public final static ArrayList<AlarmEvent> alarmListenerList = new ArrayList<>();

    public static void raiseAlarmEvent(AlarmEventType type, Alarm alarm) {
        for (AlarmEvent ae : alarmListenerList) {
            ae.onEvent(type, alarm);
        }
    }

    public static void addAlarmListener(AlarmEvent e) {
       alarmListenerList.add(e);
    }

}