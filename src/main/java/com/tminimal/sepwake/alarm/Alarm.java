package com.tminimal.sepwake.alarm;

import java.util.ArrayList;

public abstract class Alarm {

    public String name;
    public String onPreSleepStartScript;
    public String onSleepStartScript;
    public String onRingScript;
    public boolean enabled = true;
    public boolean preSleepEnabled = true;
    public int count = 0;

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

    public static void resetAlarmListTo(ArrayList<Alarm> alarmList) {
        for (Alarm a : alarmList) {
            a.waitThread.stopWait();
        }
        Alarm.alarmList = alarmList;
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