package com.tminimal.sepwake.alarm;

import com.tminimal.sepwake.d;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Alarm {

    public String name;
    public boolean enabled = true;
    public boolean preSleepEnabled = true;
    public int count = 0;

    // Alarm wait thread
    public AlarmThread waitThread;
    public void setAlarmThread(AlarmThread alarmThread) {
        waitThread = alarmThread;
    }

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

    public static void resetAlarmListTo(ArrayList<Alarm> alarms) {
        alarmList.clear();
        for (Alarm a : alarms) {
            addAlarmToList(a);
        }
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