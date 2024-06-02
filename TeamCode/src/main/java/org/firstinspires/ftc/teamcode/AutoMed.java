package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "AutoMed", group = "Robot")
public class AutoMed extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException {
        ToyChest toyChest = new ToyChest(telemetry, hardwareMap, this);
        waitForStart();
       // toyChest.autoMed();
    }
}