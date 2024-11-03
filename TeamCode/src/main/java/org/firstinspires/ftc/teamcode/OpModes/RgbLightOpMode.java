package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@SuppressWarnings("unused")
@TeleOp

public class RgbLightOpMode extends OpMode {
    ProgrammingBoard board = new ProgrammingBoard();

    @Override
    public void init() {
        board.init(hardwareMap);
        board.setServoPosition(0.0);
    }

    @Override
    public void loop() {
        /*
        * Is there a cleaner way to write a list of 'if' statements?
         */

        if(gamepad1.a) {
            // telemetry servo position is slightly off (0.2767) so it might not turn on the LED?
            board.setServoPosition(0.277);
        }
        if(gamepad1.b) {
            board.setServoPosition(0.388);
        }
        if(gamepad1.x) {
            board.setServoPosition(0.500);
        }
        if(gamepad1.y) {
            board.setServoPosition(0.611);
        }
        if (gamepad1.left_stick_button) {
            board.setServoPosition(0.0);
        }
        if (gamepad1.dpad_up) {
            board.setServoPosition(0.333);
        }
        if (gamepad1.dpad_left) {
            board.setServoPosition(0.444);
        }
        if (gamepad1.dpad_right) {
            board.setServoPosition(0.555);
        }
        if (gamepad1.dpad_down) {
            board.setServoPosition(0.666);
        }

        telemetry.addData("Servo Position", board.getServoPosition());
        telemetry.addLine("Press the following buttons");
        telemetry.addLine("A: Red");
        telemetry.addLine("B: Yellow");
        telemetry.addLine("X: Green");
        telemetry.addLine("Y: Blue");
        telemetry.addLine("");
        telemetry.addLine("Left Stick Button: Off");
        telemetry.addLine("");
        telemetry.addLine("D Pad:");
        telemetry.addLine("Up:    Orange");
        telemetry.addLine("Left:  Sage");
        telemetry.addLine("Right: Azure");
        telemetry.addLine("Down:  Indigo");
        }
}
