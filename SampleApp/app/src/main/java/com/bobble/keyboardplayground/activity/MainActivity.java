package com.bobble.keyboardplayground.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bobble.keyboardplayground.R;

/**
 * This activity represents a Activity of your app where promotional banners for the keyboard are being shown.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.launch).setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivationActivity.class);
            startActivity(intent);
        });
    }
}