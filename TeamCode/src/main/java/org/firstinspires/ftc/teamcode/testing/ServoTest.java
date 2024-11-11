package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.ServoConfigurationType;
import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
@TeleOp(name="Any Port Servo Test", group="Test")
public class ServoTest extends OpMode {
    private static final double SERVO_DEGREES = 270;
    private static final double CONTROL_RATE = 0.1;
    private boolean servoRepositionMode = false;
    private ArrayList<Servo> servos;
    private ArrayList<Double> servoPositions;

    @Override
    public void init() {
        // Obtain the servo controller from our hardware map. There should always be 1.
        List<ServoControllerEx> servoControllers = hardwareMap.getAll(ServoControllerEx.class);
        if (servoControllers.isEmpty()) {
            telemetry.addLine("Unable to obtain servo controller!");
            telemetry.update();
            return;
        }

        // Ignore robot configuration, assume there are 6 servos all connected to the ports, and
        // create 6 servo instances we can control.
        servos = new ArrayList<>();
        servoPositions = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            servos.add(new ServoImplEx(servoControllers.get(0), i, ServoConfigurationType.getStandardServoType()));
            servoPositions.add(0.0);
        }

        telemetry.addLine("WARNING: ALL servos will be centered.");
        telemetry.addLine();
        telemetry.addLine("Press play to begin");
        telemetry.update();
    }

    @Override
    public void loop() {
        if (servos == null) return;

        if (gamepad1.a) {
            // allow servo positions to be changed
            servoRepositionMode = true;
        }
        else if (gamepad1.b) {
            // set all servo positions back to 0
            servoRepositionMode = false;
            Collections.fill(servoPositions, 0.0);
        }

        if (servoRepositionMode) {
            servoPositions.set(0, clipServoDegrees(servoPositions.get(0) + gamepad1.left_stick_x * CONTROL_RATE));
            servoPositions.set(1, clipServoDegrees(servoPositions.get(1) - gamepad1.left_stick_y * CONTROL_RATE));
            servoPositions.set(2, clipServoDegrees(servoPositions.get(2) + gamepad1.right_stick_x * CONTROL_RATE));
            servoPositions.set(3, clipServoDegrees(servoPositions.get(3) - gamepad1.right_stick_y * CONTROL_RATE));
            servoPositions.set(4, clipServoDegrees(servoPositions.get(4) + (gamepad1.dpad_left ? -CONTROL_RATE : 0)));
            servoPositions.set(4, clipServoDegrees(servoPositions.get(4) + (gamepad1.dpad_right ? CONTROL_RATE : 0)));
            servoPositions.set(5, clipServoDegrees(servoPositions.get(5) + (gamepad1.dpad_down ? -CONTROL_RATE : 0)));
            servoPositions.set(5, clipServoDegrees(servoPositions.get(5) + (gamepad1.dpad_up ? CONTROL_RATE : 0)));
        }

        telemetry.addData("Servo 0" + (servoRepositionMode ? " (left stick x)" : ""),    "%.1f", servoPositions.get(0));
        telemetry.addData("Servo 1" + (servoRepositionMode ? " (left stick y)" : ""),    "%.1f", servoPositions.get(1));
        telemetry.addData("Servo 2" + (servoRepositionMode ? " (right stick x)" : ""),   "%.1f", servoPositions.get(2));
        telemetry.addData("Servo 3" + (servoRepositionMode ? " (right stick y)" : ""),   "%.1f", servoPositions.get(3));
        telemetry.addData("Servo 4" + (servoRepositionMode ? " (dpad left/right)" : ""), "%.1f", servoPositions.get(4));
        telemetry.addData("Servo 5" + (servoRepositionMode ? " (dpad up/down)" : ""),    "%.1f", servoPositions.get(5));

        // Set all servos to their positions
        for (int i = 0; i < servos.size(); i++) {
            setServoToAngle(servos.get(i), servoPositions.get(i));
        }

        telemetry.addLine();
        if (servoRepositionMode) {
            telemetry.addLine("Press B to center all servos");
        }
        else {
            telemetry.addLine("Press A to change servo angles");
        }

        telemetry.update();
    }

    private void setServoToAngle(Servo servo, double degrees) {
        servo.setPosition(Range.scale(degrees, -SERVO_DEGREES/2, SERVO_DEGREES/2, 0, 1));
    }

    private double clipServoDegrees(double degrees) {
        return Range.clip(degrees, -SERVO_DEGREES/2, SERVO_DEGREES/2);
    }
}
