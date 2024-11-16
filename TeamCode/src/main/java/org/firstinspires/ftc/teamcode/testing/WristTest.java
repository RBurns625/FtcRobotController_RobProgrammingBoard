package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Wrist;

@SuppressWarnings("unused")

@TeleOp (name="Wrist Test", group="Test")

public class WristTest extends OpMode {
    private Wrist wrist;

    @Override
    public void init() {
        wrist = new Wrist(hardwareMap);
        wrist.zero();
    }

    @Override
    public void loop() {
        if (gamepad1.a) { wrist.right();    }
        if (gamepad1.b) { wrist.straight(); }
        if (gamepad1.x) { wrist.zero();     }
        if (gamepad1.y) { wrist.left();     }

        wrist.adjustAngle(-gamepad1.left_stick_x * 0.75);
        wrist.outputTelemetry(telemetry);

    }
}