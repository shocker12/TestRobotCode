package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This class controls the claw for the robot
 * It determines how to open and close the claw
 */

public class Catapult extends RobotPart {
    String CLAW_STATUS = "Claw Status";

    public Catapult(Telemetry telemetry, HardwareInnov8ToyChest buzzLightyear, LinearOpMode opMode) {
        super(telemetry, buzzLightyear, opMode);
    }

    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2, double minSpeed, double maxSpeed) {
        double catapult;

        if (gamepad1.b) {
            this.toyChest.catapult.setPosition(0);//release
        }

        if (gamepad1.x) {
                this.toyChest.catapult.setPosition(0.5);//0.5=hold
        }

        //this.telemetry.addData("Claw Position", this.toyChest.claw.getPosition());
        //this.telemetry.update();
    }
}