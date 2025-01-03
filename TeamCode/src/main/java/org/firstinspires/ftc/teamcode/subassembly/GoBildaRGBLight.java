package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@SuppressWarnings("unused")

public class GoBildaRGBLight {
    Servo servo;

    public GoBildaRGBLight(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "rgbLight");
        setServoToAngle(0);
    }

    public void setColorBlack()    { setServoToAngle(0.000); }
    public void setColorRed()      { setServoToAngle(0.280); }
    public void setColorYellow()   { setServoToAngle(0.388); }
    public void setColorGreen()    { setServoToAngle(0.500); }
    public void setColorBlue()     { setServoToAngle(0.611); }
    public void setColorOrange()   { setServoToAngle(0.333); }
    public void setColorSage()     { setServoToAngle(0.444); }
    public void setColorAzure()    { setServoToAngle(0.555); }
    public void setColorIndigo()   { setServoToAngle(0.666); }
    public void setColorPClosed()  { setServoToAngle(0.700); }

    public double getServoPosition1() { return servo.getPosition(); }

    public void setServoToAngle(double degrees) {
        servo.setPosition(degrees);
    }
}
