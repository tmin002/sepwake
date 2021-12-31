package com.tminimal.sepwake.alarm;

public abstract class Alarm {

    public String name;
    public String onPreSleepStartScript;
    public String onSleepStartScript;
    public String onRingScript;
    public boolean enabled;
    public boolean preSleepEnabled;
    public int count;

}