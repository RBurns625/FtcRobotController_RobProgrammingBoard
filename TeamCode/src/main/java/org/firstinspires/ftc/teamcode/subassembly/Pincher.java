package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@SuppressWarnings("unused")

public class Pincher {
    GoBildaRGBLight light;
    private Servo servo;
    private double currentAngle;
    private static final double SERVO_DEGREES    =  180;
    private static final double MIN_SAFE_DEGREES =  90;
    private static final double MAX_SAFE_DEGREES =  0.0;

    public Pincher(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "pincher");
        light = new GoBildaRGBLight(hwMap);
    }

    public void setServoToAngle(double degrees) {
        degrees = Range.clip(degrees, MIN_SAFE_DEGREES, MAX_SAFE_DEGREES);
        currentAngle = degrees;
        servo.setPosition(Range.scale(degrees, -SERVO_DEGREES / 2, SERVO_DEGREES / 2, 0, 1));
    }

    public void pincherZero() {
        setServoToAngle(0);
        light.setColorYellow();
    }
    public void pincherOpen() {
        setServoToAngle(-45);
        light.setColorBlue();
    }
    public void pincherClose() {
        setServoToAngle(-90);
        light.setColorGreen();
    }

    public double getCurrentAngle() {
        return currentAngle;
    }
    public void adjustAngle(double degrees) {
        setServoToAngle(currentAngle+degrees);
    }
}
