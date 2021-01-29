package com.bobble.keyboardplayground.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bobble.keyboardplayground.R;
import com.touchtalent.bobbleime.sdk.BobbleIMESDK;

import static com.touchtalent.bobbleime.sdk.BobbleIMESDK.BobbleSupportedLanguage.BAHASA;
import static com.touchtalent.bobbleime.sdk.BobbleIMESDK.BobbleSupportedLanguage.ENGLISH;

/**
 * This activity represents a Activity of your app where promotional banners for the keyboard are being shown.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.launch).setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivationActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.changeLanguage).setOnClickListener(v -> {
            toggleAppLocale();
            String newLocale = getAppLocale(this);
            if (newLocale.equals("in"))
                BobbleIMESDK.setDefaultLanguage(BAHASA);
            else if (newLocale.equals("en"))
                BobbleIMESDK.setDefaultLanguage(ENGLISH);
            finish();
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView) findViewById(R.id.currentLanguage)).setText("Current language : " + getAppLocale(this));
    }
}