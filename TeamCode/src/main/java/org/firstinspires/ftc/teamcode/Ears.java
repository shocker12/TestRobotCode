package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This class controls the claw for the robot
 * It determines how to open and close the claw
 */

public class Ears extends RobotPart {
    String CLAW_STATUS = "Claw Status";
    double counter = 0.0;
    boolean up = true;
    double speed = 0.005;
    boolean active = false;
    boolean pressed = false;

    public Ears(Telemetry telemetry, HardwareInnov8ToyChest buzzLightyear, LinearOpMode opMode) {
        super(telemetry, buzzLightyear, opMode);
    }

    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2, double minSpeed, double maxSpeed) {
        if (gamepad1.y) {
            if(!pressed){
                pressed = true;
                active = !active;
            }
        }
        else{
            pressed = false;
        }

        if(active) {
            if (counter > 0.3) {
                up = false;
            }
            if (counter <= 0) {
                up = true;
            }
            if (up) {
                counter += speed;
            } else {
                counter -= speed;
            }

            toyChest.rightEar.setPosition(0.5 - counter);
            toyChest.leftEar.setPosition(counter);
            toyChest.tail.setPosition(counter * 2.0 + 0.2);
        }


        //this.telemetry.addData("Claw Position", this.toyChest.claw.getPosition());
        //this.telemetry.update();
    }
}