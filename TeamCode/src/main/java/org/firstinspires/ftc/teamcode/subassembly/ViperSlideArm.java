package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@SuppressWarnings("unused")

public class ViperSlideArm {
    DcMotor armMotor;
    DcMotor slideMotor;

    private final double ARM_TICKS_PER_DEGREE = 28.0 * 250047.0 / 4913.0 * 100.0 / 20.0 / 360.0;
    private final double SLIDE_TICKS_PER_MM  = (111132.0 / 289.0) / 120.0;

    final double ARM_MAX_POSITION    = 120 * ARM_TICKS_PER_DEGREE;
    final double SLIDE_MAX_EXTENSION = 440 * SLIDE_TICKS_PER_MM;

    private final double ARM_RETRACTED     = 0;
    final double ARM_COLLECT       = 0   *  ARM_TICKS_PER_DEGREE;
    final double ARM_CLEAR_BARRIER = 15  *  ARM_TICKS_PER_DEGREE;
    final double ARM_BASKET_LOW    = 90  *  ARM_TICKS_PER_DEGREE;
    final double ARM_BASKET_HIGH   = 100 * ARM_TICKS_PER_DEGREE;
    final double ARM_RUNG_LOW      = 45  * ARM_TICKS_PER_DEGREE;
    final double ARM_RUNG_HIGH     = 60  * ARM_TICKS_PER_DEGREE;
    final double ARM_HANG_PREP     = 110 * ARM_TICKS_PER_DEGREE;
    final double ARM_WINCH_ROBOT   = 10  * ARM_TICKS_PER_DEGREE;

    final double SLIDE_RETRACTED     = 0   * SLIDE_TICKS_PER_MM;
    final double SLIDE_BASKET_HIGH   = 390 * SLIDE_TICKS_PER_MM;
    final double SLIDE_BASKET_LOW    = 200 * SLIDE_TICKS_PER_MM;
    final double SLIDE_SUBMERSIBLE   = 25  * SLIDE_TICKS_PER_MM;
    final double SLIDE_RUNG_HIGH     = 50  * SLIDE_TICKS_PER_MM;
    final double SLIDE_RUNG_LOW      = 25  * SLIDE_TICKS_PER_MM;

    private double armPosition   = ARM_RETRACTED;
    private double slidePosition = SLIDE_RETRACTED;
    private double armPositionFudgeFactor;

    public void init (HardwareMap hwMap) {
        armMotor   = hwMap.get(DcMotor.class, "armMotor");
        slideMotor = hwMap.get(DcMotor.class, "slideMotor");

        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ((DcMotorEx) armMotor).setCurrentAlert(5, CurrentUnit.AMPS);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void parked() {
        armPosition   = ARM_RETRACTED;
        slidePosition = SLIDE_RETRACTED;
    }
    public void traveling() {
        slidePosition = SLIDE_RETRACTED;
    }
    public void sampleCollect() {
        armPosition   = ARM_COLLECT;
        slidePosition = SLIDE_RETRACTED;
    }
    public void clearBarrier() {
        armPosition   = ARM_CLEAR_BARRIER;
        slidePosition = SLIDE_SUBMERSIBLE;
    }
    public void highBasket() {
        armPosition   = ARM_BASKET_HIGH;
        slidePosition = SLIDE_BASKET_HIGH;
    }
    public void lowBasket() {
        armPosition   = ARM_BASKET_LOW;
        slidePosition = SLIDE_BASKET_LOW;
    }
    public void highRung() {
        armPosition   = ARM_RUNG_HIGH;
        slidePosition = SLIDE_RUNG_HIGH;
    }
    public void lowRung() {
        armPosition   = ARM_RUNG_LOW;
        slidePosition = SLIDE_RUNG_LOW;
    }
    public void hangPrepare() {
        armPosition   = ARM_HANG_PREP;
        slidePosition = SLIDE_RETRACTED;
    }
    public void hangRobot() {
        armPosition   = ARM_WINCH_ROBOT;
        slidePosition = SLIDE_RETRACTED;
    }

    public void setArmPositionFudgeFactor(double degrees) {
        armPositionFudgeFactor = degrees * ARM_TICKS_PER_DEGREE;
    }

    private void limitSlidePosition() {
        if (slidePosition > SLIDE_MAX_EXTENSION) {
            slidePosition = SLIDE_MAX_EXTENSION;
        }
        if (slidePosition < 0) {
            slidePosition = 0;
        }
    }

    public void moveSlide(double millimeters) {
        slidePosition += millimeters * SLIDE_TICKS_PER_MM;
        limitSlidePosition();
    }

    public void moveArm(double degrees) {
        armPosition += degrees * ARM_TICKS_PER_DEGREE;
    }

    private final double ARM_REAR_VERT_SUPPORT_HEIGHT_MM = 176;
    private final double ARM_LENGTH_AT_PARK_POSITION_MM  = 511;
    private final double ARM_ANGLE_AT_PARK_POSIT_DEG     = Math.toDegrees(Math.acos(
            ARM_REAR_VERT_SUPPORT_HEIGHT_MM / ARM_LENGTH_AT_PARK_POSITION_MM));

    private double calculateArmLiftComp() {
        double currentArmDegrees  = ARM_ANGLE_AT_PARK_POSIT_DEG + armPosition
                / ARM_TICKS_PER_DEGREE;
        double currentSlideLength = ARM_LENGTH_AT_PARK_POSITION_MM + slidePosition
                / SLIDE_TICKS_PER_MM;

        double requestedVerticalSupportHeight = Math.cos(Math.toRadians(currentArmDegrees))
                * currentSlideLength;
        if (requestedVerticalSupportHeight > ARM_REAR_VERT_SUPPORT_HEIGHT_MM) {
            double targetArmAngleDegrees = Math.toDegrees(Math.acos(
                    ARM_REAR_VERT_SUPPORT_HEIGHT_MM / currentSlideLength));
            return (targetArmAngleDegrees - currentArmDegrees) * ARM_TICKS_PER_DEGREE * 2;
        }

        return 0;
    }

    private void limitArmPosition() {
        if(armPosition > ARM_MAX_POSITION) {
            armPosition = ARM_MAX_POSITION;
        }

        if (armPosition < 0) {
            armPosition = 0;
        }
    }

    public void execute() {
        limitArmPosition();
        armMotor.setTargetPosition((int) (armPosition + armPositionFudgeFactor
                + calculateArmLiftComp()));
        ((DcMotorEx) armMotor).setVelocity(2100);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        slideMotor.setTargetPosition((int) (slidePosition));
        ((DcMotorEx) slideMotor).setVelocity(2100);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void outputTelemetry(Telemetry telemetry) {
        if (((DcMotorEx) armMotor).isOverCurrent()) {
            telemetry.addLine("MOTOR HAS EXCEEDED CURRENT LIMIT!!!");
        }

        telemetry.addData("Arm angle (DEG)", armPosition * ARM_TICKS_PER_DEGREE);
        telemetry.addData("Slide Extension", slidePosition / SLIDE_TICKS_PER_MM);
        telemetry.addData("Arm Motor Current", ((DcMotorEx) slideMotor).getCurrent(
                CurrentUnit.AMPS));
        telemetry.addData("Arm Angle Comp (DEG)", calculateArmLiftComp() * ARM_TICKS_PER_DEGREE);
    }
}
