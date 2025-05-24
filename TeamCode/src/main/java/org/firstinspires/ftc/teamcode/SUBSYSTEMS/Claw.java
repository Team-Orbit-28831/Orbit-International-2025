package org.firstinspires.ftc.teamcode.SUBSYSTEMS;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;

import org.firstinspires.ftc.robotcore.external.Telemetry;


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
        limelight.pipelineSwitch(0);
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
    public void autoAlignClaw(Telemetry telemetry) {
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            double tx = result.getTx(); // How far left or right the target is (degrees)
            double ty = result.getTy(); // How far up or down the target is (degrees)
            double ta = result.getTa(); // How big the target looks (0%-100% of the image)

            telemetry.addData("Target X", tx);
            telemetry.addData("Target Y", ty);
            telemetry.addData("Target Area", ta);
            telemetry.update();

            double txMin = -30.0; // Adjust based on your setup
            double txMax = 30.0;
            double servoMin = 0.0;
            double servoMax = 1.0;



            // Clamp tx to the defined range
            tx = Math.max(txMin, Math.min(txMax, tx));

            // Linear interpolation
            // Invert tx for opposite servo movement:
            double servoPosition = ((tx - txMin) * (servoMax - servoMin) / (txMax - txMin)) + servoMin;



            rotationServo.setPosition(servoPosition);

            telemetry.addData("Servo Position", servoPosition);
            telemetry.update();
        } else {
            telemetry.addData("Limelight", "No Targets");
            telemetry.update();
        }
    }

}
