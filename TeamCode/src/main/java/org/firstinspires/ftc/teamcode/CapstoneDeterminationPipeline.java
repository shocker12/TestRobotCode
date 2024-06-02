//package org.firstinspires.ftc.teamcode;
//
//import android.util.Log;
//
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;
//import org.openftc.easyopencv.OpenCvPipeline;
//
//
//public class CapstoneDeterminationPipeline extends OpenCvPipeline
//{
//
//    /*
//     * Some color constants
//     */
//    static final Scalar BLUE = new Scalar(0, 0, 255);
//    static final Scalar GREEN = new Scalar(0, 255, 0);
//    static final Scalar RED = new Scalar(255, 0, 0);
//
//    /*
//     * The core values which define the location and size of the sample regions
//     */
//    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(105,0100);
//    static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point (105, 150);
//
//    static final int REGION_WIDTH = 15;
//    static final int REGION_HEIGHT = 15;
//
//    Point region1_pointA = new Point(
//            REGION1_TOPLEFT_ANCHOR_POINT.x,
//            REGION1_TOPLEFT_ANCHOR_POINT.y);
//    Point region1_pointB = new Point(
//            REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
//            REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
//    Point region3_pointA = new Point(
//            REGION3_TOPLEFT_ANCHOR_POINT.x,
//            REGION3_TOPLEFT_ANCHOR_POINT.y);
//    Point region3_pointB = new Point(
//            REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
//            REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
//
//    /*
//     * Working variables
//     */
//    Mat region1_TholdMat;
//    Mat region3_TholdMat;
//    Mat YCrCb = new Mat();
//    Mat Cb = new Mat();
//    Mat TholdMat = new Mat();
//    int rightBox;
//    int midBox;
//
//    // Volatile since accessed by OpMode thread w/o synchronization
//    public volatile CapstonePosition position = CapstonePosition.MIDDLE;
//
//    /*
//     * This function takes the RGB frame, converts to YCrCb,
//     * and extracts the Cb channel to the 'Cb' variable
//     */
//
//    void inputToCb(Mat input)
//    {
//        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_BGR2YCrCb);
//        Core.extractChannel(YCrCb, Cb, 2);
//        Imgproc.threshold(Cb, TholdMat, 70, 255, Imgproc.THRESH_BINARY);
//
//    }
//
//
//
//    @Override
//    public Mat processFrame(Mat input)
//    {
//        inputToCb(input);
//
//        int rightBoxX = (int) (REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH)/2;
//        int rightBoxY = (int) (REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT)/2;
//        int midBoxX = (int) (REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH)/2;
//        int midBoxY = (int) (REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT)/2;
//
//        double[] rightBoxPointValues = TholdMat.get(rightBoxY, rightBoxX);
//        double[] midBoxPointValues = TholdMat.get(midBoxY, midBoxX);
//
//        rightBox = (int) rightBoxPointValues[0];
//        midBox = (int) midBoxPointValues[0];
//        Log.d("Capstone position values: ", "right: " + rightBox + "     mid: " + midBox);
//
//        if(rightBox == 0){
//            position = CapstonePosition.RIGHT;
//        }
//        else if (midBox == 0){
//            position = CapstonePosition.MIDDLE;
//        }
//        else{
//            position = CapstonePosition.LEFT;
//        }
//        Log.d("Capstone position: ", "" + position);
//        Imgproc.rectangle(
//                input, // Buffer to draw on
//                region1_pointA, // First point which defines the rectangle
//                region1_pointB, // Second point which defines the rectangle
//                BLUE, // The color the rectangle is drawn in
//                2); // Thickness of the rectangle lines
//
//        Imgproc.rectangle(
//                input, // Buffer to draw on
//                region3_pointA, // First point which defines the 3rd rectangle
//                region3_pointB, // Second point which defines the rectangle
//                BLUE, // The color the rectangle is drawn in
//                2); // Thickness of the rectangle lines
//
//        Log.d("Pipeline check", "pipeline is running");
//
//
//        Imgproc.rectangle(
//                input, // Buffer to draw on
//                region1_pointA, // First point which defines the rectangle
//                region1_pointB, // Second point which defines the rectangle
//                GREEN, // The color the rectangle is drawn in
//                -1); // Negative thickness means solid fill
//        Imgproc.rectangle(
//                input, // Buffer to draw on
//                region3_pointA, // First point which defines the rectangle
//                region3_pointB, // Second point which defines the rectangle
//                RED, // The color the rectangle is drawn in
//                -1); // Negative thickness means solid fill
//
//        return input;
//    }
//
//    public CapstonePosition getAnalysis()
//    {
//        return position;
//    }
//}

package org.firstinspires.ftc.teamcode;

