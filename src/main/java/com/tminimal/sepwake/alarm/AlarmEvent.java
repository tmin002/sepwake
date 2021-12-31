package com.tminimal.sepwake.alarm;

public interface AlarmEvent {

    void onPreSleepStart();
    void onSleepStart();
    void onRing();
}
