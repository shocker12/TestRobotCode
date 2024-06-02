package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * This class controls the drive train for the robot
 *OOH LALA
 * It calculates the distance traveled using the number of encoder "ticks" that describe the rotation of the shaft
 * and the circumference of the wheel to come up with a distance traveled through the number of rotations of the shaft.
 *muahahahaha
 * It will be used in Teleop and any of the autonomous OpModes
 */

public class DriveTrain extends RobotPart {
    String DRIVE_TRAIN_CAPTION = "Drive Status";
    private static double WHEEL_DIAMETER = 4; //inches
    private static double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
    private static double TICKS_IN_A_ROTATION = 28*40; //CPR = 28, 40:1 gearbox on motor
    private static double GEAR_RATIO = 1; // 2:1 gear ratio (2 wheel rotations for one motor rotation)
    private static final double TICKS_IN_AN_INCH = ((TICKS_IN_A_ROTATION*GEAR_RATIO)/ WHEEL_CIRCUMFERENCE) ; // The number of encoder ticks per inch for our wheels
    private double wheelPower = 0.5;

    public DriveTrain(Telemetry telemetry, HardwareInnov8ToyChest buzzLightyear, LinearOpMode opMode) {
        super(telemetry, buzzLightyear, opMode);
    }

    /*public void goForward(double inches, double power) {
        double startPosition = 0;
        double endPosition = 0;
        showData("DRIVE_TRAIN_CAPTION", "Robot is moving forward");
        startPosition = toyChest.motorRight.getCurrentPosition();
        endPosition = startPosition + (inches * TICKS_IN_AN_INCH); // How far you need to travel
        while (toyChest.motorRight.getCurrentPosition() < endPosition && this.opMode.opModeIsActive()) {
            toyChest.motorLeft.setPower(power);
            toyChest.motorRight.setPower(power);
        }
        this.stop();
        this.telemetry.update();
    }

    public void goBackward(double inches, double power) {
        double startPosition = 0;
        double endPosition = 0;
        showData("DRIVE_TRAIN_CAPTION", "Robot is moving forward");
        startPosition = toyChest.motorRight.getCurrentPosition();
        endPosition = startPosition - (inches * TICKS_IN_AN_INCH); // How far you need to travel
        while (toyChest.motorRight.getCurrentPosition() > endPosition && this.opMode.opModeIsActive()) {
            toyChest.motorLeft.setPower(-power);
            toyChest.motorRight.setPower(-power);
        }
        this.stop();
        this.telemetry.update();
    }

    public void turn(double degreesToTurn) {
        showData("Turn status: ", "Entered turn method");
        telemetry.addData("Degrees to Turn: ", degreesToTurn);
        Orientation angles;
        angles = toyChest.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentAngleIn360;
        if(angles.firstAngle <=0){
            currentAngleIn360 = angles.firstAngle + 360; //puts starting angle in terms of 360 degree circle (always positive)
        }
        else{
            currentAngleIn360 = angles.firstAngle;
        }
        showData("Turn Info", "current angle: " + currentAngleIn360);
        double targetAngle = currentAngleIn360 + degreesToTurn; // calculates angle the robot is trying to turn to
        if(targetAngle > 360){
            targetAngle -= 360; //keeps target angle within 1-360 degree range
        }
        showData("Turn Info", "target angle: " + targetAngle);
        double degreesLeftToTurn = targetAngle - currentAngleIn360;
        if(degreesLeftToTurn < -180){
            degreesLeftToTurn += 360; // makes sure robot always tries to turn shortest distance between it and the target angle
        }
        else if(degreesLeftToTurn > 180){
            degreesLeftToTurn -=360;
        }
        showData("Turn Info", "Degrees Left to turn: " + degreesLeftToTurn);
        if (degreesLeftToTurn < 0) {
            while ((degreesLeftToTurn <= 0) && this.opMode.opModeIsActive()) {
                double generalPower = (degreesToTurn - angles.firstAngle)/(degreesToTurn);
                if (generalPower < 0.5) {
                    generalPower = 0.5;
                }
                showData("General power: ", ""+ generalPower);
                toyChest.motorLeft.setPower(generalPower * wheelPower);
                toyChest.motorRight.setPower(generalPower * -1 * wheelPower);
                showData("wheelPowers: ", "" + (generalPower * wheelPower) + ", " + (generalPower * wheelPower)
                        + ", " + (-1 * generalPower * wheelPower)  + ", " +(-1 * generalPower * wheelPower));
                angles = toyChest.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                if(angles.firstAngle <=0){
                    currentAngleIn360 = angles.firstAngle + 360; //puts current angle in terms of 360 degree circle (always positive)
                }
                else{
                    currentAngleIn360 = angles.firstAngle;
                }
                degreesLeftToTurn = targetAngle - currentAngleIn360;
                if(degreesLeftToTurn < -180){
                    degreesLeftToTurn += 360; // makes sure robot always tries to turn shortest distance between it and the target angle
                }
                else if(degreesLeftToTurn > 180){
                    degreesLeftToTurn -=360;
                }
                String turnInfo = "angles: " + angles.firstAngle + ", " + angles.secondAngle + ", " + angles.thirdAngle;
                showData("Turning", turnInfo);
                showData("Turn Info", "degrees Left To Turn: "+degreesLeftToTurn);
            }
        } else if (degreesLeftToTurn > 0){ // changed the if statement so that it will keep turning back and forth until it reaches the target angle
            while ((degreesLeftToTurn >= 0) && this.opMode.opModeIsActive()) {
                double generalPower = (degreesToTurn - angles.firstAngle)/(degreesToTurn);
                if (generalPower < 0.5) {
                    generalPower = 0.5;
                }
                toyChest.motorLeft.setPower(1 * generalPower * wheelPower);
                toyChest.motorRight.setPower(-1 * generalPower * wheelPower);
                angles = toyChest.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                if(angles.firstAngle <=0){
                    currentAngleIn360 = angles.firstAngle + 360; //puts starting angle in terms of 360 degree circle (always positive)
                }
                else{
                    currentAngleIn360 = angles.firstAngle;
                }
                degreesLeftToTurn = targetAngle - currentAngleIn360;
                if(degreesLeftToTurn < -180){
                    degreesLeftToTurn += 360; // makes sure robot always tries to turn shortest distance between it and the target angle
                }
                else if(degreesLeftToTurn > 180){
                    degreesLeftToTurn -=360;
                }
                showData("DegreesLeftToTurn", degreesLeftToTurn+"");
                String turnInfo = "angles: " + angles.firstAngle + ", " + angles.secondAngle + ", " + angles.thirdAngle;
                showData("Turning", turnInfo);
            }
        }
        this.stop();

    }
    public void turn2(double degreesToTurn) {
        showData("Turn status: ", "Entered turn2 method");
        telemetry.addData("Degrees to Turn: ", degreesToTurn);
        Orientation angles;
        angles = toyChest.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentAngleIn360;
        if(angles.firstAngle <=0){
            currentAngleIn360 = angles.firstAngle + 360; //puts starting angle in terms of 360 degree circle (always positive)
        }
        else{
            currentAngleIn360 = angles.firstAngle;
        }
        showData("Turn Info", "current angle: " + currentAngleIn360);
        double targetAngle = currentAngleIn360 - degreesToTurn; // calculates angle the robot is trying to turn to
        if(targetAngle > 360){
            targetAngle -= 360; //keeps target angle within 1-360 degree range
        }
        showData("Turn Info", "target angle: " + targetAngle);
        showData("Turn Info", "degreesToTurn" + degreesToTurn );
        double degreesLeftToTurn = targetAngle - currentAngleIn360;
        if(degreesLeftToTurn < -180){
            degreesLeftToTurn += 360; // makes sure robot always tries to turn shortest distance between it and the target angle
        }
        else if(degreesLeftToTurn > 180){
            degreesLeftToTurn -=360;
        }
        showData("Turn Info", "Degrees Left to turn: " + degreesLeftToTurn);
        if (degreesLeftToTurn < 0) {
            while ((degreesLeftToTurn < 0) && this.opMode.opModeIsActive()) {
                double generalPower =  0.5;

                showData("General power: ", ""+ generalPower);
                toyChest.motorLeft.setPower(generalPower * wheelPower);
                toyChest.motorRight.setPower(generalPower * -1 * wheelPower);
                showData("wheelPowers: ", "" + (generalPower * wheelPower) + ", " + (generalPower * wheelPower)
                        + ", " + (-1 * generalPower * wheelPower)  + ", " +(-1 * generalPower * wheelPower));
                angles = toyChest.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                if(angles.firstAngle <=0){
                    currentAngleIn360 = angles.firstAngle + 360; //puts current angle in terms of 360 degree circle (always positive)
                }
                else{
                    currentAngleIn360 = angles.firstAngle;
                }
                degreesLeftToTurn = targetAngle - currentAngleIn360;
                if(degreesLeftToTurn < -180){
                    degreesLeftToTurn += 360; // makes sure robot always tries to turn shortest distance between it and the target angle
                }
                else if(degreesLeftToTurn > 180){
                    degreesLeftToTurn -=360;
                }
                String turnInfo = "angles: " + angles.firstAngle + ", " + angles.secondAngle + ", " + angles.thirdAngle;
                showData("Turning", turnInfo);
                showData("Turn Info", "degrees Left To Turn: "+degreesLeftToTurn + "360: " + currentAngleIn360 + "targetAngle" + targetAngle);
            }
        } else if (degreesLeftToTurn > 0){ // changed the if statement so that it will keep turning back and forth until it reaches the target angle
            while ((degreesLeftToTurn >= 0) && this.opMode.opModeIsActive()) {
                double generalPower = 0.5;
                toyChest.motorLeft.setPower(-1 * generalPower * wheelPower);
                toyChest.motorRight.setPower(1 * generalPower * wheelPower);
                angles = toyChest.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                if(angles.firstAngle <=0){
                    currentAngleIn360 = angles.firstAngle + 360; //puts starting angle in terms of 360 degree circle (always positive)
                }
                else{
                    currentAngleIn360 = angles.firstAngle;
                }
                degreesLeftToTurn = targetAngle - currentAngleIn360;
                if(degreesLeftToTurn < -180){
                    degreesLeftToTurn += 360; // makes sure robot always tries to turn shortest distance between it and the target angle
                }
                else if(degreesLeftToTurn > 180){
                    degreesLeftToTurn -=360;
                }
                showData("DegreesLeftToTurn", degreesLeftToTurn+"");
                String turnInfo = "angles: " + angles.firstAngle + ", " + angles.secondAngle + ", " + angles.thirdAngle;
                showData("Turning", turnInfo);
                showData("Turn Info", "degrees Left To Turn: "+degreesLeftToTurn + "360: " + currentAngleIn360 + "targetAngle" + targetAngle);
            }
        }
        this.stop();

    }*/

