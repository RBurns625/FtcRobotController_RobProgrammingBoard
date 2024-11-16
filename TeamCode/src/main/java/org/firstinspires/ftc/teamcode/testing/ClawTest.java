package org.firstinspires.ftc.teamcode.testing;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Claw;

@SuppressWarnings("unused")
@TeleOp (name="Claw Test", group="Test")

/*
 * ProgBoard Servo Port 1: "pincher"
 * ProgBoard Servo Port 2: "wrist"
 * ProgBoard Servo Port 3: "elbow"
 * ProgBoard Servo Port 5: "rgbLight"
 */

public class ClawTest extends OpMode {
    Claw    c;
    GamepadEx subDriverOp;

    @Override
    public void init() {
        c = new Claw(hardwareMap);
        subDriverOp = new GamepadEx(gamepad1);
    }

    @Override
    public void loop() {
        c.adjustPincher (-gamepad1.left_trigger + (gamepad1.right_trigger) * 0.75 );
        c.adjustWrist   (-gamepad1.left_stick_x  * 1.0);
        c.adjustElbow   (-gamepad1.right_stick_y * 1.0);

        subDriverOp.readButtons();

        if (gamepad1.a) {
            c.horizPickup();
        } else if (gamepad1.x) {
            c.clearSubmersible();
        } else if (gamepad1.b) {
            c.vertPickup();
        } else if (gamepad1.right_bumper) {
            c.prepareBasket();
        } else if (gamepad1.left_bumper) {
            c.prepareSpecimen();
        } else if (gamepad1.dpad_up) {
            c.prepareHang();
        } else if (gamepad1.dpad_left) {
            c.parked();
        } else if (gamepad1.dpad_down) {
            c.prepareHang();
        } else if (gamepad1.dpad_right) {
            c.traveling();
        } else if (subDriverOp.wasJustPressed(GamepadKeys.Button.Y)) {
            c.togglePincher();
        }

        c.outputTelemetry(telemetry);
        telemetry.update();
    }
}
