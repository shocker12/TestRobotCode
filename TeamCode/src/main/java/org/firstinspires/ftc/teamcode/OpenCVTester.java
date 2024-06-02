package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "OpenCVTester", group = "Robot")

public class OpenCVTester extends LinearOpMode {
        @Override
        public void runOpMode() throws InterruptedException {
            ToyChest toyChest = new ToyChest(telemetry, hardwareMap, this);
            waitForStart();
        //    toyChest.openCVTester();
        }
    }