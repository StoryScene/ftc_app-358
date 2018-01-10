package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;
import static java.lang.Math.*;

/**
 * Created by lawrencemao on 12/7/17.
 */

@TeleOp

public class FinalMecanumDrive extends LinearOpMode {

    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;
    DcMotor lS;

    //This function finds the magnitude of the left stick of a gamepad.
    public Double magnitudeLeftStick(Gamepad gamepad){
        return sqrt(pow(gamepad.left_stick_x, 2) + pow(gamepad.left_stick_y, 2));
    }

    //This function finds the max value given 4 values.
    public Double findMax(Double d1, Double d2, Double d3, Double d4){
        return max(max(d1, d2), max(d3, d4));
    }

    public void runOpMode() throws InterruptedException{

        fL = hardwareMap.dcMotor.get("frontLeft");    //EH2 - 1
        bL = hardwareMap.dcMotor.get("backLeft");     //EH2 - 2
        fR = hardwareMap.dcMotor.get("frontRight");   //EH2 - 0
        bR = hardwareMap.dcMotor.get("backRight");    //EH2 - 3

        fL.setDirection(DcMotor.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()) {

            //Defining drive, strafe, and rotation power.
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            //Defining the motor power distribution.
            double flPower = drive - strafe - rotate;
            double blPower = drive + strafe - rotate;
            double frPower = drive + strafe + rotate;
            double brPower = drive - strafe + rotate;

            //Defining the joystick magnitude and maximum power.
            //double POWER = -1 * pow(Range.clip(max(magnitudeLeftStick(gamepad1), abs(rotate)), -1, 1), 3) / (0.5 * pow(gamepad1.right_trigger, 2) + 1);
            double joyStick = Range.clip(max(magnitudeLeftStick(gamepad1), abs(rotate)), -1, 1);
            double POWER = -1 * joyStick * abs(joyStick);
            telemetry.addData("POWER: ", POWER);
            double maxPower = findMax(abs(flPower), abs(blPower), abs(frPower), abs(brPower)); // greatest value of all motor powers
            telemetry.addData("maxPower: ", maxPower);
            telemetry.update();

            //Sets the power for all the drive motors.
            fL.setPower(POWER * flPower / maxPower);
            bL.setPower(POWER * blPower / maxPower);
            fR.setPower(POWER * frPower / maxPower);
            bR.setPower(POWER * brPower / maxPower);

        }
    }
}