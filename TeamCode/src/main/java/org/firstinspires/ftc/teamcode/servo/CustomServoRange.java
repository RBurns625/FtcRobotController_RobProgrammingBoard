package org.firstinspires.ftc.teamcode.servo;

import androidx.core.math.MathUtils;

import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

@SuppressWarnings("unused")

public abstract class CustomServoRange {
    protected final Servo internalServo;
    private final double servoDegrees;
    private double minPwmValue;
    private double maxPwmValue;

    @SuppressWarnings("unused")
    protected CustomServoRange(Servo servo) {
        this(servo, 270, PwmControl.PwmRange.usPulseLowerDefault, PwmControl.PwmRange.usPulseUpperDefault);
    }

    protected CustomServoRange(Servo servo, double servoDegrees, double minPwmValue, double maxPwmValue) {
        this.internalServo = servo;
        this.servoDegrees = servoDegrees;
        this.minPwmValue = minPwmValue;
        this.maxPwmValue = maxPwmValue;

        setServoRange();
    }

    public void setAngle(double degrees) {
        degrees += servoDegrees / 2;
        degrees = MathUtils.clamp(degrees, 0, servoDegrees);
        internalServo.setPosition(degrees / servoDegrees);
    }

    private void setServoRange() {
        // Check to see if the servo controller is an implementation of ServoControllerEx
        if (internalServo.getController() instanceof ServoControllerEx) {
            ServoControllerEx servoController = (ServoControllerEx) internalServo.getController();
            servoController.setServoPwmRange(
                    internalServo.getPortNumber(),
                    new PwmControl.PwmRange(minPwmValue, maxPwmValue)
            );
        }
    }

    public void adjustMinPwmValue(double delta) {
        this.minPwmValue += delta;
        setServoRange();
    }

    public void adjustMaxPwmValue(double delta) {
        this.maxPwmValue += delta;
        setServoRange();
    }

    public double getMinPwmValue() {
        return minPwmValue;
    }

    public double getMaxPwmValue() {
        return maxPwmValue;
    }

    public double getMaxDegrees() { return servoDegrees / 2; }

    public double getMinDegrees() { return -servoDegrees / 2; }
}
