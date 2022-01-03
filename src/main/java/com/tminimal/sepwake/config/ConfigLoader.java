package com.tminimal.sepwake.config;

import com.tminimal.sepwake.SepWake;
import com.tminimal.sepwake.alarm.Alarm;
import com.tminimal.sepwake.alarm.StaticAlarm;
import com.tminimal.sepwake.alarm.Time;
import com.tminimal.sepwake.alarm.TimerAlarm;
import com.tminimal.sepwake.d;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

public class ConfigLoader {

    public static String configFilePath = SepWake.SEPWAKE_PWD + "/sepwake.yaml";

    public static Map<String, Object> getRawData() {
        d.s(configFilePath);
        try {
            return new Yaml().load(new FileReader(configFilePath));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Alarm> parseAlarmList(Map<String, Object> rawData) {
        ArrayList<Alarm> alarmList = new ArrayList<>();
        Map<String, Object> rawList = (Map<String, Object>) rawData.get("alarms");

        for (String alarmName : rawList.keySet()) {
            Map<String, Object> alarmMap = (Map<String, Object>) rawList.get(alarmName);

            for (String key : alarmMap.keySet()) {
                if (key.equals("type")) {
                    String type = (String) alarmMap.get(key);
                    boolean added = false;

                    if (type.equals("static")) {
                        added = alarmList.add(parseStaticAlarm(alarmMap, alarmName));
                    } else if (type.equals("timer")) {
                        added = alarmList.add(parseTimerAlarm(alarmMap, alarmName));
                    } else {
                        // todo: wrong type.
                    }

                    if (added) break;
                }
            }
        }

        return alarmList;
    }

    // Only used for parseAlarmList.
    private static Time parseTimeString(String timeFormatString) {
        String[] split = timeFormatString.split(":");
        return new Time(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    // Only used for parseAlarmList.
    private static StaticAlarm parseStaticAlarm(Map<String, Object> rawData, String alarmName) {
        StaticAlarm a = new StaticAlarm();
        a.name = alarmName;

        for (Map.Entry<String, Object> en : rawData.entrySet()) {
            String key = en.getKey();
            switch (key) {
                case "onPreSleepStartScript":
                    a.onPreSleepStartScript = (String) en.getValue();
                    break;
                case "onSleepStartScript":
                    a.onSleepStartScript = (String) en.getValue();
                    break;
                case "onRingScript":
                    a.onRingScript = (String) en.getValue();
                    break;
                case "enabled":
                    a.enabled = (boolean) en.getValue();
                    break;
                case "preSleepEnabled":
                    a.preSleepEnabled = (boolean) en.getValue();
                    break;
                case "count":
                    a.count = (int) en.getValue();
                    break;
                case "preSleepStartTime":
                    a.preSleepStartTime = parseTimeString((String) en.getValue());
                    break;
                case "sleepStartTime":
                    a.sleepStartTime = parseTimeString((String) en.getValue());
                    break;
                case "ringTime":
                    a.ringTime = parseTimeString((String) en.getValue());
                    break;
                default:
                    // todo: wrong type.
                    break;
            }
        }
        return a;
    }

    // Only used for parseAlarmList.
    private static TimerAlarm parseTimerAlarm(Map<String, Object> rawData, String alarmName) {
        TimerAlarm a = new TimerAlarm();
        a.name = alarmName;

        for (Map.Entry<String, Object> en : rawData.entrySet()) {
            String key = en.getKey();
            switch (key) {
                case "onPreSleepStartScript":
                    a.onPreSleepStartScript = (String) en.getValue();
                    break;
                case "onSleepStartScript":
                    a.onSleepStartScript = (String) en.getValue();
                    break;
                case "onRingScript":
                    a.onRingScript = (String) en.getValue();
                    break;
                case "enabled":
                    a.enabled = (boolean) en.getValue();
                    break;
                case "preSleepEnabled":
                    a.preSleepEnabled = (boolean) en.getValue();
                    break;
                case "count":
                    a.count = (int) en.getValue();
                    break;
                case "startTime":
                    a.startTime = parseTimeString((String) en.getValue());
                    break;
                case "preSleepPeriod":
                    a.preSleepPeriod = (int) en.getValue();
                    break;
                case "sleepPeriod":
                    a.sleepPeriod = (int) en.getValue();
                    break;
                default:
                    // todo: wrong type.
                    break;
            }
        }
        return a;
    }


}
