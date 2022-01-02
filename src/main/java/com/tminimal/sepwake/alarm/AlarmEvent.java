package com.tminimal.sepwake.alarm;

public interface AlarmEvent {
    void onEvent(AlarmEventType e, Alarm alarm);
}
