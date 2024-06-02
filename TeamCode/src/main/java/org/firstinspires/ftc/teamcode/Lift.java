//package org.firstinspires.ftc.teamcode;
//
//import android.util.Log;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.Gamepad;
//import com.qualcomm.robotcore.util.Range;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//
///**
// * This class controls the claw for the robot
// * It determines how to open and close the claw
// */
//
//public class Lift extends RobotPart {
//    String LIFT_STATUS = "Lift Status";
//
//    public Lift(Telemetry telemetry, HardwareInnov8ToyChest buzzLightyear, LinearOpMode opMode) {
//        super(telemetry, buzzLightyear, opMode);
//    }
//
//    public void teleopUpdate(Gamepad gamepad1, Gamepad gamepad2, double minSpeed, double maxSpeed) {
//        double liftPower;
//
//        double liftInput = gamepad2.left_stick_y;
//        liftPower    = Range.clip(liftInput, -1.0, 1.0) ;
//
////        if (gamepad2.y) {
////            this.toyChest.pawl.setPosition(0.5);
////        }
////
////        if (gamepad2.a) {
////            this.toyChest.pawl.setPosition(1);//1 is down
////        }
//
//
//        this.toyChest.liftMotor.setPower(-liftPower);
//        this.telemetry.addData("Lift Position",this.toyChest.liftMotor.getCurrentPosition());
//        //this.telemetry.addData("Pawl Position",this.toyChest.pawl.getPosition());
//        this.telemetry.update();
//    }
//
//    public void autoLift(int poleHeight, int direction) { //poleHeight: 1=low 2=mid 3=high  direction: 1=up 2=down
//        double startPosition;//ground is 1538
//        double endPosition;
//        double highPosition = 6350;
//        double midPosition = 4467;
//        double lowPosition = 2633;
//        double currentPosition;
//        startPosition = this.toyChest.liftMotor.getCurrentPosition();
//        if (poleHeight == 3) {
//            endPosition = startPosition + highPosition;
//        }
//        else if (poleHeight == 2) {
//            endPosition = startPosition + midPosition;
//        }
//        else {
//            endPosition = startPosition + lowPosition;
//        }
//        this.toyChest.pawl.setPosition(0.5); //Opening pawl
//        try {
//            Thread.sleep(1000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//
//        this.toyChest.liftMotor.setPower(1);
//        currentPosition = this.toyChest.liftMotor.getCurrentPosition();
//        while (currentPosition < endPosition) {
//            currentPosition = this.toyChest.liftMotor.getCurrentPosition();
//            this.telemetry.addData("Lift Motor", currentPosition);
//            this.telemetry.update();
//        }
//        this.toyChest.pawl.setPosition(1);
//        this.toyChest.liftMotor.setPower(0);
//
//    }
//    public void liftUp(int liftHeight) {
//        double startPosition;//ground is 2070
//        double endPosition;
//        double currentPosition;
//        startPosition = this.toyChest.liftMotor.getCurrentPosition();
//        endPosition = startPosition + liftHeight;
//        try {
//            Thread.sleep(1000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//
//        this.toyChest.liftMotor.setPower(1);
//        currentPosition = this.toyChest.liftMotor.getCurrentPosition();
//        while (currentPosition < endPosition) {
//            currentPosition = this.toyChest.liftMotor.getCurrentPosition();
//            this.telemetry.addData("Lift Motor", currentPosition);
//            this.telemetry.update();
//        }
//        this.toyChest.pawl.setPosition(0.5);
//        this.toyChest.liftMotor.setPower(0);
//
//    }
//}