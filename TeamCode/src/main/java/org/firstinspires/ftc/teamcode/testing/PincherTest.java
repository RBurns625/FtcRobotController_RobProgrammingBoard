package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Pincher;

@SuppressWarnings("unused")

@TeleOp (name="Pincher Test", group="Test)")
public class PincherTest extends OpMode {
    private Pincher pincher;

    @Override
    public void init() {
        pincher = new Pincher(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.x) {
            pincher.zero();
        }
        if(gamepad1.a) {
            pincher.open();
        }
        if (gamepad1.b) {
            pincher.closed();
        }

        pincher.adjustAngle(-gamepad1.left_trigger * 0.25);

        pincher.adjustAngle(gamepad1.right_trigger * 0.25);

        pincher.outputTelemetry(telemetry);
        telemetry.update();
    }
}