    public void teleopUpdate (Gamepad gamepad1, Gamepad gamepad2, double minSpeed, double maxSpeed){
        double leftFrontPower;
        double rightFrontPower;
        double leftBackPower;
        double rightBackPower;


        double vertical = gamepad1.left_stick_y;
        double horizontal  = gamepad1.left_stick_x;
        double counter = 1;

        showData("Min Speed", "" + minSpeed);
        showData("Max Speed", "" + maxSpeed);

        if (gamepad1.left_bumper) {
            maxSpeed = 0.2;
            minSpeed = -.2;
            showData("Left Min Speed", "" + minSpeed);
            showData("Left Max Speed", "" + maxSpeed);
        }

        leftFrontPower    = Range.clip(vertical-horizontal, minSpeed, maxSpeed) ;
        rightFrontPower   = Range.clip(vertical+horizontal, minSpeed, maxSpeed) ;
        leftBackPower    = Range.clip(vertical+horizontal, minSpeed, maxSpeed) ;
        rightBackPower   = Range.clip(vertical-horizontal, minSpeed, maxSpeed) ;

        this.toyChest.motorLeftFront.setPower(leftFrontPower);
        this.toyChest.motorRightFront.setPower(rightFrontPower);
        this.toyChest.motorLeftBack.setPower(leftBackPower);
        this.toyChest.motorRightBack.setPower(rightBackPower);
        /*this.telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        this.telemetry.update();
        if (gamepad1.b) {
            this.turn2(15);
        }
        if (gamepad1.y) {
            this.turn2(-15);
        }*/


    }

    public void stop() {
        this.showData("DRIVE_TRAIN_CAPTION", "Stopping the drive train");
        //this.telemetry.addData("wheel power", toyChest.motorLeft.getPower());
        this.telemetry.update();
        this.toyChest.motorLeftFront.setPower(0);
        this.toyChest.motorRightFront.setPower(0);
        this.toyChest.motorLeftBack.setPower(0);
        this.toyChest.motorRightBack.setPower(0);
        this.telemetry.addData(DRIVE_TRAIN_CAPTION, "Drive train is stopped");
        //this.telemetry.addData("wheel power", toyChest.motorLeft.getPower());
        this.telemetry.update();
    }
}