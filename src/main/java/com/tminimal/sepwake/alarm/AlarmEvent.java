package com.tminimal.sepwake.alarm;

import java.io.IOException;

public interface AlarmEvent {
    void onEvent(AlarmEventType e, Alarm alarm);
}
