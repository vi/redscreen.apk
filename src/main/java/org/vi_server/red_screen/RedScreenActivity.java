package org.vi_server.red_screen;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.content.Context;
import android.util.Log;
import android.graphics.Color;
import android.app.KeyguardManager;

public class RedScreenActivity extends Activity
{
    public static WakeLock wl;

    private void setupColour() {
        String colour = "red";
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                colour = extras.getString("color", colour);
                colour = extras.getString("colour", colour);
            }
        } catch (NoSuchMethodError e) {}
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colour)));
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.setupColour();

        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        layout.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            layout.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS;
        }
        getWindow().setAttributes(layout);

        try {
            getWindow().getDecorView().setSystemUiVisibility( 0
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );
        } catch (NoSuchMethodError e) {}
            
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
          KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
          keyguardManager.requestDismissKeyguard(this, null);
          setShowWhenLocked(true);
        } else {
          getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
             | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
             | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
             | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
             | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        }

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "org.vi_server.red_screen:DoNotDimScreen");
        wl.acquire();
    }

    @Override
    public void onPause() {
        wl.release();
        super.onPause();
        this.finish();
    }

    @Override
    public void onResume() {
        wl.acquire();
        super.onResume();
    }
}
