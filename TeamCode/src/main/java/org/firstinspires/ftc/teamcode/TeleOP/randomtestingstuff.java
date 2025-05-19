package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.SUBSYSTEMS.Claw;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.SUBSYSTEMS.CascadePivot;
import org.firstinspires.ftc.teamcode.SUBSYSTEMS.Claw;
import org.firstinspires.ftc.teamcode.SUBSYSTEMS.Drivetrain;
import org.firstinspires.ftc.teamcode.SUBSYSTEMS.CascadeSlides;
@TeleOp(name = "testing", group = "Linear OpMode")
public class randomtestingstuff extends LinearOpMode {

    private Claw servo;
    private Claw claw;


    @Override
    public void runOpMode() {
        claw = new Claw();
        claw.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
                claw.testServo(gamepad1.left_stick_x,gamepad1.a,gamepad1.b);

        }
    }
}