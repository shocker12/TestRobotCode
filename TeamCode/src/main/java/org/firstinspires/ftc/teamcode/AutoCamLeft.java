package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "AutoCamLeft", group = "Robot")
public class AutoCamLeft extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException {
        ToyChest toyChest = new ToyChest(telemetry, hardwareMap, this);
        waitForStart();
      //  toyChest.autoCamLeft();
    }
}