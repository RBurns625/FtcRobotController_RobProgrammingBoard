package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subassembly.Claw;
import org.firstinspires.ftc.teamcode.subassembly.GamepadSubassembly;
import org.firstinspires.ftc.teamcode.subassembly.Pincher;
import org.firstinspires.ftc.teamcode.subassembly.ViperSlideArm;

@SuppressWarnings("unused")
@TeleOp (name="Rob TeleOp Mode", group="Op Modes")

/*
 * CONFIGURATION SETUP:
 *
 * CONTROL HUB:
 * Motor:          Port 0 "FwdLt"    | Port 1 "FwdRt"   | Port 2 "AftLt"   | Port 3 "AftRt"
 * Motor Encoder:  Port 0            | Port 1           | Port 2           | Port 3
 * Servo:          Port 0 "rgbLight" |
 *
 * EXPANSION HUB
 * Motor:          Port 0 "armMotor" | Port 1 "slideMotor"  |
 * Motor Encoder:  Port 0            | Port 1               |
 * Servo:          Port 0 "pincher"  | Port 1 "wrist"       | Port 2 "elbow"
 */

public class RobTeleOp extends OpMode {
    Claw claw;
    ViperSlideArm viperSlideArm;
    GamepadSubassembly gamepadControl;
    Pincher pincher;
    GamepadEx driveOp;
    MecanumDrive drive;
    double JOYSTICK_EXPO_RATE = 1.5;
    double DRIVE_MULTIPLIER = 0.5;


    @Override
    public void init() {
        drive = new MecanumDrive(
                new Motor(hardwareMap, "FwdLt", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "FwdRt", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "AftLt", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "AftRt", Motor.GoBILDA.RPM_312)
        );
        driveOp = new GamepadEx(gamepad1);

        claw = new Claw(hardwareMap);
        pincher = new Pincher(hardwareMap);

        viperSlideArm = new ViperSlideArm();
        viperSlideArm.init(hardwareMap);

        gamepadControl = new GamepadSubassembly();
        gamepadControl.init(gamepad1, gamepad2, claw, pincher, viperSlideArm);

        telemetry.addLine("Robot is ready to rock!");
        telemetry.update();
    }

    @Override
    public void loop() {
        drive.driveFieldCentric(
                -exponentialRate(driveOp.getLeftX(), JOYSTICK_EXPO_RATE) * DRIVE_MULTIPLIER,
                -exponentialRate(driveOp.getLeftY(), JOYSTICK_EXPO_RATE) * DRIVE_MULTIPLIER,
                -exponentialRate(driveOp.getRightX(), JOYSTICK_EXPO_RATE) * DRIVE_MULTIPLIER,
                -exponentialRate(driveOp.getRightY(), JOYSTICK_EXPO_RATE) * DRIVE_MULTIPLIER
                );

        gamepadControl.execute();
        viperSlideArm.execute();
        claw.execute();

        viperSlideArm.outputTelemetry(telemetry);
        claw.outputTelemetry(telemetry);
        telemetry.update();
    }

    private double exponentialRate(double joystickValue, double sensitivity) {
        joystickValue = Math.max(-1, Math.min(1, joystickValue));

        return Math.signum(joystickValue) * Math.pow(Math.abs(joystickValue), sensitivity);
    }
}
