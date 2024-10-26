package org.firstinspires.ftc.teamcode.pedroPathing.opmodes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.competition.hardware.HWProfile;
import org.firstinspires.ftc.teamcode.competition.libraries.RobotLibrary;

@Autonomous(name = "sampleAuto", group = "Robot")
//@Disabled

public class sampleAuto extends LinearOpMode {
    private HWProfile robot = new HWProfile();
    private final LinearOpMode opMode = this;
    private RobotLibrary lib = new RobotLibrary(robot, opMode);

    public sampleAuto() {

    }

    public void runOpMode() {

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        robot.init(hardwareMap);

        telemetry.addData("Status: ", "Initializing...");
        telemetry.update();

        telemetry.addData("Status: ","Ready.");
        telemetry.update();

        waitForStart();
        telemetry.addData("Status: ","Charge!!");
        telemetry.update();

        //raise arm
        robot.hangMotor.setPower(1);
        robot.hangMotor.setTargetPosition(2200);
        sleep(1000);

        //extend arm
        robot.liftMotor.setPower(1);
        robot.liftMotor.setTargetPosition(1400);
        sleep(1000);
        robot.wrist.setPosition(0.55);


        lib.moveDirection("forward", 0.3);
        sleep(900);
        lib.halt();
        sleep(100);
//        lib.moveDirection("horizontal", 0.5);
        sleep(2000);
        lib.halt();

        //spit out sample
        robot.intake.setPower(1);
        sleep(1000);

        //back up
        lib.moveDirection("backward",-0.3);
        sleep(800);
        lib.halt();

        //retract arm
        robot.liftMotor.setTargetPosition(0);
        sleep(2000);
        robot.hangMotor.setTargetPosition(100);
        sleep(1000);

        requestOpModeStop();
        telemetry.addData("Status: ","Program Completed.");
        telemetry.update();
    }
}
