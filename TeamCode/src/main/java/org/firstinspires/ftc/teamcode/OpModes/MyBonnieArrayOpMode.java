package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@SuppressWarnings("unused")

@TeleOp
public class MyBonnieArrayOpMode extends OpMode {
    String[] lines = {
            "My Bonnie lies over the ocean",
            "My Bonnie lies over the sea",
            "My Well my Bonnie lies over the ocean",
            "Yeah bring back my Bonnie to me",
            " ",
            "Yeah bring back, ah bring back",
            "Oh bring back my Bonnie to me to me",
            "Oh bring back, oh bring back",
            "Oh bring back my Bonnie to me",
            " ",
            "Well my Bonnie lies over the ocean",
            "My Bonnie lies over the sea",
            "Yeah my Bonnie lies over the ocean",
            "Oh I said bring back my Bonnie to me",
            " ",
            "Yeah bring back, ah bring back",
            "Oh bring back my Bonnie to me to me",
            "Oh bring back, ah bring back",
            "Oh bring back my Bonnie to me"
    };
    int lineIndex;
    double DELAY_SECS = 0.5;

    double nextTime;

    @Override
    public void init() {
        lineIndex = 0;
    }

    @Override
    public void loop() {
        if (nextTime < getRuntime()) {
            lineIndex++;
            if (lineIndex >= lines.length) {
                lineIndex = lines.length - 1;
            }
            nextTime = getRuntime() + DELAY_SECS;
        }
        telemetry.addLine(lines[lineIndex]);
    }
}
