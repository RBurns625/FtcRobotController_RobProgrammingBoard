package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.HardwareMap;

@SuppressWarnings("unused")

public class MeepMeepSound {
    private boolean meeper;
    int meeperSoundID;

    public void init(HardwareMap hwMap) {
        meeperSoundID = hwMap.appContext.getResources().getIdentifier("meep11", "raw",
                hwMap.appContext.getPackageName());

        SoundPlayer.getInstance().startPlaying(hwMap.appContext, meeperSoundID);
    }
}
