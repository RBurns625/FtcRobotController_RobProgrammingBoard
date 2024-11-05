package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard;
import org.firstinspires.ftc.teamcode.subassembly.GoBildaRGBLight;

@SuppressWarnings("unused")
@TeleOp

public class RgbLightOpMode extends OpMode {
    GoBildaRGBLight light = new GoBildaRGBLight();
    ProgrammingBoard board = new ProgrammingBoard();

    @Override
    public void init() {
        light.init(hardwareMap);
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        double potValue = board.getPotRange();

        if (gamepad1.a)  light.setColorRed();
        if (gamepad1.b)  light.setColorYellow();
        if (gamepad1.x)  light.setColorGreen();
        if (gamepad1.y)  light.setColorBlue();
        if (gamepad1.dpad_up)    light.setColorOrange();
        if (gamepad1.dpad_left)  light.setColorSage();
        if (gamepad1.dpad_right) light.setColorAzure();
        if (gamepad1.dpad_down)  light.setColorIndigo();
        if (gamepad1.left_stick_button)  light.setColorBlack();
        else {
            board.setServoPosition(potValue);
        }

        telemetry.addData("Servo 1 Position", light.getServoPosition1());
        telemetry.addData("Servo 2 Position", light.getServoPosition2());
        telemetry.addLine("Press the following buttons");
        telemetry.addLine("A: Red");
        telemetry.addLine("B: Yellow");
        telemetry.addLine("X: Green");
        telemetry.addLine("Y: Blue");
        telemetry.addLine("");
        telemetry.addLine("D Pad:");
        telemetry.addLine("Up:    Orange");
        telemetry.addLine("Left:  Sage");
        telemetry.addLine("Right: Azure");
        telemetry.addLine("Down:  Indigo");
        telemetry.addLine("");
        telemetry.addLine("Left Stick Button: Off");
    }
}
