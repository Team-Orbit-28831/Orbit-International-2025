package org.firstinspires.ftc.teamcode.SUBSYSTEMS;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class TestingCodeforanythingreally {
    private DcMotor slides = null;

    public void init(HardwareMap hardwareMap){
        slides= hardwareMap.get(DcMotor.class, "slidesmotor");
    }
    public void slidesmotor(boolean up,boolean down) {
        if (up == true) {
            slides.setPower(0.2);
        }
        if (down == true){
            slides.setPower(-0.2);
        }
        else{
            slides.setPower(0);
        }

    }
}