import android.util.Log;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class CapstoneDeterminationPipeline extends OpenCvPipeline
{

    /*
     * Some color constants
     */
    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar GREEN = new Scalar(0, 255, 0);
    static final Scalar RED = new Scalar (255, 0, 0);

    /*
     * The core values which define the location and size of the sample regions
     */
    static final Point MID_REGION_TOPLEFT_ANCHOR_POINT = new Point(150,160); //for non genz phone: x: 125, y: 135
    static final Point LEFT_REGION_TOPLEFT_ANCHOR_POINT = new Point (150, 285);
    static final Point RIGHT_REGION_TOPLEFT_ANCHOR_POINT = new Point(150, 40);

    static final int REGION_WIDTH = 35;
    static final int REGION_HEIGHT = 30;


    Point midRegion_pointA = new Point(
            MID_REGION_TOPLEFT_ANCHOR_POINT.x,
            MID_REGION_TOPLEFT_ANCHOR_POINT.y);
    Point midRegion_pointB = new Point(
            MID_REGION_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            MID_REGION_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    Point leftRegion_pointA = new Point(
            LEFT_REGION_TOPLEFT_ANCHOR_POINT.x,
            LEFT_REGION_TOPLEFT_ANCHOR_POINT.y);
    Point leftRegion_pointB = new Point(
            LEFT_REGION_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            LEFT_REGION_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    Point rightRegion_pointA = new Point(
            RIGHT_REGION_TOPLEFT_ANCHOR_POINT.x,
            RIGHT_REGION_TOPLEFT_ANCHOR_POINT.y);
    Point rightRegion_pointB = new Point(
            RIGHT_REGION_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            RIGHT_REGION_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    /*
     * Working variables
     */
    Mat midRegion_Cb;
    Mat leftRegion_Cb;
    Mat rightRegion_Cb;
    Mat YCrCb = new Mat();
    Mat Cb = new Mat();

    int avg1;
    int avg2;
    int avg3;
    int max;

    // Volatile since accessed by OpMode thread w/o synchronization
    public volatile CapstonePosition position = CapstonePosition.TALL;

    /*
     * This function takes the RGB frame, converts to YCrCb,
     * and extracts the Cb channel to the 'Cb' variable
     */
    void inputToCb(Mat input)
    {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_BGR2YCrCb);
        Core.extractChannel(YCrCb, Cb, 2);
    }

    @Override
    public void init(Mat firstFrame)
    {
        inputToCb(firstFrame);

        midRegion_Cb = Cb.submat(new Rect(midRegion_pointA, midRegion_pointB));
        leftRegion_Cb = Cb.submat(new Rect(leftRegion_pointA, leftRegion_pointB));
        rightRegion_Cb = Cb.submat(new Rect(rightRegion_pointA, rightRegion_pointB));
    }

    @Override
    public Mat processFrame(Mat input)
    {
        inputToCb(input);

        avg1 = (int) Core.mean(midRegion_Cb).val[0];
        avg2 = (int) Core.mean(leftRegion_Cb).val[0];
        avg3 = (int) Core.mean(rightRegion_Cb).val[0];
        Log.d("Mid block value: ", "" + avg1);
        Log.d("Left block value: ", "" + avg2);
        Log.d("Right block value: ", "" + avg3);
        Imgproc.rectangle(
                input, // Buffer to draw on
                midRegion_pointA, // First point which defines the rectangle
                midRegion_pointB, // Second point which defines the rectangle
                BLUE, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines

        Imgproc.rectangle(
                input, // Buffer to draw on
                leftRegion_pointA, // First point which defines the rectangle
                leftRegion_pointB, // Second point which defines the rectangle
                BLUE, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines

        Imgproc.rectangle(
                input, // Buffer to draw on
                rightRegion_pointA, // First point which defines the rectangle
                rightRegion_pointB, // Second point which defines the rectangle
                BLUE, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines

        this.position = CapstonePosition.TALL; // Record our analysis
        Log.d("Pipeline check", "pipeline is running");

        if(avg3 < 125){
            this.position = CapstonePosition.SMALL;//parking spot 1
            Log.d("CapstonePosition:", "SMALL");
            Log.d("CapstonePosition:value", "avg3: "+avg3);
        }
        else if(avg3 > 165){//parking spot 3
            this.position = CapstonePosition.TALL;
            Log.d("CapstonePosition:", "TALL");
            Log.d("CapstonePosition:value", "avg3: "+avg3);
        }
        else{

            this.position = CapstonePosition.MEDIUM;//parking spot 2
            Log.d("CapstonePosition:", "MEDIUM");
            Log.d("CapstonePosition:value", "avg3: "+avg3);
        }




        Log.d("middle, left, right values: ", avg1 + " , " + avg2 + " , " + avg3);


        Imgproc.rectangle(
                input, // Buffer to draw on
                midRegion_pointA, // First point which defines the rectangle
                midRegion_pointB, // Second point which defines the rectangle
                GREEN, // The color the rectangle is drawn in
                -1); // Negative thickness means solid fill

        Imgproc.rectangle(
                input, // Buffer to draw on
                leftRegion_pointA, // First point which defines the rectangle
                leftRegion_pointB, // Second point which defines the rectangle
                BLUE, // The color the rectangle is drawn in
                -1); // Negative thickness means solid fill

        Imgproc.rectangle(
                input, // Buffer to draw on
                rightRegion_pointA, // First point which defines the rectangle
                rightRegion_pointB, // Second point which defines the rectangle
                RED, // The color the rectangle is drawn in
                -1); // Negative thickness means solid fill

        return input;
    }
}
