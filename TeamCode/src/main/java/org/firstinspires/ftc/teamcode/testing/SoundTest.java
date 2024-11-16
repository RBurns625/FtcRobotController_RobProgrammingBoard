package org.firstinspires.ftc.teamcode.testing;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subassembly.SoundSubassembly;
@SuppressWarnings("unused")
@TeleOp (name="Sound Test", group="Test")
public class SoundTest extends OpMode {

    private SoundSubassembly sound;
    private GamepadEx gamepad;
    private ElapsedTime debounceTimer = new ElapsedTime();

    @Override
    public void init() {
        sound = new SoundSubassembly(hardwareMap.appContext);

        gamepad = new GamepadEx(gamepad1);

        telemetry.addData("Meep Available", sound.isMeepAvailable());
        telemetry.addData("Beep Available", sound.isBeepAvailable());
        telemetry.update();
    }

    @Override
    public void loop() {
        if (debounceTimer.seconds() > 0.5) {
            if (gamepad.getButton(GamepadKeys.Button.A)) {
                sound.playMeep(hardwareMap.appContext);
                debounceTimer.reset();
            }
            if (gamepad.getButton(GamepadKeys.Button.B)) {
                sound.playBeep(hardwareMap.appContext);
                debounceTimer.reset();
            }
        }

        telemetry.addData("Press A for MEEP", sound.isMeepAvailable());
        telemetry.addData("Press B for BEEP", sound.isBeepAvailable());
        telemetry.update();
    }
}
