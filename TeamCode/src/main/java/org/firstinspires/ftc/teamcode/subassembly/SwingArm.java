package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@SuppressWarnings("unused")

public class SwingArm {
    ViperSlide viperSlide;
    DcMotor armMotor;
    private final double ARM_TICKS = 28.0 * 250047.0 / 4913.0 * 100.0 / 20.0 / 360.0;
    final double ARM_MAX_POSITION    = 120 * ARM_TICKS;

    private final double ARM_RETRACTED     = 0;
    final double ARM_COLLECT       = 0   *  ARM_TICKS;
    final double ARM_CLEAR_BARRIER = 15  *  ARM_TICKS;
    final double ARM_BASKET_LOW    = 90  *  ARM_TICKS;
    final double ARM_BASKET_HIGH   = 100 * ARM_TICKS;
    final double ARM_RUNG_LOW      = 45  * ARM_TICKS;
    final double ARM_RUNG_HIGH     = 60  * ARM_TICKS;
    final double ARM_HANG_PREP     = 110 * ARM_TICKS;
    final double ARM_WINCH_ROBOT   = 10  * ARM_TICKS;


    private final double ARM_REAR_VERT_SUPPORT_HEIGHT_MM = 176;
    private final double ARM_LENGTH_AT_PARK_POSITION_MM  = 511;
    private final double ARM_ANGLE_AT_PARK_POSIT_DEG     = Math.toDegrees(Math.acos(
            ARM_REAR_VERT_SUPPORT_HEIGHT_MM / ARM_LENGTH_AT_PARK_POSITION_MM));

    private double armPosition   = ARM_RETRACTED;
    private double armPositionFudgeFactor;

    public void init (HardwareMap hwMap) {
        viperSlide = new ViperSlide();
        armMotor   = hwMap.get(DcMotor.class, "armMotor");
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ((DcMotorEx) armMotor).setCurrentAlert(5, CurrentUnit.AMPS);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setTargetPosition((int) (armPosition + armPositionFudgeFactor));
        ((DcMotorEx) armMotor).setVelocity(2100);
    }

    public void parked() {
        armPosition = ARM_RETRACTED;
    }
    public void traveling() {
    }
    public void sampleCollect() {
        armPosition = ARM_COLLECT;
    }
    public void clearBarrier() {
        armPosition = ARM_CLEAR_BARRIER;
    }
    public void highBasket() {
        armPosition = ARM_BASKET_HIGH;
    }
    public void lowBasket() {
        armPosition = ARM_BASKET_LOW;
    }
    public void highRung() {
        armPosition = ARM_RUNG_HIGH;
    }
    public void lowRung() {
        armPosition = ARM_RUNG_LOW;
    }
    public void hangPrepare() {
        armPosition = ARM_HANG_PREP;
    }
    public void hangRobot() {
        armPosition = ARM_WINCH_ROBOT;
    }


    public void setArmPositionFudgeFactor(double degrees) {
        armPositionFudgeFactor = degrees * ARM_TICKS;
    }
    public void moveArm(double degrees) {
        armPosition += degrees * ARM_TICKS;
    }

    private void limitArmPosition() {
        if(armPosition > ARM_MAX_POSITION) {
            armPosition = ARM_MAX_POSITION;
        }

        if (armPosition < 0) {
            armPosition = 0;
        }
    }
    public void outputTelemetry(Telemetry telemetry) {
        if (((DcMotorEx) armMotor).isOverCurrent()) {
            telemetry.addLine("MOTOR HAS EXCEEDED CURRENT LIMIT!!!");
        }
        telemetry.addData("Arm angle (DEG)", armPosition * ARM_TICKS);
        telemetry.addData("Arm Motor Current", ((DcMotorEx) armMotor).getCurrent(
                CurrentUnit.AMPS));
    }

    }
