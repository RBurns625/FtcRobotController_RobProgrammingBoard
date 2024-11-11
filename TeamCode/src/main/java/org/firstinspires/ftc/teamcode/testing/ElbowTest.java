package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Elbow;

@SuppressWarnings("unused")
@TeleOp (name="Elbow Test", group="Test)")
public class ElbowTest extends OpMode {
    private Elbow elbow;

    @Override
    public void init() {
        elbow = new Elbow(hardwareMap);
        elbow.zero();
    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            elbow.zero();
        }
        if(gamepad1.y) {
            elbow.straight();
        }
        if (gamepad1.b) {
            elbow.down();
        }
        if (gamepad1.x) {
            elbow.angled45();
        }
        elbow.adjustAngle(-gamepad1.left_trigger  * 0.75);
        elbow.adjustAngle( gamepad1.right_trigger * 0.75);

        elbow.outputTelemetry(telemetry);
    }

}
