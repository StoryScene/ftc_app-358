package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by kevinwang on 10/20/17.
 */

@TeleOp(name = "Practice_TeleOp")
@Disabled

public class Practice_TeleOp extends LinearOpMode{

    DcMotor motorfrontLeft = null;
    DcMotor motorbackLeft = null;
    DcMotor motorfrontRight = null;
    DcMotor motorbackRight = null;

    public void runOpMode() throws InterruptedException{
        motorfrontLeft = hardwareMap.dcMotor.get("frontLeft");
        motorbackLeft = hardwareMap.dcMotor.get("backLeft");
        motorfrontRight = hardwareMap.dcMotor.get("frontRight");
        motorbackRight = hardwareMap.dcMotor.get("backRight");

        motorfrontLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorbackLeft.setDirection(DcMotor.Direction.REVERSE);

        double POWER = 0.8;

        waitForStart();

        while(opModeIsActive()){

            double drive = gamepad1.left_stick_y / 3;
            double strafe = gamepad1.left_stick_x / 3;
            double rotate = gamepad1.right_stick_x / 3;

            double frontLeftPower = POWER * (drive - strafe - rotate);
            double backLeftPower = POWER * (drive + strafe - rotate);
            double frontRightPower = POWER * (drive + strafe + rotate);
            double backRightPower = POWER * (drive - strafe + rotate);

            motorfrontLeft.setPower(frontLeftPower);
            motorbackLeft.setPower(backLeftPower);
            motorfrontRight.setPower(frontRightPower);
            motorbackRight.setPower(backRightPower);

            idle(); 

        }
    }
}
