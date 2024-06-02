package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ToyChest {

    Catapult catapult;
    Ears leftEar;
    Ears rightEar;
    Ears tail;
    //OpenCV openCV;
    CapstonePosition position;
    DriveTrain drivetrain;
    Telemetry telemetry;
    HardwareInnov8ToyChest hwmap;

    LinearOpMode opMode;
    //TensorFlow tensorFlow;



    public ToyChest(Telemetry telemetry, HardwareMap hwmap, LinearOpMode opMode) {
        this.opMode = opMode;
        this.hwmap = new HardwareInnov8ToyChest(hwmap, opMode);
        this.telemetry = telemetry;
        drivetrain = new DriveTrain(this.telemetry, this.hwmap, this.opMode);
        catapult = new Catapult(this.telemetry, this.hwmap, this.opMode);
        rightEar = new Ears(this.telemetry, this.hwmap, this.opMode);
        leftEar = new Ears(this.telemetry, this.hwmap, this.opMode);
        tail = new Ears(this.telemetry, this.hwmap, this.opMode);
        //openCV = new OpenCV(this.telemetry, this.hwmap, this.opMode);

    }

//    // Detects capstone position, spins carousel, adds telemetry based on where capstone should be dropped, parks in warehouse
//    public void autoPark() {
//        this.drivetrain.goForward(3,0.8); //initilaa 40
//        this.drivetrain.turn(-90);
//        this.drivetrain.goBackward(3, 0.8);
//        this.telemetry.addData("Dropoff Location: ", "top");
//        try {
//            Thread.sleep(500);
//        }
//        catch(InterruptedException e){
//            Log.d("Sleepy time", "Sleep failed");
//        }
//    }
//
//    // Drives forward 36 inches
//    public void autoStraight() {
//        this.drivetrain.goForward(36,0.8);
//    }
//
//    public void autoTurn(){
//        this.drivetrain.turn(0);
//        try {
//            Thread.sleep(2000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.drivetrain.turn(90);
//        try {
//            Thread.sleep(2000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.drivetrain.turn(180);
//        try {
//            Thread.sleep(2000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.drivetrain.turn(270);
//        try {
//            Thread.sleep(2000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.drivetrain.turn(360);
//    }
//
//    public void autoTall() {
//        this.claw.toyChest.claw.setPosition(0);
//        this.lift.toyChest.pawl.setPosition(0.5);
//        try {
//            Thread.sleep(500);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.lift.liftUp(150);
//        this.lift.toyChest.pawl.setPosition(1);
//        this.drivetrain.goForward(48,0.8);
//        this.drivetrain.turn(330);
//        this.lift.autoLift(3,1);
//        this.drivetrain.goForward(8,0.5);
//        this.claw.toyChest.claw.setPosition(1);
//        try {
//            Thread.sleep(2000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.drivetrain.goBackward(9,0.5);
//        this.lift.toyChest.pawl.setPosition(0.5);
//        this.drivetrain.turn(270);//180=>cone stack
//        this.drivetrain.goForward(24,0.8);
//    }
//
//    public void autoCam() {
//        position = openCV.getCapstonePosition();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            Log.d("Sleepy time", "Sleep failed");
//        }
//        position = openCV.getCapstonePosition();
//        Log.d("Read2", "Read2:" + position);
//        this.claw.toyChest.claw.setPosition(0);
//        this.lift.toyChest.pawl.setPosition(0.5);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            Log.d("Sleepy time", "Sleep failed");
//        }
//        this.lift.liftUp(150);
//        this.lift.toyChest.pawl.setPosition(1);
//        this.drivetrain.goForward(48, 0.8);
//        this.drivetrain.turn2(40);
//        this.lift.autoLift(3, 1);
//        this.drivetrain.goForward(8, 0.5);
//        this.lift.toyChest.pawl.setPosition(0);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            Log.d("Sleepy time", "Sleep failed");
//        }
//        this.claw.toyChest.claw.setPosition(1);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            Log.d("Sleepy time", "Sleep failed");
//        }
//        this.drivetrain.goBackward(10, 0.5);
//        this.lift.toyChest.pawl.setPosition(0.5);
//        this.drivetrain.turn2(35);
//        if (position == CapstonePosition.TALL) {//parking spot 1
//            this.drivetrain.goForward(20, 0.8);
//        } else if (position == CapstonePosition.SMALL) {//parking spot 3
//            this.drivetrain.goBackward(12, 0.8);
//        } else {//parking spot 2
//            this.drivetrain.goForward(1, 0.5);
//            Log.d("I am parking", "MEDIUM");
//        }
//    }
//    public void autoCamLeft() {
//        position = openCV.getCapstonePosition();
//        try {
//            Thread.sleep(2000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        position = openCV.getCapstonePosition();
//        Log.d("Read2", "Read2:"+position);
//
//        this.claw.toyChest.claw.setPosition(0);
//        this.lift.toyChest.pawl.setPosition(0.5);
//        try {
//            Thread.sleep(500);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.lift.liftUp(150);
//        this.lift.toyChest.pawl.setPosition(1);
//        this.drivetrain.goForward(48,0.75);
//        this.drivetrain.turn2(-40);
//        this.lift.toyChest.pawl.setPosition(0.5);
//        this.lift.autoLift(3,1);
//        this.drivetrain.goForward(8,0.5);
//        this.lift.toyChest.pawl.setPosition(0.5);
//        try {
//            Thread.sleep(3000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.claw.toyChest.claw.setPosition(1);
//        try {
//            Thread.sleep(2000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.drivetrain.goBackward(10,0.5);
//        this.drivetrain.turn2(-30);
//        if(position == CapstonePosition.SMALL) {//parking spot 3
//            this.drivetrain.goBackward(18, 0.75);
//            Log.d("I am parking", "SMALL");
//        }
//        else if(position == CapstonePosition.TALL){//parking spot 1
//            this.drivetrain.goForward(27, 0.75);
//            Log.d("I am parking", "TALL");
//        }
//        else{//parking spot 2
//            this.drivetrain.goForward(4 , 0.5);
//            Log.d("I am parking", "MEDIUM");
//        }
//    }
//
//    public void autoMed() {
//        this.claw.toyChest.claw.setPosition(0);
//        this.lift.toyChest.pawl.setPosition(1);
//        this.lift.liftUp(100);
//        this.drivetrain.goForward(24,0.8);
//        try {
//            Thread.sleep(1000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.drivetrain.turn2(330);
//        this.lift.autoLift(2,1);
//        this.drivetrain.goForward(6,0.5);
//        this.claw.toyChest.claw.setPosition(1);
//        try {
//            Thread.sleep(1000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.drivetrain.goBackward(3,0.5);
//        this.drivetrain.turn(270);
//        this.drivetrain.goForward(24,0.8);
//    }
//
//    public void autoShort() {
//        this.claw.toyChest.claw.setPosition(0);
//        this.lift.toyChest.pawl.setPosition(1);
//        this.lift.liftUp(100);
//        try {
//            Thread.sleep(1000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.drivetrain.turn(330);
//        this.lift.autoLift(1,1);
//        this.drivetrain.goForward(7,0.5);
//        this.claw.toyChest.claw.setPosition(1);
//        try {
//            Thread.sleep(2000);
//        }
//        catch (InterruptedException e) {
//            Log.d("Sleepy time","Sleep failed");
//        }
//        this.drivetrain.goBackward(3,0.5);
//        this.drivetrain.turn(0);
//        this.drivetrain.goForward(24,0.8);
//    }
//
//    public void openCVTester(){
//        while(this.opMode.opModeIsActive()){
//            this.openCV.getCapstonePosition();
//        }
//    }
//
    public void teleop(Gamepad gamepad1, Gamepad gamepad2){
        double minSpeed = -0.9;
        double maxSpeed = 0.9;

        while (this.opMode.opModeIsActive()) {
            drivetrain.teleopUpdate(gamepad1, gamepad2, minSpeed, maxSpeed);
            Log.d("Teleop Update", "Done with drivetrain");
            catapult.teleopUpdate(gamepad1, gamepad2, minSpeed, maxSpeed);
            Log.d("Teleop Update", "Done with claw");
            rightEar.teleopUpdate(gamepad1, gamepad2, minSpeed, maxSpeed);
            Log.d("Teleop Update", "Done with lift");
            //tail.teleopUpdate(gamepad1, gamepad2, minSpeed, maxSpeed);
            Log.d("Teleop Update", "Done with lift");

        }
    }
}