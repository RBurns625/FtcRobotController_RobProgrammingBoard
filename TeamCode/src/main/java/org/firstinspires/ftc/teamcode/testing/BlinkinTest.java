package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.BlinkinLED;

@SuppressWarnings("unused")
@TeleOp (name = "Blinkin Test", group= "Test")

public class BlinkinTest extends OpMode {

    private RevBlinkinLedDriver blinkin;

    private RevBlinkinLedDriver.BlinkinPattern[]
            patterns = {
            BlinkinLED.SOLID_RED,
            BlinkinLED.SOLID_BLUE,
            BlinkinLED.SOLID_GREEN,
            BlinkinLED.SOLID_WHITE,
            BlinkinLED.SOLID_PURPLE,
            BlinkinLED.SKY_BLUE,
            BlinkinLED.AQUA,
            BlinkinLED.HOT_PINK,
            BlinkinLED.OCEAN_PALETTE,
            BlinkinLED.STROBE_RED,
            BlinkinLED.STROBE_BLUE,
            BlinkinLED.OFF
    };

    private String[]
            patternNames = {
            "Solid Red",
            "Solid Blue",
            "Solid Green",
            "Solid White",
            "Solid Purple",
            "Sky Blue",
            "Aqua",
            "Hot Pink",
            "Ocean Palette",
            "Strobe Red",
            "Strobe Blue",
            "Off"
    };

    private int currentIndex = 0;
    private boolean previousButtonA = false;

    @Override
    public void init() {
        blinkin = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");

        blinkin.setPattern(BlinkinLED.OFF);
        telemetry.addData("Status", "Here we go!");
        telemetry.addData("Current Pattern", patternNames[currentIndex]);
        telemetry.update();
    }

    @Override
    public void loop() {
        if(gamepad1.a && !previousButtonA) {
            currentIndex = (currentIndex +1 ) % patterns.length;
            blinkin.setPattern(patterns[currentIndex]);
        }

        previousButtonA = gamepad1.a;

        telemetry.addData("Current Pattern", patternNames[currentIndex]);
        telemetry.update();
    }

}
