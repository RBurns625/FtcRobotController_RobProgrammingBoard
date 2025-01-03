package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.WheelieBar;

@SuppressWarnings("unused")
@TeleOp (name="Wheelie Test", group="Test")

public class WheelieBarTest extends OpMode {
    private WheelieBar wheelie;

    @Override
    public void init() {
        wheelie = new WheelieBar(hardwareMap);
        wheelie.collapsed();
    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            wheelie.extended();
        }
        else if(gamepad1.b) {
            wheelie.collapsed();
        }
        wheelie.adjustAngle(-gamepad1.left_trigger * 1.0);
        wheelie.adjustAngle(gamepad1.right_trigger * 1.0);

        wheelie.outputTelemetry(telemetry);
        telemetry.update();
    }
}
