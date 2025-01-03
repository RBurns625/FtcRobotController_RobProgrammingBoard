package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Claw;
import org.firstinspires.ftc.teamcode.subassembly.GamepadSubassembly;
import org.firstinspires.ftc.teamcode.subassembly.NotificationSounds;
import org.firstinspires.ftc.teamcode.subassembly.Pincher;
import org.firstinspires.ftc.teamcode.subassembly.ViperSlideArm;

@SuppressWarnings("unused")
@TeleOp (name="Rob TeleOp Mode", group="Op Modes")

/*
 * CONFIGURATION SETUP:
 *
 * CONTROL HUB:
 * Motor:          Port 0 "FwdLt"    | Port 1 "FwdRt"   | Port 2 "AftLt"   | Port 3 "AftRt"
 * Servo:          Port 0 "rgbLight" |
 *
 * EXPANSION HUB
 * Motor:          Port 0 "armMotor" | Port 1 "slideMotor"
 * Motor Encoder:  Port 0            | Port 1
 * Servo:          Port 0 "pincher"  | Port 1 "wrist"       | Port 2 "elbow"
 */

public class RobTeleOp extends OpMode {
    Claw claw;
    ViperSlideArm viperSlideArm;
    GamepadEx driveOp;
    GamepadSubassembly subDriverOp;
    Pincher pincher;
    MecanumDrive drive;
    NotificationSounds sounds;

    double JOYSTICK_EXPO_RATE = 1.5;
    double DRIVE_MULTIPLIER   = 0.5;
    boolean a_mode_active     = false;
    boolean a_lock            = false;


    @Override
    public void init() {
        drive = new MecanumDrive(
                new Motor(hardwareMap, "FwdLt", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "FwdRt", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "AftLt", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "AftRt", Motor.GoBILDA.RPM_312)
        );
        sounds          = new NotificationSounds(hardwareMap);
        driveOp         = new GamepadEx(gamepad1);
        claw            = new Claw(hardwareMap);
        pincher         = new Pincher(hardwareMap);
        subDriverOp     = new GamepadSubassembly(hardwareMap);
        viperSlideArm   = new ViperSlideArm();

        viperSlideArm.init(hardwareMap);

        sounds.playMeep();
        telemetry.addLine("Robot is ready to rock!");
        telemetry.update();
    }

    @Override
    public void loop() {
        drive.driveRobotCentric(
                -exponentialRate(driveOp.getLeftX(), JOYSTICK_EXPO_RATE) * DRIVE_MULTIPLIER,
                -exponentialRate(driveOp.getLeftY(), JOYSTICK_EXPO_RATE) * DRIVE_MULTIPLIER,
                -exponentialRate(driveOp.getRightX(), JOYSTICK_EXPO_RATE) * DRIVE_MULTIPLIER,
                false
        );

        viperSlideArm.execute();

        if (gamepad2.a) {
            subDriverOp.buttonA();
        }
        else if (gamepad2.b) {
            subDriverOp.buttonB();
        }
        else if (gamepad2.x) {
            subDriverOp.buttonX();
        }
        else if (gamepad2.right_bumper) {
            subDriverOp.rtbumper();
        }
        else if (gamepad2.left_bumper) {
            subDriverOp.ltbumper();
        }
        else if (gamepad2.dpad_up) {
            subDriverOp.dpadUp();
        }
        else if (gamepad2.dpad_left) {
            subDriverOp.dpadLeft();
        }
        else if (gamepad2.dpad_down) {
            subDriverOp.dpadDown();
        }
        else if (gamepad2.dpad_right) {
            subDriverOp.dpadRight();
        }
        if (gamepad1.y && !a_lock && !a_mode_active) {
            pincher.open(); a_lock = true; a_mode_active = true;
        }
        else if (gamepad1.y && !a_lock && a_mode_active) {
            pincher.closed(); a_mode_active = false; a_lock = true;
        }
        else if (!gamepad1.y && a_lock) {
            a_lock = false;
        }
        viperSlideArm.moveSlide(gamepad2.right_trigger + (-gamepad2.left_trigger));

        viperSlideArm.moveArm(-gamepad2.right_stick_y * 1.0);

        claw.adjustPincher(-gamepad2.right_stick_x * 1.0);
        claw.adjustElbow(-gamepad2.left_stick_y * 1.0);
        claw.adjustWrist(-gamepad2.left_stick_x * 1.0);

        viperSlideArm.outputTelemetry(telemetry);
        claw.outputTelemetry(telemetry);
        telemetry.update();
    }

    private double exponentialRate(double joystickValue, double sensitivity) {
        joystickValue = Math.max(-1, Math.min(1, joystickValue));

        return Math.signum(joystickValue) * Math.pow(Math.abs(joystickValue), sensitivity);
    }
}
