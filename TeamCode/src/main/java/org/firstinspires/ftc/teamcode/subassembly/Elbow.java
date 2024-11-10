package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class Elbow extends ServoSubassembly{
    private boolean isStraight;
    private static final double MIN_SAFE_DEGREES = -90;
    private static final double MAX_SAFE_DEGREES = 0;

    public Elbow(HardwareMap hwMap) {
        super(MIN_SAFE_DEGREES, MAX_SAFE_DEGREES, hwMap.get(Servo.class, "Elbow"));
    }

    public void zero() {
        setServoToAngle(0);
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

    public void adjustAngle(double degrees) {
        setServoToAngle(currentAngle + degrees);
    }

    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Elbow Angle", currentAngle);
        telemetry.addData("Elbow Straight", isStraight);
    }

}