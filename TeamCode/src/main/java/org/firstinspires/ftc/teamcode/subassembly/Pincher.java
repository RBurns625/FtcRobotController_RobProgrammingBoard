package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class Pincher {
    GoBildaRGBLight light;
    private Servo servo;
    private double currentAngle;
    private boolean isOpen;
    private boolean isClosed;
    private static final double SERVO_DEGREES    =  180;
    private static final double MIN_SAFE_DEGREES =  -90;
    private static final double MAX_SAFE_DEGREES =  -30;

    public Pincher(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "pincher");
        light = new GoBildaRGBLight(hwMap);
    }

    public void setServoToAngle(double degrees) {
        degrees = Range.clip(degrees, MIN_SAFE_DEGREES, MAX_SAFE_DEGREES);
        currentAngle = degrees;
        servo.setPosition(Range.scale(degrees, -SERVO_DEGREES / 2, SERVO_DEGREES / 2, 0, 1));
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

    public double getCurrentAngle() {
        return currentAngle;
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
