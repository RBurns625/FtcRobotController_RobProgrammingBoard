package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")
public class Claw {
    Elbow   elbow;
    Pincher pincher;
    Wrist   wrist;

    public Claw(HardwareMap hwMap) {
        elbow    = new Elbow(hwMap);
        pincher  = new Pincher(hwMap);
        wrist    = new Wrist(hwMap);
    }

    public void parked() {
        pincher.zero();
        wrist.zero();
        elbow.zero();
    }
    public void horizPickup() {
        pincher.open();
        wrist.straight();
        elbow.down();
    }
    public void vertPickup() {
        pincher.open();
        wrist.straight();
        elbow.down();
    }
    public void traveling() {
        pincher.closed();
        wrist.straight();
        elbow.angled45();
    }
    public void prepareBasket() {
        pincher.closed();
        wrist.right();
        elbow.angled45();
    }
    public void prepareSpecimen() {
        pincher.closed();
        wrist.left();
        elbow.angled45();
    }
    public void prepareHang() {
        pincher.zero();
        wrist.zero();
        elbow.zero();
    }
    public void clearSubmersible() {
        pincher.open();
        wrist.straight();
        elbow.down();
    }

    public void togglePincher() {
        pincher.toggle();
    }

    public void adjustPincher(double degrees) {
        pincher.adjustAngle(degrees);
    }
    public void adjustWrist(double degrees) {
        wrist.adjustAngle(degrees);
    }
    public void adjustElbow(double degrees) {
        elbow.adjustAngle(degrees);
    }

    public void outputTelemetry(Telemetry telemetry) {
        elbow.outputTelemetry(telemetry);
        pincher.outputTelemetry(telemetry);
        wrist.outputTelemetry(telemetry);
    }
}
