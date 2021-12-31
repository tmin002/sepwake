package com.tminimal.sepwake.alarm;

import java.util.ArrayList;

public class AlarmThread {

    private static Thread waiter = null;
    private final ArrayList<Alarm> alarmList;
    public final ArrayList<AlarmEvent> alarmEventList;

    private AlarmThread(ArrayList<Alarm> alarmList) {
        alarmEventList = new ArrayList<>();
        waiter = new Thread(this::threadJob);
        this.alarmList = alarmList;
    }

    // Thread management

    public AlarmThread createNewAlarmThread(ArrayList<Alarm> alarmList) {
        if (waiter == null) return new AlarmThread(alarmList);
        else return null;
    }

    public void startThread() {
        waiter.start();
    }

    public void stopThread() {
        waiter.interrupt();
    }

    // Alarm events

    public void addAlarmEventListener(AlarmEvent ae) {
        alarmEventList.add(ae);
    }

    private void raiseRingEvent() {
        for (AlarmEvent ae : alarmEventList) ae.onRing();
    }
    private void raisePreSleepStartEvent() {
        for (AlarmEvent ae : alarmEventList) ae.onPreSleepStart();
    }
    private void raiseSleepStartEvent() {
        for (AlarmEvent ae : alarmEventList) ae.onSleepStart();
    }

    // Thread job

    private void threadJob() {
        while (true) {
            for (Alarm a : alarmList) {
                if (a.enabled) {
                    Time preSleepStart, sleepStart, ring;

                    if (a.getClass() == StaticAlarm.class) {
                       StaticAlarm s = (StaticAlarm) a;
                       preSleepStart = s.preSleepStartTime;
                       sleepStart = s.sleepStartTime;
                       ring = s.ringTime;
                   } else { // TimerAlarm
                       TimerAlarm t = (TimerAlarm) a;
                       preSleepStart = t.startTime;
                       sleepStart = new Time(Time.minutesModularAdd(preSleepStart.tm(), t.preSleepPeriod));
                       ring = new Time(Time.minutesModularAdd(sleepStart.tm(), t.sleepPeriod));
                   }

                    if (preSleepStart.isSameWith(Time.getCurrentTime())) raiseRingEvent();
                    else if (sleepStart.isSameWith(Time.getCurrentTime())) raiseSleepStartEvent();
                    else if (ring.isSameWith(Time.getCurrentTime())) raisePreSleepStartEvent();
                }
            }
        }
    }

}
