package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "AutoTall", group = "Robot")
public class AutoTall extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException {
        ToyChest toyChest = new ToyChest(telemetry, hardwareMap, this);
        waitForStart();
        //    toyChest.autoTall();
    }
}