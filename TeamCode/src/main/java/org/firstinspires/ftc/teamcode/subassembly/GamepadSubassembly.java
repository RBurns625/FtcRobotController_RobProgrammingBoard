package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@SuppressWarnings("unused")

public class GamepadSubassembly {
    final double FUDGE_FACTOR_DEG = 15;
    private Claw claw;
    private ViperSlideArm viperSlideArm;
    private Gamepad gamepad2;

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
        claw.vertPickup();
        viperSlideArm.sampleCollect();
    }

    public void buttonX() {
        claw.clearSubmersible();
        viperSlideArm.clearBarrier();
    }

    public void rtbumper() {
        claw.prepareBasket();
        viperSlideArm.highBasket();
    }

    public void ltbumper() {
        claw.prepareSpecimen();
        viperSlideArm.highRung();
    }
    public void dpadUp() {
        claw.prepareHang();
        viperSlideArm.hangPrepare();
    }

    public void dpadLeft() {
        claw.parked();
        viperSlideArm.parked();
    }

    public void dpadDown() {
        claw.prepareHang();
        viperSlideArm.hangRobot();
    }

    public void dpadRight() {
        claw.traveling();
        viperSlideArm.traveling();
    }
}