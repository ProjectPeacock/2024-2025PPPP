
package org.firstinspires.ftc.teamcode.pedroPathing.opmodes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
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
import org.firstinspires.ftc.teamcode.pedroPathing.Enums.AutoManager;
import org.firstinspires.ftc.teamcode.pedroPathing.Enums.AutoLocation;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.competition.hardware.HWProfile;
import org.firstinspires.ftc.teamcode.competition.libraries.RobotLibrary;

@Autonomous(name = "leftAuto", group = "Robot")
//Disabled

public class lefttest extends LinearOpMode {
    private HWProfile robot = new HWProfile();
    private final LinearOpMode opMode = this;
    private RobotLibrary lib = new RobotLibrary(robot, opMode);
    private Follower follower;

    private AutoManager autoManager;
    private int autoState = 1;
    private int nextAutoState = 1;




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

         int PathBuilder = 1;

         autoManager.setSpeed(HWProfile.AUTO_MAX_SPEED);

            while (opMode.opModeIsActive()){
                switch (PathBuilder) {
                    case 1:
                        if(!autoManager.isBusy()) {
                            autoManager.runPath(autoManager.toBasketPath);

                            autoState = 2;
                        }
                            break;
                    case 2:
                        if(!autoManager.isBusy()){
                            autoManager.runPath((autoManager.basketScorePath));
                            autoState = 3;

                        }
                        break;



        }}}}