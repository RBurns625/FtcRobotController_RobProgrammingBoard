package org.firstinspires.ftc.teamcode.subassembly;

import android.graphics.Color;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@SuppressWarnings("unused")

public class SampleDetector {
    public enum SampleType {
        RED, BLUE, YELLOW, NONE
    }

    private static final int DETECTION_DISTANCE_MM = 12;
    private static final int RED_MIN_HUE = 20;
    private static final int RED_MAX_HUE = 40;
    private static final int BLUE_MIN_HUE = 220;
    private static final int BLUE_MAX_HUE = 240;
    private static final int YELLOW_MIN_HUE = 70;
    private static final int YELLOW_MAX_HUE = 90;

    private final RevColorSensorV3 colorSensor;
    private final float[] hsvValues = new float[3];
    private double detectedDistance;
    private double detectedHue;
    private SampleType detectedSample;

    public SampleDetector(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(RevColorSensorV3.class, "sensor_color_distance");
    }

    /**
     * Returns the type of the currently detected sample if the sample is
     * red, blue, or yellow, and it is within DETECTION_DISTANCE_MM from the color sensor.
     *
     * @return the color of the sample detected (RED, BLUE, YELLOW), or NONE
     * if no sample is detected.
     */
    public SampleType getDetectedSample() {
        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        Color.colorToHSV(colors.toColor(), hsvValues);

        detectedDistance = colorSensor.getDistance(DistanceUnit.MM);
        detectedHue = hsvValues[0];
        detectedSample = SampleType.NONE;
        if (detectedDistance < DETECTION_DISTANCE_MM) {
            if (isRed(detectedHue)) {
                detectedSample = SampleType.RED;
            }
            else if (isBlue(detectedHue)) {
                detectedSample = SampleType.BLUE;
            }
            else if (isYellow(detectedHue)) {
                detectedSample = SampleType.YELLOW;
            }
        }
        return detectedSample;
    }

    private boolean isRed(double hue) {
        return (hue >= RED_MIN_HUE) && (hue <= RED_MAX_HUE);
    }

    private boolean isBlue(double hue) {
        return (hue >= BLUE_MIN_HUE) && (hue <= BLUE_MAX_HUE);
    }

    private boolean isYellow(double hue) {
        return (hue >= YELLOW_MIN_HUE) && (hue <= YELLOW_MAX_HUE);
    }

    /**
     * Outputs telemetry calculated from the last call to getDetectedSample().
     */
    public void outputTelemetry(Telemetry telemetry) {
        telemetry.addData("Sample Distance (mm)", "%.3f", detectedDistance);
        telemetry.addData("Sample Hue", "%.3f", detectedHue);
        telemetry.addData("Sample Type", detectedSample);
    }
}
