package org.firstinspires.ftc.teamcode.subassembly;

import androidx.core.math.MathUtils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class Wrist extends ServoSubassembly{
    private static final double MIN_SAFE_DEGREES =  -90;
    private static final double MAX_SAFE_DEGREES =   90;
    private static final double WRIST_PARKED     =    0;
    private boolean isStraight;

    double wristPosition;

    public Wrist(HardwareMap hwMap) {
        super(MIN_SAFE_DEGREES, MAX_SAFE_DEGREES, hwMap.get(Servo.class, "wrist"));
        wristPosition = WRIST_PARKED;
        execute();
    }

    public void isStraight() {
        setServoToAngle(0);
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
        setServoToAngle(-90);
        isStraight = false;
    }
    public void left() {
        setServoToAngle(90);
        isStraight = false;
    }
    public void toggleAngle() {
        if (isStraight) {
            right();
        }
        else {
            straight();
        }
    }

    public void adjustAngle(double degrees) {
        setServoToAngle(currentAngle+degrees);
        isStraight = (currentAngle > -1 && currentAngle < 1);
    }

    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Wrist Angle", currentAngle);
        telemetry.addData("Wrist Straight", isStraight);
    }

    public void execute() {
        wristPosition = MathUtils.clamp(wristPosition, MIN_SAFE_DEGREES, MAX_SAFE_DEGREES);
        setServoToAngle(WRIST_PARKED);
    }

}
