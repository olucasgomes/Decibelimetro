package com.pedroyuri.decibelimetro.audio;

import android.media.MediaRecorder;
import java.io.IOException;

public class AudioMeterManager {
    private MediaRecorder recorder;
    private boolean running = false;

    public void start(String cacheDir) throws IOException {
        if (running) return;
        String outputPath = cacheDir + "/temp_meter.3gp";
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(outputPath);
        recorder.prepare();
        recorder.start();
        running = true;
    }

    public void stop() {
        if (!running) return;
        try { recorder.stop(); } catch (Exception ignored) {}
        try { recorder.release(); } catch (Exception ignored) {}
        recorder = null;
        running = false;
    }

    public int getDb() {
        if (recorder == null || !running) return 0;
        try {
            int amplitude = recorder.getMaxAmplitude();
            if (amplitude <= 0) return 0;
            double db = 20 * Math.log10(amplitude);
            db = Math.max(0, Math.min(120, db));
            return (int) db;
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isRunning() {
        return running;
    }
}
