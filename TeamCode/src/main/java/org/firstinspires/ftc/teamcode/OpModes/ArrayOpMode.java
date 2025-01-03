package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@SuppressWarnings("unused")

@TeleOp
public class ArrayOpMode extends OpMode {
    String[] words = {"Zeroth", "First", "Second", "Third", "Fourth", "Fifth"};
    int wordIndex;
    double DELAY_SECS = 0.5;

    double nextTime;

    @Override
    public void init() {
        wordIndex = 0;
    }

    @Override
    public void loop() {
        if (nextTime < getRuntime()) {
            wordIndex++;
            if (wordIndex >= words.length) {
                wordIndex = words.length - 1;
            }
            nextTime = getRuntime() + DELAY_SECS;
        }
        telemetry.addLine(words[wordIndex]);
    }
}
