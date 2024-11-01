package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@SuppressWarnings("unused")
@TeleOp

public class ServoGamepadOpMode extends OpMode {
    ProgrammingBoard board = new ProgrammingBoard();

    @Override
    public void init() {
        board.init(hardwareMap);
            }

    @Override
    public void loop() {
        if(gamepad1.a) {
            board.setServoPosition(1.0);
        }
        else if(gamepad1.b) {
            board.setServoPosition(0.0);
        }
        else {
            board.setServoPosition(0.5);
        }
    }
}
