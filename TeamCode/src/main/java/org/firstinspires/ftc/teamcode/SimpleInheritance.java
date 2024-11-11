package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@SuppressWarnings("unused")
@TeleOp
public class SimpleInheritance extends OpMode {
    SuperClass super_obj = new SuperClass();
    ChildClass child_obj = new ChildClass();

    @Override
    public void init() {
        telemetry.addData("Parent A", super_obj.a());
        telemetry.addData("Parent B", super_obj.b());
        telemetry.addData("Child  A", child_obj.a());
        telemetry.addData("Child  B", child_obj.b());
        telemetry.addData("Child  C", child_obj.c());
    }

    @Override
    public void loop() {

    }
}
