package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Claw;
import org.firstinspires.ftc.teamcode.subassembly.ColorDistanceSensor;

@SuppressWarnings("unused")
@TeleOp

public class PincherCloseOnRedTest extends OpMode {
    private ColorDistanceSensor colorSensor;
    private Claw claw;

    @Override
    public void init() {
        colorSensor = new ColorDistanceSensor(hardwareMap);
        claw  = new Claw(hardwareMap);
    }

    @Override
    public void loop() { colorSensor.closeOnRed(); }
}
