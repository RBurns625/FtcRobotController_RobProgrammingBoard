package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@SuppressWarnings("unused")
public class ProgrammingBoard {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksPerRotation;
    private Servo servo;
    private double servoPosition;

    public void init(HardwareMap hwMap) {
        // Touch Sensor Settings
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        // Motor Settings
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = motor.getMotorType().getTicksPerRev();
        // Servo Settings
        servo = hwMap.get(Servo.class, "servo");
    }
    public boolean isTouchSensorPressed() {
        return !touchSensor.getState();
    }
    public boolean isTouchSensorReleased() {
        return touchSensor.getState();
    }
    public void setMotorSpeed(double speed){
        motor.setPower(speed);
    }
    public double getMotorRotations() {
        return motor.getCurrentPosition() / ticksPerRotation;
    }
    public void setMotorZeroBehavior(DcMotor.ZeroPowerBehavior zeroBehavior) {
        motor.setZeroPowerBehavior(zeroBehavior);
    }
    public void setServoPosition(double position) {
        servo.setPosition(position);
    }
    public double getServoPosition() {
        return servo.getPosition();
    }
}
