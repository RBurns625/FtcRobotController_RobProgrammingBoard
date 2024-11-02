package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@SuppressWarnings("unused")
@TeleOp

public class rgbLightOpMode extends OpMode {
    ProgrammingBoard board = new ProgrammingBoard();

    @Override
    public void init() {
        board.init(hardwareMap);
        telemetry.addLine("Press the following buttons");
        telemetry.addLine("A: Red");
        telemetry.addLine("B: Yellow");
        telemetry.addLine("X: Green");
        telemetry.addLine("Y: Blue");
        telemetry.addLine("Use 'Right Trigger' to go through spectrum");
    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            board.setServoPosition(0.277);
            telemetry.addLine("Color: Red");
        }
        else if(gamepad1.b) {
            board.setServoPosition(0.388);
            telemetry.addLine("Color: Yellow");
        }
        else if(gamepad1.x) {
            board.setServoPosition(0.500);
            telemetry.addLine("Color: Green");
        }
        else if(gamepad1.y) {
            board.setServoPosition(0.611);
            telemetry.addLine("Color: Blue");
        }
        else board.setServoPosition(0.0);

        board.setServoPosition(gamepad1.right_trigger);
        telemetry.addData("Servo Position", board.getServoPosition());
        telemetry.update();
    }
}
