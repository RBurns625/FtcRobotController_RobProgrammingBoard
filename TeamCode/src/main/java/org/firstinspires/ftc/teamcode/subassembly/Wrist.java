package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@SuppressWarnings({"unused"})

/*
 * Wrist joint with Axon Servo installed
 * Wrist will be zeroed parallel to the top datum of the claw assembly
 * Wrist will have safety limits of -90 to 90 degrees motion
 *
 * The following notifications will play for enhanced driver situational awareness:
 * one short beep  when wrist = 0
 * two short beeps when wrist = 90
 *
 */

public class Wrist {
    private Servo servo;
    private double currentAngle;
    private static final double SERVO_DEGREES    =  180;
    private static final double MIN_SAFE_DEGREES =  5.0;
    private static final double MAX_SAFE_DEGREES = -5.0;

    public Wrist(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "wrist");
    }

    public void setServoToAngle(double degrees) {
        degrees = Range.clip(degrees, MIN_SAFE_DEGREES, MAX_SAFE_DEGREES);
        currentAngle = degrees;
        servo.setPosition(Range.scale(degrees, -SERVO_DEGREES / 2, SERVO_DEGREES / 2, 0, 1));
    }

    public double getCurrentAngle() {
        return currentAngle;
    }

}
