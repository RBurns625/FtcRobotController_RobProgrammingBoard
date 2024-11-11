package org.firstinspires.ftc.teamcode.subassembly;

import android.content.Context;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.HardwareMap;

@SuppressWarnings("unused")

public class NotificationSounds {
    private Context appContext;
    private int meepSoundID;
    private int shortBeepID;

    public NotificationSounds(HardwareMap hwMap) {
        appContext = hwMap.appContext;
        meepSoundID = hwMap.appContext.getResources().getIdentifier("meep11", "raw",
                hwMap.appContext.getPackageName());
        shortBeepID = hwMap.appContext.getResources().getIdentifier("shortbeep", "raw",
                hwMap.appContext.getPackageName());
    }

    public void playMeep() {
        SoundPlayer.getInstance().startPlaying(appContext, meepSoundID);
    }
    public void playBeep() {
        SoundPlayer.getInstance().startPlaying(appContext, shortBeepID);
    }
}
