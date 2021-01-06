package com.bobble.keyboardplayground.services;

import android.content.Intent;

import com.bobble.keyboardplayground.activity.DeepLinkActivity;
import com.touchtalent.bobbleime.services.BobbleIME;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class KeyboardService extends BobbleIME {

    @Override
    public void onQuickAccessIconTap() {
        Intent intent = new Intent(this, DeepLinkActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
