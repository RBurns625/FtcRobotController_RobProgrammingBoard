package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@SuppressWarnings("unused")
public class ProgrammingBoard {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksPerRotation;
    private Servo servo;
    private double servoPosition;
    private AnalogInput pot;
    private Servo rgbLight;
    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;
    private IMU imu;

    public void init(HardwareMap hwMap) {
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = motor.getMotorType().getTicksPerRev();
        servo = hwMap.get(Servo.class, "servo");
        pot = hwMap.get(AnalogInput.class, "pot");
        rgbLight = hwMap.get(Servo.class, "Light");
        colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
        distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
        imu = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation =
                new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

        imu.initialize(new IMU.Parameters(RevOrientation));

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
        rgbLight.setPosition(position);
    }
    public void setServoAngle(Servo servo, double degree) {
        servo.setPosition(degree);
        rgbLight.setPosition(degree);
    }
    public double getServoPosition() {
        return servo.getPosition();
    }
    public double getServoAngle() {
        return servo.getPosition();
    }
    public double getPotAngle() {
        return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
    }
    public double getPotRange() {
        return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 1.0);
    }
    public int getAmountRed() {
        return colorSensor.red();
    }
    public int getAmountBlue() {
        return colorSensor.blue();
    }
    public int getAmountGreen() {
        return colorSensor.green();
    }
    public double getDistance(DistanceUnit du) {
        return distanceSensor.getDistance(du);
    }
    public double getHeading(AngleUnit angleUnit) {
        return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
    }

}
