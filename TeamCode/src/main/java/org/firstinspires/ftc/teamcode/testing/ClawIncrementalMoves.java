package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@SuppressWarnings("unused")

@TeleOp(name = "Incremental Servo Test", group = "Test")
public class ClawIncrementalMoves extends OpMode {

    // Define servo objects
    private Servo wrist, elbow, pincher;
    // servo1 = wrist  servo 2 = elbow  servo 3 = pincher

    // Servo properties
    double wristMin   = 0.00, wristMax   = 1.00, wristPosition;
    double elbowMin   = 0.50, elbowMax   = 1.00, elbowPosition;
    double pincherMin = 0.40, pincherMax = 0.88, pincherPosition;

    // Step size for incremental movement
    final double stepSize = 0.03;

    // Direction flags for each servo (true = moving towards max, false = moving towards min)
    private boolean wristIncreasing   = true;
    private boolean elbowIncreasing   = true;
    private boolean pincherIncreasing = true;

    // Timer for updating servo positions
    private final ElapsedTime timer = new ElapsedTime();
    final double updateInterval = 0.05; // Time in seconds between each increment

    @Override
    public void init() {
        // Initialize servos
        wrist   = hardwareMap.get(Servo.class, "wrist");
        elbow   = hardwareMap.get(Servo.class, "elbow");
        pincher = hardwareMap.get(Servo.class, "pincher");

        // Start each servo at its minimum position
        wristPosition   = 0.5;
        elbowPosition   = 0.5;
        pincherPosition = 0.5;

        // Set each servo to its initial position
        wrist.setPosition(wristPosition);
        elbow.setPosition(elbowPosition);
        pincher.setPosition(pincherPosition);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        // Check if it's time to update servo positions
        if (timer.seconds() >= updateInterval) {
            // Update each servo's position incrementally
            wristPosition = updateServoPosition(wristPosition, wristMin, wristMax, stepSize, wristIncreasing);
            wrist.setPosition(wristPosition);
            wristIncreasing = updateDirection(wristPosition, wristMin, wristMax, wristIncreasing);

            elbowPosition = updateServoPosition(elbowPosition, elbowMin, elbowMax, stepSize, elbowIncreasing);
            elbow.setPosition(elbowPosition);
            elbowIncreasing = updateDirection(elbowPosition, elbowMin, elbowMax, elbowIncreasing);

            pincherPosition = updateServoPosition(pincherPosition, pincherMin, pincherMax, stepSize, pincherIncreasing);
            pincher.setPosition(pincherPosition);
            pincherIncreasing = updateDirection(pincherPosition, pincherMin, pincherMax, pincherIncreasing);

            // Reset the timer for the next interval
            timer.reset();
        }

        // Display current positions on telemetry
        telemetry.addData("Wrist Position",   wristPosition);
        telemetry.addData("Elbow Position",   elbowPosition);
        telemetry.addData("Pincher Position", pincherPosition);
        telemetry.update();
    }

    // Method to update a servo's position incrementally based on its direction
    private double updateServoPosition(double currentPosition, double minPosition, double maxPosition, double step, boolean increasing) {
        if (increasing) {
            return Math.min(currentPosition + step, maxPosition);
        } else {
            return Math.max(currentPosition - step, minPosition);
        }
    }

    // Method to update the direction when min or max position is reached
    private boolean updateDirection(double currentPosition, double minPosition, double maxPosition, boolean increasing) {
        if (currentPosition >= maxPosition) {
            return false;  // Switch to decreasing
        } else if (currentPosition <= minPosition) {
            return true;   // Switch to increasing
        }
        return increasing;  // No change in direction
    }
}
