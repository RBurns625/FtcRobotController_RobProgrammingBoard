package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@SuppressWarnings("unused")
@TeleOp (name = "Blinkin Test", group= "Test")

public class BlinkinTest extends OpMode {
    private RevBlinkinLedDriver blinkin;

    private static class ColorAndDisplayName {
        public final RevBlinkinLedDriver.BlinkinPattern pattern;
        public final String displayName;
        public ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern pattern, String displayName) {
            this.pattern = pattern;
            this.displayName = displayName;
        }
    }

    private final ColorAndDisplayName[] patternList = new ColorAndDisplayName[] {
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.RED,                   "Solid Red"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.BLUE,                  "Solid Blue"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.GREEN,                 "Solid Green"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.WHITE,                 "Solid White"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET,           "Solid Purple"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.SKY_BLUE,              "Sky Blue"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.AQUA,                  "Aqua"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.HOT_PINK,              "Hot Pink"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_OCEAN_PALETTE, "Ocean Palette"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.STROBE_RED,            "Strobe Red"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.STROBE_BLUE,           "Strobe Blue"),
            new ColorAndDisplayName(RevBlinkinLedDriver.BlinkinPattern.BLACK,                 "Off")
    };

    private int currentIndex = 0;
    private boolean previousButtonA = false;

    @Override
    public void init() {

        blinkin = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");

        blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
        telemetry.addData("Status", "Here we go!");
        telemetry.addData("Current Pattern", patternList[currentIndex].displayName);
        telemetry.update();
    }

    @Override
    public void loop() {
        if(gamepad1.a && !previousButtonA) {
            currentIndex = (currentIndex +1 ) % patternList.length;
            blinkin.setPattern(patternList[currentIndex].pattern);
        }

        previousButtonA = gamepad1.a;

        telemetry.addData("Current Pattern", patternList[currentIndex].displayName);
        telemetry.update();
    }

}
