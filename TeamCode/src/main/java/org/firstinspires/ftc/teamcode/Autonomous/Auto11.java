package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.SUBSYSTEMS.CascadePivot;
import org.firstinspires.ftc.teamcode.SUBSYSTEMS.Claw;
import org.firstinspires.ftc.teamcode.SUBSYSTEMS.Drivetrain;
import org.firstinspires.ftc.teamcode.SUBSYSTEMS.CascadeSlides;

@Disabled
@Autonomous(name = "testautonomous", group = "Autonomous")
public class Auto11 extends LinearOpMode {
    @Override
    public void runOpMode() {
        Pose2d beginPose = new Pose2d(-30,-65.3,Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        waitForStart();
            if(isStopRequested()) return;

            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .strafeTo(new Vector2d(35, -52))
                            .turn(Math.toRadians(90))
                            .strafeTo(new Vector2d(35, -12))
                            .strafeTo(new Vector2d(48, -12))
                            .strafeTo(new Vector2d(48, -60))
                            .build()
            );



    }
}

