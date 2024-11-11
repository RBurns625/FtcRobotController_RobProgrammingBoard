package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.GoBildaRGBLight;
import org.firstinspires.ftc.teamcode.subassembly.Pincher;

@SuppressWarnings("unused")

@TeleOp (name="Pincher Test", group="Test)")
public class PincherTest extends OpMode {
    private Pincher pincher;
    private GoBildaRGBLight light;
    boolean yAlreadyPressed;
    boolean pincherOpen;

    @Override
    public void init() {
        pincher = new Pincher(hardwareMap);
        light = new GoBildaRGBLight(hardwareMap);
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
        if (gamepad1.y && !yAlreadyPressed) {
            pincherOpen = !pincherOpen;
            telemetry.addData("Pincher", pincherOpen);
            if (pincherOpen) {
                pincher.open();
            } else {
                pincher.closed();
            }
        }
        yAlreadyPressed = gamepad1.y;

        if (pincher.getCurrentAngle() >= -45 ) {
            light.setColorRed();
        } else if (pincher.getCurrentAngle() <= -85) {
            light.setColorPClosed();
        } else light.setColorBlack();

        pincher.adjustAngle(-gamepad1.left_trigger * 0.75);
        pincher.adjustAngle(gamepad1.right_trigger * 0.75);

        pincher.outputTelemetry(telemetry);
        telemetry.update();
    }
}
