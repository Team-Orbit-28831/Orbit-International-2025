package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        Pose2d beginPose = new Pose2d(20,-70,Math.toRadians(90));

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();


        myBot.runAction(myBot.getDrive().actionBuilder(beginPose)
                .strafeTo(new Vector2d(24,-52)).build());

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(20, -63, 0))
                .strafeTo(new Vector2d(20, -52))
                .strafeTo(new Vector2d(35, -52))
                .turn(Math.toRadians(90))
                .strafeTo(new Vector2d(35, -12))
                .strafeTo(new Vector2d(48, -12))
                .strafeTo(new Vector2d(48, -60))
                .strafeTo(new Vector2d(48, -12))
                .strafeTo(new Vector2d(62, -12))
                .strafeTo(new Vector2d(62, -60))
                .strafeTo(new Vector2d(20, -60))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}