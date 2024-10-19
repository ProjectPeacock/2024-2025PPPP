package org.firstinspires.ftc.teamcode.competition.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HWProfile {

    /* Declare OpMode members. */
    public DcMotorEx  leftFrontDrive   = null; //the left drivetrain motor
    public DcMotorEx  rightFrontDrive  = null; //the right drivetrain motor
    public DcMotorEx  leftBackDrive    = null;
    public DcMotorEx  rightBackDrive   = null;
   // public DcMotor  armMotor         = null; //the arm motor
    public DcMotor  liftMotor        = null; //
    public DcMotor  hangMotor        = null;
    public CRServo  intake           = null; //the active intake servo
    public Servo    wrist            = null; //the wrist servo

    public Servo    intakeRotate1    = null;
    public Servo    intakeRotate2    = null;




    /* This constant is the number of encoder ticks for each degree of rotation of the arm.
    To find this, we first need to consider the total gear reduction powering our arm.
    First, we have an external 20t:100t (5:1) reduction created by two spur gears.
    But we also have an internal gear reduction in our motor.
    The motor we use for this arm is a 117RPM Yellow Jacket. Which has an internal gear
    reduction of ~50.9:1. (more precisely it is 250047/4913:1)
    We can multiply these two ratios together to get our final reduction of ~254.47:1.
    The motor's encoder counts 28 times per rotation. So in total you should see about 7125.16
    counts per rotation of the arm. We divide that by 360 to get the counts per degree. */
    public final double ARM_TICKS_PER_DEGREE =
            28 // number of encoder ticks per rotation of the bare motor
                    * 250047.0 / 4913.0 // This is the exact gear ratio of the 50.9:1 Yellow Jacket gearbox
                    * 100.0 / 20.0 // This is the external gear reduction, a 20T pinion gear that drives a 100T hub-mount gear
                    * 1/360.0; // we want ticks per degree, not per rotation


    /* These constants hold the position that the arm is commanded to run to.
    These are relative to where the arm was located when you start the OpMode. So make sure the
    arm is reset to collapsed inside the robot before you start the program.

    In these variables you'll see a number in degrees, multiplied by the ticks per degree of the arm.
    This results in the number of encoder ticks the arm needs to move in order to achieve the ideal
    set position of the arm. For example, the ARM_SCORE_SAMPLE_IN_LOW is set to
    160 * ARM_TICKS_PER_DEGREE. This asks the arm to move 160° from the starting position.
    If you'd like it to move further, increase that number. If you'd like it to not move
    as far from the starting position, decrease it. */

    public final double ARM_COLLAPSED_INTO_ROBOT  = 10;
    public final double ARM_COLLECT               = 0 * ARM_TICKS_PER_DEGREE;
    public final int ARM_CLEAR_BARRIER         = 200;
    public final double ARM_EXTENSION_ANGLE = 500;
//    public final double ARM_CLEAR_BARRIER         = 15 * ARM_TICKS_PER_DEGREE;
    public final int ARM_SCORE_SPECIMEN        = 300;
//    public final double ARM_SCORE_SPECIMEN        = 90 * ARM_TICKS_PER_DEGREE;
    public final double ARM_SCORE_SAMPLE_IN_LOW   = 90 * ARM_TICKS_PER_DEGREE;
    public final double ARM_ATTACH_HANGING_HOOK   = 110 * ARM_TICKS_PER_DEGREE;
    public final double ARM_WINCH_ROBOT           = 10  * ARM_TICKS_PER_DEGREE;
    public final int ARM_HIGH_SCORE           = 700;

    /* Variables to store the speed the intake servo should be set at to intake, and deposit game elements. */
    public final double INTAKE_COLLECT    = -1.0;
    public final double INTAKE_OFF        =  0.0;
    public final double INTAKE_DEPOSIT    =  0.5;

    // Variables for intake rotation positions
    public final double INTAKE_ROTATION_MULTIPLIER = 0.3;

    public final double INTAKE_R1_P1 = 0;
    public final double INTAKE_R1_P2 = 0 + INTAKE_ROTATION_MULTIPLIER;
    public final double INTAKE_R2_P1 = 1;
    public final double INTAKE_R2_P2 = 1 - INTAKE_ROTATION_MULTIPLIER;

    /* Variables to store the positions that the wrist should be set to when folding in, or folding out. */
    public final double WRIST_FOLDED_IN   = 0.1667;
    public final double WRIST_FOLDED_OUT  = 0.5;

    /* A number in degrees that the triggers can adjust the arm position by */
    public final double FUDGE_FACTOR = 15 * ARM_TICKS_PER_DEGREE;



    public final double LIFT_TICKS_PER_MM = (111132.0 / 289.0) / 120.0;

    public final double LIFT_COLLAPSED = 0 * LIFT_TICKS_PER_MM;
    public final double LIFT_SCORING_IN_LOW_BASKET = 0 * LIFT_TICKS_PER_MM;
    public final double LIFT_SCORING_IN_HIGH_BASKET = 480 * LIFT_TICKS_PER_MM;

    public HWProfile() {

    }

    public void init(HardwareMap hwmap) {

        /* Define and Initialize Motors */
        leftFrontDrive  =  hwmap.get(DcMotorEx.class, "frontLeftMotor");
        leftBackDrive   = hwmap.get(DcMotorEx.class, "backLeftMotor");
        rightFrontDrive = hwmap.get(DcMotorEx.class, "frontRightMotor");
        rightBackDrive  = hwmap.get(DcMotorEx.class, "backRightMotor");
        liftMotor       = hwmap.dcMotor.get("liftMotor");
        //armMotor        = hwmap.get(DcMotor.class, "left_arm"); //the arm motor
        hangMotor       = hwmap.dcMotor.get("hangMotor");
        intakeRotate1   = hwmap.servo.get("intakeR1");
        intakeRotate2   = hwmap.servo.get("intakeR2");

       /*
       we need to reverse the left side of the drivetrain so it doesn't turn when we ask all the
       drive motors to go forward.
        */

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        /* Setting zeroPowerBehavior to BRAKE enables a "brake mode". This causes the motor to slow down
        much faster when it is coasting. This creates a much more controllable drivetrain. As the robot
        stops much quicker. */
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangMotor.setTargetPosition(0);
        hangMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        /*This sets the maximum current that the control hub will apply to the arm before throwing a flag */
        //((DcMotorEx) armMotor).setCurrentAlert(5,CurrentUnit.AMPS);


        /* Before starting the armMotor. We'll make sure the TargetPosition is set to 0.
        Then we'll set the RunMode to RUN_TO_POSITION. And we'll ask it to stop and reset encoder.
        If you do not have the encoder plugged into this motor, it will not run in this code. */
        //armMotor.setTargetPosition(0);
        //armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        liftMotor.setTargetPosition(0);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        /* Define and initialize servos.*/
        intake = hwmap.get(CRServo.class, "intake");
        wrist  = hwmap.get(Servo.class, "wrist");
        intakeRotate1 = hwmap.get(Servo.class, "intakeR1");
        intakeRotate2 = hwmap.get(Servo.class, "intakeR2");

        /* Make sure that the intake is off, and the wrist is folded in. */
        intake.setPower(INTAKE_OFF);
        wrist.setPosition(WRIST_FOLDED_OUT);

        // Initializing positions for intake rotation servos
        intakeRotate1.setPosition(0.5);
        intakeRotate2.setPosition(.5);

    }
}
