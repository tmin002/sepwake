package com.tminimal.sepwake.tts;

import java.io.IOException;

public abstract class TTS {
    public abstract void speak(String str) throws IOException;
    public abstract void speakScript(String[] str, int delayBetweenSentence) throws IOException;
}
