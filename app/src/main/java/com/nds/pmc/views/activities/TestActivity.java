package com.nds.pmc.views.activities;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.nds.pmc.BuildConfig;
import com.nds.pmc.R;


/**
 * Created by Namrata Shah on 2/17/2017.
 */
@VisibleForTesting
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock keyguardLock = km.newKeyguardLock("TAG");
            keyguardLock.disableKeyguard();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(R.id.container);
        setContentView(frameLayout);
    }
}
