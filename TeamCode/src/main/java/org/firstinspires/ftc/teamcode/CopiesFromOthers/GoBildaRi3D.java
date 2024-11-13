package org.firstinspires.ftc.teamcode.CopiesFromOthers;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.subassembly.Claw;

@SuppressWarnings("unused")

@TeleOp(name="goBILDA Robot in 3 Days 24-25", group="Robot")
public class GoBildaRi3D extends LinearOpMode {

    /* Declare OpMode members. */

    public DcMotor  armMotor       = null; //the arm motor
    public DcMotor viperSlideMotor = null; //
    Claw claw;

    final double EXPO_RATE = 1.4;

    final double ARM_TICKS_PER_DEGREE =
            28 // number of encoder ticks per rotation of the bare motor
                    * 250047.0 / 4913.0 // This is the exact gear ratio of the 50.9:1 Yellow Jacket gearbox
                    * 100.0 / 20.0 // This is the external gear reduction, a 20T pinion gear that drives a 100T hub-mount gear
                    * 1/360.0; // we want ticks per degree, not per rotation

    final double ARM_COLLAPSED_INTO_ROBOT  = 0;
    final double ARM_COLLECT               = 8 * ARM_TICKS_PER_DEGREE;
    final double ARM_CLEAR_BARRIER         = 15 * ARM_TICKS_PER_DEGREE;
    final double ARM_SCORE_SPECIMEN_LOW_CHAMBER        = 32 * ARM_TICKS_PER_DEGREE;
    final double ARM_SCORE_SPECIMEN_HIGH_CHAMBER       = 70 * ARM_TICKS_PER_DEGREE;
    final double ARM_SCORE_SAMPLE_IN_LOW   = 72 * ARM_TICKS_PER_DEGREE;
    final double ARM_SCORE_SAMPLE_IN_HIGH  = 90 * ARM_TICKS_PER_DEGREE; //TODO
    final double ARM_ATTACH_HANGING_HOOK   = 110 * ARM_TICKS_PER_DEGREE;
    final double ARM_WINCH_ROBOT           = 10  * ARM_TICKS_PER_DEGREE;
    final double ARM_MINIMUM               = 0;
    final double ARM_MAXIMUM               = 110 * ARM_TICKS_PER_DEGREE;

    final double FUDGE_FACTOR = 15 * ARM_TICKS_PER_DEGREE;

    double armPosition = ARM_COLLAPSED_INTO_ROBOT;
    double armPositionFudgeFactor;

    final double VIPERSLIDE_TICKS_PER_MM = (111132.0 / 289.0) / 120.0;
    final double VIPERSLIDE_COLLAPSED = 0 * VIPERSLIDE_TICKS_PER_MM;
    final double VIPERSLIDE_SCORING_IN_HIGH_BASKET = 460 * VIPERSLIDE_TICKS_PER_MM;
    final double VIPERSLIDE_SCORING_IN_LOW_BASKET = 120 * VIPERSLIDE_TICKS_PER_MM; //TODO
    final double VIPERSLIDE_HIGH_CHAMBER = 0 * VIPERSLIDE_TICKS_PER_MM; //TODO
    final double VIPERSLIDE_LOW_CHAMBER = 0 * VIPERSLIDE_TICKS_PER_MM; //TODO


    double viperSlidePosition = VIPERSLIDE_COLLAPSED;

    double cycletime = 0;
    double looptime = 0;
    double oldtime = 0;

    double armViperSlideComp = 0;

