package com.pharos.android3lesson4weatherapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.pharos.android3lesson4weatherapi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}