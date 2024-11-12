package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")
public class ViperSlideSwingArm {
    ViperSlide viperSlide;
    SwingArm swingArm;

    public ViperSlideSwingArm(HardwareMap  hwMap) {
        viperSlide = new ViperSlide();
        swingArm   = new SwingArm();
    }

    public void parked() {
        viperSlide.parked();
        swingArm.parked();
    }
    public void traveling() {
        viperSlide.traveling();
        swingArm.traveling();
    }
    public void sampleCollect() {
        viperSlide.sampleCollect();
        swingArm.sampleCollect();
    }
    public void clearBarrier() {
        viperSlide.clearBarrier();
        swingArm.clearBarrier();
    }
    public void highBasket() {
        viperSlide.highBasket();
        swingArm.highBasket();
    }
    public void lowBasket() {
        viperSlide.lowBasket();
        swingArm.lowBasket();
    }
    public void highRung() {
        viperSlide.highRung();
        swingArm.highRung();
    }
    public void lowRung() {
        viperSlide.lowRung();
        swingArm.lowRung();
    }
    public void hangPrepare() {
        viperSlide.hangPrepare();
        swingArm.hangPrepare();
    }
    public void hangRobot() {
        viperSlide.hangRobot();
        viperSlide.hangRobot();
    }

    public void outputTelemetry(Telemetry telemetry) {
        viperSlide.outputTelemetry(telemetry);
        swingArm.outputTelemetry(telemetry);
    }
}
