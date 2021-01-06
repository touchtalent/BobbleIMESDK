package com.bobble.keyboardplayground.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bobble.keyboardplayground.R;

/**
 * This is a activity which will be deep linked into the keyboard via a branded icon.
 */
public class DeepLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);
    }
}