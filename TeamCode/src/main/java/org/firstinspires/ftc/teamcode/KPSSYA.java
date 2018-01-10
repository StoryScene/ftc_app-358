package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by kevinwang on 11/15/17.
 */

@Disabled

public class KPSSYA extends LinearOpMode {

    //Declaring all the motors, servos, and sensors.
    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;

    DcMotor lS;
    Servo left;
    Servo right;
    Servo arm;
    ColorSensor color;

    //This function finds the magnitude of the left stick of a gamepad.
    public Double magnitudeLeftStick(Gamepad gamepad) {
        return Math.sqrt(Math.pow(gamepad.left_stick_x, 2) + Math.pow(gamepad.left_stick_y, 2));
    }

    //This function finds the max value given 4 values.
    public Double findMax(Double d1, Double d2, Double d3, Double d4) {
        return Math.max(Math.max(d1, d2), Math.max(d3, d4));
    }

    //Run OpMode code.
    public void runOpMode() throws InterruptedException {

        //Defining where to find the motors, servos, and sensors.
        fL = hardwareMap.dcMotor.get("frontLeft");
        bL = hardwareMap.dcMotor.get("backLeft");
        fR = hardwareMap.dcMotor.get("frontRight");
        bR = hardwareMap.dcMotor.get("backRight");
        lS = hardwareMap.dcMotor.get("linearSlide");
        left = hardwareMap.servo.get("left");
        right = hardwareMap.servo.get("right");
        arm = hardwareMap.servo.get("arm");
        color = hardwareMap.colorSensor.get("color");

        double powerly = gamepad1.left_stick_y;
        double motorly = (((Math.pow(powerly, 2) * powerly / Math.abs(powerly))));
        // EKS DEE
        double powerlx = gamepad1.left_stick_x;
        double motorlx = (((Math.pow(powerlx, 2) * powerlx / Math.abs(powerlx))));
        double powerry = gamepad1.right_stick_y;
        double motorry = (((Math.pow(powerry, 2) * powerry / Math.abs(powerry))));
        double powerrx = gamepad1.right_stick_x;
        double motorrx = (((Math.pow(powerrx, 2) * powerrx / Math.abs(powerrx))));

        //Defining the directions of the motors and servos.
        fL.setDirection(DcMotorSimple.Direction.REVERSE);
        bL.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        //TeleOp process.
        while (opModeIsActive()) {

            //Auto-servo is held in place.
            arm.setPosition(.95);

            //Defining drive, strafe, and rotation power.
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            //Defining the motor power distribution.
            double flPower = motorly - motorlx - motorrx;
            double blPower = motorly + motorlx - motorrx;
            double frPower = motorly + motorlx + motorrx;
            double brPower = motorly - motorlx + motorrx;

            //Defining the joystick magnitude and maximum power.
            double POWER = -1 * Range.clip(Math.max(magnitudeLeftStick(gamepad1), Math.abs(motorrx)), -1, 1);
            double maxPower = findMax(Math.abs(flPower), Math.abs(blPower), Math.abs(frPower), Math.abs(brPower));

            //Sets the power for all the drive motors.


            fL.setPower(POWER * flPower / maxPower);
            bL.setPower(POWER * blPower / maxPower);
            fR.setPower(POWER * frPower / maxPower);
            bR.setPower(POWER * brPower / maxPower);

            if (fL.getPower() == 0 & bL.getPower() == 0 & fR.getPower() == 0 & bR.getPower() == 0) {
                fL.setPower(0.8);
                bL.setPower(0.8);
                fR.setPower(0.8);
                bR.setPower(0.8);
                sleep(250);
                fL.setPower(0.6);
                bL.setPower(0.6);
                fR.setPower(0.6);
                bR.setPower(0.6);
                sleep(250);
                fL.setPower(0.4);
                bL.setPower(0.4);
                fR.setPower(0.4);
                bR.setPower(0.4);
                sleep(250);
                fL.setPower(0.2);
                bL.setPower(0.2);
                fR.setPower(0.2);
                bR.setPower(0.2);
                sleep(250);
                fL.setPower(0);
                bL.setPower(0);
                fR.setPower(0);
                bR.setPower(0);
            }

            //This will lower the UltraLord if the left bumper is pressed and raise if the right is pressed.
            //The height will remain the same if both are pressed or neither are.
            if ((!(gamepad1.right_bumper) && !(gamepad1.left_bumper)) || (gamepad1.right_bumper && gamepad1.left_bumper)) {
                lS.setPower(0);
            } else if (gamepad1.right_bumper) {
                lS.setPower(0.5);
            } else if (gamepad1.left_bumper) {
                lS.setPower(-0.5);
            }

            //This will open the UltraLord if A is pressed down, and open if A is released.
            if (gamepad1.a) {
                left.setPosition(1);
                right.setPosition(1);
            } else if (!gamepad1.a) {
                left.setPosition(0);
                right.setPosition(0);
            }

        }
    }
}