package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 * <p>
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 * <p>
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 * <p>
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 */
public class HardwareInnov8ToyChest {
    /* Public OpMode members. */
    public DcMotor motorLeftFront = null;
    public DcMotor motorRightFront = null;
    public DcMotor motorLeftBack = null;
    public DcMotor motorRightBack = null;
   // public DcMotor liftMotor = null;

    public Servo rightEar = null;
    public Servo leftEar = null;
    public Servo tail = null;
    public Servo catapult = null;

    public BNO055IMU imu;

    public static final double MID_SERVO = 0.5;
    public static final double START_SERVO = 0; // all the way down
    public static final double END_SERVO = 1; // all the way up

    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();
    private LinearOpMode opMode;
    /* Constructor */
    public HardwareInnov8ToyChest(HardwareMap ahwMap, LinearOpMode opMode) {
        this.opMode = opMode;
        this.hwMap = ahwMap;
        this.init(ahwMap);
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // Define and Initialize Motors
        motorLeftFront = this.hwMap.get(DcMotor.class, "motorLeftFront");
        motorRightFront = this.hwMap.get(DcMotor.class, "motorRightFront");
        motorLeftBack = this.hwMap.get(DcMotor.class, "motorLeftBack");
        motorRightBack = this.hwMap.get(DcMotor.class, "motorRightBack");
     //   liftMotor = this.hwMap.get(DcMotor.class, "liftMotor");
        rightEar = this.hwMap.get(Servo.class, "rightEar");
        leftEar = this.hwMap.get(Servo.class, "leftEar");
        tail = this.hwMap.get(Servo.class, "tail");
        catapult = this.hwMap.get(Servo.class, "catapult");


        // Using REV motors
        motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        motorRightFront.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE);
        motorRightBack.setDirection(DcMotor.Direction.FORWARD);
       // liftMotor.setDirection(DcMotor.Direction.FORWARD);

        leftEar.setPosition(.3);
        rightEar.setPosition(.2);
        tail.setPosition(0);
        catapult.setPosition(0.5);//0 is release, 0.5 is hold

        // Set all motors to zero power
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        //liftMotor.setPower(0);

         //May want to use RUN_USING_ENCODERS if encoders are installed.
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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