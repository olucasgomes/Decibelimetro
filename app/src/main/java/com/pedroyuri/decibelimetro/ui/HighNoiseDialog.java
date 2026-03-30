package com.pedroyuri.decibelimetro.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.pedroyuri.decibelimetro.databinding.DialogAlertNoiseBinding;

public class HighNoiseDialog extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogAlertNoiseBinding binding = DialogAlertNoiseBinding.inflate(inflater, container, false);
        binding.btnEntendi.setOnClickListener(v -> dismiss());
        return binding.getRoot();
    }
}