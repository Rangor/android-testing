package com.test.demo.myapp.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.github.ajalt.reprint.core.AuthenticationFailureReason;
import com.github.ajalt.reprint.core.AuthenticationListener;
import com.github.ajalt.reprint.core.Reprint;
import com.test.demo.myapp.R;
import com.test.demo.myapp.dialog.FingerPrintDialogFragment;

public class ReprintTestActivity extends AppCompatActivity {

    DialogFragment dialogFragment;

    @BindView(R.id.floating_action_button)
    FloatingActionButton fab;

    @BindView(R.id.result)
    TextView result;

    @BindView(R.id.hardware_present)
    TextView hardwarePresent;

    @BindView(R.id.fingerprints_registered)
    TextView fingerprintsRegistered;

    @BindView(R.id.rx_switch)
    CompoundButton rxSwitch;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Reprint.initialize(this);
        setContentView(R.layout.activity_reprint_test);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        hardwarePresent.setText(String.valueOf(Reprint.isHardwarePresent()));
        fingerprintsRegistered.setText(String.valueOf(Reprint.hasFingerprintRegistered()));

        running = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancel();
    }

    @OnClick(R.id.floating_action_button)
    public void onFabClick() {

//        if (running) {
//            cancel();
//        } else {
//            start();
//        }

        dialogFragment = new FingerPrintDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
        start();
    }

    private void start() {
        running = true;
        result.setText("Listening");
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fingerprint_white_24dp));

        if (rxSwitch.isChecked()) {
//            startReactive();//Todo what to do?
        } else {
            startTraditional();
        }
    }

    private void startTraditional() {
        Reprint.authenticate(new AuthenticationListener() {
            @Override
            public void onSuccess(int moduleTag) {
                showSuccess();
            }

            @Override
            public void onFailure(@NonNull AuthenticationFailureReason failureReason, boolean fatal,
                                  @Nullable CharSequence errorMessage, int moduleTag, int errorCode) {
                showError(failureReason, fatal, errorMessage, errorCode);
            }
        });
    }

    private void cancel() {
        result.setText("Cancelled");
        running = false;
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fingerprint_white_24dp));
        Reprint.cancelAuthentication();
    }

    private void showSuccess() {
        result.setText("Success");
        dialogFragment.dismiss();
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fingerprint_white_24dp));
        running = false;
    }

    private void showError(AuthenticationFailureReason failureReason, boolean fatal, CharSequence errorMessage, int errorCode) {
        result.setText(errorMessage);

        if (fatal) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fingerprint_white_24dp));
            running = false;
        }
    }
}
