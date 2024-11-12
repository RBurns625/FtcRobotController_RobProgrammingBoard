package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.NotificationSounds;

@SuppressWarnings("unused")
@TeleOp (name="Notification Sound Test", group="Test)")
public class NotificationSoundTest extends OpMode {
    private NotificationSounds sounds;
    boolean aAlreadyPressed;
    boolean bAlreadyPressed;
    boolean aNotification;
    boolean bNotification;

    @Override
    public void init() {
        sounds = new NotificationSounds(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a && !aAlreadyPressed) {
            aNotification = !aNotification;
            if (aNotification) {
                sounds.playMeep();
            } else {
                sounds.stopSound();
            }
        }

        if (gamepad1.b && !bAlreadyPressed) {
            bNotification = !bNotification;
            if (bNotification) {
                sounds.playBeep();
            } else {
                sounds.stopSound();
            }
        }

        aAlreadyPressed = gamepad1.a;
        bAlreadyPressed = gamepad1.b;

        telemetry.addLine("A: Meep");
        telemetry.addLine("B: Beep");

    }
}

