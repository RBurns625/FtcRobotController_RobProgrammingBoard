package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class Elbow {
    private Servo servo;
    private double currentAngle;
    private boolean isStraight;
    private static final double SERVO_DEGREES  = 180;
    private static final double MIN_SAFE_ANGLE = -90;
    private static final double MAX_SAFE_ANGLE = 0;

    public Elbow(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "elbow");
    }

    public void zero() {
        setServoToAngle(-90);
        isStraight = false;
    }
    public void straight() {
        setServoToAngle(-90);
        isStraight = false;
    }
    public void down() {
        setServoToAngle(0);
        isStraight = true;
    }
    public void angled45() {
        setServoToAngle(-45);
        isStraight = false;
    }

    public void setServoToAngle(double degrees) {
        degrees = Range.clip(degrees, MIN_SAFE_ANGLE, MAX_SAFE_ANGLE);
        currentAngle = degrees;
        servo.setPosition(Range.scale(degrees, -SERVO_DEGREES / 2, SERVO_DEGREES /2, 0, 1));
    }

    public double getCurrentAngle() {
        return currentAngle;
    }

    public void adjustAngle(double degrees) {
        setServoToAngle(currentAngle + degrees);
    }

    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Elbow Angle", currentAngle);
        telemetry.addData("Elbow Straight", isStraight);
    }

}
