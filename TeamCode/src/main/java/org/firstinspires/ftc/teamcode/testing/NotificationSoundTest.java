package org.firstinspires.ftc.teamcode.testing;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.NotificationSounds;

@SuppressWarnings("unused")
@TeleOp (name="Notification Sound Test", group="Test)")
public class NotificationSoundTest extends OpMode {
    private NotificationSounds sounds;
    GamepadEx mygamepad1 = new GamepadEx(gamepad1);

    @Override
    public void init() {
        sounds = new NotificationSounds(hardwareMap);

    }

    @Override
    public void loop() {

        if (mygamepad1.wasJustPressed(GamepadKeys.Button.A)) {
            sounds.playMeep();
        } else {
            sounds.stopSound();
        }

        if (mygamepad1.wasJustPressed(GamepadKeys.Button.B)) {
            sounds.playBeep();
        } else {
            sounds.stopSound();
        }

        telemetry.addLine("A: Meep");
        telemetry.addLine("B: Beep");

    }
}

