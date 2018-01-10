package org.firstinspires.ftc.teamcode;

/**
 * Created by lawrencemao on 11/15/17.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 *  This current autonomous will knock off the jewel (30 points),
 *  Decipher the pictograph and place the block in the respective column (45 points),
 *  Drive into the safe zone (10 points)
 *  For a total of 85 points (the maximum possible)
 */

@Disabled

public class AutRedHelper extends LinearOpMode {

    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;
    DcMotor lS;
    Servo left;
    Servo right;
    Servo arm;
    ColorSensor color;
    VuforiaLocalizer vuforia;

    double dPosition = 0.3; // deployed position
    double oPosition = 1; // original (upright) position

    public void runOpMode() throws InterruptedException {

        fL = hardwareMap.dcMotor.get("frontLeft");
        bL = hardwareMap.dcMotor.get("backLeft");
        fR = hardwareMap.dcMotor.get("frontRight");
        bR = hardwareMap.dcMotor.get("backRight");
        lS = hardwareMap.dcMotor.get("linearSlide");
        left = hardwareMap.servo.get("left");
        right = hardwareMap.servo.get("right");
        arm = hardwareMap.servo.get("arm");
        color = hardwareMap.colorSensor.get("color");

        fL.setDirection(DcMotor.Direction.REVERSE);
        left.setDirection(Servo.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

        double POWER = .5;

        arm.setPosition(dPosition);
        left.setPosition(0);
        right.setPosition(0);

        waitForStart();

        while (opModeIsActive()) {

            if (color.blue()/2 > color.red()) {
                fL.setPower(POWER);
                bL.setPower(POWER);
                fR.setPower(POWER);
                bR.setPower(POWER);
                sleep(200);

                arm.setPosition(oPosition);
                sleep(200);

                fL.setPower(POWER);
                bL.setPower(POWER);
                fR.setPower(POWER);
                bR.setPower(POWER);
                sleep(1000);
            }

            if (color.blue() < color.red()/2) {
                fL.setPower(-POWER);
                bL.setPower(-POWER);
                fR.setPower(-POWER);
                bR.setPower(-POWER);
                sleep(200);

                fL.setPower(POWER);
                bL.setPower(POWER);
                fR.setPower(POWER);
                bR.setPower(POWER);
                arm.setPosition(oPosition);
                sleep(1000);
            }
            break;
        }
    }
}