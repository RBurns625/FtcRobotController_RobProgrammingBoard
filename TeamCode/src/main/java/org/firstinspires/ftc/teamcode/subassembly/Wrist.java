package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class Wrist extends ServoSubassembly{
    private static final double MIN_SAFE_DEGREES =  -90;
    private static final double MAX_SAFE_DEGREES =   90;
    private static final double WRIST_PARKED     =    0;

    public Wrist(HardwareMap hwMap) {
        super(MIN_SAFE_DEGREES, MAX_SAFE_DEGREES, hwMap.get(Servo.class, "wrist"));
    }

    public void zero() {
        setServoToAngle(0);
    }
    public void straight() {
        setServoToAngle(0);
    }
    public void right() {
        setServoToAngle(-90);
    }
    public void left() {
        setServoToAngle(90);
    }

    public void adjustAngle(double degrees) {
        setServoToAngle(currentAngle + degrees);
    }

    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Wrist Angle", currentAngle);
    }
}
