package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.sun.tools.javac.code.Attribute;

/**
 * Created by kevinwang on 10/27/17.
 */

@TeleOp(name = "TeleOp Power Test")
@Disabled

public class TeleOp_Power extends LinearOpMode{

    DcMotor motorfrontLeft;
    DcMotor motorbackLeft;
    DcMotor motorfrontRight;
    DcMotor motorbackRight;

    public void runOpMode() throws InterruptedException{

        motorfrontLeft = hardwareMap.dcMotor.get("frontLeft");
        motorbackLeft = hardwareMap.dcMotor.get("backLeft");
        motorfrontRight = hardwareMap.dcMotor.get("frontRight");
        motorbackRight = hardwareMap.dcMotor.get("backRight");

        motorfrontLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorbackLeft.setDirection(DcMotor.Direction.REVERSE);

        double POWER = 1;

        waitForStart();

        while(opModeIsActive()) {

            double maxPower = Math.max(Math.max(Math.abs(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x),
                    Math.abs(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x)),
                    Math.max(Math.abs(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x),
                            Math.abs(gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x)));

            motorfrontLeft.setPower(POWER * (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) / maxPower);
            motorbackLeft.setPower(POWER * (gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) / maxPower);
            motorfrontRight.setPower(POWER * (gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) / maxPower);
            motorbackRight.setPower(POWER * (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) / maxPower);

            if(gamepad1.left_bumper){

                POWER = POWER + 0.05;
                sleep(100);

            }

            if(gamepad1.right_bumper){

                POWER = POWER - 0.05;
                sleep(100);
            }

            POWER = Range.clip(POWER, -1, 1);

            telemetry.addData("POWER: ", POWER);
            telemetry.addData("maxPower: ", maxPower);

        }
    }
}
