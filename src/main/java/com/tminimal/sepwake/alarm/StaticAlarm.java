package com.tminimal.sepwake.alarm;

import com.tminimal.sepwake.d;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StaticAlarm extends Alarm {

    public Time preSleepStartTime = new Time(23,30);
    public Time sleepStartTime = new Time(0, 0);
    public Time ringTime = new Time(7, 0);

    public StaticAlarm() {
        this.setAlarmThread(new AlarmThread(this) {
            @Override
            public void waitLoop() throws InterruptedException {

                StaticAlarm sa = (StaticAlarm) this.alarm;
                int tm = Time.getCurrentTime().tm();

                if (sa.enabled){
                    if (sa.ringTime.tm() == tm) {
                        raiseAlarmEvent(AlarmEventType.RING, sa);
                    } else if (sa.sleepStartTime.tm() == tm) {
                        raiseAlarmEvent(AlarmEventType.SLEEPSTART, sa);
                    } else if (sa.preSleepStartTime.tm() == tm && sa.preSleepEnabled) {
                        raiseAlarmEvent(AlarmEventType.PRESLEEPSTART, sa);
                    }
                }

                Thread.sleep(1000 * (60 - LocalDateTime.now().getSecond()));
            }
        });
    }

    public int getPreSleepPeriod() {
        return Time.minuteModularSubtract(preSleepStartTime.tm(), sleepStartTime.tm());
    }
    public int getSleepPeriod() {
        return Time.minuteModularSubtract(sleepStartTime.tm(), ringTime.tm());
    }

}
