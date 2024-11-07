package org.firstinspires.ftc.teamcode.subassembly;

import com.qualcomm.robotcore.hardware.HardwareMap;

@SuppressWarnings("unused")
public class Claw {
    Elbow   elbow;
    Pincher pincher;
    Wrist   wrist;

    public Claw(HardwareMap hwMap) {
        elbow   = new Elbow(hwMap);
        pincher = new Pincher(hwMap);
        wrist   = new Wrist(hwMap);
    }

    public void parked()         { pincher.zero();   wrist.zero();      elbow.zero();     }
    public void horizPickup()    { pincher.open();   wrist.straight();  elbow.down();     }
    public void vertPickup()     { pincher.open();   wrist.straight();  elbow.down();     }
    public void traveling()      { pincher.closed(); wrist.straight();  elbow.angled45(); }
    public void scoreBasket()    { pincher.closed(); wrist.right();     elbow.angled45(); }
    public void scoreSpecimen()  { pincher.closed(); wrist.left();      elbow.angled45(); }
    public void prepareHang()    { pincher.zero();   wrist.zero();      elbow.zero();     }

    public void togglePincher()  { pincher.toggle(); }
    public void dropSample()     { pincher.open();   }
    public void pickupSample()   { pincher.closed(); }

}
