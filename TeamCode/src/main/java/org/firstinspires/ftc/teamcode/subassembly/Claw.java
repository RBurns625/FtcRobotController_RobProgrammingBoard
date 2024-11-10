package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@SuppressWarnings("unused")
public class Claw {
    Elbow   e;
    Pincher p;
    Wrist   w;

    public Claw(HardwareMap hwMap) {
        e  = new Elbow(hwMap);
        p  = new Pincher(hwMap);
        w  = new Wrist(hwMap);
    }

    public void parked()         { p.zero();   w.zero();      e.zero();     }
    public void horizPickup()    { p.open();   w.straight();  e.down();     }
    public void vertPickup()     { p.open();   w.straight();  e.down();     }
    public void traveling()      { p.closed(); w.straight();  e.angled45(); }
    public void scoreBasket()    { p.closed(); w.right();     e.angled45(); }
    public void scoreSpecimen()  { p.closed(); w.left();      e.angled45(); }
    public void prepareHang()    { p.zero();   w.zero();      e.zero();     }

    public void togglePincher()  { p.toggle(); }
    public void dropSample()     { p.open();   }
    public void pickupSample()   { p.closed(); }

    public void outputTelemetry(Telemetry telemetry) {
        e.outputTelemetry(telemetry);
        p.outputTelemetry(telemetry);
        w.outputTelemetry(telemetry);
    }

}
