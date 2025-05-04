package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.SUBSYSTEMS.Drivetrain;

@TeleOp(name = "OnlyDriveTrain", group = "Linear OpMode")
public class OnlyDriveTrain extends LinearOpMode {

    private Drivetrain drivetrain;
    private DcMotorEx leftEncoder;  // First odometry encoder on motor port 0
    private DcMotorEx rightEncoder; // Second odometry encoder on motor port 1

    @Override
    public void runOpMode() {
        drivetrain = new Drivetrain();
        drivetrain.init(hardwareMap);

        // Set up the first encoder on motor port 0 (named "leftEncoder" in the config)
        leftEncoder = hardwareMap.get(DcMotorEx.class, "leftEncoder");
        leftEncoder.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftEncoder.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        // Set up the second encoder on motor port 1 (named "rightEncoder" in the config)
        rightEncoder = hardwareMap.get(DcMotorEx.class, "rightEncoder");
        rightEncoder.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        // Optionally reverse the encoders if necessary
        leftEncoder.setDirection(DcMotorEx.Direction.REVERSE);  // Adjust if needed
        rightEncoder.setDirection(DcMotorEx.Direction.FORWARD); // Adjust if needed

        telemetry.addData("Status", "OpMode Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Control the robot drivetrain (replace this with your actual drivetrain controls)
            drivetrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

            // Display gamepad inputs
            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
            telemetry.addData("Left Stick X", gamepad1.left_stick_x);

            // Display the encoder positions for both odometry wheels
            telemetry.addData("Left Encoder Ticks", leftEncoder.getCurrentPosition());
            telemetry.addData("Right Encoder Ticks", rightEncoder.getCurrentPosition());

            telemetry.update();
        }
    }
}