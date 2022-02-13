package com.tminimal.sepwake.alarm;

import java.time.LocalDateTime;

public class TimerAlarm extends Alarm {

    public Time startTime = new Time(23, 30);
    public int preSleepPeriod = 30; // minutes;
    public int sleepPeriod = 420;

    public TimerAlarm() {
        this.setAlarmThread(new AlarmThread(this) {
            @Override
            public void waitLoop() throws InterruptedException {
                int tm = Time.getCurrentTime().tm();
                TimerAlarm ta = (TimerAlarm) this.alarm;

                if (startTime.tm() >= tm) {
                    if (ta.enabled){

                        int preSleepStartTime = ta.startTime.tm();
                        int sleepStartTime = ta.preSleepEnabled ? preSleepStartTime : preSleepStartTime + ta.preSleepPeriod;
                        int ringTime = sleepStartTime + ta.sleepPeriod;

                        if (ringTime == tm) {
                            raiseAlarmEvent(AlarmEventType.RING, ta);
                        } else if (sleepStartTime == tm) {
                            raiseAlarmEvent(AlarmEventType.SLEEPSTART, ta);
                        } else if ((preSleepStartTime == tm) && ta.preSleepEnabled) {
                            raiseAlarmEvent(AlarmEventType.PRESLEEPSTART, ta);
                        }
                    }
                }
                Thread.sleep(1000 * (60 - LocalDateTime.now().getSecond()));
            }
        });
    }

    public Time getPreSleepTime() {
        return startTime;
    }
    public Time getSleepStartTime() {
        return getPreSleepTime().addMinute(preSleepPeriod);
    }
    public Time getRingTime() {
        return getSleepStartTime().addMinute(sleepPeriod);
    }
}
