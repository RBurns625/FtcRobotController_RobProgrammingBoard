package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@SuppressWarnings("unused")

public class GamepadSubassembly {
    final double FUDGE_FACTOR_DEG = 15;
    private Claw claw;
    private ViperSlideArm viperSlideArm;
    private Gamepad gamepad2;
    boolean yAlreadyPressed;
    boolean pincherOpen;

    public GamepadSubassembly(HardwareMap hwMap) {
        gamepad2 = new Gamepad();
        claw = new Claw(hwMap);
        viperSlideArm = new ViperSlideArm();
        gamepad2 = new Gamepad();
    }

    public void moveArm() {
        viperSlideArm.moveArm(-gamepad2.right_stick_y * 1.0);
    }

    public void moveSlide() {
        viperSlideArm.moveSlide(gamepad2.right_trigger + (-gamepad2.left_trigger));
    }

    public void adjustPincher() {
        claw.adjustPincher(-gamepad2.right_stick_x * 1.0);
    }

    public void adjustElbow() {
        claw.adjustElbow(-gamepad2.left_stick_y * 1.0);
    }

    public void adjustWrist() {
        claw.adjustWrist(-gamepad2.left_stick_x * 1.0);
    }

    public void buttonA() {
        if (gamepad2.a) {
            claw.horizPickup();
            viperSlideArm.sampleCollect();
        }
    }

    public void buttonB() {
        if(gamepad2.b) {
        claw.vertPickup();
        viperSlideArm.sampleCollect();
        }
    }

    public void buttonX() {
        if (gamepad2.x) {
        claw.clearSubmersible();
        viperSlideArm.clearBarrier();
        }
    }

    public void rtbumper() {
        if (gamepad2.right_bumper) {
            claw.prepareBasket();
            viperSlideArm.highBasket();
        }
    }

    public void ltbumper() {
        if (gamepad2.left_bumper) {
            claw.prepareSpecimen();
            viperSlideArm.highRung();
        }
    }
    public void dpadUp() {
        if (gamepad2.dpad_up) {
            claw.prepareHang();
            viperSlideArm.hangPrepare();
        }
    }

    public void dpadLeft() {
        if (gamepad2.dpad_left) {
            claw.parked();
            viperSlideArm.parked();
        }
    }

    public void dpadDown() {
        if (gamepad2.dpad_down) {
            claw.prepareHang();
            viperSlideArm.hangRobot();
        }
    }

    public void dpadRight() {
        if (gamepad2.dpad_right) {
            claw.traveling();
            viperSlideArm.traveling();
        }
    }
}