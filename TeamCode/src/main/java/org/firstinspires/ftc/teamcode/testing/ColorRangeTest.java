package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.GoBildaRGBLight;

@SuppressWarnings("unused")
@TeleOp
public class ColorRangeTest extends OpMode {
    GoBildaRGBLight light;

    @Override
    public void init() {
        light = new GoBildaRGBLight(hardwareMap);
    }

    @Override
    public void loop() {
        light.setColorBlue();
    }
}
