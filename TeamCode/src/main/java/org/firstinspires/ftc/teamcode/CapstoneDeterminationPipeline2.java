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

public class CapstoneDeterminationPipeline2 extends OpenCvPipeline
{

    /*
     * Some color constants
     */
    static final Scalar RED = new Scalar (0, 255, 0);

    /*
     * The core values which define the location and size of the sample regions
     */
    static final Point RIGHT_REGION_TOPLEFT_ANCHOR_POINT = new Point(150, 40);

    static final int REGION_WIDTH = 20;
    static final int REGION_HEIGHT = 20;


    Point rightRegion_pointA = new Point(
            RIGHT_REGION_TOPLEFT_ANCHOR_POINT.x,
            RIGHT_REGION_TOPLEFT_ANCHOR_POINT.y);
    Point rightRegion_pointB = new Point(
            RIGHT_REGION_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            RIGHT_REGION_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    /*
     * Working variables
     */
    Mat rightRegion_HSV;
    Mat HSV = new Mat();
    Mat Cb = new Mat();

    int hueAvg;
    int satAvg;
    int valAvg;
    int max;

    // Volatile since accessed by OpMode thread w/o synchronization
    public volatile CapstonePosition position = CapstonePosition.TALL;

    /*
     * This function takes the RGB frame, converts to YCrCb,
     * and extracts the Cb channel to the 'Cb' variable
     */
    void inputToHSV(Mat input)
    {
        Imgproc.cvtColor(input, HSV, Imgproc.COLOR_RGB2HSV);
    }

    @Override
    public void init(Mat firstFrame)
    {
        inputToHSV(firstFrame);

        rightRegion_HSV = HSV.submat(new Rect(rightRegion_pointA, rightRegion_pointB));
    }

    @Override
    public Mat processFrame(Mat input)
    {
        inputToHSV(input);

        hueAvg = (int) Core.mean(rightRegion_HSV).val[0];
        Log.d("Right block value: ", "H: " + hueAvg);

        satAvg = (int) Core.mean(rightRegion_HSV).val[1];
        Log.d("Right block value: ", "S: " + satAvg);

        valAvg = (int) Core.mean(rightRegion_HSV).val[2];
        Log.d("Right block value: ", "V: " + valAvg);

        Imgproc.rectangle(
                input, // Buffer to draw on
                rightRegion_pointA, // First point which defines the rectangle
                rightRegion_pointB, // Second point which defines the rectangle
                RED, // The color the rectangle is drawn in
                2); // Thickness of the rectangle lines

        this.position = CapstonePosition.TALL; // Record our analysis
        Log.d("Pipeline check", "pipeline is running");

        if(hueAvg < 20){
            this.position = CapstonePosition.SMALL;//parking spot 3
            Log.d("CapstonePosition:", "SMALL");
            Log.d("CapstonePosition:value", "hueAvg: "+ hueAvg);
        }
        else if(hueAvg > 100){//parking spot 2
            this.position = CapstonePosition.MEDIUM;
            Log.d("CapstonePosition:", "MEDIUM");
            Log.d("CapstonePosition:value", "hueAvg: "+ hueAvg);
        }
        else{

            this.position = CapstonePosition.TALL;//parking spot 1
            Log.d("CapstonePosition:", "TALL");
            Log.d("CapstonePosition:value", "hueAvg: "+ hueAvg);
        }




        Log.d("right value: ","  " + hueAvg);


        Imgproc.rectangle(
                input, // Buffer to draw on
                rightRegion_pointA, // First point which defines the rectangle
                rightRegion_pointB, // Second point which defines the rectangle
                RED, // The color the rectangle is drawn in
                -1); // Negative thickness means solid fill

        return input;
    }
}
