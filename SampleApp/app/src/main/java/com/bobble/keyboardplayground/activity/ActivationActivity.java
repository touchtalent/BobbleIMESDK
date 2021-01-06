package com.bobble.keyboardplayground.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bobble.keyboardplayground.R;
import com.touchtalent.bobbleime.sdk.BobbleEnablerActivity;
import com.touchtalent.bobbleime.sdk.BobbleIMESDK;

/**
 * This activity is the entry point for starting the enabling flow.
 * Users can be shown some graphics along with feature list and a button to enable the keyboard
 */
public class ActivationActivity extends BobbleEnablerActivity implements View.OnClickListener {
    private static final String TAG = ActivationActivity.class.getSimpleName();
    Button install;
    EditText tryOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bobble);
        install = findViewById(R.id.install);
        tryOut = findViewById(R.id.tryOut);
        install.setOnClickListener(this);
        //Set the initial status of the UI according to SDK status
        setStatus(getStatus());
    }

    public void setStatus(BobbleIMESDK.IMEInstallStatus status) {
        install.setFocusable(true);
        install.setClickable(true);
        switch (status) {
            case NONE:
                install.setText("Enable Bobble Keyboard(Step-1)");
                install.setVisibility(View.VISIBLE);
                tryOut.setVisibility(View.GONE);
                break;
            case ENABLED:
                install.setText("Enable Bobble Keyboard(Step-2)");
                install.setVisibility(View.VISIBLE);
                tryOut.setVisibility(View.GONE);
                break;
            case SELECTED:
                install.setVisibility(View.GONE);
                tryOut.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * During the flow, a system activity is opened, which persists in the activity backstack
     * and cannot be closed due to system limitations. Hence consider bringing up your parent activity to top
     * and close this activity manually when user presses back button
     */
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    /**
     * Start the activation flow when user presses the button
     */
    @Override
    public void onClick(View v) {
        startActivationFlow();
    }

    /**
     * Callback to keep track of updates made in enabling flow
     * @param imeInstallStatus new modified status
     */
    @Override
    protected void onStatusChange(@NonNull BobbleIMESDK.IMEInstallStatus imeInstallStatus) {
        setStatus(imeInstallStatus);
        Log.d("ActivationActivity", "onStatusChange: " + imeInstallStatus.name());
    }

    /**
     * Callback to notify that user has passed the onboarding screen.
     * Consider closing this activity and sending the user back to your parent activity.
     */
    @Override
    protected void onUserSetup() {
        Log.d("ActivationActivity", "onUserSetup()");
        onBackPressed();
    }
}
