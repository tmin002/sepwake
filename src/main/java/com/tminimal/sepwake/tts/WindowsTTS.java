package com.tminimal.sepwake.tts;

import com.tminimal.sepwake.d;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WindowsTTS extends TTS{

    private static final String SPEAKER_SCRIPT = "CreateObject(\"SAPI.spVoice\").Speak(Wscript.Arguments(1))";
    private static final String SPEAKER_SCRIPT_PATH = ".\\sapi.vbs";
    @Override
    public void speak(String str) {
        try {
            createSpeaker();
            Process say = Runtime.getRuntime().exec(new String[] {"cscript", SPEAKER_SCRIPT_PATH, "/nologo", str});
            try {
                say.waitFor();
            } catch (InterruptedException e) {
                say.destroy();
            }
        } catch (IOException e) {
            d.s("Failed to initialize TTS.");
        }
    }

    private void createSpeaker() throws IOException {
        File speaker = new File(SPEAKER_SCRIPT_PATH);
        if (!speaker.isFile()) {
            FileWriter writer = new FileWriter(speaker);
            writer.write(SPEAKER_SCRIPT);
            writer.close();
        }
    }

}
