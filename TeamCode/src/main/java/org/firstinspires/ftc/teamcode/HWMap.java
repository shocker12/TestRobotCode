package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class HWMap {
    // Motors
    public DcMotor wheelFront = null;
    public DcMotor wheelRight = null;
    public DcMotor wheelBack = null;
    public DcMotor wheelLeft = null;
    public DcMotor stilts = null; // lift
    public DcMotor trapeze = null; // suspension

    // Servos
    public Servo arm = null; // arm elbow
    public Servo claw = null; // claw
    public Servo cannon = null; // drone launcher
    public Servo dropper = null; // dropper on arm
    public Servo ringleader = null; // autonomous pixel ground placer

    // Sensors
    public TouchSensor touchOpen = null; // first touch sensor on suspension
    public TouchSensor touchClose = null; // seconds touch sensor on suspension

    //Webcam
    public WebcamName webcam = null;

    // IMU
    public BNO055IMU imu;

    // Defining servo position numbers/ranges
    public static final double MID_SERVO = 0.5;
    public static final double START_SERVO = 0; // right on servo tester
    public static final double END_SERVO = 1; // left on servo tester

    //setting up variables for constructor
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();
    private LinearOpMode opMode;

    public HWMap(HardwareMap outsideHWMap, LinearOpMode outsideOpMode){
        this.opMode = outsideOpMode;
        this.hwMap = outsideHWMap;
        this.init();
    }

    public void init(){
        // Define and initialize motors
        wheelFront = this.hwMap.get(DcMotor.class, "wheelFront");
        wheelRight = this.hwMap.get(DcMotor.class, "wheelRight");
        wheelBack = this.hwMap.get(DcMotor.class, "wheelBack");
        wheelLeft = this.hwMap.get(DcMotor.class, "wheelLeft");
        stilts = this.hwMap.get(DcMotor.class, "stilts");
        trapeze = this.hwMap.get(DcMotor.class, "trapeze");

        //define direction motors spin
        wheelFront.setDirection(DcMotor.Direction.FORWARD);
        wheelRight.setDirection(DcMotor.Direction.FORWARD);
        wheelBack.setDirection(DcMotor.Direction.REVERSE);
        wheelLeft.setDirection(DcMotor.Direction.REVERSE);
        stilts.setDirection(DcMotor.Direction.FORWARD);
        trapeze.setDirection(DcMotor.Direction.REVERSE);

        // set all motors to 0 power on initialization
        wheelFront.setPower(0);
        wheelRight.setPower(0);
        wheelBack.setPower(0);
        wheelLeft.setPower(0);
        stilts.setPower(0);
        trapeze.setPower(0);

        // setting up encoders so you can use them to code motors
        wheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        wheelLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        stilts.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        trapeze.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define and initialize servos
        arm = this.hwMap.get(Servo.class, "arm");
        claw = this.hwMap.get(Servo.class, "claw");
        cannon = this.hwMap.get(Servo.class, "cannon");
        dropper = this.hwMap.get(Servo.class, "dropper");
        ringleader = this.hwMap.get(Servo.class, "ringleader");

        //set servo starting position
        arm.setPosition(0);//1 means down, 0 means up
        claw.setPosition(1);//1 means open, 0 means close
        cannon.setPosition(0); //0 loaded, 1 release
        dropper.setPosition(1);//1 is loaded, 0 is release
        ringleader.setPosition(1);//1 is loaded, 0 is release

        //Define and initialize touch sensors
        touchOpen = this.hwMap.get(TouchSensor.class, "touchOpen");
        touchClose = this.hwMap.get(TouchSensor.class, "touchClose");

        //setting up webcam
        webcam = this.hwMap.get(WebcamName.class,"Webcam 1");

        //setting up IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        imu = this.hwMap.get(BNO055IMU.class, "imu");
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        this.imu.initialize(parameters);
        while(!this.opMode.isStopRequested() && imu.isGyroCalibrated()) {
            this.opMode.sleep(50);
            this.opMode.idle();
        }
    }
}
