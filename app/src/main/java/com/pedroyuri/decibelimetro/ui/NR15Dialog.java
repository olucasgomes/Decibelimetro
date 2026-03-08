package com.pedroyuri.decibelimetro.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.pedroyuri.decibelimetro.databinding.DialogNr15Binding;

public class NR15Dialog extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogNr15Binding binding = DialogNr15Binding.inflate(inflater, container, false);
        binding.btnClose.setOnClickListener(v -> dismiss());
        return binding.getRoot();
    }
}
