package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;

@SuppressWarnings("unused")

@TeleOp
public class MyBonnieArrayListOpMode extends OpMode {
    ArrayList<String> lines = new ArrayList<>();

    int lineIndex;
    double DELAYS_SECS = 0.5;

    double nextTime;

    @Override
    public void init() {
        lineIndex = 0;
        lines.clear();
        lines.add("My Bonnie lies over the ocean");
        lines.add("My Bonnie lies over the sea");
        lines.add("My Well my Bonnie lies over the ocean");
        lines.add("Yeah bring back my Bonnie to me");
        lines.add(" ");
        lines.add("Yeah bring back, ah bring back");
        lines.add("Oh bring back my Bonnie to me to me");
        lines.add("Oh bring back, oh bring back");
        lines.add("Oh bring back my Bonnie to me");
        lines.add(" ");
        lines.add("Well my Bonnie lies over the ocean");
        lines.add("My Bonnie lies over the sea");
        lines.add("Yeah my Bonnie lies over the ocean");
        lines.add("Oh I said bring back my Bonnie to me");
        lines.add(" ");
        lines.add("Yeah bring back, ah bring back");
        lines.add("Oh bring back my Bonnie to me to me");
        lines.add("Oh bring back, ah bring back");
        lines.add("Oh bring back my Bonnie to me");
    }

    @Override
    public void loop() {
        if(nextTime < getRuntime()) {
            lineIndex++;
            if (lineIndex >= lines.size()) {
                lineIndex = 0;
            }
            nextTime = getRuntime() + DELAYS_SECS;
        }
        telemetry.addLine(lines.get(lineIndex));
    }
}
