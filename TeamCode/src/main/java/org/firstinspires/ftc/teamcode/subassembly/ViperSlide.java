package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")
public class ViperSlide {
    DcMotor slideMotor;
    final double SLIDE_TICKS  = (111132.0 / 289.0) / 120.0;
    final double SLIDE_MAX_EXTENSION = 440 * SLIDE_TICKS;

    final double SLIDE_RETRACTED     = 0   * SLIDE_TICKS;
    final double SLIDE_BASKET_HIGH   = 390 * SLIDE_TICKS;
    final double SLIDE_BASKET_LOW    = 200 * SLIDE_TICKS;
    final double SLIDE_SUBMERSIBLE   = 25  * SLIDE_TICKS;
    final double SLIDE_RUNG_HIGH     = 50  * SLIDE_TICKS;
    final double SLIDE_RUNG_LOW      = 25  * SLIDE_TICKS;

    private double slidePosition = SLIDE_RETRACTED;

    public void init (HardwareMap hwMap) {
        slideMotor = hwMap.get(DcMotor.class, "slideMotor");
        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setTargetPosition((int) slidePosition);
        ((DcMotorEx) slideMotor).setVelocity(2100);
    }

    public void parked()        { slidePosition = SLIDE_RETRACTED;   }
    public void traveling()     { slidePosition = SLIDE_RETRACTED;   }
    public void sampleCollect() { slidePosition = SLIDE_RETRACTED;   }
    public void clearBarrier()  { slidePosition = SLIDE_SUBMERSIBLE; }
    public void highBasket()    { slidePosition = SLIDE_BASKET_HIGH; }
    public void lowBasket()     { slidePosition = SLIDE_BASKET_LOW;  }
    public void highRung()      { slidePosition = SLIDE_RUNG_HIGH;   }
    public void lowRung()       { slidePosition = SLIDE_RUNG_LOW;    }
    public void hangPrepare()   { slidePosition = SLIDE_RETRACTED;   }
    public void hangRobot()     { slidePosition = SLIDE_RETRACTED;   }

    private void limitSlidePosition() {
        if (slidePosition > SLIDE_MAX_EXTENSION) {
            slidePosition = SLIDE_MAX_EXTENSION;
        }
        if (slidePosition < 0) {
            slidePosition = 0;
        }
    }

    public void moveSlide(double millimeters) {
        slidePosition += millimeters * SLIDE_TICKS;
        limitSlidePosition();
    }

    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Slide Extension", slidePosition / SLIDE_TICKS);
    }

}
