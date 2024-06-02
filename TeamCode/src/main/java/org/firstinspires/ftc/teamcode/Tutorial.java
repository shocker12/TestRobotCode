package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Tutorial extends LinearOpMode {

    HWMap hwMap;

    @Override
    public void runOpMode(){
        HWMap hwMap = new HWMap(hardwareMap, this);
        hwMap.wheelFront.setPower(0.2);
    }

}
