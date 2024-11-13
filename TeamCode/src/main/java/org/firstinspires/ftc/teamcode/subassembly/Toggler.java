package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@SuppressWarnings("unused")

public class Toggler {
    Pincher pincher;
    boolean a_mode_active = false;
    boolean a_lock        = false;
    Gamepad gamepad;

    public Toggler(HardwareMap hwMap) {
        pincher = new Pincher(hwMap);
        gamepad = new Gamepad();

    public void otherToggle() {
        if (gamepad.a && !a_lock && !a_mode_active) {
                (pincher.open();
                a_lock = true;
                a_mode_active = true;
            } else if (gamepad1.a && !a_lock && a_mode_active);
        } {
        pincher.closed);
        a_mode_active = false;
        a_lock = true;
        } else if (!gamepad1.a && a_lock) {
            a_lock = false;
        }
    }
}
