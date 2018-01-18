package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static java.lang.Math.*;

/**
 * Created by lawrencemao on 11/7/17.
 */

@TeleOp

public class TeleOp_358 extends LinearOpMode {

    //Declaring drive motors
    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;

    DcMotor lS;
    DcMotor retract;
    DcMotor release;
    Servo left;
    Servo right;
    Servo arm;
    ColorSensor color;

    //This function finds the magnitude of the left stick of a gamepad.
    public Double magnitudeLeftStick(Gamepad gamepad){
        return sqrt(pow(gamepad.left_stick_x, 2) + pow(gamepad.left_stick_y, 2));
    }

    //This function finds the max value given 4 values.
    public Double findMax(Double d1, Double d2, Double d3, Double d4){
        return max(max(d1, d2), max(d3, d4));
    }

    public void runOpMode() throws InterruptedException{

        //Defining drive motors
        fL = hardwareMap.dcMotor.get("frontLeft");    //EH2 - 1
        bL = hardwareMap.dcMotor.get("backLeft");     //EH2 - 2
        fR = hardwareMap.dcMotor.get("frontRight");   //EH2 - 0
        bR = hardwareMap.dcMotor.get("backRight");    //EH2 - 3

        //Attempting to run the motors with encoders
        fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Settings the directions on the left side motors
        fL.setDirection(DcMotor.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

        lS = hardwareMap.dcMotor.get("linearSlide");  //EH5 - 0
        left = hardwareMap.servo.get("left");         //EH2 - 0
        right = hardwareMap.servo.get("right");       //EH2 - 1
        arm = hardwareMap.servo.get("arm");           //EH5 - 0
        color = hardwareMap.colorSensor.get("color"); //EH5 - 0
        retract = hardwareMap.dcMotor.get("retract"); //EH5 - 1
        release = hardwareMap.dcMotor.get("release"); //EH5 - 2

        left.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()) {

            //auto-servo is held in place
            arm.setPosition(.95);

            //Exponential drive code
            TeleOp_Drive_Code.TeleOpDrive(gamepad1, fL, bL, fR, bR);

            //Controls rack and pinion
            //lS ... lift system motor speed
            lS.setPower(0);

            if(gamepad2.right_bumper){
                lS.setPower(-0.5);
            }

            if(gamepad2.left_bumper){
                lS.setPower(0.5);
            }

            //Controls UltraLord servos
            if(gamepad2.a){
                left.setPosition(1);
                right.setPosition(1);
            }

            if(!gamepad2.a){
                left.setPosition(0);
                right.setPosition(0);
            }

            // default to zero, unless button pressed
            release.setPower(0);
            retract.setPower(0);

            //Controls motors for Linear Slides
            if(gamepad2.dpad_up){
                release.setPower(-0.6);
                retract.setPower(-0.2);
            }

            if(gamepad2.dpad_down){
                retract.setPower(0.4);
                release.setPower(0.3);
            }

        }
    }
}