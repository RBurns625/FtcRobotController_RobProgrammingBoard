package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class WheelieBar extends ServoSubassembly{
    private static final double MIN_SAFE_DEGREES = -90;
    private static final double MAX_SAFE_DEGREES =   0;

    public WheelieBar(HardwareMap hwMap) {
        super(MIN_SAFE_DEGREES, MAX_SAFE_DEGREES, hwMap.get(Servo.class, "wheelie"));
    }

    public void collapsed() {
        setServoToAngle(MIN_SAFE_DEGREES);
    }

    public void extended() {
        setServoToAngle(MAX_SAFE_DEGREES);
    }

    public void adjustAngle(double degrees) {
        setServoToAngle(currentAngle + degrees);
    }

    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Wheelie position", currentAngle);
    }
}
