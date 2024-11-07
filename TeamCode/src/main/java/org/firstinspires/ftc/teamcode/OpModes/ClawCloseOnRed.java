package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@SuppressWarnings("unused")
@TeleOp
public class ClawCloseOnRed extends OpMode {
    ProgrammingBoard board = new ProgrammingBoard();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        double distanceMM = board.getDistance(DistanceUnit.MM);
        int detectColorRed = board.getAmountRed();
        double servoAngle = board.getServoPosition();

        if (distanceMM < 10.0 && detectColorRed > 1000) {
            board.setServoPosition(90);
        }
        else {
            board.setServoPosition(0);
        }
        telemetry.addData("Distance (mm)", board.getDistance(DistanceUnit.MM));
        if (servoAngle == 0) {
            telemetry.addLine("Claws Open");
        }
        else {
            telemetry.addLine("Claws Closed");
        }
    }
    public double servoAngle() {
        return board.getServoPosition();
    }
}