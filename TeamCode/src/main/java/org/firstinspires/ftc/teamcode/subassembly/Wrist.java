package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")

public class Wrist extends ServoSubassembly{
    GoBildaRGBLight light;
    private static final double MIN_SAFE_DEGREES =  -90;
    private static final double MAX_SAFE_DEGREES =   90;
    private boolean isStraight;

    public Wrist(HardwareMap hwMap) {
        super(MIN_SAFE_DEGREES, MAX_SAFE_DEGREES, hwMap.get(Servo.class, "Wrist"));
        light = new GoBildaRGBLight(hwMap);
    }

    public void isStraight() {
        setServoToAngle(0);
        isStraight = true;
    }
    public void zero() {
        setServoToAngle(0);
        isStraight = true;
        light.setColorGreen();
    }
    public void straight() {
        setServoToAngle(0);
        isStraight = true;
        light.setColorGreen();
    }
    public void right() {
        setServoToAngle(-90);
        isStraight = false;
        light.setColorBlue();
    }
    public void left() {
        setServoToAngle(90);
        isStraight = false;
        light.setColorRed();
    }
    public void toggleAngle() {
        if (isStraight) {
            right();
        }
        else {
            straight();
        }
    }

    public void adjustAngle(double degrees) {
        setServoToAngle(currentAngle+degrees);
        isStraight = (currentAngle > -1 && currentAngle < 1);
        if(currentAngle == 90) {
            light.setColorRed();
        }
        if(currentAngle == -90) {
            light.setColorBlue();
        }
        if (currentAngle == 0) {
            light.setColorGreen();
        }
        else {
            light.setColorBlack();
        }
    }

    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Wrist Angle", currentAngle);
        telemetry.addData("Wrist Straight", isStraight);
    }

}
