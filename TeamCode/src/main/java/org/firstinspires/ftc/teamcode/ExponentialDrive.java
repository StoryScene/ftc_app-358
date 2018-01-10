package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by lawrencemao on 11/13/17.
 */

@TeleOp

public class ExponentialDrive extends LinearOpMode {

    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;
    DcMotor lS;
    Servo left;
    Servo right;
    Servo arm;
    ColorSensor color;

    public void runOpMode() throws InterruptedException{

        fL = hardwareMap.dcMotor.get("frontLeft");    //2
        bL = hardwareMap.dcMotor.get("backLeft");     //2
        fR = hardwareMap.dcMotor.get("frontRight");   //2
        bR = hardwareMap.dcMotor.get("backRight");    //2
        lS = hardwareMap.dcMotor.get("linearSlide");  //5
        left = hardwareMap.servo.get("left");         //2
        right = hardwareMap.servo.get("right");       //2
        arm = hardwareMap.servo.get("arm");           //5
        color = hardwareMap.colorSensor.get("color"); //5

        fL.setDirection(DcMotor.Direction.REVERSE);
        left.setDirection(Servo.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()) {
            //auto-servo is held in place
            arm.setPosition(.95);

            double POWER = -1 * Range.clip(Math.max(Range.clip(Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2)), -1, 1),
                    Math.abs(gamepad1.right_stick_x)), -1, 1);
            double maxPower = Math.max(Math.max(Math.abs(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x),
                    Math.abs(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x)),
                    Math.max(Math.abs(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x),
                            Math.abs(gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x)));

            fL.setPower(POWER * (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) / maxPower);
            bL.setPower(POWER * (gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) / maxPower);
            fR.setPower(POWER * (gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) / maxPower);
            bR.setPower(POWER * (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) / maxPower);

            lS.setPower(0);

            if(gamepad1.right_bumper){
                lS.setPower(0.5);
            }

            if(gamepad1.left_bumper){
                lS.setPower(-0.5);
            }

            if(gamepad1.a){
                left.setPosition(1);
                right.setPosition(1);
            }
            if(!gamepad1.a){
                left.setPosition(0);
                right.setPosition(0);
            }
        }
    }
}