package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.Gamepad;

@SuppressWarnings("unused")

public class GamepadSubassembly {
    private Gamepad gamepad1;
    private Gamepad gamepad2;
    private Claw claw;
    private Pincher pincher;

    public void init (Gamepad gamepad1, Gamepad gamepad2, Claw claw, Pincher pincher) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.claw = claw;
        this.pincher = pincher;
    }

    public void execute() {
        if (gamepad1.a) {
            claw.horizPickup();
        } else if (gamepad1.b) {
            claw.vertPickup();
        } else if (gamepad1.x) {
            claw.clearSubmersible();
        } else if (gamepad1.y) {
            pincher.toggle();
        } else if (gamepad1.right_bumper) {
            claw.prepareBasket();
        } else if (gamepad1.left_bumper) {
            claw.prepareSpecimen();
        } else if (gamepad1.dpad_up) {
            claw.prepareHang();
        } else if (gamepad1.dpad_left) {
            claw.parked();
        } else if (gamepad1.dpad_down) {
            claw.prepareHang();
        } else if (gamepad1.dpad_right) {
            claw.traveling();
        }
    }
}