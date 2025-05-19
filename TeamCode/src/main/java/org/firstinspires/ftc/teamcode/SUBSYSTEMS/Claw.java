package org.firstinspires.ftc.teamcode.SUBSYSTEMS;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

public class Claw {
    private Servo clawServo = null;
    private Servo rotationServo = null;
    private Servo boxServo1 = null;
    private Servo boxServo2 = null;

    // PID VARIABLES
    private double integralSum = 0;
    private double lastError = 0;
    private final double Kp = 0.1; // Proportional gain
    private final double Ki = 0.01; // Integral gain
    private final double Kd = 0.05; // Derivative gain
    private final ElapsedTime timer = new ElapsedTime();

    // Limelight3A instance
    private Limelight3A limelight;

    // Initialize claw, arm servos, and Limelight
    public void init(HardwareMap hardwareMap) {
        clawServo = hardwareMap.get(Servo.class, "clawDrive");
        boxServo1 = hardwareMap.get(Servo.class, "boxServo1");
        boxServo2 = hardwareMap.get(Servo.class, "boxServo2");
        rotationServo = hardwareMap.get(Servo.class, "rotationServo");

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100); // Query Limelight 100 times per second
        limelight.start(); // Start Limelight
    }

    // Control claw open/close
    public void controlClaw(boolean open, boolean close) {
        if (open) {
            clawServo.setPosition(0.5);  // Open
        } else if (close) {
            clawServo.setPosition(0.75); // Close
        }
    }

    // Manual claw rotation control
    public void turnClaw(double rotateRight, double rotateLeft) {
        if (rotateRight > 0.1) {
            rotationServo.setPosition(0.8);
        } else if (rotateLeft > 0.1) {
            rotationServo.setPosition(0.2);  // Changed from -0.8 (invalid) to 0.2 (valid servo pos)
        } else {
            rotationServo.setPosition(0.5);
        }
    }

    // Control arm angle via D-pad
    public void angleClaw(boolean up, boolean down) {
        if (up) {
            boxServo1.setPosition(0.65);
            boxServo2.setPosition(0.65);
        } else if (down) {
            boxServo1.setPosition(0.2);
            boxServo2.setPosition(0.2);
        }
    }

    // PID calculation method
    public double PID(double reference, double state) {
        double error = reference - state;
        double dt = timer.seconds();

        integralSum += error * dt;
        double derivative = (error - lastError) / dt;
        lastError = error;
        timer.reset();

        return (Kp * error) + (Ki * integralSum) + (Kd * derivative);
    }

    // Get current claw position
    public double getClawPosition() {
        try {
            return clawServo.getPosition();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Auto-align claw rotation based on Limelight target yaw (tx)
    public void autoAlignClaw() {
        LLResult result = limelight.getLatestResult();

        // Check if there is a valid target
        if (result != null && result.isValid()) {


            // Get the horizontal offset (tx) in degrees
            double tx = result.getTx();

            // Align the claw if the target is sufficiently off-center
            if (Math.abs(tx) > 1.0) {
                double kP = 0.003; // Proportional gain
                double delta = -tx * kP;

                // Get the current position of the rotation servo
                double currentPos = rotationServo.getPosition();

                // Calculate the new position
                double newPos = currentPos + delta;

                // Clamp the new position between 0.0 and 1.0
                newPos = Math.max(0.0, Math.min(1.0, newPos));

                // Set the new position to the rotation servo
                rotationServo.setPosition(newPos);
            }
        }
    }

}
