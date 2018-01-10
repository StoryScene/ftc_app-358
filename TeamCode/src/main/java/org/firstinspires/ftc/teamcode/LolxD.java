package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by kevinwang on 12/5/17.
 */

@TeleOp

public class LolxD extends LinearOpMode{

    DcMotor fL;
    DcMotor bL;
    DcMotor fR;
    DcMotor bR;

    public void runOpMode() throws InterruptedException{

        fL = hardwareMap.dcMotor.get("frontLeft");
        bL = hardwareMap.dcMotor.get("backLeft");
        fR = hardwareMap.dcMotor.get("frontRight");
        bR = hardwareMap.dcMotor.get("backRight");

        fL.setDirection(DcMotorSimple.Direction.REVERSE);
        bL.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()){

            TeleOp_Drive_Code.TeleOpDrive(gamepad1, fL, bL, fR, bR);

        }

    }

}