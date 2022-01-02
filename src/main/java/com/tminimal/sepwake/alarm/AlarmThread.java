package com.tminimal.sepwake.alarm;

import com.tminimal.sepwake.d;

import java.time.LocalDateTime;

public abstract class AlarmThread {

    public Alarm alarm;
    private Thread waitThread;

    public AlarmThread(Alarm alarm) {
       this.alarm = alarm;
       this.waitThread = createNewWaitThread();
    }

    private Thread createNewWaitThread() {
        return new Thread(this::waitJobWrapper);
    }

    public void startWait() {
        //todo: check thread state before start and stop
        waitThread.start();
    }
    public void stopWait() {
        if (waitThread != null) waitThread.interrupt();
        waitThread = createNewWaitThread();
    }

    public abstract void waitLoop() throws InterruptedException;
    public void waitJobWrapper() {
        while (true) {
            try {
                waitLoop();
            } catch (InterruptedException ignored) {
                break;
            }
        }
    }
}
