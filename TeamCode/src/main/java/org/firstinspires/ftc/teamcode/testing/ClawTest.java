package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Claw;
import org.firstinspires.ftc.teamcode.subassembly.Elbow;
import org.firstinspires.ftc.teamcode.subassembly.Pincher;
import org.firstinspires.ftc.teamcode.subassembly.Wrist;

@SuppressWarnings("unused")
@TeleOp

public class ClawTest extends OpMode {
    Claw claw;
    Pincher pincher;
    Wrist wrist;
    Elbow elbow;

    @Override
    public void init() {
        claw    = new Claw(hardwareMap);
        pincher = new Pincher(hardwareMap);
        wrist   = new Wrist(hardwareMap);
        elbow   = new Elbow(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            claw.horizPickup();
        }
        if (gamepad1.x) {
            claw.clearSubmersible();
        }
        if (gamepad1.b) {
            claw.vertPickup();
        }
        if (gamepad1.y) {
            pincher.toggle();
        }
        if (gamepad1.right_bumper) {
            claw.prepareBasket();
        }
        if (gamepad1.left_bumper) {
            claw.prepareSpecimen();
        }
        if (gamepad1.dpad_up) {
            claw.prepareHang();
        }
        if (gamepad1.dpad_left) {
            claw.parked();
        }
        if (gamepad1.dpad_down) {
            claw.prepareHang();
        }
        if (gamepad1.dpad_right) {
            claw.traveling();
        }

        pincher.adjustAngle(-gamepad1.left_trigger  * 1.0);
        pincher.adjustAngle( gamepad1.right_trigger * 1.0);

        wrist.adjustAngle(-gamepad1.left_stick_x * 0.5);

        elbow.adjustAngle(-gamepad1.right_stick_y * 0.75);
    }
}
