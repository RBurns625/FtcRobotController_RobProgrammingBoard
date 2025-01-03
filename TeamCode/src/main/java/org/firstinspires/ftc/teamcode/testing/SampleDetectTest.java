package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Pincher;
import org.firstinspires.ftc.teamcode.subassembly.SampleDetector;

@SuppressWarnings("unused")

@TeleOp (name="Sample Detect Test")

public class SampleDetectTest extends OpMode {
    private Pincher pincher;
    private SampleDetector sampleDetector;

    @Override
    public void init() {
        pincher = new Pincher(hardwareMap);
        sampleDetector = new SampleDetector(hardwareMap);
        pincher.open();
    }

    @Override
    public void loop() {
        SampleDetector.SampleType detectedSample = sampleDetector.getDetectedSample();

        if ((detectedSample == SampleDetector.SampleType.RED) ||
            (detectedSample == SampleDetector.SampleType.YELLOW)) {
            pincher.closed();
        }

        sampleDetector.outputTelemetry(telemetry);
        telemetry.update();

        if(gamepad1.right_stick_button) {
            pincher.open();
        }
    }
}
