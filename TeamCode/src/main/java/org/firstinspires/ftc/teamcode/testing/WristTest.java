package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Wrist;

@SuppressWarnings("unused")

@TeleOp (name="Wrist Test", group="Test)")

/*
 * Config Setup:
 * ProgBoard servo port 1: "wrist"
 * ProgBoard servo port 5: "light"
 */

public class WristTest extends OpMode {
    private Wrist w;

    @Override
    public void init() {
        w = new Wrist(hardwareMap);
        w.zero();
    }

    @Override
    public void loop() {
        if (gamepad1.a) { w.right();    }
        if (gamepad1.b) { w.straight(); }
        if (gamepad1.x) { w.zero();     }
        if (gamepad1.y) { w.left();     }

        w.adjustAngle(-gamepad1.left_stick_x * 0.5);

        w.outputTelemetry(telemetry);

        telemetry.addLine("Gamepad A = Wrist Horizontal (Blue Light)");
        telemetry.addLine("Gamepad B = Wrist Straight (Yellow Light)");
        telemetry.addLine("Gamepad X = Wrist Zero (Green Light");

    }
}
