# VISION 2K SMART TV cleanup log

Target: `192.168.1.151:5555`  
Model: VISION 2K SMART TV (`VISION_2K_ATV3`, Android 14)  
Date: 2026-10-25

## Before

- Available memory: 486,576 kB
- Data storage: 4.0 GB total, 2.0 GB available, 48% used
- Animation scales: window `1.0`, transition `1.0`, animator unset
- Sleep timeout: 600,000 ms (10 minutes)
- Screensaver: enabled
- TalkBack was already disabled before this cleanup.

## Changes

- Installed the simple home screen with YouTube, Netflix, Prime Video, Spotify, and TikTok shortcuts.
- Kept `com.google.android.apps.tv.launcherx` enabled because it is protected on Google TV.
- Disabled, without deleting:
  - `com.android.htmlviewer` — HTML file viewer
  - `com.google.android.feedback` — Google feedback reporter
  - `com.google.android.play.games` — Google Play Games
  - `com.google.android.youtube.tvmusic` — YouTube Music
- Set all three Android animation scales to `0`.
- Set sleep timeout to 300,000 ms (5 minutes).
- Kept the screensaver enabled.

## Undo

Run `sh undo.sh 192.168.1.151`. It re-enables every package listed above, restores the original animation and timeout values, keeps the screensaver enabled, removes the home screen, and returns to the TV's stock home screen.

## After restart

- Android boot completed normally (`sys.boot_completed=1`).
- Active Home: protected Google TV launcher (`com.google.android.apps.tv.launcherx`).
- Available memory: 533,960 kB, up 47,384 kB (about 46 MB) from before.
- Disabled-package list and performance settings survived the restart.
- Sleep timeout: 300,000 ms (5 minutes).
- Screensaver: enabled.
