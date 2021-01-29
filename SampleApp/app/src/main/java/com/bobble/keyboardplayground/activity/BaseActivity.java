package com.bobble.keyboardplayground.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.touchtalent.bobbleime.sdk.BobbleEnablerActivity;

import java.util.Locale;

abstract class BaseActivity extends BobbleEnablerActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        Locale locale = new Locale(getAppLocale(newBase));
        Locale.setDefault(locale);
        Resources res = newBase.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        Context newContext = newBase.createConfigurationContext(config);
        super.attachBaseContext(newContext);
    }

    public String getAppLocale(Context context) {
        return context.getSharedPreferences("prefs", 0).getString("locale", "in");
    }

    public void toggleAppLocale() {
        SharedPreferences preferences = getSharedPreferences("prefs", 0);
        String current = preferences.getString("locale", "in");
        if (current.equals("in"))
            preferences.edit().putString("locale","en").apply();
        else
            preferences.edit().putString("locale","in").apply();
    }
}
