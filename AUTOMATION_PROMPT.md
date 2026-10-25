# CleanYourTV - automatic AI prompt

Copy the prompt below into a **local computer agent** such as Codex, Claude Code, or another agent that can run terminal commands and access devices on your home network. A web-only chatbot cannot connect to your TV.

The prompt uses one upfront authorization for ordinary reversible work. It should not repeatedly ask for approval. Some interactions cannot be automated: Android's RSA/pairing dialog, operating-system security prompts, GitHub login, and a separate confirmation before disabling Google TV's protected launcher.

```text
Clean up, speed up, and install a lightweight personalized home screen on my Android/Google TV.

I am a nontechnical user. Do not expect me to know code, ADB, package names, Git, Java, terminal commands, or folder setup. Automatically clone/download `https://github.com/kamrullab/CleanYourTV`, prepare everything it needs in a dedicated PC folder, and apply that project as my TV launcher. Give me only short instructions for taps or codes that physically require me.

Before changing anything, explain this warning in plain words: this process turns off some system apps and carries risk. A few TVs have become stuck at the startup logo or kept restarting, and an AI can make mistakes. Explain that `undo.sh` plus the saved undo list restores every package and setting changed by this workflow. Ask me once to reply `I confirm, proceed` before making TV changes.

After that single confirmation, work autonomously from start to finish. Do not ask for approval before every ordinary reversible change. The single confirmation authorizes read-only checks, creating a dedicated project folder on my PC, downloading official Android Platform Tools, reversible Android settings changes, disabling only conservatively classified nonessential packages, building/installing the custom launcher, taking screenshots, restarting the TV once, and producing undo scripts and reports.

Pause only when I must physically act or provide information: an Android RSA/pairing code dialog, an operating-system permission prompt, credentials, an unknown TV IP that cannot be discovered safely, an OLED/non-OLED answer, or the separate high-risk launcher override described below. Never claim those prompts can be bypassed.

Before applying the confirmed changes, show me the complete plain-English package classification, baseline free memory/storage, and the exact proposed SAFE NONESSENTIAL disable list. This is a report, not another approval checkpoint. Then continue automatically under my one-time confirmation.

Safety rules:
1. Never delete system packages. Use `pm disable-user --user 0` only. Save every newly disabled package before changing it and generate a tested `undo.sh`/`undo.ps1` that re-enables it.
2. Never disable Google Play services (`com.google.android.gms`), Google Services Framework, Play Store, Settings, System UI, setup/provisioning, package installer, permission controller, keyboard/IME, Bluetooth, Wi-Fi/network stack, remote-control services, HDMI/input/tuner/Live TV frameworks, audio framework, WebView when required, or persistent/unknown vendor components. When unsure, leave it enabled.
3. Keep screensaver enabled unless I explicitly confirm the panel is not OLED. Do not infer OLED status only from a marketing/model name.
4. Do not disable `com.google.android.apps.tv.launcherx` automatically. First install and verify the custom launcher while launcherx remains enabled. If firmware keeps taking Home back, guide me to enable `CleanYourTV Home button` under TV Accessibility and verify that safe redirect. Only if it is unavailable or fails, explain the boot-loop/factory-reset risk and require the exact confirmation `Disable launcherx; I accept the risk.` before disabling launcherx.
5. Keep a timestamped change log containing original values, commands, results, and exact undo commands. Preserve existing user files and Git history.
6. Download executables only from official sources. Do not weaken firewalls or Android security settings.

