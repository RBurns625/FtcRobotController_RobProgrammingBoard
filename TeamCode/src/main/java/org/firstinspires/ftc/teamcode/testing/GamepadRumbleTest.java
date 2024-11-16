package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subassembly.GoBildaRGBLight;
import org.firstinspires.ftc.teamcode.subassembly.SoundSubassembly;

@SuppressWarnings("unused")
@TeleOp (name="Rumble Test", group="Test")
public class GamepadRumbleTest extends OpMode {
    SoundSubassembly sound;
    GoBildaRGBLight light;
    Gamepad opDriver;
    Gamepad.RumbleEffect halfTimeRumble;
    Gamepad.RumbleEffect first30Rumble;
    Gamepad.RumbleEffect last30Rumble;

    boolean first30         = false;
    boolean secondHalf      = false;
    boolean last30          = false;
    boolean matchEnd        = false;
    final double FIRST_30   = 30.0;
    final double HALF_TIME  = 60.0;
    final double LAST_30    = 90.0;
    final double MATCH_TIME = 120.0;

    ElapsedTime runtime;

    @Override
    public void init() {
        runtime = new ElapsedTime();
        light = new GoBildaRGBLight(hardwareMap);
        sound = new SoundSubassembly(hardwareMap.appContext);
        sound.playMeep(hardwareMap.appContext);
    }

    @Override
    public void loop() {
        first30Rumble = new Gamepad.RumbleEffect.Builder()
                .addStep(0.5, 0.5, 250)  //  Rumble lt/rt motor 100% for 250 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(0.5, 0.5, 250)  //  Rumble lt/rt motor 100% for 250 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(0.0, 1.0, 250)  //  Rumble right motor 100% for 250 mSec
                .build();

        halfTimeRumble = new Gamepad.RumbleEffect.Builder()
                .addStep(0.0, 1.0, 500)  //  Rumble right motor 100% for 500 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
                .addStep(0.0, 0.0, 250)  //  Pause for 250 mSec
                .addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(0.0, 1.0, 500)  //  Rumble right motor 100% for 500 mSec
                .build();

        last30Rumble = new Gamepad.RumbleEffect.Builder()
                .addStep(0.0, 1.0, 250)  //  Rumble right motor 100% for 250 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(0.0, 1.0, 250)  //  Rumble right motor 100% for 250 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(0.0, 1.0, 250)  //  Rumble right motor 100% for 250 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .build();

        if ((runtime.seconds() > FIRST_30) && !first30) {
            gamepad1.runRumbleEffect(first30Rumble);
            light.setColorYellow();
            first30 = true;
        } else if ((runtime.seconds() > HALF_TIME) && !secondHalf) {
            gamepad1.runRumbleEffect(halfTimeRumble);
            light.setColorBlue();
            sound.playBeep(hardwareMap.appContext);
            secondHalf = true;
        } else if ((runtime.seconds() > LAST_30) && !last30) {
            gamepad1.runRumbleEffect(last30Rumble);
            sound.playMeep(hardwareMap.appContext);
            light.setColorRed();
            last30 = true;
        } else if ((runtime.seconds() > MATCH_TIME) && !matchEnd) {
            light.setColorBlack();
            matchEnd = true;
        }

        if (!matchEnd) {
            telemetry.addData("Match Countdown",  "%3.0f Sec \n", (MATCH_TIME - runtime.seconds()) );
        }
    }
}
