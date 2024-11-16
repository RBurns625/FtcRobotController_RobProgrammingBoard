package org.firstinspires.ftc.teamcode.subassembly;

import android.content.Context;

import com.qualcomm.ftccommon.SoundPlayer;
@SuppressWarnings("unused")
public class SoundSubassembly {

    private final int meepID;
    private final int beepID;

    private final boolean meepAvailable;
    private final boolean beepAvailable;

    public SoundSubassembly(Context context) {
        meepID = context.getResources().getIdentifier("meep11", "raw", context.getPackageName());
        beepID = context.getResources().getIdentifier("shortbeep", "raw", context.getPackageName());

        meepAvailable = SoundPlayer.getInstance().preload(context, meepID);
        beepAvailable = SoundPlayer.getInstance().preload(context, beepID);
    }

    public boolean isMeepAvailable() {
        return meepAvailable;
    }

    public boolean isBeepAvailable() {
        return beepAvailable;
    }

    public void playMeep (Context context) {
        if (meepAvailable) {
            SoundPlayer.getInstance().startPlaying(context, meepID);
        }
    }

    public void playBeep (Context context) {
        if (beepAvailable) {
            SoundPlayer.getInstance().startPlaying(context, beepID);
        }
    }

}
