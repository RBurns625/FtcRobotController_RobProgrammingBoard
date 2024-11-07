package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@SuppressWarnings("unused")
public class ColorDistanceSensor {
    Elbow elbow;
    Wrist wrist;
    Pincher pincher;
    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;
    double distanceMM;

    public ColorDistanceSensor(HardwareMap hwMap) {
        colorSensor = hwMap.get(ColorSensor.class, "color_distance_sensor)");
        distanceSensor = hwMap.get(DistanceSensor.class, "color_distance_sensor");
        elbow = new Elbow(hwMap);
        wrist = new Wrist(hwMap);
        pincher = new Pincher(hwMap);
        // claw = new Claw(hwMap);
    }
    public int getAmountRed() {
        return colorSensor.red();
    }
    public int getAmountGreen() {
        return colorSensor.green();
    }
    public int getAmountBlue() {
        return colorSensor.blue();
    }
    public double getDistanceMM(DistanceUnit du) {
        return distanceSensor.getDistance(du);
    }
    public void closeOnRed() {
        if (distanceMM < 10.0 && getAmountRed() > 1000) {
            // claw.closed();
            elbow.elbow45();
            wrist.wristLeft();
            pincher.pincherClose();
        }
    }
}
