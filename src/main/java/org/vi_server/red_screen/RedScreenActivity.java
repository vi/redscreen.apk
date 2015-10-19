package org.vi_server.red_screen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.os.PowerManager;
import android.content.Context;
import android.util.Log;
import android.graphics.Color;

public class RedScreenActivity extends Activity
{
    public static Object wl;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        String colour = "red";
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            colour = extras.getString("color", colour);
            colour = extras.getString("colour", colour);
        }
        View v = findViewById(android.R.id.content);
        v.setBackgroundColor(Color.parseColor(colour));
        
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        layout.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        getWindow().setAttributes(layout);
    
        getWindow().getDecorView().setSystemUiVisibility( 0
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION 
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
            
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
           | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
           | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
           | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "DoNotDimScreen");        
    }
}
