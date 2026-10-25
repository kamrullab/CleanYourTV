package com.kamrullab.cleanyourtv;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.accessibility.AccessibilityEvent;

// Opt-in safe Home redirect for Google TV. Nothing is disabled: turning this accessibility
// service off immediately returns Home to the stock launcher.
//
// Google TV consumes the Home key before accessibility services can see it. This service instead
// observes only the exact stock Home activity opening and places CleanYourTV in front of it.
public class HomeButton extends AccessibilityService {
    private static final ComponentName GOOGLE_TV_HOME = new ComponentName(
            "com.google.android.apps.tv.launcherx",
            "com.google.android.apps.tv.launcherx.home.HomeActivity");
    private static long lastOpened = -500;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
                || !GOOGLE_TV_HOME.getPackageName().equals(String.valueOf(event.getPackageName()))
                || !GOOGLE_TV_HOME.getClassName().equals(String.valueOf(event.getClassName()))
                || !setupDone()) return;

        SharedPreferences prefs = prefs(this);
        long now = SystemClock.elapsedRealtime();
        if (now - lastOpened < 500) return;

        // A pending launch that never reported healthy counts as a failure. Stop after three
        // failures, preventing a broken update from creating an endless redirect loop.
        long pending = prefs.getLong("pending", -1);
        if (pending >= 0 && now >= pending && now - pending < 3000) return;
        int failures = prefs.getInt("failures", 0);
        if (pending >= 0) failures++;
        if (failures >= 3) {
            prefs.edit().remove("pending").putInt("failures", failures).commit();
            return;
        }
        if (!prefs.edit().putLong("pending", now).putInt("failures", failures).commit()) return;

        lastOpened = now;
        try {
            startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } catch (Exception ignored) {}
    }

    @Override
    public void onInterrupt() {}

    // Never redirect during Android's initial device setup.
    private boolean setupDone() {
        return Settings.Secure.getInt(getContentResolver(), "user_setup_complete", 1) == 1;
    }

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences("home_button", 0);
    }

    static void onHomeResumed(Context context) {
        prefs(context).edit().remove("google_tv_boot").remove("google_tv_boot_count").commit();
    }

    static void onHomeOk(Context context) {
        prefs(context).edit().remove("pending").remove("failures").commit();
        lastOpened = -500;
    }
}
