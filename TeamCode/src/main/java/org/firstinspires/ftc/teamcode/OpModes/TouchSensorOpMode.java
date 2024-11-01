package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;

@SuppressWarnings("unused")
@TeleOp
public class TouchSensorOpMode extends OpMode {
    ProgrammingBoard board = new ProgrammingBoard();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        String touchSensorString = "Not Pressed";
        if (board.isTouchSensorPressed()) {
            touchSensorString = "Pressed";
        }
        telemetry.addData("Touch Sensor", touchSensorString);
    }
}
