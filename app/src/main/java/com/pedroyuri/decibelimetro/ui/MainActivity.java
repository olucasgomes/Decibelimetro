package com.pedroyuri.decibelimetro.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.pedroyuri.decibelimetro.R;
import com.pedroyuri.decibelimetro.audio.AudioMeterManager;
import com.pedroyuri.decibelimetro.databinding.ActivityMainBinding;
import com.pedroyuri.decibelimetro.utils.NoiseUtils;

/**
 * Main Activity of the Decibelimetro application.
 * Manages the UI, sensor permissions, and coordinates the audio measurement process.
 * Complies with Unifor project requirements for native sensor usage and Material Design. o
 */
public class MainActivity extends AppCompatActivity {
    private static final int REQ_AUDIO = 1001;
    private static final long EXPOSURE_THRESHOLD_MS = 30000; // 30 seconds of persistent noise
    
    private ActivityMainBinding binding;
    private final AudioMeterManager meter = new AudioMeterManager();
    private final Handler handler = new Handler(Looper.getMainLooper());
    
    private boolean alertShown = false;
    private long highNoiseStartTime = 0;
    private boolean isUserActiveStart = false;

    /**
     * Periodic task that updates the UI with the latest decibel level from the sensor.
     * Implements thresholds for visual feedback and safety alerts with persistence check.
     */
    private final Runnable meterTask = new Runnable() {
        @Override
        public void run() {
            if (!meter.isRunning()) return;
            int db = meter.getDb();
            binding.gaugeView.setValue(db);
            binding.tvDbValue.setText(db + " dB");
            binding.tvStatus.setText(NoiseUtils.getStatus(db));
            binding.tvDescription.setText(NoiseUtils.getDescription(db));

            // Logic to show alert only after 30 seconds of continuous exposure > 85 dB
            if (db >= 85) {
                if (highNoiseStartTime == 0) {
                    highNoiseStartTime = System.currentTimeMillis();
                }
                
                long elapsed = System.currentTimeMillis() - highNoiseStartTime;
                
                // Show alert only if exposure time is reached and not already shown
                if (elapsed >= EXPOSURE_THRESHOLD_MS && !alertShown) {
                    alertShown = true;
                    new HighNoiseDialog().show(getSupportFragmentManager(), "noise_alert");
                }
            } else if (db < 80) {
                // Reset timer and alert status if noise drops below safety margin
                highNoiseStartTime = 0;
                alertShown = false;
            }

            // Visual UI updates based on noise level (Color feedback remains instant)
            updateVisualFeedback(db);

            handler.postDelayed(this, 350);
        }
    };

    private void updateVisualFeedback(int db) {
        if (db < 20) {
            binding.statusCard.setBackgroundResource(R.drawable.bg_status_green);
            binding.tvStatus.setTextColor(ContextCompat.getColor(this, R.color.green_bright));
            binding.tvDbValue.setTextColor(ContextCompat.getColor(this, R.color.green_bright));
        } else if (db < 60) {
            binding.statusCard.setBackgroundResource(R.drawable.bg_status_blue);
            binding.tvStatus.setTextColor(ContextCompat.getColor(this, R.color.blue_bright));
            binding.tvDbValue.setTextColor(ContextCompat.getColor(this, R.color.blue_bright));
        } else if (db < 85) {
            binding.statusCard.setBackgroundResource(R.drawable.bg_status_blue);
            binding.tvStatus.setTextColor(ContextCompat.getColor(this, R.color.orange));
            binding.tvDbValue.setTextColor(ContextCompat.getColor(this, R.color.orange));
        } else {
            binding.statusCard.setBackgroundResource(R.drawable.bg_status_red);
            binding.tvStatus.setTextColor(ContextCompat.getColor(this, R.color.red));
            binding.tvDbValue.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnToggle.setOnClickListener(v -> {
            if (meter.isRunning()) {
                isUserActiveStart = false;
                stopMeter();
            } else {
                isUserActiveStart = true;
                ensurePermissionAndStart();
            }
        });

        binding.btnInfo.setOnClickListener(v -> new NR15Dialog().show(getSupportFragmentManager(), "nr15"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (meter.isRunning()) {
            stopMeter();
        }
    }

    private void ensurePermissionAndStart() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            startMeter();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQ_AUDIO);
        }
    }

    private void startMeter() {
        try {
            highNoiseStartTime = 0; // Reset timer on start
            meter.start(getCacheDir().getAbsolutePath());
            binding.btnToggle.setText("Parar Medição");
            handler.post(meterTask);
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível iniciar o microfone.", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopMeter() {
        meter.stop();
        binding.btnToggle.setText("Iniciar Medição");
        handler.removeCallbacks(meterTask);
        highNoiseStartTime = 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMeter();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_AUDIO && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startMeter();
        } else {
            Toast.makeText(this, "Permissão de microfone negada.", Toast.LENGTH_SHORT).show();
        }
    }
}