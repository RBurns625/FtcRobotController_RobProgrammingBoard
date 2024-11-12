package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.Gamepad;

@SuppressWarnings("unused")

public class GamepadSubassembly {
    final double FUDGE_FACTOR_DEG = 15;
    private Gamepad gamepad1;
    private Gamepad gamepad2;
    private Claw claw;
    private Pincher pincher;
    private ViperSlideArm viperSlideArm;
    boolean yAlreadyPressed;
    boolean pincherOpen;

    public void init (Gamepad gamepad1, Gamepad gamepad2, Claw claw, Pincher pincher,
                      ViperSlideArm viperSlideArm) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.claw = claw;
        this.pincher = pincher;
        this.viperSlideArm = viperSlideArm;
    }

    public void execute() {
        viperSlideArm.moveArm(-gamepad1.right_stick_y * 1.0);
        viperSlideArm.setArmPositionFudgeFactor(FUDGE_FACTOR_DEG
                * (gamepad2.right_trigger + (-gamepad2.left_trigger)));
        claw.adjustPincher(-gamepad1.right_stick_x * 1.0);
        claw.adjustElbow(-gamepad1.left_stick_y * 1.0);
        claw.adjustWrist(-gamepad1.left_stick_x * 1.0);

        if (gamepad1.a) {
            claw.horizPickup();
            viperSlideArm.sampleCollect();
        } if (gamepad1.b) {
            claw.vertPickup();
            viperSlideArm.sampleCollect();
        } if (gamepad1.x) {
            claw.clearSubmersible();
            viperSlideArm.clearBarrier();
        } if (gamepad1.right_bumper) {
            claw.prepareBasket();
            viperSlideArm.highBasket();
        } if (gamepad1.left_bumper) {
            claw.prepareSpecimen();
            viperSlideArm.highRung();
        } if (gamepad1.dpad_up) {
            claw.prepareHang();
            viperSlideArm.hangPrepare();
        } if (gamepad1.dpad_left) {
            claw.parked();
            viperSlideArm.parked();
        } if (gamepad1.dpad_down) {
            claw.prepareHang();
            viperSlideArm.hangRobot();
        } if (gamepad1.dpad_right) {
            claw.traveling();
            viperSlideArm.traveling();
        }
        if (gamepad1.y && !yAlreadyPressed) {
            pincherOpen = !pincherOpen;
            if (pincherOpen) {
            pincher.open();
            } else {
                pincher.closed();
            }
        }
        yAlreadyPressed = gamepad1.y;
    }
}