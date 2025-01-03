package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class Pincher extends ServoSubassembly{
    private static final double MIN_SAFE_DEGREES =  -67;
    private static final double MAX_SAFE_DEGREES =   20;
    private static final double PINCHER_OPEN     =  -15;
    private boolean isOpen;

    public Pincher(HardwareMap hwMap) {
        super(MIN_SAFE_DEGREES, MAX_SAFE_DEGREES, hwMap.get(Servo.class, "pincher"));
    }

    public void zero()   {
        setServoToAngle(MAX_SAFE_DEGREES);
        isOpen   = true;
    }
    public void open()   {
        setServoToAngle(PINCHER_OPEN);
        isOpen   = true;
    }
    public void closed() {
        setServoToAngle(MIN_SAFE_DEGREES);
        isOpen   = false;
    }

    public void toggle() {
        if (!isOpen) {
            open();
        } else {
            closed();
        }
    }

     public void adjustAngle(double degrees) {
        setServoToAngle(currentAngle + degrees);
    }

    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Pincher Angle", currentAngle);
        telemetry.addData("Pincher Open", isOpen);
        telemetry.addData("Pincher Closed", !isOpen);
    }
}
