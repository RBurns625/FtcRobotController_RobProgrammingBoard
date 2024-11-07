package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.ColorDistanceSensor;

@SuppressWarnings("unused")
@TeleOp

public class PincherCloseOnRedTest extends OpMode {
    private ColorDistanceSensor color;

    @Override
    public void init() {
        color = new ColorDistanceSensor(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            color.closeOnRed();
        }
    }
}
