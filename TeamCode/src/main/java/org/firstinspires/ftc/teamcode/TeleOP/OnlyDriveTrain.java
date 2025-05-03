package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SUBSYSTEMS.Drivetrain;
@TeleOp(name = "OnlyDriveTrain", group = "Linear OpMode")
public class OnlyDriveTrain extends LinearOpMode {
    private Drivetrain drivetrain;
    @Override
    public void runOpMode(){
        drivetrain = new Drivetrain();
        drivetrain.init(hardwareMap);
        telemetry.addData("Status","OpMode Initialised");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()){
            drivetrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            telemetry.addData("sticky",gamepad1.left_stick_y);
            telemetry.addData("stickx",gamepad1.left_stick_x);
            telemetry.update();
        }



    }
}
