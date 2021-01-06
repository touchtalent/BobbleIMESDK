package com.bobble.keyboardplayground;

import android.app.Application;

import com.touchtalent.bobbleime.sdk.BobbleIMESDK;

public class KeyboardPlayGroundApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BobbleIMESDK.initialise(this);
    }
}
