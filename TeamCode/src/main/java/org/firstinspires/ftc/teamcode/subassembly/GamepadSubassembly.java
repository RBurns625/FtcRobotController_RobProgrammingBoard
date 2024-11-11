package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.Gamepad;

@SuppressWarnings("unused")

public class GamepadSubassembly {
    final double FUDGE_FACTOR_DEG = 15;
    private Gamepad gamepad1;
    private Claw claw;
    private Pincher pincher;
    private ViperSlideArm viperSlideArm;

    public void init (Gamepad gamepad1, Gamepad gamepad2, Claw claw, Pincher pincher,
                      ViperSlideArm viperSlideArm) {
        this.gamepad1 = gamepad1;
        this.claw = claw;
        this.pincher = pincher;
        this.viperSlideArm = viperSlideArm;
    }

    public void execute() {
        viperSlideArm.moveArm(-gamepad1.right_stick_y * 1.0);
        viperSlideArm.setArmPositionFudgeFactor(FUDGE_FACTOR_DEG
                * (gamepad1.right_trigger + (-gamepad1.left_trigger)));
        claw.adjustPincher(-gamepad1.right_stick_x * 1.0);
        claw.adjustElbow(-gamepad1.left_stick_y * 1.0);
        claw.adjustWrist(-gamepad1.left_stick_x * 1.0);

        if (gamepad1.a) {
            claw.horizPickup();
            viperSlideArm.sampleCollect();
        } else if (gamepad1.b) {
            claw.vertPickup();
            viperSlideArm.sampleCollect();
        } else if (gamepad1.x) {
            claw.clearSubmersible();
            viperSlideArm.clearBarrier();
        } else if (gamepad1.y) {
            pincher.toggle();
        } else if (gamepad1.right_bumper) {
            claw.prepareBasket();
            viperSlideArm.highBasket();
        } else if (gamepad1.left_bumper) {
            claw.prepareSpecimen();
            viperSlideArm.highRung();
        } else if (gamepad1.dpad_up) {
            claw.prepareHang();
            viperSlideArm.hangPrepare();
        } else if (gamepad1.dpad_left) {
            claw.parked();
            viperSlideArm.parked();
        } else if (gamepad1.dpad_down) {
            claw.prepareHang();
            viperSlideArm.hangRobot();
        } else if (gamepad1.dpad_right) {
            claw.traveling();
            viperSlideArm.traveling();
        } else if (gamepad1.left_stick_button) {
            claw.dropSample();
        } else if (gamepad1.right_stick_button) {
            claw.pickupSample();
        }
    }
}