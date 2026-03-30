package com.pedroyuri.decibelimetro.audio;

import android.media.MediaRecorder;
import java.io.IOException;

/**
 * Manager class responsible for interfacing with the device's microphone.
 * Uses MediaRecorder to capture audio amplitude and convert it to decibels (dB).
 * This fulfills the requirement for using a native sensor in the project.
 */
public class AudioMeterManager {
    private MediaRecorder recorder;
    private boolean running = false;

    /**
     * Starts the audio recording process.
     * @param cacheDir The directory where the temporary audio file will be stored.
     * @throws IOException If the recorder fails to prepare or start.
     */
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

    /**
     * Stops and releases the MediaRecorder resources.
     */
    public void stop() {
        if (!running) return;
        try { recorder.stop(); } catch (Exception ignored) {}
        try { recorder.release(); } catch (Exception ignored) {}
        recorder = null;
        running = false;
    }

    /**
     * Calculates the current decibel level based on the maximum amplitude captured
     * since the last call to this method.
     * 
     * Formula: dB = 20 * log10(Amplitude)
     * @return The decibel level as an integer, ranging from 0 to 120.
     */
    public int getDb() {
        if (recorder == null || !running) return 0;
        try {
            int amplitude = recorder.getMaxAmplitude();
            if (amplitude <= 0) return 0;
            // Standard approximation for dB calculation from raw amplitude
            double db = 20 * Math.log10(amplitude);
            db = Math.max(0, Math.min(120, db));
            return (int) db;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * @return True if the recorder is currently active.
     */
    public boolean isRunning() {
        return running;
    }
}