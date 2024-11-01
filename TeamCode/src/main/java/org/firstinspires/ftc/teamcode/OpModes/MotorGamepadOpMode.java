package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@SuppressWarnings("unused")
@TeleOp

public class MotorGamepadOpMode extends OpMode {
    ProgrammingBoard board = new ProgrammingBoard();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    double squareInputWithSign(double input) {
        double output = input * input;
        if (input <0) {
            output = output * -1;
        }
        return output;
    }

    @Override
    public void loop() {
        double motorSpeed = squareInputWithSign(gamepad1.left_stick_y);
        board.setMotorSpeed(motorSpeed);
        telemetry.addData("Motor Speed", motorSpeed);

        if(gamepad1.a) {
            board.setMotorZeroBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            telemetry.addData("Zero", "Brake");
        }
        else if (gamepad1.b) {
            board.setMotorZeroBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            telemetry.addData("Zero", "Float");
        }
    }
}
