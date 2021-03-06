package com.tminimal.sepwake.ring;

import com.tminimal.sepwake.SepWake;
import com.tminimal.sepwake.alarm.Alarm;
import com.tminimal.sepwake.alarm.AlarmEventType;
import com.tminimal.sepwake.config.ConfigSaver;
import com.tminimal.sepwake.d;

import java.io.IOException;

public class RingManager {

    public static void setRingEventListeners() {
        Alarm.addAlarmListener((type, alarm) -> {
            String script = "";

            if (type == AlarmEventType.RING) {
               script = alarmScript.getWakeupScript(alarm);
            } else if (type == AlarmEventType.SLEEPSTART) {
                script = alarmScript.getSleepStartScript(alarm);
            } else if (type == AlarmEventType.PRESLEEPSTART) {
                script = alarmScript.getPreSleepScript(alarm);
            }
            SepWake.defaultTTS.speakAsync(script);

            if (type == AlarmEventType.RING) {
                alarm.count++;
                ConfigSaver.saveConfig();
            }
        });
    }
}
