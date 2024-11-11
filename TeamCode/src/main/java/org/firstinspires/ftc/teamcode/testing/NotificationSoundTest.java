package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.NotificationSounds;

@SuppressWarnings("unused")
@TeleOp
public class NotificationSoundTest extends OpMode {
    private NotificationSounds sounds;

    @Override
    public void init() {
        sounds = new NotificationSounds(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            sounds.playMeep();
        }
        telemetry.addLine("A: Meep");
    }
}
