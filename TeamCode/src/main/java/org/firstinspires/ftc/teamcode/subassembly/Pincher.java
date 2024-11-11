package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class Pincher extends ServoSubassembly{
    GoBildaRGBLight light;
    private static final double MIN_SAFE_DEGREES =  -90;
    private static final double MAX_SAFE_DEGREES =  -30;
    private boolean isOpen;
    private boolean isClosed;


    public Pincher(HardwareMap hwMap) {
        super(MIN_SAFE_DEGREES, MAX_SAFE_DEGREES, hwMap.get(Servo.class, "Pincher Servo"));
        light = new GoBildaRGBLight(hwMap);
    }

    public void zero()   {
        setServoToAngle(MAX_SAFE_DEGREES);
        isOpen   = true;
        isClosed = false;
        light.setColorYellow();
    }
    public void open()   {
        setServoToAngle(-45);
        isOpen   = true;
        isClosed = false;
        light.setColorBlue();
    }
    public void closed() {
        setServoToAngle(MIN_SAFE_DEGREES);
        isOpen   = false;
        isClosed = true;
        light.setColorGreen();
    }

    public void toggle() {
        if (isOpen) {
            closed();
            isOpen   = false;
            isClosed = true;
        } else {
            open();
            isOpen   = true;
            isClosed = false;
        }
    }

    public void adjustAngle(double degrees) {
        setServoToAngle(currentAngle + degrees);
    }

    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Pincher Angle", currentAngle);
        telemetry.addData("Pincher Open", isOpen);
        telemetry.addData("Pincher Closed", isClosed);
    }

}
