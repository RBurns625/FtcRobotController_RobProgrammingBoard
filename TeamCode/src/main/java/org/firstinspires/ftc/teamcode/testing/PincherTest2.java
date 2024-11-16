package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Pincher;

@SuppressWarnings("unused")

@TeleOp (name="Pincher Test 2", group="Test")
public class PincherTest2 extends OpMode {
    private Pincher pincher;
    boolean a_mode_active = false;
    boolean a_lock        = false;

    @Override
    public void init() {
        pincher = new Pincher(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.x) {
            pincher.zero();
        } else if(gamepad1.a) {
            pincher.open();
        } else if (gamepad1.b) {
            pincher.closed();
        } else if (gamepad1.y && !a_lock && !a_mode_active) {
            pincher.open();
            a_lock = true;
            a_mode_active = true;
            } else if (gamepad1.y && !a_lock && a_mode_active) {
            pincher.closed();
            a_mode_active = false;
            a_lock = true;
            } else if (!gamepad1.y && a_lock) {
            a_lock = false;
            }

        pincher.adjustAngle(-gamepad1.left_trigger * 0.75);
        pincher.adjustAngle(gamepad1.right_trigger * 0.75);

        pincher.outputTelemetry(telemetry);
        telemetry.update();
    }
}
