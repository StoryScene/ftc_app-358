package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.sun.tools.javac.code.Attribute;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;

/**
 * Created by kevinwang on 10/27/17.
 */

@TeleOp(name = "TeleOp Test")
@Disabled

public class TeleOp_Test extends LinearOpMode{

    DcMotor motorfrontLeft;
    DcMotor motorbackLeft;
    DcMotor motorfrontRight;
    DcMotor motorbackRight;

    public void runOpMode() throws InterruptedException{

        motorfrontLeft = hardwareMap.dcMotor.get("frontLeft");
        motorbackLeft = hardwareMap.dcMotor.get("backLeft");
        motorfrontRight = hardwareMap.dcMotor.get("frontRight");
        motorbackRight = hardwareMap.dcMotor.get("backRight");

        motorfrontLeft.setMode(RunMode.RUN_USING_ENCODER);
        motorbackLeft.setMode(RunMode.RUN_USING_ENCODER);
        motorfrontRight.setMode(RunMode.RUN_USING_ENCODER);
        motorbackRight.setMode(RunMode.RUN_USING_ENCODER);

        motorfrontLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorbackLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()) {

            double POWER = Range.clip(Math.max(Range.clip(Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2)), -1, 1),
                    Math.abs(gamepad1.right_stick_x)), -1, 1);
            double maxPower = Math.max(Math.max(Math.abs(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x),
                    Math.abs(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x)),
                    Math.max(Math.abs(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x),
                            Math.abs(gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x)));

            motorfrontLeft.setPower(POWER * (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) / maxPower);
            motorbackLeft.setPower(POWER * (gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) / maxPower);
            motorfrontRight.setPower(POWER * (gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) / maxPower);
            motorbackRight.setPower(POWER * (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) / maxPower);

            telemetry.addData("POWER: ", POWER);
            telemetry.addData("maxPower: ", maxPower);

        }
    }
}