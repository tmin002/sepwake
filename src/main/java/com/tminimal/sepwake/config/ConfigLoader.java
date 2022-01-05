package com.tminimal.sepwake.config;

import com.tminimal.sepwake.SepWake;
import com.tminimal.sepwake.alarm.Alarm;
import com.tminimal.sepwake.alarm.StaticAlarm;
import com.tminimal.sepwake.alarm.Time;
import com.tminimal.sepwake.alarm.TimerAlarm;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ConfigLoader {
    private ConfigLoader() {}

    public static String configFilePath = SepWake.SEPWAKE_PWD + "/sepwake.yaml";

    public static void loadConfig() throws NullPointerException, FileNotFoundException {
        Map<String, Object> rawYaml = ConfigLoader.getRawData();

        if (rawYaml != null) {
            ConfigLoader.loadSettings(rawYaml);
            Alarm.resetAlarmListTo(ConfigLoader.parseAlarmList(rawYaml));
        } else {
            throw new NullPointerException("Empty configuration file");
        }
    }

    public static Map<String, Object> getRawData() throws FileNotFoundException {
        return new Yaml().load(new FileReader(configFilePath));
    }

    // Settings load (might use lombok in future version)
    public static void loadSettings(Map<String, Object> rawData) {

    }

    // Alarm load (might use lombok in future version)
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
                    }

                    if (added) break;
                }
            }
        }

        return alarmList;
    }

    // Only used for parseAlarmList.
    private static StaticAlarm parseStaticAlarm(Map<String, Object> rawData, String alarmName) {
        StaticAlarm a = new StaticAlarm();
        a.name = alarmName;

        for (Map.Entry<String, Object> en : rawData.entrySet()) {
            String key = en.getKey();
            switch (key) {
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
                    a.preSleepStartTime = Time.parseTimeString((String) en.getValue());
                    break;
                case "sleepStartTime":
                    a.sleepStartTime = Time.parseTimeString((String) en.getValue());
                    break;
                case "ringTime":
                    a.ringTime = Time.parseTimeString((String) en.getValue());
                    break;
                default:
                    // wrong type (which cannot exist tho)
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
                    a.startTime = Time.parseTimeString((String) en.getValue());
                    break;
                case "preSleepPeriod":
                    a.preSleepPeriod = (int) en.getValue();
                    break;
                case "sleepPeriod":
                    a.sleepPeriod = (int) en.getValue();
                    break;
                default:
                    // wrong type (which cannot exist tho)
                    break;
            }
        }
        return a;
    }


}