    @Override
    public void runOpMode() {
        /* Define and Initialize Motors */
        com.arcrobotics.ftclib.drivebase.MecanumDrive drive = new MecanumDrive(
                new Motor(hardwareMap, "frontLeftMotor", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "frontRightMotor", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "backLeftMotor", Motor.GoBILDA.RPM_312),
                new Motor(hardwareMap, "backRightMotor", Motor.GoBILDA.RPM_312)
        );
        GamepadEx driverOp    = new GamepadEx(gamepad1);
        GamepadEx subDriverOp = new GamepadEx(gamepad2);

        viperSlideMotor = hardwareMap.dcMotor.get("viperSlideMotor");
        armMotor        = hardwareMap.get(DcMotor.class, "armMotor"); //the arm motor

        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ((DcMotorEx) armMotor).setCurrentAlert(5,CurrentUnit.AMPS);

        armMotor.setTargetPosition(0);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        viperSlideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        viperSlideMotor.setTargetPosition(0);
        viperSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        claw = new Claw(hardwareMap);
        claw.parked();

        /* Send telemetry message to signify robot waiting */
        telemetry.addLine("Robot Ready.");
        telemetry.update();

        /* Wait for the game driver to press play */
        waitForStart();

        /* Run until the driver presses stop */
        while (opModeIsActive())

        {
            drive.driveRobotCentric(
                    -exponentialRate(driverOp.getLeftX(), EXPO_RATE),
                    -exponentialRate(driverOp.getLeftY(), EXPO_RATE),
                    -exponentialRate(driverOp.getRightX(), EXPO_RATE),
                    false
            );
            subDriverOp.readButtons();

            /* Here we create a "fudge factor" for the arm position.
            This allows you to adjust (or "fudge") the arm position slightly with the gamepad triggers.
            We want the left trigger to move the arm up, and right trigger to move the arm down.
            So we add the right trigger's variable to the inverse of the left trigger. If you pull
            both triggers an equal amount, they cancel and leave the arm at zero. But if one is larger
            than the other, it "wins out". This variable is then multiplied by our FUDGE_FACTOR.
            The FUDGE_FACTOR is the number of degrees that we can adjust the arm by with this function. */

            armPositionFudgeFactor = FUDGE_FACTOR * (gamepad2.right_trigger + (-gamepad2.left_trigger));

            /* Here we implement a set of if else statements to set our arm to different scoring positions.
            We check to see if a specific button is pressed, and then move the arm (and sometimes
            intake and wrist) to match. For example, if we click the right bumper we want the robot
            to start collecting. So it moves the armPosition to the ARM_COLLECT position,
            it folds out the wrist to make sure it is in the correct orientation to intake, and it
            turns the intake on to the COLLECT mode.*/

            if(gamepad2.a) {
                /* This is the correct height to score the sample in the LOW BASKET */
                armPosition = ARM_SCORE_SAMPLE_IN_LOW;
                viperSlidePosition = VIPERSLIDE_SCORING_IN_LOW_BASKET;
                claw.prepareBasket();

            }
            else if (gamepad2.b) {
                    /* This is about 20° up from the collecting position to clear the barrier
                    Note here that we don't set the wrist position or the intake power when we
                    select this "mode", this means that the intake and wrist will continue what
                    they were doing before we clicked left bumper. */
                armPosition = ARM_CLEAR_BARRIER;
            }

            else if (gamepad2.x) {
                /* This is the vertical claw pick-up/collecting arm position */
                armPosition = ARM_COLLECT;
                viperSlidePosition = VIPERSLIDE_COLLAPSED;
                claw.vertPickup();
            }
            else if (gamepad2.y){
                /* This is the correct height to score the sample in the HIGH BASKET*/
                armPosition = ARM_SCORE_SAMPLE_IN_HIGH;
                viperSlidePosition = VIPERSLIDE_SCORING_IN_HIGH_BASKET;
                claw.prepareBasket();
            }

            else if (gamepad2.dpad_left){
                armPosition = ARM_SCORE_SPECIMEN_LOW_CHAMBER;
                viperSlidePosition = VIPERSLIDE_LOW_CHAMBER;
                claw.prepareHang();
            }

            else if (gamepad1.dpad_left) {
                    /* This turns off the intake, folds in the wrist, and moves the arm
                    back to folded inside the robot. This is also the starting configuration */
                armPosition = ARM_COLLAPSED_INTO_ROBOT;
                viperSlidePosition = VIPERSLIDE_COLLAPSED;
                claw.parked();
            }

            else if (gamepad2.dpad_right){
                /* This is the correct height to score SPECIMEN on the HIGH CHAMBER */
                armPosition = ARM_SCORE_SPECIMEN_HIGH_CHAMBER;
                viperSlidePosition = VIPERSLIDE_HIGH_CHAMBER;
                claw.prepareSpecimen();
            }

            else if (gamepad1.dpad_up){
                /* This sets the arm to vertical to hook onto the LOW RUNG for hanging */
                armPosition = ARM_ATTACH_HANGING_HOOK;
                viperSlidePosition = VIPERSLIDE_COLLAPSED;
                claw.parked();
            }

            else if (gamepad1.dpad_down){
                /* this moves the arm down to viper slide the robot up once it has been hooked */
                armPosition = ARM_WINCH_ROBOT;
            }
            else if (subDriverOp.wasJustPressed(GamepadKeys.Button.RIGHT_STICK_BUTTON)) {
                claw.togglePincher();
                telemetry.addLine("Right Stick Button Pressed");
            }

            claw.adjustWrist(-gamepad2.left_stick_x);
            claw.adjustElbow(gamepad2.left_stick_y);

            armPosition += -gamepad2.right_stick_y * ARM_TICKS_PER_DEGREE * 0.1;
            armPosition = Range.clip(armPosition, ARM_MINIMUM, ARM_MAXIMUM);

            if (armPosition < 45 * ARM_TICKS_PER_DEGREE){
                armViperSlideComp = (0.25568 * viperSlidePosition);
            } else{
                armViperSlideComp = 0;
            }

            armMotor.setTargetPosition((int) (armPosition + armPositionFudgeFactor + armViperSlideComp));
            ((DcMotorEx) armMotor).setVelocity(2100);
            armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if (gamepad2.right_bumper){
                viperSlidePosition += 2800 * cycletime;
            } else if (gamepad2.left_bumper){
                viperSlidePosition -= 2800 * cycletime;
            }

            if (viperSlidePosition > VIPERSLIDE_SCORING_IN_HIGH_BASKET){
                viperSlidePosition = VIPERSLIDE_SCORING_IN_HIGH_BASKET;
            }

            if (viperSlidePosition < 0){
                viperSlidePosition = 0;
            }

            viperSlideMotor.setTargetPosition((int) (viperSlidePosition));
            ((DcMotorEx) viperSlideMotor).setVelocity(2100);
            viperSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if (((DcMotorEx) armMotor).isOverCurrent()){
                telemetry.addLine("MOTOR EXCEEDED CURRENT LIMIT!");
            }

            looptime = getRuntime();
            cycletime = looptime-oldtime;
            oldtime = looptime;

            /* send telemetry to the driver of the arm's current position and target position */
            telemetry.addData("arm Target Position: ", armMotor.getTargetPosition());
            telemetry.addData("arm Position (degrees): ", armPosition/ARM_TICKS_PER_DEGREE);
            telemetry.addData("arm Encoder: ", armMotor.getCurrentPosition());
            telemetry.addData("slide variable", viperSlidePosition);
            telemetry.addData("slide Position (mm) : ", viperSlidePosition/VIPERSLIDE_TICKS_PER_MM);
            telemetry.addData("slide Target Position", viperSlideMotor.getTargetPosition());
            telemetry.addData("slide current position", viperSlideMotor.getCurrentPosition());
            telemetry.addData("slideMotor Current:",((DcMotorEx) viperSlideMotor).getCurrent(CurrentUnit.AMPS));
            claw.outputTelemetry(telemetry);
            telemetry.update();

        }
    }
    private double exponentialRate(double oldValue, double exponent) {
        return Math.signum(oldValue) * Math.pow(Math.abs(oldValue), exponent);
    }
}