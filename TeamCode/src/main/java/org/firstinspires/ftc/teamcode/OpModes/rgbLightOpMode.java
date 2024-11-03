package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@SuppressWarnings("unused")
@TeleOp

public class rgbLightOpMode extends OpMode {
    ProgrammingBoard board = new ProgrammingBoard();
    private static final double SERVO_DEGREES = 270.0;
    private double rgbDegrees = 0;

    @Override
    public void init() {
        board.init(hardwareMap);
        telemetry.addLine("Press the following buttons");
        telemetry.addLine("A: Red");
        telemetry.addLine("B: Yellow");
        telemetry.addLine("X: Green");
        telemetry.addLine("Y: Blue");
        telemetry.addLine("Use 'Right Trigger' to go through spectrum");

        setServoToAngle(rgbDegrees);
    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            board.setServoPosition(0.277);
            telemetry.addLine("Color: Red");
            telemetry.update();
        }
        else if(gamepad1.b) {
            board.setServoPosition(0.388);
            telemetry.addLine("Color: Yellow");
            telemetry.update();
        }
        else if(gamepad1.x) {
            board.setServoPosition(0.500);
            telemetry.addLine("Color: Green");
            telemetry.update();
        }
        else if(gamepad1.y) {
            board.setServoPosition(0.611);
            telemetry.addLine("Color: Blue");
            telemetry.update();
        }
        else if (gamepad1.dpad_down) {
            board.setServoPosition(0.0);
            telemetry.addLine("Color: None");
            telemetry.update();}

            board.setServoPosition(gamepad1.right_trigger);

            telemetry.addData("Servo Position", board.getServoPosition());
            telemetry.update();
        }
    private void setServoToAngle(double degrees) {
        degrees += SERVO_DEGREES / 2;
        board.setServoPosition(degrees / SERVO_DEGREES);
    }
}