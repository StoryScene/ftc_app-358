package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.sun.tools.javac.code.Attribute;

/**
 * Created by kevinwang on 11/6/17.
 */

@TeleOp(name = "TeleOp Encoders")
@Disabled

public class TeleOp_Encoders extends LinearOpMode {

    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;
    DcMotor lS;
    Servo left;
    Servo right;

    public void runOpMode() throws InterruptedException{

        fL = hardwareMap.dcMotor.get("frontLeft");
        bL = hardwareMap.dcMotor.get("backLeft");
        fR = hardwareMap.dcMotor.get("frontRight");
        bR = hardwareMap.dcMotor.get("backRight");
        lS = hardwareMap.dcMotor.get("linearSlide");
        left = hardwareMap.servo.get("left");
        right = hardwareMap.servo.get("right");

        fL.setDirection(DcMotor.Direction.REVERSE);
        left.setDirection(Servo.Direction.REVERSE);

        fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while(opModeIsActive()) {

            double POWER = -1 * Range.clip(Math.max( Range.clip( Math.sqrt( Math.pow( gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2)), -1, 1),
                    Math.abs(gamepad1.right_stick_x)), -1, 1);
            double maxPower = Math.max(Math.max(Math.abs(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x),
                    Math.abs(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x)),
                    Math.max(Math.abs(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x),
                            Math.abs(gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x)));

            fL.setPower(POWER * (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) / maxPower);
            bL.setPower(POWER * (gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) / maxPower);
            fR.setPower(POWER * (gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) / maxPower);
            bR.setPower(POWER * (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) / maxPower);

            if(gamepad1.left_bumper && gamepad1.right_bumper){
                lS.setPower(0);
            }
            else if(gamepad1.right_bumper){
                lS.setPower(0.5);
            }
            else if(gamepad1.left_bumper){
                lS.setPower(-0.5);
            }
            else{
                lS.setPower(0);
            }

            if(gamepad1.a){
                left.setPosition(1);
                right.setPosition(1);
            }
            else{
                left.setPosition(0);
                right.setPosition(0);
            }
        }
    }
}