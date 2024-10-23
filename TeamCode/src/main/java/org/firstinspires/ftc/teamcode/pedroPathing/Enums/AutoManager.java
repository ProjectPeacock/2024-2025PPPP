package org.firstinspires.ftc.teamcode.pedroPathing.Enums;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierPoint;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;


public class AutoManager {
    public Pose startPose = new Pose(7.6, 87.1, Math.toRadians(90));
    public Path basketPose;
    public Path rightSamplePose;
    public Path midSP;
    public Pose lSP;
    public Path parkPrep;
    public Path park;
    public Path basketScorePath;
    private Follower follower;
    private boolean pathingDisabled;
    public Path toBasketPath;
    public Path backupPath;
    private Point leftParkLocation = new Point(64,89,Point.CARTESIAN);
    private Point leftScoreLocation = new Point(20,110,Point.CARTESIAN);






    public AutoManager(Follower _follower) {
        follower = _follower;
    }

    public void runPath(Path path) {
        if(!pathingDisabled) follower.followPath(path);
    }

    public void update() {
        follower.update();
    }

    public boolean isBusy() {
        return follower.isBusy();
    }

    public void disablePathing(boolean disabled) {
        pathingDisabled = disabled;
    }

    public void holdCurrentPoint() {
        BezierPoint currentPoint = new BezierPoint(follower.getCurrentPath().getLastControlPoint());
        follower.holdPoint(currentPoint, follower.getCurrentPath().getPathEndHeadingConstraint());
    }

    public void setSpeed(double speed) {
        follower.setMaxPower(speed);
    }

    public void buildPaths(AutoLocation autoLocation) {
        if(autoLocation == AutoLocation.LEFT_SIMPLE) {
            basketPose = new Path(
                    new BezierLine(
                            new Point(7.976, 83.819, Point.CARTESIAN),
                            new Point(25, 97.8, Point.CARTESIAN)
                    )
            );

            toBasketPath.setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(120));


            basketScorePath = new Path(
                    new BezierLine(
                            toBasketPath.getLastControlPoint(),
                            leftScoreLocation
                    )
            );

            basketScorePath.setConstantHeadingInterpolation(Math.toRadians(140));
//            bucketScorePath.setLinearHeadingInterpolation(toBucketPath.getPathEndHeadingConstraint(), Math.toRadians(150));

            backupPath = new Path(
                    new BezierLine(
                            basketScorePath.getLastControlPoint(),
                            new Point(25, 95, Point.CARTESIAN)
                    )
            );

            backupPath.setConstantHeadingInterpolation(Math.toRadians(140));

            park = new Path(
                    new BezierCurve(
                            backupPath.getLastControlPoint(),
                            new Point(85, 125, Point.CARTESIAN),
                            leftParkLocation
                    )
            );

            park.setConstantHeadingInterpolation(Math.toRadians(0));
        } else if(autoLocation == AutoLocation.LEFT_SCORE_GOLD) {
            toBasketPath = new Path(
                    new BezierLine(
                            new Point(7.976, 83.819, Point.CARTESIAN),
                            new Point(25, 97.8, Point.CARTESIAN)
                    )
            );

            toBasketPath.setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(120));


            basketScorePath = new Path(
                    new BezierLine(
                            toBasketPath.getLastControlPoint(),
                            leftScoreLocation
                    )
            );

            basketScorePath.setConstantHeadingInterpolation(Math.toRadians(140));
//            bucketScorePath.setLinearHeadingInterpolation(toBucketPath.getPathEndHeadingConstraint(), Math.toRadians(150));

            backupPath = new Path(
                    new BezierLine(
                            basketScorePath.getLastControlPoint(),
                            new Point(25, 95, Point.CARTESIAN)
                    )
            );

            backupPath.setConstantHeadingInterpolation(Math.toRadians(140));

            rightSamplePose = new Path(
                    new BezierLine(
                            backupPath.getLastControlPoint(),
                            new Point(25, 110, Point.CARTESIAN)
                    )
            );

            rightSamplePose.setConstantHeadingInterpolation(Math.toRadians(0));

            park = new Path(
                    new BezierCurve(
                            backupPath.getLastControlPoint(),
                            new Point(85, 125, Point.CARTESIAN),
                            leftParkLocation
                    )
            );

            park.setConstantHeadingInterpolation(Math.toRadians(0));
        }


    }
}