package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Wrist;

@SuppressWarnings("unused")

@TeleOp

/*
 * Config Setup:
 * Programming Board Port 1: "wrist"
 * Programming Board Port 5: "light"
 */

public class WristTest extends OpMode {
    private Wrist wrist;

    @Override
    public void init() {
        wrist = new Wrist(hardwareMap);
        wrist.wristZero();
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            wrist.wristRight();
        }
        if (gamepad1.b) {
            wrist.wristStraight();
        }
        if (gamepad1.x) {
            wrist.wristZero();
        }
        if (gamepad1.y) {
            wrist.wristLeft();
        }

        telemetry.addData("Servo Position", wrist.getCurrentAngle());
        telemetry.addLine("Gamepad A = Wrist Horizontal (Blue Light(");
        telemetry.addLine("Gamepad B = Wrist Straight (Yellow Light");
        telemetry.addLine("Gamepad X = Wrist Zero (Green Light");

    }
}
