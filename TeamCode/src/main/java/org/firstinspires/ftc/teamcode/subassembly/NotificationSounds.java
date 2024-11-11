package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class NotificationSounds {
     private boolean meepSound;
     int meepSoundID;
     Telemetry telemetry;

     public void init(HardwareMap hwMap) {
         meepSoundID = hwMap.appContext.getResources().getIdentifier("meep11", "raw",
             hwMap.appContext.getPackageName());
         if (meepSoundID !=0)
             meepSound = SoundPlayer.getInstance().preload(hwMap.appContext, meepSoundID);
         if (meepSound) {
             SoundPlayer.getInstance().startPlaying(hwMap.appContext, meepSoundID);
         } else {
             telemetry.addLine("File Not Found");
         }
    }
}
