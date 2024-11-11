package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Claw;
import org.firstinspires.ftc.teamcode.subassembly.Elbow;
import org.firstinspires.ftc.teamcode.subassembly.Pincher;
import org.firstinspires.ftc.teamcode.subassembly.Wrist;

@SuppressWarnings("unused")
@TeleOp (name="Claw Test", group="Test)")

/*
 * ProgBoard Servo Port 1: "pincher"
 * ProgBoard Servo Port 2: "wrist"
 * ProgBoard Servo Port 3: "elbow"
 * ProgBoard Servo Port 5: "light"
 */

public class ClawTest extends OpMode {
    Claw    c;
    Pincher p;
    Wrist   w;
    Elbow   e;

    @Override
    public void init() {
        c = new Claw(hardwareMap);
        p = new Pincher(hardwareMap);
        w = new Wrist(hardwareMap);
        e = new Elbow(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a) { c.horizPickup();      }
        if (gamepad1.x) { c.clearSubmersible(); }
        if (gamepad1.b) { c.vertPickup();       }
        if (gamepad1.y) { p.toggle();           }

        if (gamepad1.right_bumper) { c.prepareBasket();   }
        if (gamepad1.left_bumper)  { c.prepareSpecimen(); }

        if (gamepad1.dpad_up)    { c.prepareHang(); }
        if (gamepad1.dpad_left)  { c.parked();      }
        if (gamepad1.dpad_down)  { c.prepareHang(); }
        if (gamepad1.dpad_right) { c.traveling();   }

        p.adjustAngle  (-gamepad1.left_trigger  * 0.75);
        p.adjustAngle  ( gamepad1.right_trigger * 0.75);
        w.adjustAngle  (-gamepad1.left_stick_x  * 0.75);
        e.adjustAngle  (-gamepad1.right_stick_y * 0.75);
    }
}
