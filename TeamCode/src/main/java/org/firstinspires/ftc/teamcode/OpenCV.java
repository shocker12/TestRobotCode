//package org.firstinspires.ftc.teamcode;
//
//import android.util.Log;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvWebcam;
////import org.openftc.easyopencv.OpenCvWebcam; until webcam needs to be used
//
//public class OpenCV {
//    OpenCvWebcam webcam;
//    //OpenCvInternalCamera phoneCam;
//    CapstoneDeterminationPipeline2 pipeline;
//    Telemetry telemetry;
//    HardwareInnov8ToyChest toyChest;
//    LinearOpMode opMode;
//    WebcamName webcamName;
//
//
//
//    public OpenCV(Telemetry telemetry, HardwareInnov8ToyChest toyChest, LinearOpMode opMode) {
//
//        this.opMode = opMode;
//        this.toyChest = toyChest;
//        this.telemetry = telemetry;
//        this.telemetry.update();
//        this.pipeline = new CapstoneDeterminationPipeline2();
//        webcamName =this.toyChest.hwMap.get(WebcamName.class, "Webcam 1");
//        int cameraMonitorViewId = toyChest.hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", toyChest.hwMap.appContext.getPackageName());
//        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
//        //phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
//
//
//
//        webcam.setPipeline(pipeline);
//
//        // We set the viewport policy to optimized view so the preview doesn't appear 90 deg
//        // out when the RC activity is in portrait. We do our actual image processing assuming
//        // landscape orientation, though.
//        webcam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
//
//        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened() {
//                webcam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);
//            }
//
//            @Override
//            public void onError(int errorCode) {
//                Log.e("asyncCameraListener", "" + errorCode);
//            }
//        });
// }
//
//    public CapstonePosition getCapstonePosition() {
//        long endTime = System.currentTimeMillis() + 1000;
//        while(System.currentTimeMillis() <= endTime && this.opMode.opModeIsActive()){
//        }
//        Log.d("capstone position value: ", "" + pipeline.position);
//        return pipeline.position;
//    }
//
//
//}