Automatic workflow:
1. Create a dedicated folder named `CleanYourTV` in the current workspace or another user-approved writable directory. Record the absolute path.
2. Detect Windows/macOS/Linux and check for `adb`, Git, Bash/PowerShell, Java 17, and Android build tools. If ADB is missing, download official Android Platform Tools from Google into the dedicated folder and verify that `adb version` works. Do not install unrelated software globally when a local portable tool works.
3. Determine the TV IP from the value I provide. If absent, inspect the local subnet and ARP/neighbor table conservatively, identify likely Android TV devices through ping and standard discovery, and show the candidate before making an ADB connection. Do not scan public networks.
4. Ping the TV and report latency/loss. Probe only standard TV/ADB ports. Try `adb connect TV_IP:5555`. For modern Wireless Debugging, walk me through `Pair device with pairing code`, run `adb pair IP:PAIRING_PORT`, then connect to the separate debugging port. If the TV displays `Allow debugging?`, tell me to select `Always allow` and `Allow`, then continue automatically.
5. Verify `adb get-state=device`. Read manufacturer, brand, model, Android/API/security patch, build, CPU ABI, hardware, RAM, storage, uptime, thermal state, current Home handler, default IME, installed/disabled packages, and user-installed apps. Do not expose the serial number publicly.
6. Measure a baseline: available memory, swap, storage, app-cache total, CPU idle/load, temperature, cold/hot launcher times when applicable, and current animation/sleep/screensaver settings. Save the baseline report.
7. Inventory every package. Produce a plain-English table with categories: REQUIRED/NEVER DISABLE, FEATURE/KEEP UNLESS UNUSED, SAFE NONESSENTIAL, USER APP, and UNKNOWN/LEAVE ON. Explicitly list the package name, enabled state, reason, and proposed action. Continue automatically only with SAFE NONESSENTIAL packages. Leave FEATURE and UNKNOWN packages on unless I explicitly named the feature as unwanted.
8. Before each disable, append the package to `/data/local/tmp/cleanyourtv-disabled.txt` and the local change log. Disable, verify it appears in `pm list packages -d`, and restore immediately if verification or a required service check fails.
9. Turn animation scales off. Set sleep timeout to five minutes. Keep screensaver enabled. Save and document the original values for undo.
10. Clone or update `https://github.com/kamrullab/CleanYourTV` into the dedicated PC folder. Preserve its Git history and attribution. Do not overwrite unrelated local changes.
11. Detect launchable user apps and configure the launcher row automatically. Prefer the apps the user actually installed. Keep HDMI inputs, Settings, network status, Play Store, and an Add App control.
12. Personalize the header as `GitHub icon • KAMRUL HOSSAIN` unless I provide another name. Build from source with Java 17/Android SDK or use the repository's successful GitHub Actions artifact. Use stable signing credentials for updates; never commit a private signing key or secret.
13. Install/update `com.kamrullab.cleanyourtv`, set it as the Android Home role, open it, and verify foreground focus. Take a real TV screenshot, inspect it visually, and update `docs/home-screen.png` plus the README preview.
14. Press Home and verify routing. If the stock Google TV launcher wins, leave it enabled and report the firmware priority conflict. Ask me to open TV Settings > Accessibility and enable `CleanYourTV Home button`, then test Home and one restart. This service is the preferred safe fix because it observes only the stock Home activity and disables nothing. Only follow the launcherx high-risk path if the Accessibility redirect is unavailable or fails and I give the exact separate confirmation required above. If that confirmation is given, save launcherx in the undo list, disable it, verify custom Home immediately, wait 30 seconds, restart once, and automatically restore launcherx if Home/boot verification fails while ADB remains available.
15. Wait at least 30 seconds after the last TV change, restart the TV once, reconnect ADB, wait for `sys.boot_completed=1`, and verify model, Home, HDMI/input framework, Settings, keyboard, Bluetooth, Wi-Fi, Play services, disabled list, animation/sleep/screensaver values, memory, storage, temperature, and recent crash/ANR logs.
16. Benchmark the custom launcher with at least two cold starts and one hot start. Report launch times and PSS memory. Treat tiny gfxinfo samples as inconclusive rather than claiming 100% jank.
17. Clear app caches only if they are materially large and only with Android-supported cache-trim commands; never clear app data, logins, media, or downloads. Do not delete the undo list. Report space before and after.
18. Generate `PACKAGE_REVIEW.md`, `CLEANUP_LOG.md`, `undo.sh`, and Windows-friendly undo instructions. Test shell syntax and verify the repository is clean except for intended changes.
19. If GitHub credentials and the intended repository are already available, commit and push the source, reports, and verified screenshot. Never print tokens or secrets. Otherwise stop with exact push commands instead of requesting credentials in chat.
20. Finish with a concise before/after table, disabled-package list, benchmark results, remaining limitations, undo command, repository/commit link, and a reminder to turn off USB/Network/Wireless debugging or Developer Options.

My TV IP (optional): <TV_IP>
My preferred apps (optional): <APP_NAMES_OR_AUTO>
My display name: KAMRUL HOSSAIN
My repository: https://github.com/kamrullab/CleanYourTV
```

## What still needs a human tap

- Accepting Android's first RSA debugging prompt.
- Entering a Wireless Debugging pairing code when Android requires it.
- Approving operating-system/network permissions that the agent cannot bypass.
- Confirming whether the panel is OLED.
- Explicitly accepting the launcher boot-loop risk before disabling `launcherx`.

These pauses are Android and operating-system security boundaries, not missing automation.
