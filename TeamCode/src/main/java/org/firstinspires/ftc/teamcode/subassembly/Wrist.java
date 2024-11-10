package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class Wrist {
    private Servo servo;
    private boolean isStraight;
    private double currentAngle;
    private static final double SERVO_DEGREES    =  180;
    private static final double MIN_SAFE_DEGREES =  -90;
    private static final double MAX_SAFE_DEGREES =   90;

    public Wrist(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "wrist");
    }

    public void isStraight() {
        setServoToAngle(-90);
        isStraight = true;
    }
    public void zero() {
        setServoToAngle(0);
        isStraight = true;
    }
    public void straight() {
        setServoToAngle(0);
        isStraight = true;
    }
    public void right() {
        setServoToAngle(90);
        isStraight = false;
    }
    public void left() {
        setServoToAngle(-90);
        isStraight = false;
    }

    public void setServoToAngle(double degrees) {
        degrees = Range.clip(degrees, MIN_SAFE_DEGREES, MAX_SAFE_DEGREES);
        currentAngle = degrees;
        servo.setPosition(Range.scale(degrees, -SERVO_DEGREES / 2, SERVO_DEGREES / 2, 0, 1));
    }

    public double getCurrentAngle() {return currentAngle;}

    public void adjustAngle(double degrees) { setServoToAngle(currentAngle+degrees); }

    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Wrist Angle", currentAngle);
        telemetry.addData("Wrist Straight", isStraight);
    }

}
