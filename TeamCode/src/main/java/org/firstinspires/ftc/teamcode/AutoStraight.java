package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// Drives forward 36 inches
@Autonomous(name = "AutoStraight", group = "Robot")
public class AutoStraight extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException {
        ToyChest toyChest = new ToyChest(telemetry, hardwareMap, this);
        waitForStart();
   //     toyChest.autoStraight();
    }
}