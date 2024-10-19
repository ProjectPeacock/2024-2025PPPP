
package org.firstinspires.ftc.teamcode.pedroPathing.opmodes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.competition.hardware.HWProfile;
import org.firstinspires.ftc.teamcode.competition.libraries.RobotLibrary;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierPoint;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.tuning.FollowerConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.robotcore.external.Telemetry.Log;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.util.Timer;





import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.competition.hardware.HWProfile;
import org.firstinspires.ftc.teamcode.competition.libraries.RobotLibrary;

@Autonomous(name = "leftAuto", group = "Robot")
@Disabled

public class lefttest extends LinearOpMode {
    private HWProfile robot = new HWProfile();
    private final LinearOpMode opMode = this;
    private RobotLibrary lib = new RobotLibrary(robot, opMode);
    private Follower follower;
    public lefttest() {}
    private Pose startPose = new Pose(7.6, 87.1, Math.toRadians(90));
    private Pose basketPose = new Pose(8.68, 136, Math.toRadians(90));
    private Pose rightSamplePose = new Pose(44.6, 121.4, Math.toRadians(-23));
    private Pose B1 = new Pose(6.5, 138.9, Math.toRadians(156));
    private Pose midSP = new Pose(42.54, 132, Math.toRadians(-10));
    private Pose B2 = new Pose(6.7, 136, Math.toRadians(172));
    private Pose lSP = new Pose(45.65, 141.46, Math.toRadians(6));
    private Pose B3 = new Pose (7.8, 137, Math.toRadians(-174));
    private Pose parkPrep = new Pose(65.489, 104, Math.toRadians(-30));
    private Pose park = new Pose(66.1, 87, Math.toRadians(-90));




    public void runOpMode() {

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
        follower = new Follower(hardwareMap);

        robot.init(hardwareMap);

        telemetry.addData("Status: ", "Initializing...");
        telemetry.update();

        telemetry.addData("Status: ", "Ready.");
        telemetry.update();

        waitForStart();
        telemetry.addData("Status: ", "Charge!!");
        telemetry.update();
        class GeneratedPath {

            public void GOPose() {
                String PathBuilder = null;
                switch (PathBuilder) {
                    default:
                    case "Go":

                        PathBuilder builder = new PathBuilder();

                        builder
                                .addPath(
                                        // Line 1
                                        new BezierLine(
                                                new Point(7.610, 87.610, Point.CARTESIAN),
                                                new Point(8.585, 136.780, Point.CARTESIAN)
                                        )
                                )
                                .setTangentHeadingInterpolation()
                                .addPath(
                                        // Line 2
                                        new BezierLine(
                                                new Point(8.585, 136.780, Point.CARTESIAN),
                                                new Point(44.683, 121.366, Point.CARTESIAN)
                                        )
                                )
                                .setTangentHeadingInterpolation()
                                .addPath(
                                        // Line 3
                                        new BezierLine(
                                                new Point(44.683, 121.366, Point.CARTESIAN),
                                                new Point(6.244, 138.732, Point.CARTESIAN)
                                        )
                                )
                                .setTangentHeadingInterpolation()
                                .addPath(
                                        // Line 4
                                        new BezierLine(
                                                new Point(6.244, 138.732, Point.CARTESIAN),
                                                new Point(45.268, 131.512, Point.CARTESIAN)
                                        )
                                )
                                .setTangentHeadingInterpolation()
                                .addPath(
                                        // Line 5
                                        new BezierLine(
                                                new Point(45.268, 131.512, Point.CARTESIAN),
                                                new Point(5.659, 136.976, Point.CARTESIAN)
                                        )
                                )
                                .setTangentHeadingInterpolation()
                                .addPath(
                                        // Line 6
                                        new BezierLine(
                                                new Point(5.659, 136.976, Point.CARTESIAN),
                                                new Point(45.659, 141.463, Point.CARTESIAN)
                                        )
                                )
                                .setTangentHeadingInterpolation()
                                .addPath(
                                        // Line 7
                                        new BezierLine(
                                                new Point(45.659, 141.463, Point.CARTESIAN),
                                                new Point(7.220, 137.756, Point.CARTESIAN)
                                        )
                                )
                                .setTangentHeadingInterpolation()
                                .addPath(
                                        // Line 8
                                        new BezierLine(
                                                new Point(7.220, 137.756, Point.CARTESIAN),
                                                new Point(65.561, 104.000, Point.CARTESIAN)
                                        )
                                )
                                .setTangentHeadingInterpolation()
                                .addPath(
                                        // Line 9
                                        new BezierLine(
                                                new Point(65.561, 104.000, Point.CARTESIAN),
                                                new Point(66.146, 87.024, Point.CARTESIAN)
                                        )
                                )
                                .setTangentHeadingInterpolation();
                }

            }


        }}}