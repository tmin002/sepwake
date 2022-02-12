package com.tminimal.sepwake.tts;

import com.tminimal.sepwake.d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MacosTTS extends TTS {

    public String voiceName = "Yuna";

    @Override
    public void speak(String str) throws IOException {
        Process say = Runtime.getRuntime().exec(new String[] {"say", "-v", voiceName, str});
        d.s(str);

        try {
            say.waitFor();
        } catch (InterruptedException e) {
            say.destroy();
        }
    }

    @Override
    public void speakScript(String[] script, int delayBetweenSentence) throws IOException {
        for (String str : script) {
            speak(str);
            try {
                Thread.sleep(delayBetweenSentence);
            } catch (InterruptedException ignored) {}
        }
    }
}
