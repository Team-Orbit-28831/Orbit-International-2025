//package org.firstinspires.ftc.teamcode.Autonomous;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.SUBSYSTEMS.CascadePivot;
//import org.firstinspires.ftc.teamcode.SUBSYSTEMS.Claw;
//import org.firstinspires.ftc.teamcode.SUBSYSTEMS.Drivetrain;
//import org.firstinspires.ftc.teamcode.SUBSYSTEMS.CascadeSlides;
//@Autonomous
//
//public class Auto11 extends LinearOpMode {
//
//    private CascadePivot pivot;
//    private CascadeSlides slides;
//    private Claw claw;
//    private Drivetrain drivetrain;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        pivot = new CascadePivot();
//        slides = new CascadeSlides();
//        drivetrain = new Drivetrain();
//        claw = new Claw();
//
//        pivot.init(hardwareMap);
//        slides.init(hardwareMap);
//        drivetrain.init(hardwareMap);
//        claw.init(hardwareMap);
//
//        waitForStart();  // Wait for the start signal
//
//        if (opModeIsActive()) {
//
//            claw.controlClaw(0, 1);
//            sleep(500);
//
//            pivot.movePivot(38);
//            sleep(500);
//
//            slides.move(3381);
//            sleep(1000);
//
//            claw.controlArm(true, false);
//            sleep(500);
//
//            claw.controlClaw(1, 0);
//            sleep(500);
//
//            claw.controlArm(false, true);
//            sleep(500);
//
//            slides.move(-3381);
//            sleep(1000);
//
//            pivot.movePivot(-38);
//            sleep(500);
//        }
//    }
//}
